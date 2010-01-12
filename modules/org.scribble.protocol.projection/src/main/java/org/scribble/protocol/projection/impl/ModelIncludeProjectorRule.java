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
 * This abstract class provides the ModelInclude implementation of the
 * projector rule.
 */
public abstract class ModelIncludeProjectorRule implements ProjectorRule {

	/**
	 * This method creates the model include object being projected.
	 * 
	 * @return The model include
	 */
	protected abstract ModelInclude createModelInclude();
	
	/**
	 * This method projects the supplied model object based on the
	 * specified role.
	 * 
	 * @param model The model object
	 * @param participant The participant
	 * @param l The model listener
	 * @return The projected model object
	 */
	public ModelObject project(ProjectorContext context, ModelObject model,
					Participant participant, Journal l) {
		ModelInclude ret=createModelInclude();
		ModelInclude source=(ModelInclude)model;
		
		ret.derivedFrom(source);
		
		java.util.Iterator<DeclarationBinding> iter=
					source.getBindings().iterator();
		while (iter.hasNext()) {
			DeclarationBinding db=iter.next();
			
			// Don't project declaration if same as role - this
			// will be done in the model include statement,
			// not the bindings
			if (db.getLocalName().equals(participant.getName()) == false) {
				
				DeclarationBinding dbcopy=
						new DeclarationBinding(db.getLocalName(),
									db.getBoundName());
					
				dbcopy.derivedFrom(db);
					
				ret.getBindings().add(dbcopy);
			}
		}
		
		Participant mappedParticipant=participant;
		
		// Determine whether role name has been mapped
		DeclarationBinding db=null;
		if ((db=source.getDeclarationBinding(participant.getName())) != null) {
			String rename=db.getBoundName();
			
			if (rename != null) {
				mappedParticipant = new Participant();
				mappedParticipant.setName(rename);
			}
		}
		
		if (source.getInlineProtocol() != null) {
			ret.setInlineProtocol((Protocol)context.project(source.getInlineProtocol(),
								mappedParticipant, l));
		}
		
		if (source.getReference() != null) {
			
			// Store protocol against mapped participant
			Protocol defn=source.getProtocol();
			
			if (defn != null &&
					defn.getParticipants().contains(mappedParticipant)) {
				
				// If inner reference, then record interest
				// in project of definition against this mapped
				// role
				if (source.getReference().isInner()) {
					context.registerInterest(defn, mappedParticipant);
				}
				
				ret.setReference((ProtocolReference)context.project(
							source.getReference(), mappedParticipant, l));
			} else {
				ret = null;
			}
		}

		return(ret);
	}
}
