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
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/SingleInteraction@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testSingleInteractionAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/SingleInteraction.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/SingleInteraction@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testChoiceAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Choice.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Choice@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testChoiceAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Choice.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Choice@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testRepeatAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Repeat.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Repeat@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testRepeatAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Repeat.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Repeat@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testParallelAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Parallel.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Parallel@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testParallelAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Parallel.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Parallel@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testOptionalAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Optional.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Optional@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testOptionalAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Optional.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Optional@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}

	@org.junit.Test
	public void testRaiseAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Raise.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Raise@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testRaiseAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Raise.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/Raise@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}

	@org.junit.Test
	public void testRunSubProtocolAtBuyer() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/RunSubProtocol.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/RunSubProtocol@Buyer.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Buyer");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
	}
	
	@org.junit.Test
	public void testRunSubProtocolAtSeller() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/RunSubProtocol.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		ProtocolModel expected=CTKUtil.getModel("tests/protocol/local/RunSubProtocol@Seller.spr", logger);
		
		assertNotNull(expected);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Produce projection of model to buyer
		Participant participant=new Participant("Seller");
		ProtocolModel projected=CTKUtil.project(model, participant, logger);
		
		CTKUtil.verify(projected, expected);
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
