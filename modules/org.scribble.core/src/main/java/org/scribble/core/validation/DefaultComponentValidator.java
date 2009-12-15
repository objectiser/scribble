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
package org.scribble.core.validation;

import org.scribble.core.logger.ScribbleLogger;
import org.scribble.core.model.ModelObject;
import org.apache.log4j.*;

public class DefaultComponentValidator implements Validator {
	
	private java.util.Map<Class<? extends ModelObject>, ComponentValidatorRule> m_rules=
		new java.util.HashMap<Class<? extends ModelObject>, ComponentValidatorRule>();

	private static Logger _log=Logger.getLogger(DefaultComponentValidator.class);
	
	protected void register(ComponentValidatorRule rule) {
		m_rules.put(rule.getValidatedClass(), rule);
	}

	protected void unregister(ComponentValidatorRule rule) {
		m_rules.remove(rule.getValidatedClass());
	}
	
	protected ComponentValidatorRule getRule(Class<? extends ModelObject> cls) {
		return(m_rules.get(cls));
	}

	public void validate(org.scribble.core.model.Model<?> model,
						ScribbleLogger logger) {
		
		model.visit(new ValidatingVisitor(logger));
	}

	public class ValidatingVisitor implements org.scribble.core.model.Visitor {
		
		public ValidatingVisitor(ScribbleLogger logger) {
			m_logger = logger;
		}
		
		@SuppressWarnings("unchecked")
		public boolean visit(ModelObject obj) {
		
			// Find validation rule for each class up the
			// inheritance hierarchy that are derived from
			// the ModelObject
			Class<? extends ModelObject> cls=obj.getClass();
			
			while (cls != null) {
				ComponentValidatorRule rule=getRule(cls);
				
				if (_log.isTraceEnabled()) {
					_log.trace("Component rule for class="+cls+" is "+rule);
				}
				
				if (rule != null) {
					rule.validate(obj, m_logger);
				}
				
				if (cls != ModelObject.class) {
					cls = (Class<? extends ModelObject>)cls.getSuperclass();
				} else {
					cls = null;
				}
			}
			
			return(true);
		}
		
		private ScribbleLogger m_logger=null;
	}
}
