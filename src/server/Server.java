package server;

import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;

import server.cache.Cache;
import service.Election;

public class Server {
	
	public static void main(String[] args) {
		
		System.setProperty("java.security.policy", Config.SECURITY_POLICY);
		System.setProperty("java.rmi.server.hostname", Config.SERVER_HOSTNAME);
		System.setProperty("java.rmi.server.codebase", Config.SERVER_CODEBASE);
		
		try {
			Cache<Vote> cache = new Cache<Vote>(Config.TTL_CACHE);
			Election election = new ElectionServant(cache);
			
			Election stub = (Election) UnicastRemoteObject.exportObject(election, 0);
			Registry registry = LocateRegistry.getRegistry();
			
			registry.rebind(Config.REGISTRY_ELECTION_NAME, stub);
			
			System.out.println("The Election's Server is runnig...");
		} catch (Exception e) {
			System.err.println("Election Server Error initalization: method main " + e.getMessage());
			e.printStackTrace();
		}
	}
}
