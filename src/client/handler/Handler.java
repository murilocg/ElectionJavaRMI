package client.handler;

import java.rmi.RemoteException;

import client.ElectionController;
import client.exception.MissingParamException;

public abstract class Handler {

	protected ElectionController controller;

	public Handler(ElectionController controller) {
		this.controller = controller;
	}

	public abstract void handle() throws RemoteException;

	public abstract void parseParams(String... args) throws MissingParamException;
}
