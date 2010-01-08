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
package org.scribble.protocol.validation.connectedness;

import java.util.Collections;

import org.scribble.common.logger.ScribbleLogger;
import org.scribble.protocol.model.Block;
import org.scribble.protocol.model.ModelObject;
import org.scribble.protocol.model.Participant;
import org.scribble.protocol.model.Visitor;

public class ConnectednessVisitor implements Visitor {
	
	public ConnectednessVisitor(ScribbleLogger logger) {
		m_logger = logger;
	}

	public boolean visit(ModelObject obj) {
		
		if (obj instanceof Block) {
			Block block=(Block)obj;
			java.util.List<Participant> precedingInitiator=null;
			java.util.List<Participant> precedingFinal=null;
			
			for (int i=0; i < block.getContents().size(); i++) {
				
				if (precedingInitiator != null &&
						precedingFinal != null &&
						block.getContents().get(i).initiatorParticipants().size() > 0) {
					if (Collections.disjoint(precedingInitiator,
							block.getContents().get(i).initiatorParticipants()) &&
							Collections.disjoint(precedingFinal,
									block.getContents().get(i).initiatorParticipants())) {
						
						m_logger.error(org.scribble.common.util.MessageUtil.format(
								java.util.PropertyResourceBundle.getBundle(
								"org.scribble.protocol.validation.connectedness.Messages"),
									"_ACTIVITY_NOT_CONNECTED",
									new String[]{}), null);
					}
				}
				
				if (block.getContents().get(i).initiatorParticipants().size() > 0) {
					precedingInitiator = block.getContents().get(i).initiatorParticipants();
					precedingFinal = block.getContents().get(i).finalParticipants();
				}
			}
		}		
		
		return(true);
	}

	public void conclude(ModelObject obj) {
	}

	public void prepare(ModelObject obj) {
	}
	
	private ScribbleLogger m_logger=null;
}
