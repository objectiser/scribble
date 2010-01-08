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
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.TypeReference;

/**
 * This class provides the TypeReference comparator rule.
 */
public class TypeReferenceComparatorRule implements ComparatorRule {

	/**
	 * This method determines whether the comparison rule is
	 * associated with the supplied type.
	 * 
	 * @param obj The object to check
	 * @return Whether the object is of a type supported by the
	 * 					comparison rule
	 */
	public boolean isTypeSupported(ModelObject obj) {
		return(obj instanceof TypeReference);
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
		return(main instanceof TypeReference &&
				ref instanceof TypeReference);
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
					ModelObject reference, ScribbleLogger l, boolean deep) {
		boolean ret=false;
		TypeReference mtr=(TypeReference)main;
		TypeReference rtr=(TypeReference)reference;
		
		if ((ret = mtr.equals(rtr)) == false) {
			String mesg=org.scribble.core.util.MessageUtil.format(
					java.util.PropertyResourceBundle.getBundle(
					"org.scribble.conformance.Messages").
					getString("_TYPE_MISMATCH"),
					new String[]{rtr.getName()});
			
			/*
			ModelIssue issue=new ModelIssue(mtr, mesg);
			
			UpdateFromReferencedDescription res=
					new UpdateFromReferencedDescription();
			res.setModel(context.getReferenceSource());
			res.setSourceReference(rtr.getSource());
			
			issue.getResolutions().add(res);
			
			l.error(issue);
			*/
			
			l.error(mesg, null);
		}
	
		return(ret);
	}
	
}
