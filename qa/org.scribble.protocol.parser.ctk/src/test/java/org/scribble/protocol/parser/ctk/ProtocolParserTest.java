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
package org.scribble.protocol.parser.ctk;

import static org.junit.Assert.*;

import java.util.Comparator;

import org.scribble.common.logging.*;
import org.scribble.protocol.model.*;

public class ProtocolParserTest {
	
	public ProtocolModel getModel(String filename, Journal logger) {
		ProtocolModel ret=null;
		
		java.io.InputStream is=
				ClassLoader.getSystemResourceAsStream("tests/"+
							filename+".spr");
		
		if (is == null) {
			fail("Failed to load protocol '"+filename+"'");
		}
		
		org.scribble.protocol.parser.ProtocolParser parser=null;
		
		try {
			String clsName=System.getProperty("scribble.protocol.parser");
			
			if (clsName == null) {
				clsName = "org.scribble.protocol.parser.antlr.ANTLRProtocolParser";
			}
			
			Class<?> cls=Class.forName(clsName);
			
			parser = (org.scribble.protocol.parser.ProtocolParser)
								cls.newInstance();

		} catch(Exception e) {
			fail("Failed to get Protocol parser: "+e);
		}

		ret = parser.parse(is, logger);
		
		return(ret);
	}

	/**
	 * This method validates a model against the expected model.
	 * 
	 * @param model The model constructed by the parser
	 * @param expected The expected model
	 */
	public void verify(ProtocolModel model, ProtocolModel expected) {
		java.util.List<ModelObject> mlist=sequence(model);
		java.util.List<ModelObject> elist=sequence(expected);
		
		assertNotNull(mlist);
		assertNotNull(elist);
		
		int len=mlist.size();
		
		if (len > elist.size()) {
			len = elist.size();
		}
		
		for (int i=0; i < len; i++) {
			if (mlist.get(i).getClass() != elist.get(i).getClass()) {
				fail("Element ("+i+") mismatch class model="+
						mlist.get(i).getClass()+" expected="+
						elist.get(i).getClass());
			} else {
				Comparator<ModelObject> comparator=
					ComparatorUtil.getComparator(mlist.get(i).getClass());
				
				if (comparator == null) {
					fail("No comparator found for type: "+mlist.get(i).getClass());
				} else if (comparator.compare(mlist.get(i), elist.get(i)) != 0) {
					fail("Element ("+i+") did not match: "+mlist.get(i)+
							" expected="+elist.get(i));
				}
			}
		}
		
		assertTrue(mlist.size() == elist.size());
	}
	
