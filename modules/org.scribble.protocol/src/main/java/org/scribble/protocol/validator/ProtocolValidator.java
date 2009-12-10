package org.scribble.protocol.validator;

import org.scribble.core.logger.ScribbleLogger;
import org.scribble.core.model.Model;
import org.scribble.core.validation.*;

public class ProtocolValidator implements Validator {

	public void validate(Model model, ScribbleLogger logger) {
		System.out.println("PROTOCOL VALIDATOR: "+model);
	}

}
