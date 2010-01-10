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

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.Raise;
import org.scribble.protocol.validation.rules.RaiseValidatorRule;

//import java.util.logging.*;

public class ProtocolComponentValidator implements ProtocolValidator {
	
	private java.util.Map<Class<? extends ModelObject>, ProtocolComponentValidatorRule> m_rules=
		new java.util.HashMap<Class<? extends ModelObject>, ProtocolComponentValidatorRule>();

	//private static Logger _log=Logger.getLogger(ProtocolComponentValidator.class.getName());
	
	public ProtocolComponentValidator() {
		
		// Register protocol component validator rules
		register(new RaiseValidatorRule());	
	}
	
	protected void register(ProtocolComponentValidatorRule rule) {
		m_rules.put(rule.getValidatedClass(), rule);
	}

	protected void unregister(ProtocolComponentValidatorRule rule) {
		m_rules.remove(rule.getValidatedClass());
	}
	
	protected ProtocolComponentValidatorRule getRule(Class<? extends ModelObject> cls) {
		return(m_rules.get(cls));
	}

	public void validate(org.scribble.protocol.model.ProtocolModel model,
						Journal logger) {
		
		model.visit(new ValidatingVisitor(logger));
	}

	public class ValidatingVisitor extends org.scribble.protocol.model.DefaultVisitor {
		
		public ValidatingVisitor(Journal logger) {
			m_logger = logger;
		}
		
		/**
		 * This method visits a raise component.
		 * 
		 * @param elem The raise
		 */
		public void visitRaise(Raise elem) {
			RaiseValidatorRule rule=new RaiseValidatorRule();
			
			rule.validate(elem, m_logger);
		}
		
		private Journal m_logger=null;
	}
}
