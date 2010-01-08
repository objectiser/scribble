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
 * This class represents the base class for all Scribble behavioural
 * activities.
 */
public abstract class Behaviour extends Activity {

	private static final long serialVersionUID = -487404402751133649L;

	/**
	 * This method returns whether the behaviour is a grouping
	 * construct.
	 * 
	 * @return Whether the behaviour is a grouping construct 
	 */
	public boolean isGroupingConstruct() {
		return(false);
	}
	
	/**
	 * This method returns whether the behaviour is a wait
	 * state.
	 * 
	 * @return Whether the behaviour is a wait state
	 */
	public boolean isWaitState() {
		return(false);
	}
	
	/**
	 * This method returns the list of participants that are
	 * associated with the behaviour.
	 * 
	 * @return The list of associated participants
	 */
	public java.util.List<Participant> associatedParticipants() {
		return(new java.util.Vector<Participant>());
	}
}
