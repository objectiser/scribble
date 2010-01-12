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
 * This class provides the ConformanceReference implementation of the
 * projector rule.
 */
public class ConformanceReferenceProjectorRule extends ModelReferenceProjectorRule {

	/**
	 * This is the default constructor.
	 */
	public ConformanceReferenceProjectorRule() {
		super(true);
	}
	
	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == ConformanceReference.class);
	}
	
	/**
	 * This method returns the new reference instance.
	 * 
	 * @param source The source reference
	 * @return The model reference
	 */
	protected ModelReference createReference(ModelReference source) {
		return(new ConformanceReference());
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
		ConformanceReference source=(ConformanceReference)model;

		ConformanceReference ret=null;
		
		// Check if conformance reference relates to the projected role
		// TODO: Need to be able to project if projected role is in the
		// name mapping list?? Also possibly if source is not located
		if (source.getLocatedParticipant() != null &&
				source.getLocatedParticipant().equals(participant.getName())) {
			ret = (ConformanceReference)super.project(context,
					model, participant, l);
			
			// Project name mapping
			java.util.Map<String,String> mapping=source.getNameMapping();
			String mappedName=null;
			
			java.util.Iterator<String> iter=mapping.keySet().iterator();
			while (iter.hasNext()) {
				String key=iter.next();
				String value=mapping.get(key);
				
				ret.getNameMapping().put(key, value);
				
				if (participant.getName().equals(value)) {
					mappedName = key;
				}
			}
			
			/*
			 * TODO: Set located participant
			 *
			if (mappedName != null) {
				ret.setLocatedParticipant(mappedName);
			}
			*/
		}

		return(ret);
	}
}
