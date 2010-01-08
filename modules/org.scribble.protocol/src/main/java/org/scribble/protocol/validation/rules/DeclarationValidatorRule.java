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

import org.scribble.core.logger.ScribbleLogger;
import org.scribble.protocol.model.Block;
import org.scribble.protocol.model.Declaration;
import org.scribble.protocol.model.Definition;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.validation.ComponentValidatorRule;

/**
 * This class provides the validation rule for the Declaration
 * model component.
 *
 */
public class DeclarationValidatorRule implements ComponentValidatorRule {

	private static final Logger _log=Logger.getLogger(DeclarationValidatorRule.class.getName());
	
	/**
	 * This method returns the class being validated.
	 * 
	 * @return The class being validated
	 */
	public Class<? extends ModelObject> getValidatedClass() {
		return(org.scribble.protocol.model.Declaration.class);
	}
	
	/**
	 * This method validates the supplied model object.
	 * 
	 * @param obj The model object being validated
	 * @param logger The logger
	 */
	public void validate(ModelObject obj,
					ScribbleLogger logger) {
		Declaration elem=(Declaration)obj;
		
		// Check name has been defined
		if (elem.getName() != null &&
				elem.getName().trim().length() >= 0) {
		
			// Check all grouping constructs containing the
			// declaration, to ensure that only one declaration
			// exists with this name, in the scope
			Block b=null;
			ModelObject cur=elem;
			
			if (cur.getParent() instanceof Block) {
				b = (Block)elem.getParent();
			}
			
			while (cur != null && b != null) {
				int index=b.getContents().indexOf(cur);
				
				if (index == -1) {
					_log.warning("Declaration not part of parent block");
					cur = null;
				} else {
					for (int i=0; i < index; i++) {
						ModelObject mo=b.getContents().get(i);
						
						if (mo instanceof Declaration) {
							Declaration decl=(Declaration)mo;
							
							if (decl.getName() != null &&
									elem.getName().equals(decl.getName())) {
								logger.error(org.scribble.core.util.MessageUtil.format(
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
						cur = b.getParent();
						
						// Only continue if the current object
						// is not a definition, and it is contained
						// inside a block
						if ((cur instanceof Definition) == false &&
								cur.getParent() instanceof Block) {
							b = (Block)cur.getParent();
						} else {
							b = null;
						}
					}
				}
			}
		}
	}
}
