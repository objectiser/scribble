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

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.*;

/**
 * This class provides the Choice implementation of the
 * projector rule.
 */
public class ChoiceProjectorRule implements ProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == Choice.class);
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
		Choice ret=new Choice();
		Choice source=(Choice)model;

		ret.derivedFrom(source);
		
		if (source.getFromParticipant() != null) {
			ret.setFromParticipant(new Participant(source.getFromParticipant()));
		}
		
		if (source.getToParticipant() != null) {
			ret.setToParticipant(new Participant(source.getToParticipant()));
		}
		
		for (int i=0; i < source.getWhenBlocks().size(); i++) {
			WhenBlock block=(WhenBlock)
					context.project(source.getWhenBlocks().get(i), participant,
							l);
			
			if (block != null) {
				ret.getWhenBlocks().add(block);
			}
		}
		
		// Check if choice has atleast one path
		if (ret.getWhenBlocks().size() == 0) {
			ret = null;
		}
		
		return(ret);
	}
}
