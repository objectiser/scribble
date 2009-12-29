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
package org.scribble.conformance;

import org.scribble.core.model.*;

/**
 * This class provides an iterator implementation over a behaviour list.
 */
public class BehaviourListIterator implements java.util.Iterator<Behaviour> {

	public BehaviourListIterator(BehaviourList list) {
		m_list = list;
	}
	
	protected BehaviourListIterator(BehaviourListIterator iter) {
		m_list = iter.m_list;
		m_index = iter.m_index;
		if (iter.m_subList != null) {
			m_subList = iter.m_subList.snapshot();
		}
	}
	
	public BehaviourListIterator snapshot() {
		return(new BehaviourListIterator(this));
	}
	
	public boolean hasNext() {
		boolean ret=false;
		
		if (m_index < m_list.getBehaviourList().size() ||
				(m_subList != null && m_subList.hasNext())) {
			ret = true;
		}
		
		return(ret);
	}

	public Behaviour next() {
		Behaviour ret=null;
		boolean f_end=false;
		
		do {
			if (m_subList != null) {
				
				// Check if sublist has next
				if (m_subList.hasNext()) {
					ret = m_subList.next();
				} else {
					// Clear the list
					m_subList = null;
				}
			} else if (m_index < m_list.getBehaviourList().size()) {
				
				ret = m_list.getBehaviourList().get(m_index++);
				
				if (ret instanceof BehaviourList) {
					m_subList = ((BehaviourList)ret).getIterator();
					ret = null;
				}
			} else {
				f_end = true;
			}
		} while(ret == null && f_end == false);
		
		return(ret);
	}

	public void remove() {
	}
	
	public ModelReference getComposedSource() {
		ModelReference ret=null;
		
		if (m_subList != null) {
			ret = m_subList.getComposedSource();
		} else if (m_list.getModelInclude() != null) {
			ret = m_list.getModelInclude().getReference();
		}
		
		return(ret);
	}
	
	public ModelInclude getModelInclude() {
		ModelInclude ret=m_list.getModelInclude();
		
		if (ret == null && m_subList != null) {
			ret = m_subList.getModelInclude();
		}
		
		return(ret);
	}
	
	public NameMap getNameMap() {
		NameMap ret=m_list;
		
		if (m_subList != null) {
			ret = m_subList.getNameMap();
		}
		
		return(ret);
	}

	private BehaviourList m_list=null;
	private int m_index=0;
	private BehaviourListIterator m_subList=null;
}
