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

import org.scribble.core.logger.ScribbleLogger;
import org.scribble.core.model.*;

/**
 * This interface represents the comparator context.
 */
public interface ComparatorContext {
	
	/**
	 * This method determines if the supplied model object has
	 * any comparison rules associated with it.
	 * 
	 * @param obj The model object
	 * @return Whether the type is supported by any comparator rules
	 */
	public boolean isTypeSupported(ModelObject obj);
	
	/**
	 * This method determines whether a comparison rule exists that is
	 * appropriate for the supplied model objects.
	 * 
	 * @param main The main model object to be compared
	 * @param ref The reference model object to be compared against
	 * @return Whether a rule is relevant for the
	 * 				model objects
	 */
	public boolean isComparisonSupported(ModelObject main, ModelObject ref);

	/**
	 * This method compares a main model object against a reference
	 * model object, to determine if the main model object is equivalent.
	 * If not, then it reports differences to the model
	 * listener.
	 * 
	 * @param main The model object being checked
	 * @param reference The reference model object
	 * @param l The logger
	 * @param deep Perform a deep compare
	 * @return Whether the model objects are comparable
	 */
	public boolean compare(ModelObject main, ModelObject reference,
						ScribbleLogger l, boolean deep);
	
	/**
	 * This method pushes the main name map on to the stack.
	 * 
	 * @param map The main name map
	 */
	public void pushMainNameMap(NameMap map);
	
	/**
	 * This method pushes the reference name map on to the stack.
	 * 
	 * @param map The reference name map
	 */
	public void pushReferenceNameMap(NameMap map);

	/**
	 * This method returns the main name that has been mapped
	 * to the supplied name. If the supplied name has
	 * no mapping, then the same main name will be returned.
	 * 
	 * @param name The current main name
	 * @return The original main name
	 */
	public String getMainName(String name);
	
	/**
	 * This method returns the reference name that has been mapped
	 * to the supplied name. If the supplied name has
	 * no mapping, then the same reference name will be returned.
	 * 
	 * @param name The current reference name
	 * @return The original reference name
	 */
	public String getReferenceName(String name);
	
	/**
	 * This method pops the main name map from the stack.
	 * 
	 * @return The main name map
	 */
	public NameMap popMainNameMap();
	
	/**
	 * This method pops the reference name map from the stack.
	 * 
	 * @return The reference name map
	 */
	public NameMap popReferenceNameMap();

}
