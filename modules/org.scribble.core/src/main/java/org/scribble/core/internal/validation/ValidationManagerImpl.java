package org.scribble.core.internal.validation;

import org.scribble.core.validation.ValidationManager;
import org.scribble.core.validation.Validator;

public class ValidationManagerImpl implements ValidationManager {
	
	public void addValidator(Validator validator) {
		m_validators.add(validator);
	}

	public void removeValidator(Validator validator) {
		m_validators.remove(validator);
	}

	public void validate(org.scribble.core.model.Model model,
				org.scribble.core.logger.ScribbleLogger logger) {
	
		for (Validator v : m_validators) {
			v.validate(model, logger);
		}
	}
	
	private java.util.List<Validator> m_validators=
				new java.util.Vector<Validator>();
}
