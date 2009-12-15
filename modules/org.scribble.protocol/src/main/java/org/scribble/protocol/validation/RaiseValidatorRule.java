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
package org.scribble.protocol.validation;

import org.scribble.core.model.*;
import org.scribble.core.validation.ComponentValidatorRule;
import org.scribble.protocol.model.*;
import org.scribble.core.logger.ScribbleLogger;

public class RaiseValidatorRule implements ComponentValidatorRule {

	public Class<? extends ModelObject> getValidatedClass() {
		return(org.scribble.protocol.model.Raise.class);
	}
	
	public void validate(ModelObject obj,
					ScribbleLogger logger) {
		Raise elem=(Raise)obj;

		if (elem.getType() != null) {
			boolean f_found=false;
			
			// Check that a 'catch' for this type
			ModelObject act=elem.getParent();
			
			while (f_found == false && act != null &&
					(act instanceof Definition) == false) {
			
				if (act instanceof TryEscape) {
					TryEscape te=(TryEscape)act;
					
					for (int i=0; f_found == false &&
							i < te.getEscapeBlocks().size(); i++) {
						
						if (te.getEscapeBlocks().get(i) instanceof CatchBlock &&
								elem.getType().equals(((CatchBlock)
									te.getEscapeBlocks().get(i)).getType())) {
							f_found = true;
						}
					}
				}
				
				act = act.getParent();
			}
			
			if (f_found == false) {
				logger.error(org.scribble.core.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.Messages"),
							"_RAISED_TYPE_NOT_CAUGHT",
							new String[]{elem.getType().toString()}),
							obj.getProperties());
			}
		}
	}
}
