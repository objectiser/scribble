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

import org.scribble.core.validation.DefaultComponentValidator;

public class ProtocolComponentValidator extends DefaultComponentValidator {

	public ProtocolComponentValidator() {
		
		// Register protocol component validator rules
		register(new RaiseValidatorRule());
		
	}
	/*
	public void validate(Model<?> model, ScribbleLogger logger) {
		logger.warning("PROTOCOL VALIDATOR: "+model, null);
		
		logger.error(org.scribble.core.util.MessageUtil.format(
				java.util.ResourceBundle.getBundle(
				"org.scribble.protocol.validator.Messages"),
					"_PROTOCOL",
					new String[]{"test"}), null);
	}
	*/
}
