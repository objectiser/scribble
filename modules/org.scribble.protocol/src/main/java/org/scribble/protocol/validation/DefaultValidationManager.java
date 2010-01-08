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

public class DefaultValidationManager implements ValidationManager {
	
	public void addValidator(Validator validator) {
		m_validators.add(validator);
	}

	public void removeValidator(Validator validator) {
		m_validators.remove(validator);
	}

	public void validate(org.scribble.protocol.model.ProtocolModel model,
				org.scribble.common.logger.ScribbleLogger logger) {
	
		for (Validator v : m_validators) {
			v.validate(model, logger);
		}
	}
	
	private java.util.List<Validator> m_validators=
				new java.util.Vector<Validator>();
}
