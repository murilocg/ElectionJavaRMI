package server;

import java.rmi.RemoteException;
import java.util.Vector;

import server.cache.Cache;
import service.CannotVoteTwiceException;
import service.Election;

public class ElectionServant implements Election {

	private Vector<Vote> votos;
	private Cache<Vote> cache;

	public ElectionServant(Cache<Vote> cache) {
		this.votos = new Vector<Vote>();
		this.cache = cache;
	}

	@Override
	public boolean vote(String voterId, String candidateId) throws RemoteException, CannotVoteTwiceException {
		System.out.println("action=vote, voterId=" + voterId + ", candidateId=" + candidateId);

		synchronized (cache) {
			Vote cachedVote = cache.get(voterId);
			if (cachedVote != null && cachedVote.getCandidateId().equals(candidateId))
				return cachedVote.isVoted();
		}

		synchronized (votos) {
			if (alreadyVoted(voterId))
				throw new CannotVoteTwiceException(voterId, candidateId);
			Vote vote = new Vote(voterId, candidateId);
			vote.setVoted(this.votos.add(vote));
			this.cacheResponse(voterId, vote);
			return vote.isVoted();
		}

	}

	@Override
	public long result(String candidateId) throws RemoteException {
		synchronized (votos) {
			long result = votos.stream().filter(v -> v.getCandidateId().equals(candidateId)).count();
			System.out.println("action=result, candidateId=" + candidateId + ", votes=" + result);
			return result;
		}
	}

	private void cacheResponse(String voterId, Vote vote) {
		synchronized (cache) {
			cache.put(voterId, vote);
		}
	}

	private boolean alreadyVoted(String voterId) {
		synchronized (votos) {
			return votos.stream().filter(v -> v.getVoterId().equals(voterId)).limit(1).findFirst().orElse(null) != null;
		}
	}

}
