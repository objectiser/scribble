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
package org.scribble.protocol.projection.impl;

import java.util.logging.Logger;

/**
 * This class represents information associated with a scope.
 */
public class Scope {

	/**
	 * The default constructor for the scope.
	 */
	public Scope() {
		// Initialize the state
		pushState();
	}
	
	/**
	 * This is the copy constructor for the scope.
	 * 
	 * @param copy The copy
	 */
	public Scope(Scope copy) {
		m_locatedParticipant = copy.m_locatedParticipant;
		
		for (int i=0; i < copy.m_stateStack.size(); i++) {
			java.util.Map<String,Object> current=copy.m_stateStack.get(i);
			
			java.util.Map<String,Object> copyStackEntry=
						new java.util.Hashtable<String,Object>();
		
			java.util.Iterator<String> iter=current.keySet().iterator();
			
			while (iter.hasNext()) {
				String key=iter.next();
				Object value=current.get(key);
				
				copyStackEntry.put(key, value);
			}
			
			m_stateStack.add(copyStackEntry);
		}
	}
	
	/**
	 * This method returns the named state from the current
	 * scope.
	 * 
	 * @param name The state name
	 * @return The state value, or null if not found
	 */
	public Object getState(String name) {
		Object ret=null;
		
		if (m_stateStack.size() > 0) {
			for (int i=0; ret == null && i < m_stateStack.size(); i++) {
				java.util.Map<String,Object> current=m_stateStack.get(i);
				ret = current.get(name);
			}
		}
		
		return(ret);
	}
	
	/**
	 * This method sets the value associated with the supplied
	 * name in the current state scope.
	 * 
	 * @param name The state name
	 * @param value The state value
	 */
	public void setState(String name, Object value) {
		if (m_stateStack.size() > 0) {
			java.util.Map<String,Object> current=m_stateStack.get(0);
			current.put(name, value);
		}
	}

	/**
	 * This method returns the located participant associated with the
	 * current scope.
	 * 
	 * @return The located participant
	 */
	public String getLocatedParticipant() {
		return(m_locatedParticipant);
	}
	
	/**
	 * This method sets the located participant associated with the
	 * current scope.
	 * 
	 * @param located The located participant
	 */
	public void setLocatedParticipant(String located) {
		m_locatedParticipant = located;
	}

	/**
	 * This method pushes the current state onto a stack.
	 */
	public void pushState() {
		m_stateStack.add(0, new java.util.Hashtable<String,Object>());
	}
	
	/**
	 * This method pops the current state from the stack.
	 */
	public void popState() {
		if (m_stateStack.size() > 0) {
			m_stateStack.remove(0);
		} else {
			logger.severe("No state entry to pop from stack");
		}
	}
	
	/**
	 * This method returns the properties associated with
	 * the scope.
	 * 
	 * @return The properties
	 */
	public java.util.Map<String,Object> getProperties() {
		return(m_properties);
	}
		
	private static Logger logger = Logger.getLogger("org.scribble.parser");

	private String m_locatedParticipant=null;
	private java.util.List<java.util.Map<String,Object>> m_stateStack=
		new java.util.Vector<java.util.Map<String,Object>>();
	private java.util.Map<String,Object> m_properties=new java.util.HashMap<String, Object>();
}
