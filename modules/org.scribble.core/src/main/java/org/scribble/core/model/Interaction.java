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
 * This class represents an interaction.
 * 
 */
public class Interaction extends Behaviour {

	private static final long serialVersionUID = -3628413228540452576L;

	/**
	 * This class returns the message signature.
	 * 
	 * @return The message signature
	 */
	@Reference(containment=true)
	public MessageSignature getMessageSignature() {
		return(m_messageSignature);
	}
	
	/**
	 * This method sets the message signature.
	 * 
	 * @param signature The message signature
	 */
	public void setMessageSignature(MessageSignature signature) {
		
		if (m_messageSignature != null) {
			m_messageSignature.setParent(null);
		}
		
		m_messageSignature = signature;
		
		if (m_messageSignature != null) {
			m_messageSignature.setParent(this);
		}
	}
	
	/**
	 * This method returns the optional channel.
	 * 
	 * @return The channel
	 */
	public Channel getChannel() {
		return(m_channel);
	}
	
	/**
	 * This method sets the channel.
	 * 
	 * @param channel The channel
	 */
	public void setChannel(Channel channel) {
		m_channel = channel;
	}
	
	/**
	 * This method returns the optional 'from' participant.
	 * 
	 * @return The optional 'from' participant
	 */
	public Participant getFromParticipant() {
		return(m_fromParticipant);
	}
	
	/**
	 * This method sets the optional 'from' participant.
	 * 
	 * @param part The optional 'from' participant
	 */
	public void setFromParticipant(Participant part) {
		m_fromParticipant = part;
	}
	
	/**
	 * This method returns the optional 'to' participant.
	 * 
	 * @return The optional 'to' participant
	 */
	public Participant getToParticipant() {
		return(m_toParticipant);
	}
	
	/**
	 * This method sets the optional 'to' participant.
	 * 
	 * @param part The optional 'to' participant
	 */
	public void setToParticipant(Participant part) {
		m_toParticipant = part;
	}
	
	/**
	 * This method returns the label used to identify
	 * this request.
	 * 
	 * @return The request label
	 */
	public String getRequestLabel() {
		return(m_requestLabel);
	}
	
	/**
	 * This method sets the label used to identify this
	 * request.
	 * 
	 * @param label The request label
	 */
	public void setRequestLabel(String label) {
		m_requestLabel = label;
	}
	
	/**
	 * This method returns the label used to correlate
	 * this response with a previous request.
	 * 
	 * @return The replyTo label
	 */
	public String getReplyToLabel() {
		return(m_replyToLabel);
	}
	
	/**
	 * This method sets the label used to correlate
	 * this response with a previous request.
	 * 
	 * @param label The replyTo label
	 */
	public void setReplyToLabel(String label) {
		m_replyToLabel = label;
	}
	
	/**
	 * This method returns the list of roles that are
	 * responsible for initiating the activity. This can
	 * be used to determine whether the model is
	 * consistent in terms of decision makers subsequently
	 * initiating actions.
	 * 
	 * @return The list of initiator roles
	 */
	@Override
	public java.util.List<Participant> initiatorParticipants() {
		java.util.List<Participant> ret=super.initiatorParticipants();
		
		if (getFromParticipant() != null) {
			
			if (ret.contains(getFromParticipant()) == false) {
				ret.add(getFromParticipant());
			}
		} else {
			Definition defn=enclosingDefinition();
			
			if (defn != null) {
				Participant located=defn.getLocatedName().getParticipant();
				
				if (located != null && getToParticipant() != null &&
						getToParticipant().equals(located) == false &&
						ret.contains(located) == false) {
					ret.add(located);
				}
			}
		}
		
		return(ret);
	}

	/**
	 * This method returns the list of roles that are
	 * associated with the outcome of the activity.
	 * 
	 * @return The list of final roles
	 */
	@Override
	public java.util.List<Participant> finalParticipants() {
		java.util.List<Participant> ret=super.finalParticipants();
		
		if (getToParticipant() != null) {
			
			if (ret.contains(getToParticipant()) == false) {
				ret.add(getToParticipant());
			}
		} else {
			Definition defn=enclosingDefinition();
			
			if (defn != null) {
				Participant located=defn.getLocatedName().getParticipant();
				
				if (located != null && getFromParticipant() != null &&
						getFromParticipant().equals(located) == false &&
						ret.contains(located) == false) {
					ret.add(located);
				}
			}
		}
		
		return(ret);
	}

	/**
	 * This method returns the list of roles that are
	 * associated with the behaviour.
	 * 
	 * @return The list of associated roles
	 */
	@Override
	public java.util.List<Participant> associatedParticipants() {
		java.util.List<Participant> ret=super.associatedParticipants();
		
		if (getToParticipant() != null &&
					ret.contains(getToParticipant()) == false) {
			ret.add(getToParticipant());
		}
		
		if (getFromParticipant() != null &&
					ret.contains(getFromParticipant()) == false) {
			ret.add(getFromParticipant());
		}
		
		if (getToParticipant() == null || getFromParticipant() == null) {
			Participant located=locatedParticipant();
			
			if (located != null && ret.contains(located) == false) {
				ret.add(located);
			}
		}
		
		return(ret);
	}

	/**
	 * This method returns whether the behaviour is a wait
	 * state.
	 * 
	 * @return Whether the behaviour is a wait state
	 */
	@Override
	public boolean isWaitState() {
		boolean ret=false;
		Participant role=locatedParticipant();
		
		// Check if interaction is a receive
		if (role != null &&
			((getToParticipant() != null && role.equals(getToParticipant())) ||
			(getFromParticipant() != null && role.equals(getFromParticipant()) == false))) {
			ret = true;
		}
		
		return(ret);
	}

	public String toString() {
		StringBuffer ret=new StringBuffer();
		
		if (getMessageSignature() != null) {
			ret.append(getMessageSignature());
			ret.append(" ");
		}
		
		if (getFromParticipant() != null) {
			ret.append(getFromParticipant());
			ret.append("->");
			
			if (getToParticipant() != null) {
				ret.append(getToParticipant());
			}
		} else {
			ret.append("->");
			
			if (getToParticipant() != null) {
				ret.append(getToParticipant());
			}
		}
		
		return(ret.toString());
	}
	
	private MessageSignature m_messageSignature=null;
	private Channel m_channel=null;
	private Participant m_fromParticipant=null;
	private Participant m_toParticipant=null;
	private String m_requestLabel=null;
	private String m_replyToLabel=null;
}
