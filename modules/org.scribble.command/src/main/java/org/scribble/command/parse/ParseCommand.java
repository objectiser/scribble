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
package org.scribble.command.parse;

public class ParseCommand implements org.scribble.command.Command {

	public String getName() {
		return("parse");
	}
	
	public String getDescription() {
		return("Parse a Scribble description");
	}
	
	public boolean execute(String args[]) {
		boolean ret=false;
		
		if (args.length == 1) {
			System.out.println("PARSE "+args[0]);
			ret = true;
		} else {
			System.err.println("PARSE EXPECTING 1 PARAMETER");
		}
		
		return(ret);
	}

}
