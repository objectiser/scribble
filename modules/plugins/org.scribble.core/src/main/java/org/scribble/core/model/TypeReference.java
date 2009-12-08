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
 * 24 Oct 2007 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This class represents a type reference.
 */
public class TypeReference extends ModelReference {

	private static final long serialVersionUID = 6492984753933254949L;

	/**
	 * This is the default constructor for the type reference.
	 */
	public TypeReference() {
	}

	/**
	 * This method determines whether another reference, that
	 * implements (or conforms to) this reference, can be
	 * used.
	 * 
	 * @return Whether to use an implementation of this
	 * 				referenced model
	 */
	public boolean useImplementations() {
		return(true);
	}
	
}
