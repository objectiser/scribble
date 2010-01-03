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
 * This class represents the base class for models associated with
 * specific notations. The details associated with the notation are
 * contained within derived classes.
 *
 */
public class Model<T extends Definition> extends ModelObject {

	private static final long serialVersionUID = -1282833027993451521L;

	/**
	 * The default constructor for the model.
	 */
	public Model() {
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
	 * This method returns a list of local model references
	 * associated with a global conversation model. The
	 * supplied reference provides the template for the
	 * local model references.
	 * 
	 * @param template The global model reference that should
	 * 				be used as the template for the local model
	 * 				references
	 * @return The list of local model references
	 */
	/*
	public java.util.List<ModelReference> getLocalModels(final ModelReference template) {
		final java.util.List<ModelReference> localModelRefs=
					new java.util.Vector<ModelReference>();
		
		if (getDefinition() != null) {
		
			// Identify all of the roles defined in the
			// top level definition
			getDefinition().visit(new Visitor() {
				
				public void prepare(ModelObject obj) {
				}
				
				public boolean visit(ModelObject obj) {
					boolean ret=true;
					
					if (obj instanceof Definition &&
							m_definition != obj) {
						ret = false;
						
					} else if (obj instanceof ParticipantList &&
							((ParticipantList)obj).isOpen() == false) {
						ParticipantList plist=(ParticipantList)obj;
						
						for (int i=0; i < plist.getParticipants().size(); i++) {
							Participant role=plist.getParticipants().get(i);
							
							// Create model reference for this role
							ModelReference lref=
								new ModelReference(template);
							
							lref.setLocatedRole(role.getName());
							
							Definition defn=plist.enclosingDefinition();
							
							while (defn != null &&
										(defn.getParent() instanceof Model<?>) == false) {
								lref.getSubDefinitionPath().addPathElement(0,
											defn.getLocatedName().getName());
								
								ModelObject parent=defn.getParent();
								
								if (parent instanceof Activity) {
									defn = ((Activity)defn.getParent()).enclosingDefinition();
								} else {
									defn = null;
								}
							}
							
							localModelRefs.add(lref);
						}
					}
					
					return(ret);
				}
				
				public void conclude(ModelObject obj) {
				}
			});
		}
		
		return(localModelRefs);
	}
	*/

	/**
	 * This method returns the definition associated with
	 * this model.
	 * 
	 * @return The definition
	 */
	@Reference(containment=true)
	public T getDefinition() {
		return(m_definition);
	}
	
	/**
	 * This method set the definition associated with the
	 * model.
	 * 
	 * @param defn The definition
	 */
	public void setDefinition(T defn) {
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
	private T m_definition=null;
	private java.util.List<Import> m_imports=
				new ContainmentList<Import>(this, Import.class);
}
