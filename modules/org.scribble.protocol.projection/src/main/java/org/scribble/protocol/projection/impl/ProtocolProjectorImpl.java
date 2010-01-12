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
import org.scribble.protocol.projection.ProtocolProjector;

/**
 * This class provides an implementation of the protocol projector.
 *
 */
public class ProtocolProjectorImpl implements ProtocolProjector {

	/**
	 * This method projects a 'global' protocol model to a specified
	 * participant's 'local' protocol model.
	 * 
	 * @param model The 'global' protocol model
	 * @param participant The participant to project
	 * @param journal Journal for reporting issues
	 * @return The 'local' protocol model
	 */
	public ProtocolModel project(ProtocolModel model,
			Participant participant, Journal journal) {
		return(project(model, null, participant, journal));
	}
	
	/**
	 * This method projects a 'global' protocol model to a specified
	 * participant's 'local' protocol model.
	 * 
	 * @param model The 'global' protocol model
	 * @param subPath The subpath in the model to be projected, or null if all
	 * @param participant The participant to project
	 * @param journal Journal for reporting issues
	 * @return The 'local' protocol model
	 */
	public ProtocolModel project(ProtocolModel model, SubProtocolPath subPath,
			Participant participant, Journal journal) {
		ProtocolModel ret=null;
		
		DefaultProjectorContext context=new DefaultProjectorContext(subPath);
		ModelObject obj=context.project(model, participant, journal);
		
		if (obj != null) {
			if (obj instanceof ProtocolModel) {
				ret = (ProtocolModel)obj;
			} else {
				String modelName=model.getNamespace().getName();
				
				modelName += "("+model.getProtocol().getLocatedName().getName();
				
				if (model.getProtocol().getLocatedName().getParticipant() != null) {
					modelName += ","+model.getProtocol().getLocatedName().getParticipant();
				}
					
				modelName += ")";
				
				journal.error(org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle("org.scribble.validation.Messages"),
						"_NOT_PROJECTED_MODEL",
						new String[]{modelName}), null);
			}
		}
		
		return(ret);
	}

}
