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
 * This class represents an import definition associated with a model.
 * 
 */
public class Import extends ModelObject {

	private static final long serialVersionUID = -5422814413966814121L;

	/**
	 * The default constructor.
	 */
	public Import() {
	}
	
	/**
	 * This method returns the fully qualified name of the
	 * definition(s) being imported.
	 * 
	 * @return The name
	 */
	public String getName() {
		return(m_name);
	}
	
	/**
	 * This method sets the fully qualified name of the
	 * definition(s) being imported.
	 * 
	 * @param name The name
	 */
	public void setName(String name) {
		m_name = name;
	}
	
	/**
	 * This method returns the optional alias.
	 * 
	 * @return The alias
	 */
	public String getAlias() {
		return(m_alias);
	}
	
	/**
	 * This method sets the optional alias.
	 * 
	 * @param alias The alias
	 */
	public void setAlias(String alias) {
		m_alias = alias;
	}
	
	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.visitImport(this);
	}
	
	private String m_name=null;
	private String m_alias=null;
}
