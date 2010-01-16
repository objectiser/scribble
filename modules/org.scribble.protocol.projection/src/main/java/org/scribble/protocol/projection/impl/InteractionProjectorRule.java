/*
 * Copyright 2009-10 Scribble.org
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
package org.scribble.protocol.projection.impl;

import org.scribble.protocol.model.*;
import org.scribble.common.logging.Journal;

/**
 * This class provides the Interaction implementation of the
 * projector rule.
 */
public class InteractionProjectorRule implements ProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == Interaction.class);
	}
	
	/**
	 * This method returns a new instance of the interaction model
	 * object.
	 * 
	 * @return The new interaction
	 */
	protected Interaction createInteraction() {
		return(new Interaction());
	}
	
	/**
	 * This method projects the supplied model object based on the
	 * specified role.<p>
	 * 
	 * @param model The model object
	 * @param participant The participant
	 * @param l The model listener
	 * @return The projected model object
	 */
	public ModelObject project(ProjectorContext context, ModelObject model,
					Participant participant, Journal l) {
		Interaction ret=createInteraction();
		Interaction source=(Interaction)model;
		boolean f_roleFound=false;
		
		ret.derivedFrom(source);

		if (source.getRequestLabel() != null) {
			ret.setRequestLabel(source.getRequestLabel());
		}
		
		if (source.getReplyToLabel() != null) {
			ret.setReplyToLabel(source.getReplyToLabel());
		}
		
		if (source.getFromParticipant() != null) {
			
			if (source.getFromParticipant().equals(participant)) {				
				f_roleFound = true;
			} else {
				// Only set role if not projected role
				// Find role in state
				Object state=context.getState(source.getFromParticipant().getName());
				
				if (state instanceof Participant) {
					Participant r=new Participant();
					r.setName(source.getFromParticipant().getName());
					
					r.derivedFrom(source.getFromParticipant());
					
					ret.setFromParticipant(r);
				}
			}
		}
		
		if (source.getToParticipant() != null) {
			
			if (source.getToParticipant().equals(participant)) {
				f_roleFound = true;
			} else {
				// Only set role if not projected role
				// Find role in state
				Object state=context.getState(source.getToParticipant().getName());
				
				if (state instanceof Participant) {
					Participant r=new Participant();
					r.setName(source.getToParticipant().getName());
					
					r.derivedFrom(source.getToParticipant());
					
					ret.setToParticipant(r);
				}
			}
		}
		
		// Check if participant found
		if (f_roleFound) {
			ret.setMessageSignature((MessageSignature)
					context.project(source.getMessageSignature(),
							participant, l));
		} else {
			// Participant not associated with the interaction
			ret = null;
		}
		
		return(ret);
	}
}
