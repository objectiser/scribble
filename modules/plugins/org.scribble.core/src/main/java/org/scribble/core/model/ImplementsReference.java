/*
 * Copyright 2007-8 Pi4 Technologies Ltd
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
 * 1 Feb 2008 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This class represents a 'implements' link to another
 * definition.
 */
public class ImplementsReference extends NameMappingReference {

	private static final long serialVersionUID = -4001311732651746245L;

	/**
	 * This is the constructor for the 'implements' reference.
	 * 
	 * @param notation The notation
	 */
	public ImplementsReference(String notation) {
	}
	
	/**
	 * This is the copy constructor for the 'implements'
	 * reference.
	 * 
	 * @param ref The reference to copy
	 */
	public ImplementsReference(ImplementsReference ref) {
		super(ref);
	}
	
}
