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
 * This class provides a default visitor which can be used
 * to traverse a model.
 */
public class DefaultVisitor implements Visitor {
	
	private boolean m_defaultGroupReturn=true;
	
	public DefaultVisitor() {
	}
	
	public DefaultVisitor(boolean defaultGroupReturn) {
		m_defaultGroupReturn = defaultGroupReturn;
	}

	public void setDefaultGroupReturn(boolean b) {
		m_defaultGroupReturn = b;
	}
	
	/**
	 * This method indicates the start of a
	 * block.
	 * 
	 * @param elem The block
	 * @return Whether to process the contents
	 */
	public boolean startBlock(Block elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * block.
	 * 
	 * @param elem The block
	 */
	public void endBlock(Block elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * block.
	 * 
	 * @param elem The catch block
	 * @return Whether to process the contents
	 */
	public boolean startCatchBlock(CatchBlock elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * block.
	 * 
	 * @param elem The catch block
	 */
	public void endCatchBlock(CatchBlock elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * block.
	 * 
	 * @param elem The interrupt block
	 * @return Whether to process the contents
	 */
	public boolean startInterruptBlock(InterruptBlock elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * block.
	 * 
	 * @param elem The interrupt block
	 */
	public void endInterruptBlock(InterruptBlock elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * block.
	 * 
	 * @param elem The when block
	 * @return Whether to process the contents
	 */
	public boolean startWhenBlock(WhenBlock elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * block.
	 * 
	 * @param elem The when block
	 */
	public void endWhenBlock(WhenBlock elem) {
	}
	
	/**
	 * This method visits a namespace component.
	 * 
	 * @param elem The namespace
	 */
	public void visitNamespace(Namespace elem) {
	}
	
	/**
	 * This method visits an import component.
	 * 
	 * @param elem The import
	 */
	public void visitImport(Import elem) {
	}
	
	/**
	 * This method visits a participant component.
	 * 
	 * @param elem The participant
	 */
	public void visitParticipant(Participant elem) {
	}
	
	/**
	 * This method visits an interaction component.
	 * 
	 * @param elem The interaction
	 */
	public void visitInteraction(Interaction elem) {
	}
	
	/**
	 * This method visits a located name component.
	 * 
	 * @param elem The located name
	 */
	public void visitLocatedName(LocatedName elem) {
	}
	
	/**
	 * This method visits a message signature component.
	 * 
	 * @param elem The message signature
	 */
	public void visitMessageSignature(MessageSignature elem) {
	}
	
	/**
	 * This method visits a model reference component.
	 * 
	 * @param elem The model reference
	 */
	public void visitModelReference(ModelReference elem) {
	}
	
	/**
	 * This method visits a raise component.
	 * 
	 * @param elem The raise
	 */
	public void visitRaise(Raise elem) {
	}
	
	/**
	 * This method visits a declaration binding component.
	 * 
	 * @param elem The declaration binding
	 */
	public void visitDeclarationBinding(DeclarationBinding elem) {
	}
	
	/**
	 * This method visits a run component.
	 * 
	 * @param elem The run
	 */
	public void visitRun(Run elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * protocol.
	 * 
	 * @param elem The protocol
	 * @return Whether to process the contents
	 */
	public boolean startProtocol(Protocol elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * protocol.
	 * 
	 * @param elem The protocol
	 */
	public void endProtocol(Protocol elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * choice.
	 * 
	 * @param elem The choice
	 * @return Whether to process the contents
	 */
	public boolean startChoice(Choice elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * choice.
	 * 
	 * @param elem The choice
	 */
	public void endChoice(Choice elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * parallel.
	 * 
	 * @param elem The parallel
	 * @return Whether to process the contents
	 */
	public boolean startParallel(Parallel elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * parallel.
	 * 
	 * @param elem The parallel
	 */
	public void endParallel(Parallel elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * participant list.
	 * 
	 * @param elem The participant list
	 * @return Whether to process the contents
	 */
	public boolean startParticipantList(ParticipantList elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * participant list.
	 * 
	 * @param elem The participant list
	 */
	public void endParticipantList(ParticipantList elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * repeat.
	 * 
	 * @param elem The repeat
	 * @return Whether to process the contents
	 */
	public boolean startRepeat(Repeat elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * repeat.
	 * 
	 * @param elem The repeat
	 */
	public void endRepeat(Repeat elem) {
	}
	
	/**
	 * This method indicates the start of an
	 * Optional construct.
	 * 
	 * @param elem The Optional construct
	 * @return Whether to process the contents
	 */
	public boolean startOptional(Optional elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of an
	 * Optional construct.
	 * 
	 * @param elem The Optional construct
	 */
	public void endOptional(Optional elem) {
	}
	
	/**
	 * This method indicates the start of a
	 * try escape.
	 * 
	 * @param elem The try escape
	 * @return Whether to process the contents
	 */
	public boolean startTryEscape(TryEscape elem) {
		return(m_defaultGroupReturn);
	}
	
	/**
	 * This method indicates the end of a
	 * try escape.
	 * 
	 * @param elem The try escape
	 */
	public void endTryEscape(TryEscape elem) {
	}
	
}
