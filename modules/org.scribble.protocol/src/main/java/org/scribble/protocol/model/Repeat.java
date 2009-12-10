/*
 * Copyright 2007 Pi4 Technologies Ltd
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
 *
 * Change History:
 * 29 Oct 2007 : Initial version created by gary
 */
package org.scribble.protocol.model;

import org.scribble.core.model.*;

/**
 * This class represents the Repeat construct.
 * 
 */
public class Repeat extends SinglePathBehaviour {

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
		
		Role role=getLocatedRole();
		
		// Check if not a decision making role
		if (role != null && m_roles.contains(role) == false) {
			ret = true;
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the list of roles at which
	 * the repeat decision is located.
	 * 
	 * @return The list of roles
	 */
	public java.util.List<Role> getRoles() {
		return(m_roles);
	}
	
	/**
	 * This method returns the activities.
	 * 
	 * @return The block of activities
	 */
	@Reference(containment=true)
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
	public java.util.List<Role> getInitiatorRoles() {
		java.util.List<Role> ret=super.getInitiatorRoles();

		for (int i=0; i < getRoles().size(); i++) {
			if (ret.contains(getRoles().get(i)) == false) {
				ret.add(getRoles().get(i));
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
	public java.util.List<Role> getFinalRoles() {
		java.util.List<Role> ret=super.getFinalRoles();
				
		return(ret);
	}

	private Block m_block=new Block();
	private java.util.List<Role> m_roles=new java.util.Vector<Role>();
}
