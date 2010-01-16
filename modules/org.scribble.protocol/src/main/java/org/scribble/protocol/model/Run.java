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
 * This class represents the Run construct.
 * 
 */
public class Run extends Behaviour {

	private static final long serialVersionUID = 7877693795925137333L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Run() {
	}
	
	/**
	 * This method determines whether the compose construct
	 * is asynchronous.
	 * 
	 * @return Whether the compose is asynchronous
	 */
	public boolean isAsynchronous() {
		return(false);
	}
	
	/**
	 * This method returns the bindings for the
	 * composition construct.
	 * 
	 * @return The list of bindings
	 */
	public java.util.List<DeclarationBinding> getBindings() {
		return(m_bindings);
	}
	
	/**
	 * This method returns the declaration binding associated
	 * with the supplied declaration.
	 * 
	 * @param decl The declaration
	 * @return The declaration binding, or null if not found
	 */
	public DeclarationBinding getDeclarationBinding(String declName) {
		DeclarationBinding ret=null;
		
		java.util.Iterator<DeclarationBinding> iter=getBindings().iterator();
		
		while (ret == null && iter.hasNext()) {
			ret = iter.next();
			
			if (ret.getLocalName().equals(declName) == false) {
				ret = null;
			}
		}
		
		return(ret);
	}
		
	/**
	 * This method returns the list of roles that are
	 * responsible for initiating the activity. This can
	 * be used to determine whether the model is
	 * consistent in terms of decision makers subsequently
	 * initiating actions.
	 * 
	 * @return The list of initiator roles
	 */
	@Override
	public java.util.List<Participant> initiatorParticipants() {
		java.util.List<Participant> ret=super.initiatorParticipants();
		
		Protocol defn=getProtocol();
		
		if (defn != null) {
			ret.addAll(defn.getBlock().initiatorParticipants());
		}
		
		return(ret);
	}

	/**
	 * This method returns the list of roles that are
	 * associated with the outcome of the activity.
	 * 
	 * @return The list of final roles
	 */
	@Override
	public java.util.List<Participant> finalParticipants() {
		java.util.List<Participant> ret=null;
		
		if (isAsynchronous()) {
			ret = initiatorParticipants();
			
		} else {
			ret = super.finalParticipants();
			
			Protocol defn=getProtocol();
			
			if (defn != null) {
				ret.addAll(defn.getBlock().finalParticipants());
			}
		}
		
		
		return(ret);
	}

	/**
	 * This method returns the protocol reference associated
	 * with the run construct.
	 * 
	 * @return The protocol reference, or null if not defined
	 */
	public ProtocolReference getReference() {
		return(m_reference);
	}
	
	/**
	 * This method sets the protocol reference associated
	 * with the run construct.
	 * 
	 * @param ref The protocol reference
	 */
	public void setReference(ProtocolReference ref) {
		
		if (m_reference != null) {
			m_reference.setParent(null);
		}
		
		m_reference = ref;
		
		if (m_reference != null) {
			m_reference.setParent(this);
		}
	}
	
	/**
	 * This method returns the protocol, if available as an inner protocol within the
	 * enclosing protocol, or binding information is available to load the referenced
	 * protocol.
	 * 
	 * @return The protocol, or null if not found
	 */
	public Protocol getProtocol() {
		Protocol ret=null;
		
		Protocol parent=enclosingProtocol();
		
		if (parent != null) {
			for (int i=0; ret == null && i < parent.getBlock().getContents().size(); i++) {
				
				if (parent.getBlock().getContents().get(i) instanceof Protocol) {
					Protocol inner=(Protocol)parent.getBlock().getContents().get(i);
					
					if (m_reference.getName().equals(inner.getLocatedName().getName())) {
						ret = inner;
					}
				}
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.visitRun(this);
			
		for (DeclarationBinding db : getBindings()) {
			visitor.visitDeclarationBinding(db);
		}
	}

	private ProtocolReference m_reference=null;
	private java.util.List<DeclarationBinding> m_bindings=new java.util.Vector<DeclarationBinding>();
}
