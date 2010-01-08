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
package org.scribble.designer;

import org.scribble.protocol.parser.ProtocolParser;
import org.scribble.protocol.validation.ValidationManager;

/**
 * This class provides a manager for accessing services used
 * by the designer.
 *
 */
public class DesignerServices {
	
	public static ValidationManager getValidationManager() {
		return(m_validationManager);
	}
	
	public static void setValidationManager(ValidationManager vm) {
		m_validationManager = vm;
	}
	
	public static ProtocolParser getProtocolParser() {
		return(m_protocolParser);
	}
	
	public static void setProtocolParser(ProtocolParser parser) {
		m_protocolParser = parser;
	}
	
	private static ValidationManager m_validationManager=null;
	private static ProtocolParser m_protocolParser=null;
}