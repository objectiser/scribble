/*
 * Copyright 2007 Pi4 Technologies Ltd
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
 * 20 Oct 2007 : Initial version created by gary
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
	 * This is the constructor initialized with the source
	 * reference.
	 * 
	 * @param source The source reference
	 */
	public ModelObject(int start, int end) {
		m_sourceRef.setStartPosition(start);
		m_sourceRef.setEndPosition(end);
	}
	
	/**
	 * This method returns the source reference.
	 * 
	 * @return The source reference
	 */
	public SourceRef getSource() {
		return(m_sourceRef);
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
			m_sourceRef = new SourceRef(modelObj.getSource());
			
			m_annotations = new Annotations(modelObj.getAnnotations());
		}
	}
	
	/**
	 * This method returns the annotations associated
	 * with this model object.
	 * 
	 * @return The annotations
	 */
	public Annotations getAnnotations() {
		return(m_annotations);
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
								
								if (refObject instanceof java.util.List) {
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
	
	/**
	 * This method sets the URI on the current model object, and
	 * iterates through all contained model objects to set the
	 * relative URI on them.
	 * 
	 * @param uriPart The model object's URI part, relative to
	 * 				its parent
	 */
	protected void initializeURIPart(String uriPart) {
		String uri="";
		
		if (getParent() != null) {
			uri = getParent().getSource().getModelObjectURI();
		}

		uri += "/" + (uriPart == null ? "":uriPart);
		
		// Initialize the URI on the source reference
		getSource().setModelObjectURI(uri);
		
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
							
							if (refObject instanceof java.util.List) {
								java.util.List<?> list=(java.util.List<?>)refObject;
								
								for (int j=0; j < list.size(); j++) {
									if (list.get(j) instanceof ModelObject) {
										
										((ModelObject)list.get(j)).initializeURIPart(pds[i].getName()+"."+(j+1));
										
									} else {
										logger.severe("Property '"+
												pds[i].getName()+"' on class '"+
												getClass()+
												"': Object in list is not a ModelObject");
									}
								}
							} else if (refObject instanceof ModelObject) {
								((ModelObject)refObject).initializeURIPart(pds[i].getName());
								
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
	
	/**
	 * This method returns the child model object associated with
	 * the supplied URI part.
	 * 
	 * @param uriPart The URI part
	 * @return The model object, or null if not found
	 */
	protected ModelObject findChild(String uriPart) {
		ModelObject ret=null;
		int index=-1;
		int pos=-1;
		
		// Check if has an index
		if ((index=uriPart.indexOf('.')) != -1) {
			String num=uriPart.substring(index+1);
			
			uriPart = uriPart.substring(0, index);
			
			try {
				pos = Integer.parseInt(num);
			} catch(Exception e) {
				e.printStackTrace();
			}	
		}
		
		// Find property
		java.beans.BeanInfo bi=null;
		java.beans.PropertyDescriptor pd=null;
		
		try {
			bi = java.beans.Introspector.getBeanInfo(getClass());
		} catch(Exception e) {
			logger.log(java.util.logging.Level.SEVERE,
					"Failed to get bean info for class '"+
					getClass()+"'", e);
		}
		
		if (bi != null) {
			java.beans.PropertyDescriptor[] pds=bi.getPropertyDescriptors();
			
			for (int i=0; pd == null && i < pds.length; i++) {
				if (pds[i].getName().equals(uriPart)) {
					pd = pds[i];
				}
			}
		}
		
		if (pd != null && pd.getReadMethod() != null) {
			try {
				Object refObject=pd.getReadMethod().invoke(this, (Object[])null);
				
				if (refObject instanceof java.util.List &&
						pos != -1) {
					java.util.List<?> list=(java.util.List<?>)refObject;
					
					ret = (ModelObject)list.get(pos-1);
				} else if (refObject instanceof ModelObject) {
					ret = (ModelObject)refObject;
					
				} else if (refObject != null) {
					logger.severe("Property '"+pd.getName()+"' on class '"+
							getClass()+
							"': Contained object is not a ModelObject or list: "+
							refObject.getClass());
				}
			} catch(Exception e) {
				logger.severe("Failed to obtain property '"+
						pd.getName()+"' on class '"+
						getClass()+"': "+e);
				e.printStackTrace();
			}
		}
		
		return(ret);
	}
	
	/* DON'T PROVIDE AN EQUALS IMPL, as this causes a problem with comparator rules
	 * when checking for multiple paths - but can use equality of the source refs
	public boolean equals(Object obj) {
		boolean ret=false;
		
		if (obj instanceof ModelObject) {
			ModelObject other=(ModelObject)obj;
			
			if (m_sourceRef != null &&
					other.m_sourceRef != null) {
				ret = m_sourceRef.equals(other.m_sourceRef);
			}
		}
		return(ret);
	}
	*/
	
	private static Logger logger = Logger.getLogger("org.scribble.model");

	private SourceRef m_sourceRef=new SourceRef();
	private ModelObject m_parent=null;
	private Annotations m_annotations=new Annotations();
}