	/**
	 * This method converts the model tree into a flat list of model
	 * objects which can be compared.
	 * 
	 * @param model The model
	 * @return The list of model objects
	 */
	protected java.util.List<ModelObject> sequence(ProtocolModel model) {
		final java.util.List<ModelObject> ret=new java.util.Vector<ModelObject>();
		
		model.visit(new Visitor() {

			@Override
			public void endBlock(Block elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endCatchBlock(CatchBlock elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endChannelList(ChannelList elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endChoice(Choice elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endInterruptBlock(InterruptBlock elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endParallel(Parallel elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endParticipantList(ParticipantList elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endProtocol(Protocol elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endRecursion(Recursion elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endRepeat(Repeat elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void endTryEscape(TryEscape elem) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public boolean startBlock(Block elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startCatchBlock(CatchBlock elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startChannelList(ChannelList elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startChoice(Choice elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startInterruptBlock(InterruptBlock elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startParallel(Parallel elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startParticipantList(ParticipantList elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startProtocol(Protocol elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startRecursion(Recursion elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startRepeat(Repeat elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public boolean startTryEscape(TryEscape elem) {
				ret.add(elem);
				return true;
			}

			@Override
			public void visitChannel(Channel elem) {
				ret.add(elem);
			}

			@Override
			public void visitDeclarationBinding(DeclarationBinding elem) {
				ret.add(elem);
			}

			@Override
			public void visitImport(Import elem) {
				ret.add(elem);
			}

			@Override
			public void visitInteraction(Interaction elem) {
				ret.add(elem);
			}

			@Override
			public void visitLocatedName(LocatedName elem) {
				ret.add(elem);
			}

			@Override
			public void visitMessageSignature(MessageSignature elem) {
				ret.add(elem);
			}

			@Override
			public void visitModelReference(ModelReference elem) {
				ret.add(elem);
			}

			@Override
			public void visitNamespace(Namespace elem) {
				ret.add(elem);
			}

			@Override
			public void visitParticipant(Participant elem) {
				ret.add(elem);
			}

			@Override
			public void visitRaise(Raise elem) {
				ret.add(elem);
			}

			@Override
			public void visitRecur(Recur elem) {
				ret.add(elem);
			}

			@Override
			public void visitRun(Run elem) {
				ret.add(elem);
			}

		});
		
		return(ret);
	}
	
	@org.junit.Test
	public void testSingleInteraction() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("SingleInteraction", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testSingleInteractionRPC() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("SingleInteractionRPC", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testSingleInteractionWithChannel() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("SingleInteractionWithChannel", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("SingleInteractionWithChannel");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		ChannelList cl=new ChannelList();
		Channel ch=new Channel();
		ch.setName("mych");
		
		cl.getChannels().add(ch);
		
		protocol.getBlock().add(cl);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		interaction.setChannel(new Channel("mych"));

		protocol.getBlock().add(interaction);
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testSingleInteractionWithChannel2() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("SingleInteractionWithChannel2", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
		LocatedName ln=new LocatedName();
		ln.setName("SingleInteractionWithChannel");
		protocol.setLocatedName(ln);
		
		ParticipantList rl=new ParticipantList();
		Participant buyer=new Participant();
		buyer.setName("Buyer");
		rl.getParticipants().add(buyer);
		Participant seller=new Participant();
		seller.setName("Seller");
		rl.getParticipants().add(seller);
		
		protocol.getBlock().add(rl);
		
		ChannelList cl=new ChannelList();
		Channel ch=new Channel();
		ch.setName("mych");
		ch.setFromParticipant(buyer);
		ch.setToParticipant(seller);
		
		cl.getChannels().add(ch);
		
		protocol.getBlock().add(cl);
		
		Interaction interaction=new Interaction();
		
		MessageSignature ms=new MessageSignature();
		TypeReference tref=new TypeReference();
		tref.setName("Order");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setChannel(new Channel("mych"));
		
		protocol.getBlock().add(interaction);
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testRaise() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("Raise", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		verify(model, expected);
	}

	@org.junit.Test
	public void testTryCatchInterrupt() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("TryCatchInterrupt", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testChoice() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("Choice", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		choice.getParticipants().add(buyer);
		
		Block b1=new Block();
		choice.getBlocks().add(b1);
		
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
		choice.getBlocks().add(b2);
		
		interaction=new Interaction();
		
		ms=new MessageSignature();
		tref=new TypeReference();
		tref.setName("Cancel");
		ms.getTypes().add(tref);
		interaction.setMessageSignature(ms);
		interaction.setFromParticipant(buyer);
		interaction.setToParticipant(seller);
		
		b2.add(interaction);
		
		protocol.getBlock().add(choice);
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testParallel() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("Parallel", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		verify(model, expected);
	}
	
	@org.junit.Test
	public void testRepeat() {
		TestJournal logger=new TestJournal();
		
		ProtocolModel model=getModel("Repeat", logger);
		
		assertNotNull(model);
		
		assertTrue(logger.getErrorCount() == 0);
		
		// Build expected model
		ProtocolModel expected=new ProtocolModel();
		
		Namespace ns=new Namespace();
		ns.setName("example.helloworld");
		expected.setNamespace(ns);
		
		Protocol protocol=new Protocol();
		expected.setDefinition(protocol);
		
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
		
		verify(model, expected);
	}
}
