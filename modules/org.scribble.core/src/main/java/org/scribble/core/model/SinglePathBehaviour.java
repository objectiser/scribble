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
 * This interface represents behaviours that are associated
 * with a single path.
 */
public interface SinglePathBehaviour {

	/**
	 * This method returns whether the behaviour is a grouping
	 * construct.
	 * 
	 * @return Whether the behaviour is a grouping construct 
	 */
	public boolean isGroupingConstruct();
	
	/**
	 * This method returns the block of activities
	 * associated with the single path.
	 * 
	 * @return The block of activities
	 */
	public Block getBlock();
	
	/**
	 * This method indicates whether the construct requires a
	 * strict scope to be maintained. If the scope does not
	 * need to be strictly maintained, then it is possible that
	 * activities could be contained by the construct or
	 * following the construct.
	 * 
	 * @return Whether a strict scope should be maintained
	 */
	public boolean isStrictScope();
	
	/**
	 * This method indicates whether the construct is
	 * conditional.
	 * 
	 * @return Whether the construct is conditional
	 */
	public boolean isConditional();
	
	/**
	 * This method indicates whether the construct is
	 * repetitive.
	 * 
	 * @return Whether the construct supports repetition
	 */
	public boolean isRepetition();
	
}
