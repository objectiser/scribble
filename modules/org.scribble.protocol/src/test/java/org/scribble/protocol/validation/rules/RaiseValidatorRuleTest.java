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
import org.scribble.core.model.*;

public class RaiseValidatorRuleTest {

	@org.junit.Test
	public void testRaiseTypeCaught() {
		
		Raise raise=new Raise();
		TypeReference tref=new TypeReference();
		tref.setName("ns.lp");
		//tref.setLocalpart("lp");
		//tref.setNotation("spr");
		raise.setType(tref);
		
		TryEscape te=new TryEscape();
		te.getBlock().getContents().add(raise);
		
		TypeReference catchType=new TypeReference();
		catchType.setName("ns.lp");
		//catchType.setLocalpart("lp");
		//catchType.setNotation("spr");
		
		CatchBlock cb=new CatchBlock();
		cb.setType(catchType);
		
		te.getEscapeBlocks().add(cb);
			
		RaiseValidatorRule rule=new RaiseValidatorRule();
		
		TestScribbleLogger logger=new TestScribbleLogger();

		rule.validate(raise, logger);
		
		logger.verifyErrors(new String[]{});
	}
	
	@org.junit.Test
	public void testRaiseTypeNotCaught() {
		
		Raise raise=new Raise();
		TypeReference tref=new TypeReference();
		tref.setName("ns.lp1");
		//tref.setLocalpart("lp1");
		//tref.setNotation("spr");
		raise.setType(tref);
		
		TryEscape te=new TryEscape();
		te.getBlock().getContents().add(raise);
		
		TypeReference catchType=new TypeReference();
		catchType.setName("ns.lp2");
		//catchType.setLocalpart("lp2");
		//catchType.setNotation("spr");
		
		CatchBlock cb=new CatchBlock();
		cb.setType(catchType);
		
		te.getEscapeBlocks().add(cb);
			
		RaiseValidatorRule rule=new RaiseValidatorRule();
		
		TestScribbleLogger logger=new TestScribbleLogger();

		rule.validate(raise, logger);
		
		logger.verifyErrors(new String[]{
				org.scribble.core.util.MessageUtil.format(
						java.util.PropertyResourceBundle.getBundle(
						"org.scribble.protocol.validation.rules.Messages"),
							"_RAISED_TYPE_NOT_CAUGHT",
							new String[]{tref.toText()})
		});
	}
}
