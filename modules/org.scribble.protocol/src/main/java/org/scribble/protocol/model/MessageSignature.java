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
 * This class represents a message signature.
 */
public class MessageSignature extends ModelObject {

	private static final long serialVersionUID = 8952431919812954593L;

	/**
	 * The default constructor.
	 */
	public MessageSignature() {
	}
	
	/**
	 * This method returns the optional operation.
	 * 
	 * @return The optional operation
	 */
	public String getOperation() {
		return(m_operation);
	}
	
	/**
	 * This method sets the operation.
	 * 
	 * @param operation The operation
	 */
	public void setOperation(String operation) {
		m_operation = operation;
	}
	
	// TODO: Need to think about actual type for this list
	// See how JDT DOM handles local details (e.g. short
	// name) versus fully qualified name identifying the
	// actual type. Issue also is if the model can be
	// updated, what happens to the fully qualified name?
	/**
	 * This method returns the list of type references. If
	 * no operation is defined, then only one type reference
	 * should be defined.
	 * 
	 * @return The list of type references
	 */
	@Reference(containment=true)
	public java.util.List<TypeReference> getTypes() {
		return(m_types);
	}
	
	public String toString() {
		String ret=getOperation();
		
		if (getOperation() != null &&
					getOperation().trim().length() > 0) {
			ret += "(";
		}
		
		for (int i=0; i < m_types.size(); i++) {
			if (i > 0) {
				ret += ",";
			}
			ret += m_types.get(i).getName();	
		}
		
		if (getOperation() != null &&
				getOperation().trim().length() > 0) {
			ret += ")";
		}
		
		if (ret == null) {
			ret = "<No Signature>";
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
		visitor.visitMessageSignature(this);
		
		for (TypeReference tref : getTypes()) {
			tref.visit(visitor);
		}
	}

	private String m_operation=null;
	private java.util.List<TypeReference> m_types=
			new ContainmentList<TypeReference>(this, TypeReference.class);
}
