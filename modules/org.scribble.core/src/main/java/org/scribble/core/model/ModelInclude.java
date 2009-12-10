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
 * This abstract class represents a behavioural activity that
 * includes another model description, providing name mapping
 * information where appropriate.
 */
public abstract class ModelInclude extends Behaviour {

	private static final long serialVersionUID = 5201789723150072145L;

	/**
	 * This method returns the reference associated with
	 * the included model.
	 * 
	 * @return The model reference
	 */
	public abstract ModelReference getReference();
	
	/**
	 * This method returns the definition being
	 * included.
	 * 
	 * @return The definition
	 */
	public abstract Definition getDefinition();
	
	/**
	 * This method indicates whether the model include is
	 * an inline definition.
	 * 
	 * @return Whether an inline definition
	 */
	public boolean isInline() {
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
	public DeclarationBinding getDeclarationBinding(Declaration decl) {
		DeclarationBinding ret=null;
		
		java.util.Iterator<DeclarationBinding> iter=getBindings().iterator();
		
		while (ret == null && iter.hasNext()) {
			ret = iter.next();
			
			if (ret.getDeclaration() != decl) {
				ret = null;
			}
		}
		
		return(ret);
	}
		
	/**
	 * This method determines whether the model include construct
	 * is asynchronous.
	 * 
	 * @return Whether the model include is asynchronous
	 */
	public abstract boolean isAsynchronous();
	
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
	public java.util.List<Role> getInitiatorRoles() {
		java.util.List<Role> ret=super.getInitiatorRoles();
		
		Definition defn=getDefinition();
		
		if (defn != null) {
			ret.addAll(defn.getBlock().getInitiatorRoles());
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
	public java.util.List<Role> getFinalRoles() {
		java.util.List<Role> ret=null;
		
		if (isAsynchronous()) {
			ret = getInitiatorRoles();
			
		} else {
			ret = super.getFinalRoles();
			
			Definition defn=getDefinition();
			
			if (defn != null) {
				ret.addAll(defn.getBlock().getFinalRoles());
			}	
		}
		
		
		return(ret);
	}

	private java.util.List<DeclarationBinding> m_bindings=new java.util.Vector<DeclarationBinding>();
}
