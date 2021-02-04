package service;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface Election extends Remote {
	
	public boolean vote(String voterId, String candidate)  throws RemoteException, CannotVoteTwiceException;

	long result(String candidateId) throws RemoteException;
}
