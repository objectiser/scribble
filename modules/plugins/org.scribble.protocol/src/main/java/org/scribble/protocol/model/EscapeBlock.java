/*
 * Copyright 2005-8 Pi4 Technologies Ltd
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
 * 18 Oct 2008 : Initial version created by gary
 */
package org.scribble.protocol.model;

import org.scribble.core.model.Block;
import org.scribble.core.model.Role;

/**
 * This class represents a group of activities within
 * an 'escape' block of a try/escape construct.
 * 
 */
public abstract class EscapeBlock extends Block {

	private static final long serialVersionUID = -2794418546744091644L;

	/**
	 * This method returns the list of roles at which
	 * the escape decision is located.
	 * 
	 * @return The list of roles
	 */
	public java.util.List<Role> getRoles() {
		return(m_roles);
	}
		
	private java.util.List<Role> m_roles=new java.util.Vector<Role>();
}
