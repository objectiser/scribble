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
package org.scribble.protocol.validation.rules;

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.Interaction;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.Participant;
import org.scribble.protocol.validation.ProtocolComponentValidatorRule;

/**
 * This class provides the validation rule for the Interaction
 * model component.
 *
 */
public class InteractionValidatorRule implements ProtocolComponentValidatorRule {

	/**
	 * This method returns the class being validated.
	 * 
	 * @return The class being validated
	 */
	public Class<? extends ModelObject> getValidatedClass() {
		return(org.scribble.protocol.model.Interaction.class);
	}
	
	/**
	 * This method validates the supplied model object.
	 * 
	 * @param obj The model object being validated
	 * @param logger The logger
	 */
	public void validate(ModelObject obj,
					Journal logger) {
		Interaction elem=(Interaction)obj;
		
		// Identify definition and whether it has a located role
		Participant locatedRole=null;
		
		if (elem.enclosingProtocol() != null &&
				elem.enclosingProtocol().getLocatedName() != null) {
			locatedRole = elem.enclosingProtocol().getLocatedName().getParticipant();
		}

		// Check that between the channel and the interaction, there
		// are 'to' and 'from' roles defined
		if (elem.getFromParticipant() == null &&
				(elem.getChannel() == null ||
						elem.getChannel().getFromParticipant() == null)) {
			
			// Check if local model and 'to' is not the same as the
			// located role
			if (locatedRole == null || elem.getToParticipant() == null ||
					locatedRole.equals(elem.getToParticipant().getName())) {
			
				logger.error(org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle("org.scribble.validation.Messages"),
						"_INTERACTION_ROLE",
						new String[]{"from"}), obj.getProperties());
			}
		}

		if (elem.getToParticipant() == null &&
				(elem.getChannel() == null ||
						elem.getChannel().getToParticipant() == null)) {
			
			// Check if local model and 'from' is not the same as the
			// located role
			if (locatedRole == null || elem.getFromParticipant() == null ||
					locatedRole.equals(elem.getFromParticipant().getName())) {
			
				logger.error(org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle("org.scribble.validation.Messages"),
						"_INTERACTION_ROLE",
						new String[]{"to"}), obj.getProperties());
			}
		}
	}
}
