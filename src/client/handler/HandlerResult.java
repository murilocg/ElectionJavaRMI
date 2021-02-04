package client.handler;

import java.rmi.RemoteException;

import client.ElectionController;
import client.exception.MissingParamException;

public class HandlerResult extends Handler {

	private String candidate;

	public HandlerResult(ElectionController controller, String... args) throws MissingParamException {
		super(controller);
		this.parseParams(args);
	}

	@Override
	public void handle() throws RemoteException {
		long result = controller.result(candidate);
		System.out.println("The Candidate " + candidate + " has " + result + " votes");
	}

	@Override
	public void parseParams(String... args) throws MissingParamException {
		candidate = args[2] != null ? args[2] : "";
		if (candidate.isEmpty())
			throw new MissingParamException("candidate's name ");
	}
}
