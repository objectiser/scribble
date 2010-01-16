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
 * This class represents the Choice construct between
 * two or more paths.
 * 
 */
public class Choice extends MultiPathBehaviour {

	private static final long serialVersionUID = 1989654742243530028L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Choice() {
	}
	
	/**
	 * This method determines whether the paths are mutually
	 * exclusive.
	 * 
	 * @return Whether the paths are mutually exclusive
	 */
	public boolean isMutuallyExclusivePaths() {
		return(true);
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
		
		Participant participant=locatedParticipant();
		
		// Check if not a decision making participant
		if (participant != null && m_toParticipant != null &&
				participant.getName().equals(m_toParticipant.getName())) {
			ret = true;
		}
		
		return(ret);
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
	 * This method returns the list of mutually exclusive
	 * activity blocks that comprise the multi-path construct.
	 * 
	 * @return The list of blocks
	 */
	public java.util.List<WhenBlock> getWhenBlocks() {
		return(m_blocks);
	}
	
	/**
	 * This method returns the list of mutually exclusive
	 * activity blocks that comprise the multi-path construct.
	 * 
	 * @return The list of blocks
	 */
	public java.util.List<? extends Block> getPaths() {
		return(getWhenBlocks());
	}
	
	/**
	 * This method creates a new path within the multi-path
	 * behaviour. If the implementation cannot create
	 * the new path, then it will return null.
	 * 
	 * @return The new block, or null if cannot be created
	 */
	public Block createNewPath() {
		WhenBlock ret=new WhenBlock();
		
		m_blocks.add(ret);
		
		return(ret);
	}
	
	/**
	 * This method removes a path from the multi-path
	 * behaviour.
	 * 
	 * @param path The path
	 * @return Whether the path was removed
	 */
	public boolean removePath(Block path) {
		boolean ret=false;
		
		ret = m_blocks.remove(path);
		
		return(ret);
	}
	
	/**
	 * This method indicates whether the construct requires a
	 * strict scope to be maintained. If the scope does not
	 * need to be strictly maintained, then it is possible that
	 * either activities relevant to each path could either
	 * be duplicated within each path, or shared following
	 * the construct.
	 * 
	 * @return Whether a strict scope should be maintained
	 */
	public boolean isStrictScope() {
		return(false);
	}
	
	/**
	 * This method returns the ordering constraint for
	 * the multipath behaviour.
	 * 
	 * @return The ordering constraint
	 */
	public OrderingConstraint getOrdering() {
		return(OrderingConstraint.Unordered);
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
			ret.add(getFromParticipant());
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
				
		// TODO: Should it add 'to' participant to list?
		
		return(ret);
	}

	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.startChoice(this);
		
		for (Block b : getPaths()) {
			b.visit(visitor);
		}
		
		visitor.endChoice(this);
	}
	
	private java.util.List<WhenBlock> m_blocks=new ContainmentList<WhenBlock>(this, WhenBlock.class);
	private Participant m_fromParticipant=null;
	private Participant m_toParticipant=null;
}
