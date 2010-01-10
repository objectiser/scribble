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

import org.scribble.protocol.model.*;
import org.scribble.protocol.validation.ProtocolComponentValidatorRule;
import org.scribble.common.logging.Journal;

public class RaiseValidatorRule implements ProtocolComponentValidatorRule {

	public Class<? extends ModelObject> getValidatedClass() {
		return(org.scribble.protocol.model.Raise.class);
	}
	
	public void validate(ModelObject obj,
					Journal logger) {
		Raise elem=(Raise)obj;

		if (elem.getType() != null) {
			boolean f_found=false;
			
			// Check that a 'catch' for this type
			ModelObject act=elem.getParent();
			
			while (f_found == false && act != null &&
					(act instanceof Protocol) == false) {
			
				if (act instanceof TryEscape) {
					TryEscape te=(TryEscape)act;
					
					for (int i=0; f_found == false &&
							i < te.getBlocks().size(); i++) {
						
						if (te.getBlocks().get(i) instanceof CatchBlock &&
								elem.getType().equals(((CatchBlock)
									te.getBlocks().get(i)).getType())) {
							f_found = true;
						}
					}
				}
				
				act = act.getParent();
			}
			
			if (f_found == false) {
				logger.error(org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.rules.Messages"),
							"_RAISED_TYPE_NOT_CAUGHT",
							new String[]{elem.getType().toText()}),
							obj.getProperties());
			}
		}
	}
}
