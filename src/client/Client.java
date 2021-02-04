package client;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import client.exception.InvalidActionException;
import client.exception.MissingParamException;
import client.handler.Handler;
import client.handler.HandlerBuilder;
import service.Election;

public class Client {


	public static void main(String[] args) {

		System.setProperty("java.security.policy", Config.SECURITY_POLICY);
		System.setProperty("java.rmi.server.codebase", Config.SERVER_CODEBASE);
		System.setProperty("sun.rmi.transport.connectionTimeout", Config.CONNECTION_TIMEOUT);
		System.setProperty("sun.rmi.transport.tcp.responseTimeout", Config.RESPONSE_TIMEOUT);

		String server = args.length > 0 ? args[0] : "";
		String action = args.length > 1 ? args[1] : "";

		if (server.isEmpty()) {
			exit("The Server parameter is missing", 1);
		} else if (action.isEmpty()) {
			exit("The Action parameter is missing", 1);
		} else {
			start(server, action, args);
		}
	}

	private static void start(String server, String action, String... args) {
		Registry registry;
		try {

			registry = LocateRegistry.getRegistry(server);
			Election election = (Election) registry.lookup(Config.REGISTRY_ELECTION_NAME);
			ElectionController controller = new ElectionController(election, new IdGeneratorMD5());
			Handler handler = HandlerBuilder.build(action, controller, args);
			handler.handle();
			
		} catch (RemoteException e) {
			e.printStackTrace();
		} catch (NotBoundException e) {
			e.printStackTrace();
		} catch (InvalidActionException | MissingParamException e) {
			exit(e.getMessage(), 1);
		}
	}

	private static void exit(String message, int status) {
		System.err.println(message);
		System.exit(status);
	}
}
