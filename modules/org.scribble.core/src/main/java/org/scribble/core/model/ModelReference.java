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
 * This class represents a reference to a Scribble destination
 * within the model repository.
 */
public class ModelReference extends ModelObject {

	private static final long serialVersionUID = 7962754571821586984L;

	/**
	 * This is the constructor for the model reference.
	 * 
	 * @param notation The notation
	 */
	public ModelReference() {
		//m_notation = notation;
		
		// Mark as placeholder, until either alias or
		// local part set
		m_placeholder = true;
	}
	
	/**
	 * This is the copy constructor for the model reference.
	 * 
	 * @param ref The reference to copy
	 */
	public ModelReference(ModelReference ref) {
		m_alias = ref.m_alias;
		m_namespace = ref.m_namespace;
		m_localpart = ref.m_localpart;
		m_subDefinitionPath = new SubDefinitionPath(ref.getSubDefinitionPath());
		m_locatedRole = ref.m_locatedRole;
		m_notation = ref.m_notation;
		m_inner = ref.m_inner;
		m_fullyQualified = ref.m_fullyQualified;
		
		if (ref.m_properties != null) {
			m_properties = new java.util.Hashtable<String, Object>();
			
			java.util.Iterator<String> iter=ref.m_properties.keySet().iterator();
			
			while (iter.hasNext()) {
				String key=iter.next();
				Object value=ref.m_properties.get(key);
				
				m_properties.put(key, value);
			}
		}
	}
	
	/**
	 * This is the constructor for the model reference.
	 * 
	 * @param namespace The namespace
	 * @param localpart The localpart
	 * @param located The optional located role
	 * @param notation The notation
	 */
	public ModelReference(String namespace, String localpart,
					String located, String notation) {
		m_namespace = namespace;
		m_localpart = localpart;
		m_locatedRole = located;
		m_notation = notation;

	}
	
	/**
	 * This method returns the alias.
	 * 
	 * @return The alias
	 */
	public String getAlias() {
		String ret=m_alias;

		// If alias not defined, then use local part
		// (The getLocalpart method will do the reverse,
		// so if only one field is defined, then they
		// will be treated as the same).
		if (ret == null) {
			ret = m_localpart;
		}
		
		return(ret);
	}
	
	/**
	 * This method sets the alias.
	 * 
	 * @param alias The alias
	 */
	public void setAlias(String alias) {
		m_alias = alias;
		
		// If the alias is set, then model reference
		// is not a placeholder
		m_placeholder = false;
	}
	
	/**
	 * This method returns the namespace.
	 * 
	 * @return The namespace
	 */
	public String getNamespace() {
		return(m_namespace);
	}
	
	/**
	 * This method sets the namespace.
	 * 
	 * @param namespace The namespace
	 */
	public void setNamespace(String namespace) {
		m_namespace = namespace;
	}
	
	/**
	 * This method returns the localpart.
	 * 
	 * @return The localpart
	 */
	public String getLocalpart() {
		String ret=m_localpart;
		
		// If local part not defined, then use alias
		// (The getAlias method will do the reverse,
		// so if only one field is defined, then they
		// will be treated as the same).
		if (ret == null) {
			ret = m_alias;
		}

		return(ret);
	}
	
	/**
	 * This method sets the localpart.
	 * 
	 * @param localpart The localpart
	 */
	public void setLocalpart(String localpart) {
		m_localpart = localpart;
		
		// If the local part is set, then model reference
		// is not a placeholder
		m_placeholder = false;
	}
	
	/**
	 * This method returns the sub definition path. The
	 * path identifies the sub definition of interest,
	 * within a global model, for a located role.
	 * 
	 * @return The sub definition path
	 */
	public SubDefinitionPath getSubDefinitionPath() {
		return(m_subDefinitionPath);
	}
	
	/**
	 * This method returns the located role associated
	 * with the model reference.
	 * 
	 * @return The located role
	 */
	public String getLocatedRole() {
		return(m_locatedRole);
	}
	
	/**
	 * This method sets the located role associated
	 * with the model reference.
	 * 
	 * @param located The located role
	 */
	public void setLocatedRole(String located) {
		m_locatedRole = located;
	}
	
	/**
	 * This method returns the notation code associated with the
	 * model reference.
	 * 
	 * @return The notation code
	 */
	public String getNotation() {
		return(m_notation);
	}
	
	/**
	 * This method sets the notation code associated with the
	 * model reference.
	 * 
	 * @param notation The notation code
	 */
	public void setNotation(String notation) {
		m_notation = notation;
	}
	
	/**
	 * This method indicates whether the model reference is simply
	 * being used as a placeholder to provide information about the
	 * notation of the model.
	 * 
	 * @return Whether the model reference is a placeholder
	 */
	public boolean isPlaceholder() {
		return(m_placeholder);
	}
	
	/**
	 * This method determines whether the model reference is
	 * fully qualified. This means that the namespace and
	 * localpart were explicitly defined, as opposed to the
	 * namespace being implicitly resolved using import
	 * statements associated with the model.<p>
	 * <p>
	 * The default value is false.
	 * 
	 * @return Whether the model reference is fully qualified
	 */
	public boolean isFullyQualified() {
		return(m_fullyQualified);
	}
	
