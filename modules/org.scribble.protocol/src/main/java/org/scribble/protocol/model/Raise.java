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
 * This class represents the Raise construct.
 * 
 */
public class Raise extends Behaviour {

	private static final long serialVersionUID = -8930392757457429214L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Raise() {
	}
	
	/**
	 * This method returns the list of roles at which
	 * the decision is located.
	 * 
	 * @return The list of roles
	 */
	public java.util.List<Participant> getParticipants() {
		return(m_roles);
	}
		
	/**
	 * This method returns the reference of the type associated
	 * with the catch block.
	 * 
	 * @return The type
	 */
	@Reference(containment=true)
	public TypeReference getType() {
		return(m_type);
	}
	
	/**
	 * This method sets the reference of the type associated
	 * with the catch block.
	 * 
	 * @param type The type
	 */
	public void setType(TypeReference type) {
		
		if (m_type != null) {
			m_type.setParent(null);
		}
		
		m_type = type;
		
		if (m_type != null) {
			m_type.setParent(this);
		}
	}
	
	private java.util.List<Participant> m_roles=new java.util.Vector<Participant>();
	private TypeReference m_type=null;
}
