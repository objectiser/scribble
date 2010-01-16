/*
 * Copyright 2009-10 Scribble.org
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
package org.scribble.protocol.ctk;

import static org.junit.Assert.*;

import org.scribble.protocol.model.*;

public class ProtocolProjectorTest {
	
	@org.junit.Test
	public void testSingleInteractionAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/SingleInteraction.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel buyer=CTKUtil.getModel("tests/protocol/local/SingleInteraction@Buyer.spr", logger);
		
		assertNotNull(buyer);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel expected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(buyer, expected);
	}
	
	@org.junit.Test
	public void testSingleInteractionAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/SingleInteraction.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel buyer=CTKUtil.getModel("tests/protocol/local/SingleInteraction@Seller.spr", logger);
		
		assertNotNull(buyer);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel expected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(buyer, expected);
	}
	
	@org.junit.Test
	public void testMultiPartyInteractionsAndChoiceAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/MultiPartyInteractionsAndChoice.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/MultiPartyInteractionsAndChoice@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testMultiPartyInteractionsAndChoiceAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/MultiPartyInteractionsAndChoice.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/MultiPartyInteractionsAndChoice@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testMultiPartyInteractionsAndChoiceAtBroker() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/MultiPartyInteractionsAndChoice.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/MultiPartyInteractionsAndChoice@Broker.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Broker");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
}
