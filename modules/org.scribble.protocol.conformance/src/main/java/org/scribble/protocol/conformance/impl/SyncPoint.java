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
package org.scribble.protocol.conformance.impl;

import org.scribble.protocol.model.Behaviour;

public class SyncPoint {

	public SyncPoint(Behaviour main, Behaviour ref,
			BehaviourListIterator mainiter,
			BehaviourListIterator refiter,
			int mainCount, int refCount) {
		m_main = main;
		m_reference = ref;
		m_mainIterator = mainiter;
		m_referenceIterator = refiter;
		m_mainCount = mainCount;
		m_referenceCount = refCount;
	}
	
	public Behaviour getMain() {
		return(m_main);
	}
	
	public Behaviour getReference() {
		return(m_reference);
	}
	
	public BehaviourListIterator getMainIterator() {
		return(m_mainIterator);
	}
	
	public BehaviourListIterator getReferenceIterator() {
		return(m_referenceIterator);
	}
	
	public int getMainCount() {
		return(m_mainCount);
	}
	
	public int getReferenceCount() {
		return(m_referenceCount);
	}
	
	public int getTotal() {
		return(m_mainCount+m_referenceCount);
	}
	
	public boolean isGap() {
		return(getTotal() > 0);
	}
	
	private Behaviour m_main=null;
	private Behaviour m_reference=null;
	private BehaviourListIterator m_mainIterator=null;
	private BehaviourListIterator m_referenceIterator=null;
	private int m_mainCount=0;
	private int m_referenceCount=0;
}
