/*
 * Copyright 2005-9 Pi4 Technologies Ltd
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
 *
 * Change History:
 * 26 Apr 2009 : Initial version created by gary
 */
package org.scribble.core.model;

/**
 * This class provides the path to a definition scope contained
 * within a top level definition.
 */
public class SubDefinitionPath {

	/**
	 * The default constructor.
	 */
	public SubDefinitionPath() {		
	}
	
	/**
	 * This constructor is initialised with the arrau of
	 * path elements.
	 * 
	 * @param elems The array of path elements
	 */
	public SubDefinitionPath(String[] elems) {
		for (int i=0; i < elems.length; i++) {
			m_path.add(elems[i]);
		}
	}
	
	/**
	 * The copy constructor.
	 * 
	 * @param sdp The path to copy
	 */
	public SubDefinitionPath(SubDefinitionPath sdp) {
		m_path.addAll(sdp.m_path);
	}
	
	/**
	 * This method adds an element to the end of the path.
	 * 
	 * @param elem The element
	 */
	public void addPathElement(String elem) {
		m_path.add(elem);
	}
	
	/**
	 * This method adds an element to the specified location
	 * within the path.
	 * 
	 * @param pos The position
	 * @param elem The element
	 */
	public void addPathElement(int pos, String elem) {
		m_path.add(pos, elem);
	}
	
	/**
	 * This method returns the element at the specified position
	 * within the path.
	 * 
	 * @param pos The position
	 * @return The element
	 */
	public String getPathElement(int pos) {
		return(m_path.get(pos));
	}
	
	/**
	 * This method removes an element from the specified location
	 * within the path.
	 * 
	 * @param pos The position
	 * @return The removed element
	 */
	public String removePathElement(int pos) {
		return(m_path.remove(pos));
	}
	
	/**
	 * This method returns the number of elements within the
	 * path.
	 * 
	 * @return The number of elements in the path
	 */
	public int getPathElementCount() {
		return(m_path.size());
	}
	
	/**
	 * This method clears the path associated with the sub
	 * definition.
	 */
	public void clear() {
		m_path.clear();
	}
	
	public boolean equals(Object other) {
		boolean ret=false;
		
		if (other instanceof SubDefinitionPath) {
			SubDefinitionPath sdp=(SubDefinitionPath)other;
			
			ret = (m_path.size() ==
				sdp.m_path.size());

			for (int i=0; ret && i < m_path.size(); i++) {
				ret = m_path.get(i).equals(
							sdp.m_path.get(i));
			}
		}
		
		return(ret);
	}
	
	public String toString() {
		String ret="";
		
		for (int i=0; i < m_path.size(); i++) {
			ret += "$"+m_path.get(i);
		}
		
		return(ret);
	}		
	
	private java.util.List<String> m_path=new java.util.Vector<String>();
}
