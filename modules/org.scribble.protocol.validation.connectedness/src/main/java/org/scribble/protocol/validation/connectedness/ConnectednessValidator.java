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
package org.scribble.protocol.validation.connectedness;

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.ProtocolModel;
import org.scribble.protocol.validation.ProtocolValidator;

public class ConnectednessValidator implements ProtocolValidator {

	public void validate(ProtocolModel model, Journal logger) {
		ConnectednessVisitor visitor=new ConnectednessVisitor(logger);
		
		model.visit(visitor);
	}
}
