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

import org.scribble.protocol.model.Behaviour;
import org.scribble.protocol.model.Block;
import org.scribble.protocol.model.SinglePathBehaviour;

public class TestSinglePathBehaviour extends Behaviour
				implements SinglePathBehaviour {

	private static final long serialVersionUID = 8373816924012770668L;
	
	public TestSinglePathBehaviour() {
		m_block.setParent(this);
	}
	
	public Block getBlock() {
		return(m_block);
	}

	public void setBlock(Block block) {
		if (m_block != null) {
			m_block.setParent(null);
		}
		
		m_block = block;
		
		if (m_block != null) {
			m_block.setParent(this);
		}
	}

	@Override
	public boolean isRepetition() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean isStrictScope() {
		// TODO Auto-generated method stub
		return false;
	}

	private Block m_block=new Block();

	@Override
	public void visit(Visitor visitor) {
		// TODO Auto-generated method stub
		
	}
}
