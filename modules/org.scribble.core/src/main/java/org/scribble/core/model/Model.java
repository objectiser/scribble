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
public abstract class Model extends ModelObject {

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
	 * This method returns the model name associated with
	 * the model.
	 * 
	 * @return The model name
	 */
	//public abstract ModelName getModelName();

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
	public java.util.List<ModelReference> getLocalModels(final ModelReference template) {
		final java.util.List<ModelReference> localModelRefs=
					new java.util.Vector<ModelReference>();
		
		java.util.List<Definition> defns=getDefinitions();
		
		for (int i=0; i < defns.size(); i++) {
			// Identify all of the roles defined in the
			// top level definition
			defns.get(i).visit(new Visitor() {
				
				public boolean visit(ModelObject obj) {
					boolean ret=true;
					
					if (obj instanceof RoleList &&
							((RoleList)obj).isOpen() == false) {
						RoleList plist=(RoleList)obj;
						
						for (int i=0; i < plist.getRoles().size(); i++) {
							Role role=plist.getRoles().get(i);
							
							// Create model reference for this role
							ModelReference lref=
								new ModelReference(template);
							
							lref.setLocatedRole(role.getName());
							
							Definition defn=plist.getEnclosingDefinition();
							
							while (defn != null &&
										(defn.getParent() instanceof Model) == false) {
								lref.getSubDefinitionPath().addPathElement(0,
											defn.getLocatedName().getName());
								
								ModelObject parent=defn.getParent();
								
								if (parent instanceof Activity) {
									defn = ((Activity)defn.getParent()).getEnclosingDefinition();
								} else {
									defn = null;
								}
							}
							
							// Associate role annotations with local model reference
							// NOTE: This may be a temporary measure while Role objects
							// have the Contract model associated with them, as a
							// convenient way to make the info available to the generator.
							lref.getAnnotations().putAll(role.getAnnotations());
							
							localModelRefs.add(lref);
						}
					}
					
					return(ret);
				}
			});
		}
		
		return(localModelRefs);
	}

	/**
	 * This method returns the list of definitions supported by
	 * this model.
	 * 
	 * @return The list of definitions
	 */
	public abstract java.util.List<Definition> getDefinitions();
	
	/**
	 * This method determines whether the model is located.
	 * 
	 * @return Whether the model is located
	 */
	public boolean isLocated() {
		boolean ret=false;
		
		java.util.List<Definition> defns=getDefinitions();
		
		if (defns.size() > 0) {
			ret = (defns.get(0).getLocatedName().getRole() != null);
		}
		
		return(ret);
	}
	
	/**
	 * This method initializes the URIs for the model
	 * objects contained in the model.
	 *
	 */
	public void initializeURIs() {
		initializeURIPart("");
	}
	
	/**
	 * This method returns the contained model object,
	 * within the model, based on the supplied URI.
	 * 
	 * @param uri The URI
	 * @return The model object, or null if not found
	 */
	public ModelObject findModelObject(String uri) {
		ModelObject ret=this;
		
		String[] parts=uri.split("/");
		
		// Start at position 2, as the URI begins with "//"
		for (int i=2; ret != null && i < parts.length; i++) {
			ret = ret.findChild(parts[i]);
		}
		
		return(ret);
	}
	
	private Namespace m_namespace=null;
	private java.util.List<Import> m_imports=
				new ContainmentList<Import>(this, Import.class);
}
