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

import java.util.logging.Logger;

/**
 * This is the generic object from which all Scribble model objects
 * are derived.
 */
public abstract class ModelObject implements java.io.Serializable {

	private static final long serialVersionUID = -8915435247669402908L;

	/**
	 * This is the default constructor for the model object.
	 */
	public ModelObject() {
	}
	
	/**
	 * This method returns the parent of this
	 * model object.
	 * 
	 * @return The parent, or null if top model
	 * 					object
	 */
	public ModelObject getParent() {
		return(m_parent);
	}
	
	/**
	 * This method sets the parent model object.
	 * 
	 * @param parent The parent
	 */
	public void setParent(ModelObject parent) {
		m_parent = parent;
	}
	
	/**
	 * This method establishes the necessary information to
	 * indicate that the current model object is derived
	 * from the supplied source model object.
	 * 
	 * @param modelObj The source model object
	 */
	public void derivedFrom(ModelObject modelObj) {
		if (modelObj != null) {
			m_properties = new java.util.HashMap<String,java.io.Serializable>(modelObj.getProperties());
		}
	}
	
	/**
	 * This method returns the properties associated
	 * with this model object.
	 * 
	 * @return The properties
	 */
	public java.util.Map<String,java.io.Serializable> getProperties() {
		return(m_properties);
	}
	
	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		boolean visitContained=visitor.visit(this);
		
		if (visitContained) {
			
			// Iterate over references and contained model objects
			java.beans.BeanInfo bi=null;
			
			try {
				bi = java.beans.Introspector.getBeanInfo(getClass());
			} catch(Exception e) {
				logger.log(java.util.logging.Level.SEVERE,
						"Failed to get bean info for class '"+
						getClass()+"'", e);
			}
			
			if (bi != null) {
				java.beans.PropertyDescriptor[] pds=bi.getPropertyDescriptors();
				
				for (int i=0; i < pds.length; i++) {
					if (pds[i].getReadMethod() != null) {
						org.scribble.core.model.Reference ref=
							(org.scribble.core.model.Reference)
							pds[i].getReadMethod().getAnnotation(
									org.scribble.core.model.Reference.class);
						
						if (ref != null && ref.containment()) {
							try {
								Object refObject=pds[i].getReadMethod().invoke(this, (Object[])null);
								
								if (refObject instanceof java.util.List<?>) {
									java.util.List<?> list=(java.util.List<?>)refObject;
									
									for (int j=0; j < list.size(); j++) {
										if (list.get(j) instanceof ModelObject) {
											
											((ModelObject)list.get(j)).visit(visitor);
											
										} else {
											logger.severe("Property '"+
													pds[i].getName()+"' on class '"+
													getClass()+
													"': Object in list is not a ModelObject");
										}
									}
								} else if (refObject instanceof ModelObject) {
									((ModelObject)refObject).visit(visitor);
									
								} else if (refObject != null) {
									logger.severe("Property '"+pds[i].getName()+"' on class '"+
											getClass()+
											"': Contained object is not a ModelObject or list: "+
											refObject.getClass());
								}
							} catch(Exception e) {
								logger.severe("Failed to obtain property '"+
										pds[i].getName()+"' on class '"+
										getClass()+"': "+e);
								e.printStackTrace();
							}
						}
					}
				}			
			}
		}
	}
	
	private static Logger logger = Logger.getLogger("org.scribble.model");

	private ModelObject m_parent=null;
	private java.util.Map<String,java.io.Serializable> m_properties=
				new java.util.HashMap<String, java.io.Serializable>();
}
