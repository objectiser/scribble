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
 * This class provides the CatchBlock implementation of the
 * projector rule.
 */
public class CatchBlockProjectorRule extends AbstractBlockProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == CatchBlock.class);
	}
	
	/**
	 * This method creates a new block of the appropriate
	 * type.
	 * 
	 * @return The block
	 */
	protected Block createBlock() {
		return(new CatchBlock());
	}
	
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
		CatchBlock ret=(CatchBlock)super.project(context,
				model, participant, l);
		CatchBlock source=(CatchBlock)model;

		// If block not projected, then if projected role is in
		// the list, the re-instate
		if (ret == null && source.getParticipants().contains(participant)) {
			ret = new CatchBlock();
			
			ret.derivedFrom(source);
		}
		
		if (ret != null) {
			
			// Project the list of roles
			for (int i=0; i < source.getParticipants().size(); i++) {
				ret.getParticipants().add(new Participant(source.getParticipants().get(i)));
			}
			
			if (source.getType() != null) {
		
				// Project the type reference
				ret.setType((TypeReference)
						context.project(source.getType(),
								participant, l));
			}
		}
		
		return(ret);
	}
}
