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
 * This class represents a group of activities within
 * an 'escape' block of a try/escape construct.
 * 
 */
public abstract class EscapeBlock extends Block {

	private static final long serialVersionUID = -2794418546744091644L;

	/**
	 * This method returns the list of participants at which
	 * the escape decision is located.
	 * 
	 * @return The list of participants
	 */
	public java.util.List<Participant> getParticipants() {
		return(m_roles);
	}
		
	private java.util.List<Participant> m_roles=new java.util.Vector<Participant>();
}
