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
 * 29 Nov 2007 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This interface represents behaviours that are associated
 * with multiple paths.
 */
public abstract class MultiPathBehaviour extends Behaviour {

	private static final long serialVersionUID = 2630833363341001897L;

	/**
	 * This method returns whether the behaviour is a grouping
	 * construct.
	 * 
	 * @return Whether the behaviour is a grouping construct 
	 */
	@Override
	public boolean isGroupingConstruct() {
		return(true);
	}
	
	/**
	 * This method returns the list of mutually exclusive
	 * activity blocks that comprise the multi-path construct.
	 * 
	 * @return The list of blocks
	 */
	public abstract java.util.List<Block> getPaths();
	
	/**
	 * This method creates a new path within the multi-path
	 * behaviour. If the implementation cannot create
	 * the new path, then it will return null.
	 * 
	 * @return The new block, or null if cannot be created
	 */
	public abstract Block createNewPath();
	
	/**
	 * This method removes a path from the multi-path
	 * behaviour.
	 * 
	 * @param path The path
	 * @return Whether the path was removed
	 */
	public abstract boolean removePath(Block path);
	
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
	 * This method indicates whether the construct is
	 * conditional.
	 * 
	 * @return Whether the construct is conditional
	 */
	public boolean isConditional() {
		return(false);
	}
	
	/**
	 * This method determines whether the paths are mutually
	 * exclusive.
	 * 
	 * @return Whether the paths are mutually exclusive
	 */
	public boolean isMutuallyExclusivePaths() {
		return(false);
	}
	
	/**
	 * This method returns the ordering constraint for
	 * the multipath behaviour.
	 * 
	 * @return The ordering constraint
	 */
	public abstract OrderingConstraint getOrdering();
	
}
