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

import org.scribble.common.logging.Journal;
import org.scribble.protocol.parser.ProtocolParser;

public class ParseCommand implements org.scribble.command.Command {

	public ParseCommand() {	
	}
	
	public void setJournal(Journal journal) {
		m_journal = journal;
	}
	
	public void setProtocolParser(ProtocolParser parser) {
		m_protocolParser = parser;
	}
	
	public String getName() {
		return("parse");
	}
	
	public String getDescription() {
		return("Parse a Scribble description");
	}
	
	public boolean execute(String args[]) {
		boolean ret=false;
		
		if (args.length == 1) {
			m_journal.info("PARSE "+args[0], null);
			
			java.io.File f=new java.io.File(args[0]);
			
			if (f.exists() == false) {
				m_journal.error("File not found '"+args[0]+"'", null);
			} else {
				// TODO: Check if protocol
				try {
					java.io.InputStream is=new java.io.FileInputStream(f);
			
					m_protocolParser.parse(is, m_journal);
			
					is.close();
					
					ret = true;
				} catch(Exception e) {
					m_journal.error("Failed to parse file '"+args[0]+"'", null);
				}
			}
		} else {
			m_journal.error("PARSE EXPECTING 1 PARAMETER", null);
		}
		
		return(ret);
	}

	private Journal m_journal=null;
	private ProtocolParser m_protocolParser=null;
}
