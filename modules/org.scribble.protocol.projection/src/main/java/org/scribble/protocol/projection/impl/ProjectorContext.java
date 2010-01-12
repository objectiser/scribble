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
 * This interface represents the comparator context.
 */
public interface ProjectorContext {
	
	/**
	 * This method projects the supplied model object based on the
	 * specified role.
	 * 
	 * @param model The model object
	 * @param participant The participant
	 * @param l The model listener
	 * @return The projected model object
	 */
	public ModelObject project(ModelObject model, Participant participant,
						Journal	l);
	
	/**
	 * This method returns the named state from the current
	 * scope.
	 * 
	 * @param name The state name
	 * @return The state value, or null if not found
	 */
	public Object getState(String name);
	
	/**
	 * This method sets the value associated with the supplied
	 * name in the current state.
	 * 
	 * @param name The state name
	 * @param value The state value
	 */
	public void setState(String name, Object value);
	
	/**
	 * This method pushes the current state onto a stack.
	 */
	public void pushState();
	
	/**
	 * This method pops the current state from the stack.
	 */
	public void popState();
	
	/**
	 * This method pushes the current scope onto a stack.
	 */
	public void pushScope();
	
	/**
	 * This method pops the current scope from the stack.
	 */
	public void popScope();
	
	/**
	 * This method determines whether the context is associated
	 * with the outer scope.
	 * 
	 * @return Whether the context is for the outer scope
	 */
	public boolean isOuterScope();
	
	/**
	 * This method registers an interest in the projected participant
	 * associated with the supplied definition.
	 * 
	 * @param defn The definition
	 * @param participant The projected participant of interest
	 */
	public void registerInterest(Protocol defn, Participant participant);
	
	/**
	 * This method returns the list of participants of interest
	 * for the supplied definition.
	 * 
	 * @param defn The definition
	 * @return The list of participants, or null if no participants have
	 * 				registered interest in the definition
	 */
	public java.util.List<Participant> getParticipantsOfInterestForDefinition(Protocol defn);
	
	/**
	 * This method returns the sub definition path being
	 * projected. If the list is empty, then the top level
	 * definition will be used.
	 * 
	 * @return The sub definition path
	 */
	public SubProtocolPath getSubProtocolPath();
	
}
