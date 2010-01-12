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

//import static org.junit.Assert.*;

import org.scribble.protocol.model.Interaction;
import org.scribble.protocol.model.Protocol;
import org.scribble.protocol.model.ProtocolModel;
import org.scribble.protocol.model.Participant;
import org.scribble.protocol.validation.connectedness.ConnectednessValidator;

public class ConnectednessValidatorTest {

	@org.junit.Test
	public void testInteractionReqResp() {
		
		ProtocolModel model=new ProtocolModel();

		Protocol defn=new Protocol();
		model.setProtocol(defn);
		
		Interaction interaction1=new Interaction();
		defn.getBlock().add(interaction1);
		
		Participant fromP=new Participant();
		fromP.setName("fromPart");
		
		Participant toP=new Participant();
		toP.setName("toPart");
		
		interaction1.setFromParticipant(fromP);
		interaction1.setToParticipant(toP);
		
		Interaction interaction2=new Interaction();
		defn.getBlock().add(interaction2);
		
		interaction2.setFromParticipant(toP);
		interaction2.setToParticipant(fromP);
		
		ConnectednessValidator validator=new ConnectednessValidator();
		
		TestScribbleLogger logger=new TestScribbleLogger();

		validator.validate(model, logger);
		
		logger.verifyErrors(new String[]{});
	}
	
	@org.junit.Test
	public void testInteractionsDisconnected() {
		ProtocolModel model=new ProtocolModel();

		Protocol defn=new Protocol();
		model.setProtocol(defn);
		
		Interaction interaction1=new Interaction();
		defn.getBlock().add(interaction1);
		
		Participant fromP1=new Participant();
		fromP1.setName("fromPart1");
		
		Participant toP1=new Participant();
		toP1.setName("toPart1");
		
		interaction1.setFromParticipant(fromP1);
		interaction1.setToParticipant(toP1);
		
		Interaction interaction2=new Interaction();
		defn.getBlock().add(interaction2);
		
		Participant fromP2=new Participant();
		fromP2.setName("fromPart2");
		
		Participant toP2=new Participant();
		toP2.setName("toPart2");
		
		interaction2.setFromParticipant(fromP2);
		interaction2.setToParticipant(toP2);
		
		ConnectednessValidator validator=new ConnectednessValidator();
		
		TestScribbleLogger logger=new TestScribbleLogger();

		validator.validate(model, logger);
		
		logger.verifyErrors(new String[]{
				org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.connectedness.Messages"),
						"_ACTIVITY_NOT_CONNECTED",
							new String[]{})
		});
	}
}
