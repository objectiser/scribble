package org.scribble.core.validation;

public interface ValidationManager {
	
	public void addValidator(Validator validator);

	public void removeValidator(Validator validator);

	public void validate(org.scribble.core.model.Model model,
				org.scribble.core.logger.ScribbleLogger logger);
	
}
