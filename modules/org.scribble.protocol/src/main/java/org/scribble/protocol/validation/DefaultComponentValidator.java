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

import org.scribble.common.logger.ScribbleLogger;
import org.scribble.protocol.model.ModelObject;

import java.util.logging.*;

public class DefaultComponentValidator implements Validator {
	
	private java.util.Map<Class<? extends ModelObject>, ComponentValidatorRule> m_rules=
		new java.util.HashMap<Class<? extends ModelObject>, ComponentValidatorRule>();

	private static Logger _log=Logger.getLogger(DefaultComponentValidator.class.getName());
	
	protected void register(ComponentValidatorRule rule) {
		m_rules.put(rule.getValidatedClass(), rule);
	}

	protected void unregister(ComponentValidatorRule rule) {
		m_rules.remove(rule.getValidatedClass());
	}
	
	protected ComponentValidatorRule getRule(Class<? extends ModelObject> cls) {
		return(m_rules.get(cls));
	}

	public void validate(org.scribble.protocol.model.Model<?> model,
						ScribbleLogger logger) {
		
		model.visit(new ValidatingVisitor(logger));
	}

	public class ValidatingVisitor implements org.scribble.protocol.model.Visitor {
		
		public ValidatingVisitor(ScribbleLogger logger) {
			m_logger = logger;
		}
		
		/**
		 * This method can be used to prepare for
		 * visiting the supplied model object.
		 * 
		 * @param obj The model object
		 */
		public void prepare(ModelObject obj) {
			
			// TODO: Decide how best to deal with grouping constructs
		}
		
		@SuppressWarnings("unchecked")
		public boolean visit(ModelObject obj) {
		
			// Find validation rule for each class up the
			// inheritance hierarchy that are derived from
			// the ModelObject
			Class<? extends ModelObject> cls=obj.getClass();
			
			while (cls != null) {
				ComponentValidatorRule rule=getRule(cls);
				
				if (_log.isLoggable(Level.FINEST)) {
					_log.finest("Component rule for class="+cls+" is "+rule);
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
		
		/**
		 * This method can be used to conclude any
		 * work associated with visiting the supplied 
		 * model object.
		 * 
		 * @param obj The model object
		 */
		public void conclude(ModelObject obj) {
			
			// TODO: Decide how best to deal with grouping constructs
		}

		private ScribbleLogger m_logger=null;
	}
}