	/**
	 * This method sets whether the model reference is
	 * fully qualified.
	 * 
	 * @param fq Whether the model reference is fully qualified
	 */
	public void setFullyQualified(boolean fq) {
		m_fullyQualified = fq;
	}
	
	/**
	 * This method determines if the model reference has been resolved
	 * to an existing definition.
	 * 
	 * @return Whether the reference has been resolved
	 */
	public boolean isResolved() {
		return(m_namespace != null || isInner());
	}
	
	/**
	 * This method determines whether the reference is to an inner
	 * description.
	 * 
	 * @return Whether the reference is related to an inner description
	 */
	public boolean isInner() {
		return(m_inner);
	}
	
	/**
	 * This method sets whether the reference is to an inner description.
	 * 
	 * @param inner Whether reference to inner description
	 */
	public void setInner(boolean inner) {
		m_inner = inner;
	}
	
	/**
	 * This method sets the property associated with
	 * the supplied name to the supplied value.
	 * 
	 * @param name The name
	 * @param value The value
	 */
	public void setProperty(String name, Object value) {
		m_properties.put(name, value);
	}
	
	/**
	 * This method returns the set of property names
	 * associated with the model reference.
	 * 
	 * @return The set of property names
	 */
	public java.util.Set<String> getPropertyNames() {
		return(m_properties.keySet());
	}
	
	/**
	 * This method determines whether another reference, that
	 * implements (or conforms to) this reference, can be
	 * used.
	 * 
	 * @return Whether to use an implementation of this
	 * 				referenced model
	 */
	public boolean useImplementations() {
		return(false);
	}
	
	/**
	 * This method returns the property associated with
	 * the supplied name.
	 * 
	 * @param name The name
	 * @return The value, or null if not found
	 */
	public Object getProperty(String name) {
		return(m_properties.get(name));
	}
	
	/**
	 * This method determines if the named property
	 * is associated with the model reference.
	 * 
	 * @param name The property name
	 * @return Whether the property is associated with the
	 * 					model reference
	 */
	public boolean hasProperty(String name) {
		return(m_properties.containsKey(name));
	}
	
	/**
	 * This method determines if the supplied object is
	 * equivalent to this object.
	 * 
	 * @param other The other object
	 * @return Whether the objects are equal
	 */
	public boolean equals(Object other) {
		boolean ret=false;
		
		// TODO: Do we need to consider comparison against a model ref
		// with just an alias???
		
		if (other instanceof ModelReference) {
			ModelReference ref=(ModelReference)other;
			
			ret = compare(m_namespace, ref.m_namespace);
			
			if (ret) {
				ret = compare(m_localpart, ref.m_localpart);
			}
			
			if (ret) {
				ret = m_subDefinitionPath.equals(
							ref.m_subDefinitionPath);
			}
			
			if (ret) {
				ret = compare(m_locatedRole, ref.m_locatedRole);
			}
			
			if (ret) {
				ret = compare(m_notation, ref.m_notation);
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method compares two strings.
	 * 
	 * @param first The first string
	 * @param second The second string
	 * @return Whether the strings are the same
	 */
	protected boolean compare(String first, String second) {
		boolean ret=true;
		
		if (first == null && second != null) {
			ret = false;
		} else if (first != null && second == null) {
			ret = false;
		} else if (first != null) {
			ret = first.equals(second);
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the hash code.
	 * 
	 * @return The hash code
	 */
	public int hashCode() {
		int ret=0;
		
		if (getAlias() != null) {
			ret = getAlias().hashCode();
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the textual representation of the model
	 * reference.
	 * 
	 * @return The textual representation
	 */
	public String toText() {
		String ret=getNamespace();
		
		if (ret == null) {
			ret = getAlias();
		} else {
			ret += "."+getAlias();
		}
		
		if (getLocatedRole() != null) {
			ret += LOCATED_REFERENCE_SEPARATOR+getLocatedRole();
		}
		
		return(ret);
	}
	
	public String toString() {
		String subdefnpath=m_subDefinitionPath.toString();
				
		return("Ref["+getAlias()+" ns="+m_namespace+" lp="+
				m_localpart+subdefnpath+" loc="+m_locatedRole+
				" "+m_notation+"]");
	}
	
	public static final String NAMESPACES_TO_MONITOR="NamespacesToMonitor";
	public static final String LOCATED_REFERENCE_SEPARATOR="@";
	
	private String m_alias=null;
	private String m_namespace=null;
	private String m_localpart=null;
	private SubDefinitionPath m_subDefinitionPath=new SubDefinitionPath();
	private String m_locatedRole=null;
	private String m_notation=null;
	private boolean m_inner=false;
	private boolean m_fullyQualified=false;
	private boolean m_placeholder=false;
	private java.util.Hashtable<String, Object> m_properties=new java.util.Hashtable<String, Object>();
}
