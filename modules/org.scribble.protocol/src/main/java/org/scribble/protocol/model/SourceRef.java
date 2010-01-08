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

public class SourceRef implements java.io.Serializable {

	private static final long serialVersionUID = -6129050166122933390L;

	/**
	 * This is the default constructor.
	 */
	public SourceRef() {
	}
	
	/**
	 * This is the copy constructor for the source reference.
	 * 
	 * @param ref The source reference to copy
	 */
	public SourceRef(SourceRef ref) {
		m_startPosition = ref.getStartPosition();
		m_endPosition = ref.getEndPosition();
		m_modelObjectURI = ref.getModelObjectURI();
		m_componentURI = ref.getComponentURI();
		m_object = ref.getObject();
		m_properties.putAll(ref.getProperties());
	}
	
	/**
	 * This is the constructor to initialize the source reference
	 * using positional information.
	 * 
	 * @param start The start position
	 * @param end The end position
	 */
	public SourceRef(int start, int end) {
		setStartPosition(start);
		setEndPosition(end);
	}
	
	/**
	 * This method sets the start position of this model object
	 * within the source description.
	 * 
	 * @param pos The start position, or -1 if not relevant
	 */
	public void setStartPosition(int pos) {
		m_startPosition = pos;
	}
	
	/**
	 * This method returns the start position of this model object
	 * within the source description.
	 * 
	 * @return The start position, or -1 if not set
	 */
	public int getStartPosition() {
		return(m_startPosition);
	}
	
	/**
	 * This method sets the end position of this model object
	 * within the source description.
	 * 
	 * @param pos The end position, or -1 if not relevant
	 */
	public void setEndPosition(int pos) {
		m_endPosition = pos;
	}
	
	/**
	 * This method returns the end position of this model object
	 * within the source description.
	 * 
	 * @return The end position, or -1 if not set
	 */
	public int getEndPosition() {
		return(m_endPosition);
	}
	
	/**
	 * This method returns the model object location within
	 * the model.
	 * 
	 * @return The model object URI
	 */
	public String getModelObjectURI() {
		return(m_modelObjectURI);	
	}
	
	/**
	 * This method sets the model object location within the
	 * model.
	 * 
	 * @param uri The model object URI
	 */
	public void setModelObjectURI(String uri) {
		m_modelObjectURI = uri;
	}
	
	/**
	 * This method returns the optional component location within the
	 * source model. This can be used to locate the source component
	 * associated with the Scribble model object. The format of the
	 * location will be dependent upon the nature of the model from
	 * which the Scribble model was derived.
	 * 
	 * @return The component location
	 */
	public String getComponentURI() {
		return(m_componentURI);	
	}
	
	/**
	 * This method sets the component location within the
	 * source model.
	 * 
	 * @param component The component location
	 */
	public void setComponentURI(String component) {
		m_componentURI = component;
	}
	
	/**
	 * This method returns the object associated with the
	 * source reference. If the object is not available then
	 * it can be resolved using the start/end and/or component
	 * information.
	 * 
	 * @return The object, or null if not defined
	 */
	public Object getObject() {
		return(m_object);
	}
	
	/**
	 * This method sets the object within the
	 * source model.
	 * 
	 * @param obj The object
	 */
	public void setObject(Object obj) {
		m_object = obj;
	}
	
	/**
	 * This method returns the set of additional properties
	 * that are associated with the source reference.
	 * 
	 *@return The additional properties
	 */
	public java.util.Map<String,Object> getProperties() {
		return(m_properties);
	}
	
	public String toString() {
		String ret="";
		
		if (m_componentURI != null) {
			ret = m_componentURI;
		}
		
		if (m_startPosition != -1) {
			if (ret.length() > 0) {
				ret += ",";
			}
			ret += "start="+m_startPosition;
		}
		
		if (m_endPosition != -1) {
			if (ret.length() > 0) {
				ret += ",";
			}
			ret += "end="+m_endPosition;
		}
		
		return(ret);
	}
	
	public boolean equals(Object obj) {
		boolean ret=false;

		if (obj instanceof SourceRef) {
			SourceRef other=(SourceRef)obj;
			
			if (m_modelObjectURI != null &&
					other.m_modelObjectURI != null) {
				ret = m_modelObjectURI.equals(other.m_modelObjectURI);
			}
		}
		
		return(ret);
	}
	
	private int m_startPosition=-1;
	private int m_endPosition=-1;
	private String m_modelObjectURI=null;
	private String m_componentURI=null;
	private Object m_object=null;
	private java.util.Map<String,Object> m_properties=
				new java.util.HashMap<String, Object>();
}
