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
 * 23 Oct 2007 : Initial version created by gary
 */
package org.scribble.protocol.model;

import org.scribble.core.model.*;

/**
 * This class represents the protocol model.
 * 
 */
public class ProtocolModel extends Model {

	private static final long serialVersionUID = 9154775764537136769L;

	/**
	 * This method returns the model name associated with
	 * the model.
	 * 
	 * @return The model name
	 */
	public LocatedName getModelName() {
		LocatedName ret=null;
		
		if (m_protocol != null) {
			ret = m_protocol.getLocatedName();
		}
		
		return(ret);
	}
		
	/**
	 * This method returns a read-only list of roles
	 * associated with the model. If this model represents
	 * a local model, then the roles in the list
	 * will be the remote roles, with the local
	 * role being associated with the model name.
	 * 
	 * @return The read-only list of roles
	 */
	public java.util.List<Role> getRoles() {
		java.util.List<Role> ret=null;
		final java.util.List<Role> roles=
			new java.util.Vector<Role>();
		
		if (m_protocol != null) {
			// Identify all of the roles defined in the
			// top level protocol
			m_protocol.visit(new Visitor() {
				
				public boolean visit(ModelObject obj) {
					boolean ret=true;
					
					if (obj instanceof Protocol &&
							m_protocol != obj) {
						ret = false;
						
					} else if (obj instanceof RoleList) {
						RoleList plist=(RoleList)obj;
						
						roles.addAll(plist.getRoles());
					}
					
					return(ret);
				}
			});
		}
		
		ret = new java.util.Vector<Role>(roles) {
			public boolean add(Role obj) {
				throw new java.lang.RuntimeException("Read only list");
			}
		};
		
		return(ret);
	}

	/**
	 * This method sets the protocol.
	 * 
	 * @param protocol The protocol
	 */
	public void setProtocol(Protocol protocol) {
		
		if (m_protocol != null) {
			m_protocol.setParent(null);
		}
		
		m_protocol = protocol;
		
		if (m_protocol != null) {
			m_protocol.setParent(this);
		}
	}
	
	/**
	 * This method returns the protocol.
	 * 
	 * @return The protocol
	 */
	@Reference(containment=true)
	public Protocol getProtocol() {
		return(m_protocol);
	}
	
	/**
	 * This method returns the list of definitions supported by
	 * this model.
	 * 
	 * @return The list of definitions
	 */
	public java.util.List<Definition> getDefinitions() {
		java.util.List<Definition> ret=new java.util.Vector<Definition>();
		
		ret.add(getProtocol());
		
		return(ret);
	}
	
	private Protocol m_protocol=null;
}
