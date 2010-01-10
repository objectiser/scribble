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
 * an interrupt specific 'escape' block of a
 * try/escape construct.
 * 
 */
public class InterruptBlock extends EscapeBlock {

	private static final long serialVersionUID = -5384952960277487512L;

	/**
	 * This method visits the model object using the supplied
	 * visitor.
	 * 
	 * @param visitor The visitor
	 */
	public void visit(Visitor visitor) {
		
		if (visitor.startInterruptBlock(this)) {
		
			for (int i=0; i < getContents().size(); i++) {
				getContents().get(i).visit(visitor);
			}
		}
		
		visitor.endInterruptBlock(this);
	}
	
}
