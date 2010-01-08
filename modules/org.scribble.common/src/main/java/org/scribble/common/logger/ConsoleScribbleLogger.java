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
package org.scribble.common.logger;

import java.io.Serializable;

public class ConsoleScribbleLogger implements ScribbleLogger {

	public void error(String issue, java.util.Map<String,Serializable> props) {
		System.out.println("ERROR: "+issue);
	}
	
	public void warning(String issue, java.util.Map<String,Serializable> props) {
		System.out.println("WARN: "+issue);
	}
	
	public void info(String issue, java.util.Map<String,Serializable> props) {
		System.out.println("INFO: "+issue);
	}

	public void debug(String issue, java.util.Map<String,Serializable> props) {
		System.out.println("DEBUG: "+issue);
	}

	public void trace(String issue, java.util.Map<String,Serializable> props) {
		System.out.println("TRACE: "+issue);
	}

}
