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
 * This class represents the Try/Escape construct.
 * 
 */
public class TryEscape extends MultiPathBehaviour {

	private static final long serialVersionUID = -2764658967983880397L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public TryEscape() {
		m_block.setParent(this);
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
	 * This method returns the list of mutually exclusive
	 * escape blocks.
	 * 
	 * @return The list of escape blocks
	 */
	@Reference(containment=true)
	public java.util.List<EscapeBlock> getEscapeBlocks() {
		return(m_escapeBlocks);
	}
	
	/**
	 * This method returns the list of mutually exclusive
	 * activity blocks that comprise the multi-path construct.
	 * 
	 * @return The list of blocks
	 */
	public java.util.List<Block> getPaths() {
		java.util.List<Block> ret=new java.util.Vector<Block>();
		
		ret.add(getBlock());
		
		ret.addAll(getEscapeBlocks());

		return(ret);
	}
	
	/**
	 * This method creates a new path within the multi-path
	 * behaviour. If the implementation cannot create
	 * the new path, then it will return null.
	 * 
	 * @return The new block, or null if cannot be created
	 */
	public Block createNewPath() {
		Block ret=null;
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
		return(true);
	}
	
	/**
	 * This method returns the ordering constraint for
	 * the multipath behaviour.
	 * 
	 * @return The ordering constraint
	 */
	public OrderingConstraint getOrdering() {
		// Return as unordered, although the first path
		// (i.e. the try block) must appear first - but
		// this will be guaranteed in a comparison
		// against another try/escape construct,
		// as this is determined by the language construct.
		return(OrderingConstraint.Unordered);
	}
	
	private Block m_block=new Block();
	private java.util.List<EscapeBlock> m_escapeBlocks=new ContainmentList<EscapeBlock>(this, EscapeBlock.class);
}
