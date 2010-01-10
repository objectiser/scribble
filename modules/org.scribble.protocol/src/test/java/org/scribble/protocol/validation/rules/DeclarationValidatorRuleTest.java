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
package org.scribble.protocol.validation.rules;

import org.scribble.protocol.model.*;
import org.scribble.protocol.validation.rules.DeclarationValidatorRule;

public class DeclarationValidatorRuleTest {

	private static final String TEST_NAME = "TestName";

	@org.junit.Test
	public void testValidateNoDuplicate() {
		Protocol defn=new Protocol();
		TestDeclaration decl=new TestDeclaration();
		decl.setName(TEST_NAME);

		defn.getBlock().add(decl);
	
		DeclarationValidatorRule rule=new DeclarationValidatorRule();
		
		TestScribbleLogger logger=new TestScribbleLogger();
		
		rule.validate(decl, logger);
		
		logger.verifyErrors(new String[]{});
	}
	
	@org.junit.Test
	public void testValidateSingleLevelDuplicate() {
		Protocol defn=new Protocol();
		TestDeclaration decl1=new TestDeclaration();
		decl1.setName(TEST_NAME);

		defn.getBlock().add(decl1);
		
		Interaction in=new Interaction();
		defn.getBlock().add(in);
	
		TestDeclaration decl2=new TestDeclaration();
		decl2.setName(TEST_NAME);

		defn.getBlock().add(decl2);
	
		DeclarationValidatorRule rule=new DeclarationValidatorRule();
		
		TestScribbleLogger logger=new TestScribbleLogger();
		
		rule.validate(decl2, logger);
		
		logger.verifyErrors(new String[]{
				org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.rules.Messages"),
							"_EXISTING_DECLARATION",
							new String[]{TEST_NAME})
		});
	}
	
	@org.junit.Test
	public void testValidateDuplicateInSinglePathBehaviour() {
		Protocol defn=new Protocol();
		TestDeclaration decl1=new TestDeclaration();
		decl1.setName(TEST_NAME);

		defn.getBlock().add(decl1);
		
		TestSinglePathBehaviour spb=new TestSinglePathBehaviour();
		defn.getBlock().add(spb);
		
		Interaction in=new Interaction();
		spb.getBlock().add(in);
	
		TestDeclaration decl2=new TestDeclaration();
		decl2.setName(TEST_NAME);

		spb.getBlock().add(decl2);
	
		DeclarationValidatorRule rule=new DeclarationValidatorRule();
		
		TestScribbleLogger logger=new TestScribbleLogger();
		
		rule.validate(decl2, logger);
		
		logger.verifyErrors(new String[]{
				org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.rules.Messages"),
							"_EXISTING_DECLARATION",
							new String[]{TEST_NAME})
		});
	}
	
	@org.junit.Test
	public void testValidateDuplicateInMultiPathBehaviour() {
		Protocol defn=new Protocol();
		TestDeclaration decl1=new TestDeclaration();
		decl1.setName(TEST_NAME);

		defn.getBlock().add(decl1);
		
		TestMultiPathBehaviour mpb=new TestMultiPathBehaviour();
		defn.getBlock().add(mpb);
		
		mpb.createNewPath();
		
		Block path=mpb.createNewPath();
		
		Interaction in=new Interaction();
		path.add(in);
	
		TestDeclaration decl2=new TestDeclaration();
		decl2.setName(TEST_NAME);

		path.add(decl2);
	
		DeclarationValidatorRule rule=new DeclarationValidatorRule();
		
		TestScribbleLogger logger=new TestScribbleLogger();
		
		rule.validate(decl2, logger);
		
		logger.verifyErrors(new String[]{
				org.scribble.common.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.rules.Messages"),
							"_EXISTING_DECLARATION",
							new String[]{TEST_NAME})
		});
	}
}
