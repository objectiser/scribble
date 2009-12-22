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
package org.scribble.core.model;

/**
 * This class represents the name of a model.
 */
public class LocatedName extends ModelObject {

	private static final long serialVersionUID = -7430644107592080014L;

	/**
	 * This is the default constructor for the model name.
	 */
	public LocatedName() {
	}
	
	/**
	 * This is the constructor for the model name.
	 * 
	 * @param name The name
	 * @param located The optional located role
	 */
	public LocatedName(String name, Participant located) {
		m_name = name;
		m_participant = located;
	}
	
	/**
	 * This method returns the name.
	 * 
	 * @return The name
	 */
	public String getName() {
		return(m_name);
	}
	
	/**
	 * This method sets the name.
	 * 
	 * @param name The name
	 */
	public void setName(String name) {
		m_name = name;
	}
	
	/**
	 * This method returns the located participant. This
	 * field is set when the protocol represents a local
	 * model.
	 * 
	 * @return The located participant
	 */
	@Reference(containment=true)
	public Participant getParticipant() {
		return(m_participant);
	}
	
	/**
	 * This method sets the located participant. This
	 * field is set when the protocol represents a local
	 * model.
	 * 
	 * @param participant The located participant
	 */
	public void setParticipant(Participant participant) {
		
		if (m_participant != null) {
			m_participant.setParent(null);
		}
		
		m_participant = participant;
		
		if (m_participant != null) {
			m_participant.setParent(this);
		}
	}
	
	public int hashCode() {
		int ret=super.hashCode();
		
		if (m_name != null) {
			ret = m_name.hashCode();
		}
		
		return(ret);
	}
	
	public boolean equals(Object other) {
		boolean ret=false;
		
		if (other instanceof LocatedName &&
				m_name != null &&
				((LocatedName)other).m_name != null &&
				m_name.equals(((LocatedName)other).m_name)) {
			
			if (m_participant == null &&
					((LocatedName)other).m_participant == null) {
				ret = true;
			} else if (m_participant != null &&
					((LocatedName)other).m_participant != null &&
					m_participant.equals(((LocatedName)other).m_participant)) {
				ret = true;
			}
		}
		
		return(ret);
	}
			
	public String toString() {
		return("Name["+getName()+" loc="+m_participant+"]");
	}
	
	private String m_name=null;
	private Participant m_participant=null;
}
