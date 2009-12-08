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
 * 7 Dec 2007 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This class represents the binding between a declaration and
 * the name of a declaration in a composed definition.
 */
public class DeclarationBinding extends ModelObject {

	private static final long serialVersionUID = -8912117052145055821L;

	/**
	 * The default constructor.
	 */
	public DeclarationBinding() {
	}
	
	/**
	 * This constructor initializes the declaration and bound
	 * name.
	 * 
	 * @param decl The declaration
	 * @param boundName The bound name
	 */
	public DeclarationBinding(Declaration decl, String boundName) {
		m_declaration = decl;
		m_boundName = boundName;
	}
	
	/**
	 * This method sets the declaration.
	 * 
	 * @param decl The declaration
	 */
	public void setDeclaration(Declaration decl) {
		m_declaration = decl;
	}
	
	/**
	 * This method returns the declaration.
	 * 
	 * @return The declaration
	 */
	public Declaration getDeclaration() {
		return(m_declaration);
	}

	/**
	 * This method sets the bound name.
	 * 
	 * @param boundName The bound name
	 */
	public void setBoundName(String boundName) {
		m_boundName = boundName;
	}
	
	/**
	 * This method returns the bound name.
	 * 
	 * @return The bound name
	 */
	public String getBoundName() {
		return(m_boundName);
	}

	private Declaration m_declaration=null;
	private String m_boundName=null;
}
