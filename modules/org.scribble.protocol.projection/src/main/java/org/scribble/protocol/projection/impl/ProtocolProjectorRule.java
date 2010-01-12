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

//import java.util.logging.Logger;

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.*;

/**
 * This class provides the Protocol implementation of the
 * projector rule.
 */
public class ProtocolProjectorRule implements ProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == Protocol.class);
	}
	
	/**
	 * This method projects the supplied model object based on the
	 * specified role.
	 * 
	 * @param model The model object
	 * @param role The role
	 * @param l The model listener
	 * @return The projected model object
	 */
	public ModelObject project(ProjectorContext context, ModelObject model,
					Participant participant, Journal l) {
		Activity ret=null;
		Protocol source=(Protocol)model;
		java.util.List<Participant> participants=null;
		
		if (context.isOuterScope() ||
				(participants=context.getParticipantsOfInterestForDefinition(source)) != null) {

			if (participants == null) {
				participants = new java.util.Vector<Participant>();
				participants.add(participant);
			}
			
			for (int j=0; j < participants.size(); j++) {
				Protocol prot = new Protocol();

				// Set current role
				participant = participants.get(j);
							
				prot.derivedFrom(source);
			
				LocatedName modelName=(LocatedName)
						context.project(source.getLocatedName(),
								participant, l);
				prot.setLocatedName(modelName);
				
				// Project 'conforms to' references
				for (int i=0; i < source.getConformsTo().size(); i++) {
					ConformanceReference ref=source.getConformsTo().get(i);
					
					ConformanceReference projectedRef=
						(ConformanceReference)context.project(ref,
								participant, l);
					
					if (projectedRef != null) {
						prot.getConformsTo().add(projectedRef);
					}
				}
				
				context.pushScope();
				
				prot.setBlock((Block)context.project(source.getBlock(),
						participant, l));
				prot.getBlock().setParent(prot);
				
				context.popScope();
				
				if (ret == null) {
					ret = prot;
				} else if (ret instanceof Block) {
					((Block)ret).getContents().add(prot);
				} else {
					Block b=new Block();
					b.getContents().add(ret);
					b.getContents().add(prot);
					ret = b;
				}
			}
		}

		return(ret);
	}
	
	//private static Logger logger = Logger.getLogger("org.scribble.protocol.projector");
}
