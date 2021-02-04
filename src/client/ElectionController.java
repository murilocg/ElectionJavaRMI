package client;

import java.net.SocketTimeoutException;
import java.rmi.RemoteException;

import service.CannotVoteTwiceException;
import service.Election;

public class ElectionController {

	private Election election;
	private IdGenerator idGenerator;

	public ElectionController(Election election, IdGenerator idGenerator) {
		this.election = election;
		this.idGenerator = idGenerator;
	}

	public boolean vote(String voter, String candidate)
			throws RemoteException, CannotVoteTwiceException, SocketTimeoutException {
		return election.vote(idGenerator.generator(voter), idGenerator.generator(candidate));
	}

	public long result(String candidate) throws RemoteException {
		return election.result(idGenerator.generator(candidate));
	}
}
