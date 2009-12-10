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
 * This class represents the base class for all Scribble definition
 * components.
 */
public abstract class Activity extends Statement {
	
	private static final long serialVersionUID = 907254090600526766L;

	/**
	 * This method returns the list of roles that are
	 * responsible for initiating the activity. This can
	 * be used to determine whether the model is
	 * consistent in terms of decision makers subsequently
	 * initiating actions.
	 * 
	 * @return The list of initiator roles
	 */
	public java.util.List<Role> getInitiatorRoles() {
		return(new java.util.Vector<Role>());
	}

	/**
	 * This method returns the list of roles that are
	 * associated with the outcome of the activity.
	 * 
	 * @return The list of final roles
	 */
	public java.util.List<Role> getFinalRoles() {
		return(new java.util.Vector<Role>());
	}
	
	/**
	 * This method returns the optional located role
	 * associated with the enclosing definition.
	 * 
	 * @return The located role, or null if the
	 * 			enclosing definition is not located
	 */
	protected Role getLocatedRole() {
		Role ret=null;
		
		if (getParent() instanceof Activity) {
			ret = ((Activity)getParent()).getLocatedRole();
		}
		
		return(ret);
	}

	/**
	 * This method returns the definition in which this
	 * activity is contained.
	 * 
	 * @return The definition, or null if not found
	 */
	public Definition getEnclosingDefinition() {
		Definition ret=null;
		
		if (getParent() instanceof Activity) {
			ret = ((Activity)getParent()).getEnclosingDefinition();
		}
		
		return(ret);
	}
	
	/**
	 * This method indicates whether the activity
	 * is a conditional construct.
	 * 
	 * @return Whether the activity is conditional
	 */
	public boolean isConditional() {
		return(false);
	}
	
	/**
	 * This method returns whether the activity represents
	 * a scope.
	 * 
	 * @return Whether activity represents a scope
	 */
	public boolean isScope() {
		return(false);
	}
}
