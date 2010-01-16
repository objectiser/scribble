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
package org.scribble.protocol.ctk;

import static org.junit.Assert.*;

import org.scribble.protocol.model.*;

public class ProtocolParserTest {
	
	@org.junit.Test
	public void testSingleInteraction() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/SingleInteraction.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("SingleInteraction");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		protocol.getBlock().add(interaction);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testSingleInteractionRPC() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/SingleInteractionRPC.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("SingleInteractionRPC");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		
		ms.setOperation("submit");
		
		TypeReference tref1=new TypeReference();
		tref1.setName("Order");
		ms.getTypes().add(tref1);
		
		TypeReference tref2=new TypeReference();
		tref2.setName("Customer");
		ms.getTypes().add(tref2);

		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		protocol.getBlock().add(interaction);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testRaise() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Raise.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("Raise");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);

		Raise raise=new Raise();
		
		TypeReference tref=new TypeReference();
		tref.setName("ExcType");
		raise.setType(tref);
		
		raise.getParticipants().add(seller);
		
		protocol.getBlock().add(raise);
		
		CTKUtil.verify(model, expected);
	}

	@org.junit.Test
	public void testTryCatchInterrupt() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/TryCatchInterrupt.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("TryCatchInterrupt");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);

		TryEscape tryEscape=new TryEscape();
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		Block b=new Block();
		
		b.add(interaction);
		
		tryEscape.getBlocks().add(b);

		CatchBlock catchBlock=new CatchBlock();
		
		catchBlock.getParticipants().add(seller);
		
		tref=new TypeReference();
		tref.setName("NoStock");
		catchBlock.setType(tref);
		
		interaction=new Interaction();
		
		ms=new MessageSignature();
		tref=new TypeReference();
		tref.setName("OutOfStock");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(seller);
		interaction.setToParticipant(buyer);
		
		catchBlock.add(interaction);

		tryEscape.getBlocks().add(catchBlock);
		
		InterruptBlock interruptBlock=new InterruptBlock();
		
		interruptBlock.getParticipants().add(buyer);
		
		interaction=new Interaction();
		
		ms=new MessageSignature();
		tref=new TypeReference();
		tref.setName("OrderExpired");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		interruptBlock.add(interaction);

		tryEscape.getBlocks().add(interruptBlock);
		
		protocol.getBlock().add(tryEscape);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testChoice() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Choice.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("SingleInteraction");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		Choice choice=new Choice();
		
		choice.setFromParticipant(buyer);
		choice.setToParticipant(seller);

		WhenBlock b1=new WhenBlock();
		choice.getWhenBlocks().add(b1);
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		b1.setMessageSignature(ms);
		
		WhenBlock b2=new WhenBlock();
		choice.getWhenBlocks().add(b2);
		
		ms=new MessageSignature();
		tref=new TypeReference();
		tref.setName("Cancel");
		ms.getTypes().add(tref);
		b2.setMessageSignature(ms);
		
		protocol.getBlock().add(choice);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testParallel() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Parallel.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("Parallel");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		Parallel parallel=new Parallel();
		
		Block b1=new Block();
		parallel.getBlocks().add(b1);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		b1.add(interaction);
		
		Block b2=new Block();
		parallel.getBlocks().add(b2);
		
		interaction=new Interaction();
		
		ms=new MessageSignature();
		tref=new TypeReference();
		tref.setName("Cancel");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		b2.add(interaction);
		
		protocol.getBlock().add(parallel);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testRepeat() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Repeat.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("Repeat");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		Repeat repeat=new Repeat();
		
		repeat.getParticipants().add(buyer);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		repeat.getBlock().add(interaction);
		
		protocol.getBlock().add(repeat);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testOptional() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/Optional.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("Optional");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		Optional optional=new Optional();
		
		optional.getParticipants().add(buyer);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		optional.getBlock().add(interaction);
		
		protocol.getBlock().add(optional);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testRunSubProtocol() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/RunSubProtocol.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("RunSubProtocol");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		

		Run run=new Run();
		
		ProtocolReference ref=new ProtocolReference();
		ref.setName("Sub");
		
		run.setReference(ref);
		
		DeclarationBinding db1=new DeclarationBinding("Buyer", "SubBuyer");
		run.getBindings().add(db1);
		
		DeclarationBinding db2=new DeclarationBinding("Seller", "SubSeller");
		run.getBindings().add(db2);
		
		protocol.getBlock().add(run);
		
		
		Protocol subprotocol=new Protocol();
		
		LocatedName subln=new LocatedName();
		subln.setName("Sub");
		subprotocol.setLocatedName(subln);
		
		ParticipantList subpl=new ParticipantList();
		Participant subbuyer=new Participant();
		subbuyer.setName("SubBuyer");
		subpl.getParticipants().add(subbuyer);
		Participant subseller=new Participant();
		subseller.setName("SubSeller");
		subpl.getParticipants().add(subseller);
		
		subprotocol.getBlock().add(subpl);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(subbuyer);
		interaction.setToParticipant(subseller);
		
		subprotocol.getBlock().add(interaction);
		
		protocol.getBlock().add(subprotocol);
		
		CTKUtil.verify(model, expected);
	}
	
	@org.junit.Test
	public void testRunInlineProtocol() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=CTKUtil.getModel("tests/protocol/global/RunInlineProtocol.spr", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setProtocol(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("RunInlineProtocol");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		

		Run run=new Run();
		
		DeclarationBinding db1=new DeclarationBinding("Buyer", "SubBuyer");
		run.getBindings().add(db1);
		
		DeclarationBinding db2=new DeclarationBinding("Seller", "SubSeller");
		run.getBindings().add(db2);
		
		protocol.getBlock().add(run);
		
		
		Protocol subprotocol=new Protocol();
		
		run.setInlineProtocol(subprotocol);
		
		LocatedName subln=new LocatedName();
		subln.setName("Sub");
		subprotocol.setLocatedName(subln);
		
		ParticipantList subpl=new ParticipantList();
		Participant subbuyer=new Participant();
		subbuyer.setName("SubBuyer");
		subpl.getParticipants().add(subbuyer);
		Participant subseller=new Participant();
		subseller.setName("SubSeller");
		subpl.getParticipants().add(subseller);
		
		subprotocol.getBlock().add(subpl);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(subbuyer);
		interaction.setToParticipant(subseller);
		
		subprotocol.getBlock().add(interaction);
				
		CTKUtil.verify(model, expected);
	}
}
