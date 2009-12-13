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

import java.util.List;

import org.scribble.core.model.Definition;
import org.scribble.core.validation.*;
import org.scribble.core.logger.*;

public class ValidateCommand implements org.scribble.command.Command {

	public ValidateCommand() {
	}
	
	public void setValidationManager(ValidationManager vm) {
		m_validationManager = vm;
	}
	
	public void setLogger(ScribbleLogger sl) {
		m_logger = sl;
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
			System.out.println("VALIDATE "+args[0]);
			
			org.scribble.core.model.Model model=
				new org.scribble.core.model.Model();
			
			m_validationManager.validate(model, m_logger);
			
			ret = true;
		} else {
			System.err.println("VALIDATE EXPECTING 1 PARAMETER");
		}
		
		return(ret);
	}

	private ValidationManager m_validationManager=null;
	private ScribbleLogger m_logger=null;
}
