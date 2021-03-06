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
 * This class represents the Repeat construct.
 * 
 */
public class Repeat extends Behaviour implements SinglePathBehaviour {

	private static final long serialVersionUID = -3254432118077412347L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Repeat() {
		m_block.setParent(this);
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
		
		// Check if not a decision making role
		if (role != null && m_participants.contains(role) == false) {
			ret = true;
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the list of participants at which
	 * the repeat decision is located.
	 * 
	 * @return The list of participants
	 */
	public java.util.List<Participant> getParticipants() {
		return(m_participants);
	}
	
	/**
	 * This method returns the activities.
	 * 
	 * @return The block of activities
	 */
	public Block getBlock() {
		return(m_block);
	}
	
	/**
	 * This method sets the block.
	 * 
	 * @param block The block
	 */
	public void setBlock(Block block) {
		if (m_block != null) {
			m_block.setParent(null);
		}
		
		m_block = block;
		
		if (m_block != null) {
			m_block.setParent(this);
		}
	}

	/**
	 * This method indicates whether the construct requires a
	 * strict scope to be maintained. If the scope does not
	 * need to be strictly maintained, then it is possible that
	 * activities could be contained by the construct or
	 * following the construct.
	 * 
	 * @return Whether a strict scope should be maintained
	 */
	public boolean isStrictScope() {
		return(true);
	}
	
	/**
	 * This method indicates whether the activity
	 * is a conditional construct.
	 * 
	 * @return Whether the activity is conditional
	 */
	public boolean isConditional() {
		return(true);
	}
	
	/**
	 * This method indicates whether the construct is
	 * repetitive.
	 * 
	 * @return Whether the construct supports repetition
	 */
	public boolean isRepetition() {
		return(true);
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

		for (int i=0; i < getParticipants().size(); i++) {
			if (ret.contains(getParticipants().get(i)) == false) {
				ret.add(getParticipants().get(i));
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
				
		return(ret);
	}

	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.startRepeat(this);
		
		if (getBlock() != null) {
			getBlock().visit(visitor);
		}
		
		visitor.endRepeat(this);
	}

	private Block m_block=new Block();
	private java.util.List<Participant> m_participants=new java.util.Vector<Participant>();
}
