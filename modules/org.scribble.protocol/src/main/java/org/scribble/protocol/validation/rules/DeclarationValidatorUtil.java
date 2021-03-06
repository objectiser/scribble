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
package org.scribble.protocol.validation.rules;

import java.util.logging.Logger;

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.Activity;
import org.scribble.protocol.model.Block;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.Protocol;
import org.scribble.protocol.validation.ProtocolComponentValidatorRule;

/**
 * This class provides the validation util for the Declaration
 * model component.
 *
 */
public class DeclarationValidatorUtil {

	private static final Logger _log=Logger.getLogger(DeclarationValidatorUtil.class.getName());
	
	/**
	 * This method validates the supplied model object.
	 * 
	 * @param obj The model object being validated
	 * @param logger The logger
	 */
	public static void validate(ModelObject obj,
					Journal logger) {		
		
		/*
		 * TODO: Change implementation so that the enclosing protocol is 
		 * processed to build up the declarations within the scope,
		 * and when the current declaration is checked, it should see
		 * whether the name is already in the declarations.
		 *
		
		// Check name has been defined
		if (elem.getName() != null &&
				elem.getName().trim().length() >= 0) {
		
			// Check all grouping constructs containing the
			// declaration, to ensure that only one declaration
			// exists with this name, in the scope
			Block b=null;
			Activity cur=elem;
			
			if (cur.getParent() instanceof Block) {
				b = (Block)elem.getParent();
			}
			
			while (cur != null && b != null) {
				int index=b.indexOf(cur);
				
				if (index == -1) {
					_log.warning("Declaration not part of parent block");
					cur = null;
				} else {
					for (int i=0; i < index; i++) {
						ModelObject mo=b.get(i);
						
						if (mo instanceof Declaration) {
							Declaration decl=(Declaration)mo;
							
							if (decl.getName() != null &&
									elem.getName().equals(decl.getName())) {
								logger.error(org.scribble.common.util.MessageUtil.format(
										java.util.PropertyResourceBundle.getBundle(
										"org.scribble.protocol.validation.rules.Messages"),
											"_EXISTING_DECLARATION",
											new String[]{elem.getName()}),
											obj.getProperties());
								
								// Quit loop
								cur = null;
							}
						}
					}
					
					if (cur != null) {
						
						if (b.getParent() instanceof Activity) {
							cur = (Activity)b.getParent();
							
							// Only continue if the current object
							// is not a definition, and it is contained
							// inside a block
							if ((cur instanceof Protocol) == false &&
									cur.getParent() instanceof Block) {
								b = (Block)cur.getParent();
							} else {
								b = null;
							}
						} else {
							b = null;
							cur = null;
						}
					}
				}
			}
		}
		*/
	}
}
