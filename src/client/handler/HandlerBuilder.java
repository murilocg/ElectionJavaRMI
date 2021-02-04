package client.handler;

import client.ElectionController;
import client.exception.InvalidActionException;
import client.exception.MissingParamException;

public class HandlerBuilder {

	public static Handler build(String action, ElectionController controller, String... args)
			throws InvalidActionException, MissingParamException {
		if (action.equals("vote"))
			return new HandlerVote(controller, args);
		else if (action.equals("result"))
			return new HandlerResult(controller, args);
		throw new InvalidActionException(action);
	}

}
