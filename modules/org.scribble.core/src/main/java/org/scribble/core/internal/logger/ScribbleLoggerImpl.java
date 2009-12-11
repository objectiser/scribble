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
package org.scribble.core.internal.logger;

import org.scribble.core.logger.ScribbleLogger;

public class ScribbleLoggerImpl implements ScribbleLogger {

	public void error(org.scribble.core.model.ModelObject modelObject,
					String issue) {
		System.out.println("ERROR: "+issue);
	}
	
	public void warning(org.scribble.core.model.ModelObject modelObject,
			String issue) {
		System.out.println("WARN: "+issue);
	}
	
	public void info(org.scribble.core.model.ModelObject modelObject,
			String issue) {
		System.out.println("INFO: "+issue);
	}

	public void debug(org.scribble.core.model.ModelObject modelObject,
			String issue) {
		System.out.println("DEBUG: "+issue);
	}

	public void trace(org.scribble.core.model.ModelObject modelObject,
			String issue) {
		System.out.println("TRACE: "+issue);
	}

}
