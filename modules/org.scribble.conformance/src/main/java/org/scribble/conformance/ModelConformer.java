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

import org.scribble.core.conformance.Conformer;
import org.scribble.core.logger.ScribbleLogger;
import org.scribble.core.model.Model;

public class ModelConformer implements Conformer {

	public void conforms(Model<?> model, Model<?> ref, ScribbleLogger logger) {
		logger.info("CONFORMS: "+model+" ref="+ref, null);
	}

}
