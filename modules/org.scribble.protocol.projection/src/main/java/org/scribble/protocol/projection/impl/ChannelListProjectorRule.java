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
 * This class provides the ChannelList implementation of the
 * projector rule.
 */
public class ChannelListProjectorRule implements ProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == ChannelList.class);
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
		ChannelList ret=new ChannelList();
		ChannelList source=(ChannelList)model;
		
		// TODO: Validate the projection, as currently compares to role/participant???
		
		for (int i=0; i < source.getChannels().size(); i++) {
			if (source.getChannels().get(i).equals(participant) == false) {
				Channel ch=new Channel();
				
				ch.setName(source.getChannels().get(i).getName());
				
				// Add to state
				context.setState(ch.getName(), ch);

				ret.getChannels().add(ch);
			}
		}
		
		return(ret);
	}
}