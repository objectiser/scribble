/*
 * Copyright 2009-10 Scribble.org
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
 * a when block associated with a choice.
 * 
 */
public class WhenBlock extends Block {

	private static final long serialVersionUID = -7404079936756441609L;

	/**
	 * This class returns the message signature.
	 * 
	 * @return The message signature
	 */
	public MessageSignature getMessageSignature() {
		return(m_messageSignature);
	}
	
	/**
	 * This method sets the message signature.
	 * 
	 * @param signature The message signature
	 */
	public void setMessageSignature(MessageSignature signature) {
		
		if (m_messageSignature != null) {
			m_messageSignature.setParent(null);
		}
		
		m_messageSignature = signature;
		
		if (m_messageSignature != null) {
			m_messageSignature.setParent(this);
		}
	}
	
	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		
		if (visitor.startWhenBlock(this)) {
		
			if (getMessageSignature() != null) {
				getMessageSignature().visit(visitor);
			}
			
			for (Activity act : getContents()) {
				act.visit(visitor);
			}
		}
		
		visitor.endWhenBlock(this);
	}
	
	private MessageSignature m_messageSignature=null;
}
