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
 * This class provides the Repeat implementation of the
 * projector rule.
 */
public class RepeatProjectorRule implements ProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == Repeat.class);
	}
	
	/**
	 * This method projects the supplied model object based on the
	 * specified participant.
	 * 
	 * @param model The model object
	 * @param participant The participant
	 * @param l The model listener
	 * @return The projected model object
	 */
	public ModelObject project(ProjectorContext context, ModelObject model,
					Participant participant, Journal l) {
		Repeat ret=new Repeat();
		Repeat source=(Repeat)model;

		ret.derivedFrom(source);
		
		// Project the list of roles
		for (int i=0; i < source.getParticipants().size(); i++) {
			ret.getParticipants().add(new Participant(source.getParticipants().get(i)));
		}
		
		if (ret != null && source.getBlock() != null) {
			
			// Project the block
			ret.setBlock((Block)
					context.project(source.getBlock(),
								participant, l));
			ret.getBlock().setParent(ret);
		}
		
		return(ret);
	}
}
