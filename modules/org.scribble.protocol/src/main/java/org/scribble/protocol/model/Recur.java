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
 * This class represents the Recur construct.
 * 
 */
public class Recur extends Behaviour {

	private static final long serialVersionUID = -9113642656716684751L;

	/**
	 * This is the default constructor.
	 * 
	 */
	public Recur() {
	}
	
	/**
	 * This method returns the recursion block to be
	 * performed.
	 * 
	 * @return The recursion block
	 */
	public Recursion getRecursionBlock() {
		return(m_recursionBlock);
	}
	
	/**
	 * This method sets the recursion block to be
	 * performed.
	 * 
	 * @param recursion The recursion block
	 */
	public void setRecursionBlock(Recursion recursion) {
		m_recursionBlock = recursion;
	}

	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		visitor.visitRecur(this);
	}

	private Recursion m_recursionBlock=null;
}
