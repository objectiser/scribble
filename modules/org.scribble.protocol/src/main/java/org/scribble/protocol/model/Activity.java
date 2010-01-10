/*
 * Copyright 2009 Scribble.org
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
package org.scribble.protocol.model;

/**
 * This class represents the base class for all Scribble definition
 * components.
 */
public abstract class Activity extends ModelObject {
	
	private static final long serialVersionUID = 907254090600526766L;

	/**
	 * This method returns the list of participants that are
	 * responsible for initiating the activity. This can
	 * be used to determine whether the model is
	 * consistent in terms of decision makers subsequently
	 * initiating actions.
	 * 
	 * @return The list of initiator participants
	 */
	public java.util.List<Participant> initiatorParticipants() {
		return(new java.util.Vector<Participant>());
	}

	/**
	 * This method returns the list of participants that are
	 * associated with the outcome of the activity.
	 * 
	 * @return The list of final participants
	 */
	public java.util.List<Participant> finalParticipants() {
		return(new java.util.Vector<Participant>());
	}
	
	/**
	 * This method returns the optional located participant
	 * associated with the enclosing definition.
	 * 
	 * @return The located participant, or null if the
	 * 			enclosing definition is not located
	 */
	protected Participant locatedParticipant() {
		Participant ret=null;
		
		if (getParent() instanceof Activity) {
			ret = ((Activity)getParent()).locatedParticipant();
		}
		
		return(ret);
	}

	/**
	 * This method returns the protocol in which this
	 * activity is contained.
	 * 
	 * @return The protocol, or null if not found
	 */
	public Protocol enclosingProtocol() {
		Protocol ret=null;
		
		if (getParent() instanceof Activity) {
			ret = ((Activity)getParent()).enclosingProtocol();
		}
		
		return(ret);
	}
	
	/**
	 * This method indicates whether the activity
	 * is a conditional construct.
	 * 
	 * @return Whether the activity is conditional
	 */
	public boolean isConditional() {
		return(false);
	}
	
	/**
	 * This method returns whether the activity represents
	 * a scope.
	 * 
	 * @return Whether activity represents a scope
	 */
	public boolean isScope() {
		return(false);
	}
}
