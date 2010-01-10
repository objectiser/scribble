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
 * This class represents the RecursionBlock construct.
 * 
 */
public class Recursion extends Declaration
				implements SinglePathBehaviour {

	private static final long serialVersionUID = -3777899920653462575L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Recursion() {
		m_block.setParent(this);
	}
	
	/**
	 * This method returns the name associated with the
	 * recursion construct.
	 * 
	 * @return The name
	 */
	public String getName() {
		return(m_name);
	}
	
	/**
	 * This method sets the name associated with the
	 * recursion construct.
	 * 
	 * @param name The name
	 */
	public void setName(String name) {
		m_name = name;
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
		m_block = block;
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
	 * This method returns whether the behaviour is a grouping
	 * construct.
	 * 
	 * @return Whether the behaviour is a grouping construct 
	 */
	public boolean isGroupingConstruct() {
		return(true);
	}
	
	/**
	 * This method indicates whether the construct is
	 * conditional.
	 * 
	 * @return Whether the construct is conditional
	 */
	public boolean isConditional() {
		return(false);
	}
	
	/**
	 * This method indicates whether the construct is
	 * repetitive.
	 * 
	 * @return Whether the construct supports repetition
	 */
	public boolean isRepetition() {
		return(false);
	}
	
	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.startRecursion(this);
		
		if (getBlock() != null) {
			getBlock().visit(visitor);
		}
		
		visitor.endRecursion(this);
	}

	private String m_name=null;
	private Block m_block=new Block();
}
