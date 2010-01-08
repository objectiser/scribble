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
package org.scribble.conformance;

import org.scribble.core.logger.ScribbleLogger;

public interface Conformer {

	/**
	 * This method checks whether the supplied model conforms to
	 * the reference model.
	 * 
	 * @param model The model to be verified
	 * @param ref The reference model
	 * @param logger The logger
	 */
	public void conforms(org.scribble.protocol.model.Model<?> model,
			org.scribble.protocol.model.Model<?> ref,
				ScribbleLogger logger);
	
}
