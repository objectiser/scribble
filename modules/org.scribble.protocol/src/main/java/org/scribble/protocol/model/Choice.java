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

import org.scribble.core.model.*;

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
		
		Role role=getLocatedRole();
		
		// Check if not a decision making role
		if (role != null && m_roles.contains(role) == false) {
			ret = true;
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the list of roles at which
	 * the choice decision is located.
	 * 
	 * @return The list of roles
	 */
	public java.util.List<Role> getRoles() {
		return(m_roles);
	}
	
	/**
	 * This method returns the list of mutually exclusive
	 * activity blocks that comprise the multi-path construct.
	 * 
	 * @return The list of blocks
	 */
	@Reference(containment=true)
	public java.util.List<Block> getBlocks() {
		return(m_blocks);
	}
	
	/**
	 * This method returns the list of mutually exclusive
	 * activity blocks that comprise the multi-path construct.
	 * 
	 * @return The list of blocks
	 */
	public java.util.List<Block> getPaths() {
		return(getBlocks());
	}
	
	/**
	 * This method creates a new path within the multi-path
	 * behaviour. If the implementation cannot create
	 * the new path, then it will return null.
	 * 
	 * @return The new block, or null if cannot be created
	 */
	public Block createNewPath() {
		Block ret=new Block();
		
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

	private java.util.List<Block> m_blocks=new ContainmentList<Block>(this, Block.class);
	private java.util.List<Role> m_roles=new java.util.Vector<Role>();
}
