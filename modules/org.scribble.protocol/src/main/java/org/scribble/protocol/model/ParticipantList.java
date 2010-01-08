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
 * This class represents the list of participants declared within
 * a Scribble definition.
 */
public class ParticipantList extends Activity {

	private static final long serialVersionUID = -536190968014907396L;

	/**
	 * This method returns the list of participants.
	 * 
	 * @return The list of participants
	 */
	@Reference(containment=true)
	public java.util.List<Participant> getParticipants() {
		return(m_participants);
	}
	
	/**
	 * This method returns the participant associated with
	 * the supplied name.
	 * 
	 * @param name The participant name
	 * @return The participant, or null if not found
	 */
	public Participant getParticipant(String name) {
		Participant ret=null;
		
		for (int i=0; ret == null &&
					i < m_participants.size(); i++) {
			if (m_participants.get(i).getName().equals(name)) {
				ret = m_participants.get(i);
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method determines whether the participants in the
	 * list are 'open', meaning they can be bound to.
	 * 
	 * @return Whether the participant list is 'open'
	 */
	public boolean isOpen() {
		return(m_open);
	}
	
	/**
	 * This method sets whether the participant list is 'open'.
	 *  
	 * @param open Whether the participant list should be open
	 */
	public void setOpen(boolean open) {
		m_open = open;
	}
	
	private boolean m_open=false;
	private java.util.List<Participant> m_participants=
			new ContainmentList<Participant>(this, Participant.class);
}
