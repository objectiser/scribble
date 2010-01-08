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
 * This class represents a conformance link to another
 * definition.
 */
public class ConformanceReference extends NameMappingReference {

	private static final long serialVersionUID = -6759419194304963409L;

	/**
	 * This is the constructor for the conformance reference.
	 * 
	 * @param notation The notation
	 */
	public ConformanceReference(String notation) {
	}
	
	/**
	 * This is the copy constructor for the conformance
	 * reference.
	 * 
	 * @param ref The reference to copy
	 */
	public ConformanceReference(ConformanceReference ref) {
		super(ref);
	}
}
