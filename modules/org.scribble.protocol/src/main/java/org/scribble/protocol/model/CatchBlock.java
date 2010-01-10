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
 * This class represents a group of activities within
 * a catch specific 'escape' block of a
 * try/escape construct.
 * 
 */
public class CatchBlock extends EscapeBlock {

	private static final long serialVersionUID = 775444118561357992L;

	/**
	 * This method returns the reference of the type associated
	 * with the catch block.
	 * 
	 * @return The type
	 */
	public TypeReference getType() {
		return(m_type);
	}
	
	/**
	 * This method sets the reference of the type associated
	 * with the catch block.
	 * 
	 * @param type The type
	 */
	public void setType(TypeReference type) {
		
		if (m_type != null) {
			m_type.setParent(null);
		}
		
		m_type = type;
		
		if (m_type != null) {
			m_type.setParent(this);
		}
	}
	
	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		
		if (visitor.startCatchBlock(this)) {
		
			for (Participant p : getParticipants()) {
				p.visit(visitor);
			}
			
			if (getType() != null) {
				getType().visit(visitor);
			}
			
			for (Activity act : getContents()) {
				act.visit(visitor);
			}
		}
		
		visitor.endCatchBlock(this);
	}
	
	private TypeReference m_type=null;
}
