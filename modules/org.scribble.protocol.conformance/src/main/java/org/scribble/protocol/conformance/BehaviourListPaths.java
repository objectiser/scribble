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
package org.scribble.protocol.conformance;

import org.scribble.protocol.model.Behaviour;
import org.scribble.protocol.model.Definition;
import org.scribble.protocol.model.MultiPathBehaviour;
import org.scribble.protocol.model.Participant;
import org.scribble.protocol.model.SinglePathBehaviour;

/**
 * This class represents the group of behaviour list paths
 * that need to be compared.
 */
public class BehaviourListPaths extends Behaviour {

	private static final long serialVersionUID = -1317457198564113028L;

	/**
	 * This constructor initializes the list of paths.
	 * 
	 * @param source The source behaviour
	 * @param lists The list of BehaviourList objects
	 */
	public BehaviourListPaths(Behaviour source,
					java.util.List<BehaviourList> lists) {
		m_source = source;
		m_lists = lists;
		
		derivedFrom(source);
	}
	
	/**
	 * This method returns the source behaviour associated
	 * with the multiple paths.
	 * 
	 * @return The source behaviour
	 */
	public Behaviour getSourceBehaviour() {
		return(m_source);
	}
	
	/**
	 * This method resets the index to the current path
	 * to the beginning.
	 */
	public void reset() {
		m_index = 0;
	}
	
	/**
	 * This method returns the next appropriate BehaviourList.
	 * 
	 * @return The BehaviourList
	 */
	public BehaviourList getNext() {
		BehaviourList ret=null;
		
		if (m_index < m_lists.size()) {
			ret = m_lists.get(m_index++);
		}
		
		return(ret);
	}
	
	/**
	 * This method determines whether there are more behaviour
	 * lists to return.
	 * 
	 * @return Whether more behaviour lists to process
	 */
	public boolean hasNext() {
		return(m_index < m_lists.size());
	}
	
	/**
	 * This method removes the handled behaviour list from
	 * the list, to mark it as handled, and sets the
	 * position to be the next list after the one that is
	 * handled.
	 * 
	 * @param list The list that has been handled
	 */
	public void handled(BehaviourList list) {
		if (m_lists.contains(list)) {
			m_index = m_lists.indexOf(list);
			m_lists.remove(list);
		}
	}
	
	/**
	 * This method determines whether this multipath
	 * construct is 'visible' in terms of conformance.
	 * 
	 * @return Whether the construct is visible (or
	 * 				observable)
	 */
	public boolean isVisible() {
		boolean ret=false;
		
		for (int i=0; ret == false && i < m_lists.size(); i++) {
			if (m_lists.get(i).getBehaviourList().size() > 0) {
				ret = true;
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method returns whether the behaviour is a grouping
	 * construct.
	 * 
	 * @return Whether the behaviour is a grouping construct 
	 */
	@Override
	public boolean isGroupingConstruct() {
		return(m_source.isGroupingConstruct());
	}
	
	/**
	 * This method indicates whether the activity
	 * is a conditional construct.
	 * 
	 * @return Whether the activity is conditional
	 */
	@Override
	public boolean isConditional() {
		return(m_source.isConditional());
	}
	
	/**
	 * This method returns whether the behaviour is a wait
	 * state.
	 * 
	 * @return Whether the behaviour is a wait state
	 */
	@Override
	public boolean isWaitState() {
		return(m_source.isWaitState());
	}
	
	/**
	 * This method returns the behaviour lists representing the
	 * paths associated with a single or multi-path construct.
	 * 
	 * @return The paths
	 */
	public java.util.List<BehaviourList> getPaths() {
		return(m_lists);
	}
	
	/**
	 * This method determines if the underlying grouping construct
	 * has strict scope.
	 * 
	 * @return Whether the behaviour represents a strict scope
	 */
	public boolean isStrictScope() {
		boolean ret=true;
		
		// Default is to be strict scope. Only change if
		// explicitly set to false by single or multiple path
		// behaviour
		if (m_source instanceof SinglePathBehaviour) {
			ret = ((SinglePathBehaviour)m_source).isStrictScope();
			
		} else if (m_source instanceof MultiPathBehaviour) {
			ret = ((MultiPathBehaviour)m_source).isStrictScope();
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the definition in which this
	 * activity is contained.
	 * 
	 * @return The definition, or null if not found
	 */
	@Override
	public Definition enclosingDefinition() {
		return(m_source.enclosingDefinition());
	}
	
	/**
	 * This method returns the list of participants that are
	 * responsible for initiating the activity. This can
	 * be used to determine whether the model is
	 * consistent in terms of decision makers subsequently
	 * initiating actions.
	 * 
	 * @return The list of initiator participants
	 */
	@Override
	public java.util.List<Participant> initiatorParticipants() {
		return(m_source.initiatorParticipants());
	}

	private Behaviour m_source=null;
	private java.util.List<BehaviourList> m_lists=null;
	private int m_index=0;
}
