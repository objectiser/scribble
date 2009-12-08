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
 * 23 Oct 2007 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This interface represents the details associated with a
 * Scribble notation.
 */
public interface Notation {

	/**
	 * This method returns the code associated with the notation.
	 * 
	 * @return The notation code
	 */
	public String getCode();
	
	/**
	 * This method returns the name of the notation.
	 * 
	 * @return The name
	 */
	public String getName();
	
	/**
	 * This method returns the initial description associated
	 * with the supplied reference.
	 * 
	 * @param ref The reference
	 * @return The initial description, or null if no description
	 */
	public String getInitialDescription(ModelReference ref);
	
	/**
	 * This method determines whether the Scribble editor should
	 * be used for editing the notation.
	 * 
	 * @return Whether the Scribble editor should be used
	 */
	public boolean useScribbleEditor();
	
	/**
	 * This method returns the optional 'super' notation.
	 * If defined, then this notation is a derived notation
	 * from the 'super' notation.
	 * 
	 * @return The optional 'super' notation
	 */
	public Notation getSuperNotation();

}
