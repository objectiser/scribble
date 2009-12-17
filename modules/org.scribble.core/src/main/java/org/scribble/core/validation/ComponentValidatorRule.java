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

import org.scribble.core.model.ModelObject;
import org.scribble.core.logger.ScribbleLogger;

/**
 * This interface represents the validation rule for the
 * model components.
 *
 */
public interface ComponentValidatorRule {

	/**
	 * This method returns the class being validated.
	 * 
	 * @return The class being validated
	 */
	public Class<? extends ModelObject> getValidatedClass();
	
	/**
	 * This method validates the supplied model object.
	 * 
	 * @param obj The model object being validated
	 * @param logger The logger
	 */
	public void validate(ModelObject obj,
					ScribbleLogger logger);

}
