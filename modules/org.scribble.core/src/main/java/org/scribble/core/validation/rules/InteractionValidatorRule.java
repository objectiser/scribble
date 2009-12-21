/*
 * Copyright 2009 Scribble.org
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */
package org.scribble.core.validation.rules;

import org.scribble.core.model.*;
import org.scribble.core.validation.ComponentValidatorRule;
import org.scribble.core.logger.ScribbleLogger;

/**
 * This class provides the validation rule for the Interaction
 * model component.
 *
 */
public class InteractionValidatorRule implements ComponentValidatorRule {

	/**
	 * This method returns the class being validated.
	 * 
	 * @return The class being validated
	 */
	public Class<? extends ModelObject> getValidatedClass() {
		return(org.scribble.core.model.Interaction.class);
	}
	
	/**
	 * This method validates the supplied model object.
	 * 
	 * @param obj The model object being validated
	 * @param logger The logger
	 */
	public void validate(ModelObject obj,
					ScribbleLogger logger) {
		Interaction elem=(Interaction)obj;
		
		// Identify definition and whether it has a located role
		Role locatedRole=null;
		
		if (elem.enclosingDefinition() != null &&
				elem.enclosingDefinition().getLocatedName() != null) {
			locatedRole = elem.enclosingDefinition().getLocatedName().getRole();
		}

		// Check that between the channel and the interaction, there
		// are 'to' and 'from' roles defined
		if (elem.getFromRole() == null &&
				(elem.getChannel() == null ||
						elem.getChannel().getFromRole() == null)) {
			
			// Check if local model and 'to' is not the same as the
			// located role
			if (locatedRole == null || elem.getToRole() == null ||
					locatedRole.equals(elem.getToRole().getName())) {
			
				logger.error(org.scribble.core.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle("org.scribble.validation.Messages"),
						"_INTERACTION_ROLE",
						new String[]{"from"}), obj.getProperties());
			}
		}

		if (elem.getToRole() == null &&
				(elem.getChannel() == null ||
						elem.getChannel().getToRole() == null)) {
			
			// Check if local model and 'from' is not the same as the
			// located role
			if (locatedRole == null || elem.getFromRole() == null ||
					locatedRole.equals(elem.getFromRole().getName())) {
			
				logger.error(org.scribble.core.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle("org.scribble.validation.Messages"),
						"_INTERACTION_ROLE",
						new String[]{"to"}), obj.getProperties());
			}
		}
	}
}
