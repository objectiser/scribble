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
package org.scribble.command.conforms;

import org.scribble.common.logger.ScribbleLogger;
import org.scribble.protocol.conformance.Conformer;
import org.scribble.protocol.model.Protocol;
import org.scribble.protocol.parser.ProtocolParser;

public class ConformsCommand implements org.scribble.command.Command {

	public ConformsCommand() {	
	}
	
	public void setLogger(ScribbleLogger sl) {
		m_logger = sl;
	}
	
	public void setProtocolParser(ProtocolParser parser) {
		m_protocolParser = parser;
	}
	
	public void setConformer(Conformer conformer) {
		m_conformer = conformer;
	}
	
	public String getName() {
		return("conforms");
	}
	
	public String getDescription() {
		return("Check two Scribble descriptions for conformance");
	}
	
	public boolean execute(String args[]) {
		boolean ret=false;
		
		if (args.length == 2) {
			java.io.File f1=new java.io.File(args[0]);
			java.io.File f2=new java.io.File(args[1]);
			
			if (f1.exists() == false) {
				m_logger.error("File not found '"+args[0]+"'", null);
			} else if (f2.exists() == false) {
				m_logger.error("File not found '"+args[1]+"'", null);
			} else {
				try {
					java.io.InputStream is=new java.io.FileInputStream(f1);
					
					org.scribble.protocol.model.Model<Protocol> p1=
							m_protocolParser.parse(is, m_logger);
			
					is.close();
					
					is=new java.io.FileInputStream(f2);
					
					org.scribble.protocol.model.Model<Protocol> p2=
							m_protocolParser.parse(is, m_logger);
			
					is.close();
					
					if (p1 != null && p2 != null) {
						m_conformer.conforms(p1, p2, m_logger);
						
						ret = true;
					}
				} catch(Exception e) {
					m_logger.error("Failed to check conformance of '"+
							args[0]+"' against '"+args[1]+"': "+e, null);
					e.printStackTrace();
				}
			}
		} else {
			m_logger.error("CONFORMS EXPECTING 2 PARAMETERS", null);
		}
		
		return(ret);
	}

	private ScribbleLogger m_logger=null;
	private ProtocolParser m_protocolParser=null;
	private Conformer m_conformer=null;
}
