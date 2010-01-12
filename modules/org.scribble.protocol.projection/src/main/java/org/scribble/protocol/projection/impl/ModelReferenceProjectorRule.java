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
 * This class provides the abstract ModelReference implementation of the
 * projector rule.
 */
public abstract class ModelReferenceProjectorRule implements ProjectorRule {

	public ModelReferenceProjectorRule(boolean locatedRoleProjected) {
		m_locatedRoleProjected = locatedRoleProjected;
	}
	
	/**
	 * This method returns the new reference instance.
	 * 
	 * @param source The source reference
	 * @return The model reference
	 */
	protected abstract ModelReference createReference(ModelReference source);
		
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
		ModelReference source=(ModelReference)model;
		ModelReference ret=(ModelReference)createReference(source);
		
		ret.derivedFrom(source);

		ret.setName(source.getName());
		
		ret.setFullyQualified(source.isFullyQualified());
		ret.setInner(source.isInner());
		
		/*
		 * TODO: Determine when/how to set located participant
		 * 
		if (isLocatedParticipantProjected()) {
			ret.setLocatedRole(participant.getName());
		} else {
			ret.setLocatedRole(source.getLocatedRole());
		}
		*/
		
		return(ret);
	}
	
	/**
	 * This method determines whether the located role
	 * should be projected.
	 * 
	 * @return Whether the located role should be projected
	 */
	protected boolean isLocatedParticipantProjected() {
		return(m_locatedRoleProjected);
	}
	
	private boolean m_locatedRoleProjected=false;
}
