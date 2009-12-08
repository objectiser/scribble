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
 * 20 Mar 2008 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This interfaces represents a reference that has
 * associated name mapping information.
 */
public abstract class NameMappingReference extends ModelReference {

	private static final long serialVersionUID = -4511382903786589595L;

	/**
	 * This is the constructor for a name mapping reference.
	 * 
	 * @param notation The notation
	 */
	public NameMappingReference() {
	}

	/**
	 * This is the copy constructor for the name mapping
	 * reference.
	 * 
	 * @param ref The reference to copy
	 */
	public NameMappingReference(NameMappingReference ref) {
		super(ref);
		
		java.util.Iterator<String> iter=ref.getNameMapping().keySet().iterator();
		
		while (iter.hasNext()) {
			String key=iter.next();
			String value=ref.getNameMapping().get(key);
			
			getNameMapping().put(key, value);
		}
	}
	
	/**
	 * This method returns the mapping between names in the main
	 * definition and the definition being implemented.
	 * 
	 * @return The name mapping information
	 */
	public java.util.Map<String,String> getNameMapping() {
		return(m_nameMapping);
	}
	
	private java.util.Map<String,String> m_nameMapping=new java.util.Hashtable<String,String>();
}
