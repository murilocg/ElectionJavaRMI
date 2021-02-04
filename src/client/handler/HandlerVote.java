package client.handler;

import java.net.SocketTimeoutException;
import java.rmi.RemoteException;
import java.rmi.UnmarshalException;

import client.Config;
import client.ElectionController;
import client.exception.MissingParamException;
import service.CannotVoteTwiceException;

public class HandlerVote extends Handler {

	private String voter;
	private String candidate;

	public HandlerVote(ElectionController controller, String... args) throws MissingParamException {
		super(controller);
		this.parseParams(args);
	}

	@Override
	public void handle() throws RemoteException {
		retry(0);
	}

	private void retry(int tries) throws RemoteException {
		try {
			controller.vote(voter, candidate);
			System.out.println("You voted in the candidate " + candidate);
		} catch (CannotVoteTwiceException e) {
			System.out.println(e.getMessage());
		} catch (SocketTimeoutException | UnmarshalException e) {
			if (tries < Config.MAX_TIMEOUT / Integer.parseInt(Config.RESPONSE_TIMEOUT) ) {
				System.out.println("Connection response timeout, tries= " + (tries + 1) + ", retrying...");
				retry(tries + 1);
			} else {
				System.out.println("Could not connect to the server");
			}
		}
	}

	@Override
	public void parseParams(String... args) throws MissingParamException {
		voter = args.length > 2 ? args[2] : "";
		candidate = args.length > 3 ? args[3] : "";
		if (voter.isEmpty())
			throw new MissingParamException("voter's name");
		if (candidate.isEmpty())
			throw new MissingParamException("Candidate's name");
	}
}
