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
package org.scribble.command.validate;

import org.scribble.common.logger.*;
import org.scribble.protocol.model.Protocol;
import org.scribble.protocol.parser.ProtocolParser;
import org.scribble.protocol.validation.ValidationManager;

public class ValidateCommand implements org.scribble.command.Command {

	public ValidateCommand() {
	}
	
	public void setValidationManager(ValidationManager vm) {
		m_validationManager = vm;
	}
	
	public void setLogger(ScribbleLogger sl) {
		m_logger = sl;
	}
	
	public void setProtocolParser(ProtocolParser parser) {
		m_protocolParser = parser;
	}
	
	public String getName() {
		return("validate");
	}
	
	public String getDescription() {
		return("Validate a Scribble description");
	}
	
	public boolean execute(String args[]) {
		boolean ret=false;
		
		if (args.length == 1) {
			m_logger.info("PARSE "+args[0], null);
			
			java.io.File f=new java.io.File(args[0]);
			
			if (f.exists() == false) {
				m_logger.error("File not found '"+args[0]+"'", null);
			} else {
				// TODO: Check if protocol
				try {
					java.io.InputStream is=new java.io.FileInputStream(f);
			
					org.scribble.protocol.model.ProtocolModel model=
							m_protocolParser.parse(is, m_logger);
			
					if (model != null) {
						System.out.println("VALIDATE "+args[0]);
						
						m_validationManager.validate(model, m_logger);
						
						ret = true;						
					}
					
					is.close();
					
				} catch(Exception e) {
					m_logger.error("Failed to parse file '"+args[0]+"'", null);
				}
			}
		} else {
			System.err.println("VALIDATE EXPECTING 1 PARAMETER");
		}
		
		return(ret);
	}

	private ValidationManager m_validationManager=null;
	private ScribbleLogger m_logger=null;
	private ProtocolParser m_protocolParser=null;
}
