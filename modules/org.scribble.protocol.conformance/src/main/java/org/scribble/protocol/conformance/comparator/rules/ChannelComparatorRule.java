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
package org.scribble.protocol.conformance.comparator.rules;

import org.scribble.common.logging.Journal;
import org.scribble.protocol.conformance.comparator.ComparatorContext;
import org.scribble.protocol.model.Channel;
import org.scribble.protocol.model.ModelObject;

/**
 * This class provides the Channel comparator rule.
 */
public class ChannelComparatorRule implements ComparatorRule {

	/**
	 * This method determines whether the comparison rule is
	 * associated with the supplied type.
	 * 
	 * @param obj The object to check
	 * @return Whether the object is of a type supported by the
	 * 					comparison rule
	 */
	public boolean isTypeSupported(ModelObject obj) {
		return(obj instanceof Channel);
	}
	
	/**
	 * This method determines whether the comparison rule is
	 * appropriate for the supplied model objects.
	 * 
	 * @param main The main model object to be compared
	 * @param ref The reference model object to be compared against
	 * @return Whether the rule is relevant for the
	 * 				model objects
	 */
	public boolean isComparisonSupported(ModelObject main, ModelObject ref) {
		return(main instanceof Channel &&
				ref instanceof Channel);
	}
	
	/**
	 * This method compares a model object against a reference
	 * component to determine if they are equal.
	 * 
	 * @param context The context
	 * @param main The main model object
	 * @param reference The reference model object
	 * @param l The model listener
	 * @param deep Perform a deep compare
	 * @return Whether the model objects are comparable
	 */
	public boolean compare(ComparatorContext context, ModelObject main,
					ModelObject reference, Journal l, boolean deep) {
		boolean ret=false;
		Channel mainch=(Channel)main;
		Channel refch=(Channel)reference;

		String mainName=context.getMainName(mainch.getName());
		String refName=context.getReferenceName(refch.getName());
		
		ret = mainName.equals(refName);
		
		if (ret == false) {
			String mesg=org.scribble.common.util.MessageUtil.format(
					java.util.PropertyResourceBundle.getBundle(
					"org.scribble.protocol.conformance.Messages").
					getString("_CHANNEL_MISMATCH"),
							new String[]{mainName, refName});
			
			l.error(mesg, null); //new ModelIssue(main, mesg));
		}

		return(ret);
	}
	
}
