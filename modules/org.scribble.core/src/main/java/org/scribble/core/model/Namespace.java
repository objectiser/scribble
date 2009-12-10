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
 * This class represents the namespace associated with a model.
 * 
 */
public class Namespace extends Statement {

	private static final long serialVersionUID = 2546583254402988900L;

	/**
	 * The default constructor.
	 */
	public Namespace() {
	}
	
	/**
	 * This method returns the name associated with the namespace.
	 * 
	 * @return The name
	 */
	public String getName() {
		return(m_name);
	}
	
	/**
	 * This method sets the name associated with the namespace.
	 * 
	 * @param name The namespace
	 */
	public void setName(String name) {
		m_name = name;
	}
	
	private String m_name=null;
}
