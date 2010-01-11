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
 * This class represents the channel declaration.
 */
public class Channel extends ModelObject {

	private static final long serialVersionUID = 1158151945766114601L;

	/**
	 * This is the default constructor.
	 */
	public Channel() {
	}
	
	/**
	 * This constructor initializes the channel with a name.
	 * 
	 * @param name The channel name
	 */
	public Channel(String name) {
		m_name = name;
	}
	
	/**
	 * This method returns the name of the channel.
	 * 
	 * @return The name
	 */
	public String getName() {
		return(m_name);
	}
	
	/**
	 * This method sets the name of the channel.
	 * 
	 * @param name The name
	 */
	public void setName(String name) {
		m_name = name;
	}
	
	/**
	 * This method returns the 'from' participant.
	 * 
	 * @return The 'from' participant
	 */
	public Participant getFromParticipant() {
		return(m_fromParticipant);
	}
	
	/**
	 * This method sets the 'from' participant.
	 * 
	 * @param participant The 'from' participant
	 */
	public void setFromParticipant(Participant participant) {
		m_fromParticipant = participant;
	}
	
	/**
	 * This method returns the 'to' participant.
	 * 
	 * @return The 'to' participant
	 */
	public Participant getToParticipant() {
		return(m_toParticipant);
	}
	
	/**
	 * This method sets the 'to' participant.
	 * 
	 * @param role The 'to' participant
	 */
	public void setToRole(Participant participant) {
		m_toParticipant = participant;
	}
	
	public boolean equals(Object obj) {
		boolean ret=false;
	
		if (obj instanceof Channel) {
			Channel other=(Channel)obj;
			
			if (other.getName() != null && other.getName().equals(m_name)) {
				ret = true;
			}
		}
		
		return(ret);
	}
	
	public int hashCode() {
		int ret=super.hashCode();
		
		if (m_name != null) {
			ret = m_name.hashCode();
		}
		
		return(ret);
	}
	
	public String toString() {
		return(getName());
	}

	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.visitChannel(this);
	}
	
	private String m_name=null;
	private Participant m_fromParticipant=null;
	private Participant m_toParticipant=null;
}
