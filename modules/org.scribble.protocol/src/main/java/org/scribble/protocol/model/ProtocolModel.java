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
 * This class represents the base class for models associated with
 * specific notations. The details associated with the notation are
 * contained within derived classes.
 *
 */
public class ProtocolModel extends ModelObject {

	private static final long serialVersionUID = -1282833027993451521L;

	/**
	 * The default constructor for the model.
	 */
	public ProtocolModel() {
	}
	
	/**
	 * This method sets the namespace associated with the model.
	 * 
	 * @param namespace The namespace
	 */
	public void setNamespace(Namespace namespace) {
		
		if (m_namespace != null) {
			m_namespace.setParent(null);
		}
		
		m_namespace = namespace;
		
		if (m_namespace != null) {
			m_namespace.setParent(this);
		}
	}
	
	/**
	 * This method returns the namespace associated with the model.
	 * 
	 * @return The namespace
	 */
	@Reference(containment=true)
	public Namespace getNamespace() {
		return(m_namespace);
	}
	
	/**
	 * This method returns the list of import definitions.
	 * 
	 * @return The import definitions
	 */
	@Reference(containment=true)
	public java.util.List<Import> getImports() {
		return(m_imports);
	}
	
	/**
	 * This method returns the definition associated with
	 * this model.
	 * 
	 * @return The definition
	 */
	@Reference(containment=true)
	public Protocol getDefinition() {
		return(m_definition);
	}
	
	/**
	 * This method set the definition associated with the
	 * model.
	 * 
	 * @param defn The definition
	 */
	public void setDefinition(Protocol defn) {
		m_definition = defn;
	}
	
	/**
	 * This method determines whether the model is located.
	 * 
	 * @return Whether the model is located
	 */
	public boolean isLocated() {
		boolean ret=false;
		
		if (getDefinition() != null) {
			ret = (getDefinition().getLocatedName().getParticipant() != null);
		}
		
		return(ret);
	}
	
	private Namespace m_namespace=null;
	private Protocol m_definition=null;
	private java.util.List<Import> m_imports=
				new ContainmentList<Import>(this, Import.class);
}
