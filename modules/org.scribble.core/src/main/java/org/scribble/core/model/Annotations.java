/*
 * Copyright 2005-9 Pi4 Technologies Ltd
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
 * 20 Apr 2009 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This class represents a map of named serializable
 * information that can be used to provide additional
 * details about a model component.
 */
public class Annotations extends java.util.HashMap<String,java.io.Serializable> {

	private static final long serialVersionUID = -4594153737794956180L;

	/**
	 * The default constructor.
	 */
	public Annotations() {
	}
	
	/**
	 * This is the copy constructor.
	 * 
	 * @param annotations The annotations to copy
	 */
	public Annotations(Annotations annotations) {
		putAll(annotations);
	}
}
