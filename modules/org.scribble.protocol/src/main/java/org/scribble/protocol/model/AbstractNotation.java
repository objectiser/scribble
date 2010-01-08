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
 * This abstract implementation .
 */
public abstract class AbstractNotation implements Notation {

	/**
	 * The constructor to initialise the details associated
	 * with the notation.
	 * 
	 * @param code The code
	 * @param name The name
	 */
	public AbstractNotation(String code, String name) {
		m_code = code;
		m_name = name;
	}
	
	/**
	 * This method returns the code associated with the notation.
	 * 
	 * @return The notation code
	 */
	public String getCode() {
		return(m_code);
	}
	
	/**
	 * This method returns the name of the notation.
	 * 
	 * @return The name
	 */
	public String getName() {
		return(m_name);
	}
	
	/**
	 * This method returns the initial description associated
	 * with the supplied reference.
	 * 
	 * @param ref The reference
	 * @return The initial description, or null if no description
	 */
	public String getInitialDescription(ModelReference ref) {
		return(null);
	}
	
	/**
	 * This method returns the optional 'super' notation.
	 * If defined, then this notation is a derived notation
	 * from the 'super' notation.
	 * 
	 * @return The optional 'super' notation
	 */
	public Notation getSuperNotation() {
		return(null);
	}
	
	private String m_code=null;
	private String m_name=null;
}
