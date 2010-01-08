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

import java.util.logging.Level;
import java.util.logging.Logger;

import org.scribble.common.logger.ScribbleLogger;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.ModelReference;

/**
 * This class represents the default comparator context.
 */
public class DefaultComparatorContext implements ComparatorContext {

	/**
	 * This is the default constructor for the comparator context.
	 * 
	 * @param mainSource The main model source reference
	 * @param refSource The reference model source reference
	 */
	public DefaultComparatorContext(ModelReference mainSource,
						ModelReference refSource) {
		m_mainSource = mainSource;
		m_refSource = refSource;
		
		m_rules.add(new BehaviourListComparatorRule());
		m_rules.add(new BlockComparatorRule());
		m_rules.add(new ChannelComparatorRule());
		m_rules.add(new InteractionComparatorRule());
		m_rules.add(new MessageSignatureComparatorRule());
		m_rules.add(new ParticipantComparatorRule());
		m_rules.add(new TypeReferenceComparatorRule());
	}
	
	/**
	 * This method returns the model reference associated with
	 * the source for the main description being compared
	 * against.
	 * 
	 * @return The main source
	 */
	public ModelReference getMainSource() {
		return(m_mainSource);
	}
	
	/**
	 * This method returns the model reference associated with
	 * the source for the reference description being compared
	 * against.
	 * 
	 * @return The reference source
	 */
	public ModelReference getReferenceSource() {
		return(m_refSource);
	}
	
	/**
	 * This method determines if the supplied model object has
	 * any comparison rules associated with it.
	 * 
	 * @param obj The model object
	 * @return Whether the type is supported by any comparator rules
	 */
	public boolean isTypeSupported(ModelObject obj) {
		boolean ret=false;
		
		for (int i=0; ret == false && i < getRules().size(); i++) {
			ret = getRules().get(i).isTypeSupported(obj);
		}

		return(ret);
	}
	
	/**
	 * This method determines whether a comparison rule exists that is
	 * appropriate for the supplied model objects.
	 * 
	 * @param main The main model object to be compared
	 * @param ref The reference model object to be compared against
	 * @return Whether a rule is relevant for the
	 * 				model objects
	 */
	public boolean isComparisonSupported(ModelObject main, ModelObject ref) {
		boolean ret=false;
		
		for (int i=0; ret == false && i < getRules().size(); i++) {
			ret = getRules().get(i).isComparisonSupported(main, ref);
		}

		return(ret);
	}

	/**
	 * This method compares a main model object against a reference
	 * model object, to determine if the main model object is equivalent.
	 * If not, then it reports differences to the model
	 * listener.
	 * 
	 * @param main The model object being checked
	 * @param reference The reference model object
	 * @param l The model listener
	 * @param deep Perform a deep compare
	 * @return Whether the model objects are comparable
	 */
	public boolean compare(ModelObject main, ModelObject reference,
						ScribbleLogger l, boolean deep) {
		boolean ret=true;
		boolean f_ruleApplied=false;
		
		if (logger.isLoggable(Level.FINEST)) {
			logger.finest("Compare: main="+main+" reference="+reference);
		}
		
		// Add rules appropriate for the comparison must return true,
		// for the compare to return true
		for (int i=0; ret == true && i < getRules().size(); i++) {
			if (getRules().get(i).isComparisonSupported(main, reference)) {
				f_ruleApplied = true;
				
				if (logger.isLoggable(Level.FINEST)) {
					logger.finest("Invoking rule: "+getRules().get(i));
				}
				
				ret = getRules().get(i).compare(this, main,
							reference, l, deep);
				
				if (logger.isLoggable(Level.FINEST)) {
					logger.finest("Result from rule: "+getRules().get(i)+" = "+ret);
				}
			}
		}

		// GPB (10/11/08) have removed check for main being behaviour
		// as compare should only be called on behaviours now - but
		// was not checking 'block'
		if (f_ruleApplied == false /*&& main instanceof Behaviour*/) {
			
			// TODO:
			// Record reference object location and
			// start/end position with error, so they
			// can be navigated
			String mesg=org.scribble.common.util.MessageUtil.format(
					java.util.PropertyResourceBundle.getBundle(
					"org.scribble.protocol.conformance.Messages").
					getString("_UNEXPECTED_BEHAVIOUR"), null);
			
			/*
			ModelIssue issue=new ModelIssue(main, mesg);
			
			issue.getResolutions().add(new DeleteFromModel());
			
			l.error(issue);
			*/
			
			java.util.Map<String, java.io.Serializable> props=
				new java.util.HashMap<String, java.io.Serializable>();
			
			l.error(mesg, props);
			
			ret = false;
		}
		
		return(ret);
	}
		
	/**
	 * This method returns a list of comparison rules.
	 * 
	 * @return The list of comparison rules
	 */
	public java.util.List<ComparatorRule> getRules() {
		return(m_rules);
	}
	
	/**
	 * This method pushes the main name map on to the stack.
	 * 
	 * @param map The main name map
	 */
	public void pushMainNameMap(NameMap map) {
		m_mainNameMapStack.add(0, map);
	}
	
	/**
	 * This method pushes the reference name map on to the stack.
	 * 
	 * @param map The reference name map
	 */
	public void pushReferenceNameMap(NameMap map) {
		m_referenceNameMapStack.add(0, map);
	}

	/**
	 * This method returns the main name that has been mapped
	 * to the supplied name. If the supplied name has
	 * no mapping, then the same main name will be returned.
	 * 
	 * @param name The current main name
	 * @return The original main name
	 */
	public String getMainName(String name) {
		String ret=name;
		
		for (int i=0; i < m_mainNameMapStack.size(); i++) {
			NameMap map=m_mainNameMapStack.get(i);
			ret = map.getName(name);
		}
		
		return(ret);
	}
	
	/**
	 * This method returns the reference name that has been mapped
	 * to the supplied name. If the supplied name has
	 * no mapping, then the same reference name will be returned.
	 * 
	 * @param name The current reference name
	 * @return The original reference name
	 */
	public String getReferenceName(String name) {
		String ret=name;
		
		for (int i=0; i < m_referenceNameMapStack.size(); i++) {
			NameMap map=m_referenceNameMapStack.get(i);
			ret = map.getName(name);
		}
		
		return(ret);
	}
	
	/**
	 * This method pops the main name map from the stack.
	 * 
	 * @return The main name map
	 */
	public NameMap popMainNameMap() {
		NameMap ret=null;
		
		if (m_mainNameMapStack.size() > 0) {
			ret = m_mainNameMapStack.remove(0);
		}
		
		return(ret);
	}
	
	/**
	 * This method pops the reference name map from the stack.
	 * 
	 * @return The reference name map
	 */
	public NameMap popReferenceNameMap() {
		NameMap ret=null;
		
		if (m_referenceNameMapStack.size() > 0) {
			ret = m_referenceNameMapStack.remove(0);
		}
		
		return(ret);
	}
	
	private static Logger logger = Logger.getLogger("org.scribble.comparator");

	private ModelReference m_mainSource=null;
	private ModelReference m_refSource=null;
	private java.util.List<ComparatorRule> m_rules=new java.util.Vector<ComparatorRule>();
	private java.util.List<NameMap> m_mainNameMapStack=new java.util.Vector<NameMap>();
	private java.util.List<NameMap> m_referenceNameMapStack=new java.util.Vector<NameMap>();
}
