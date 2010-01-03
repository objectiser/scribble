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
package org.scribble.core.model;

import static org.junit.Assert.*;

public class ModelReferenceTest {

	@org.junit.Test
	public void testLocalPart() {
		ModelReference ref=new ModelReference();
		String testname="test";
		
		ref.setName(testname);
		
		if (ref.getName().equals(testname) == false) {
			fail("Name not as expected");
		}

		if (ref.getLocalpart().equals(testname) == false) {
			fail("Localpart not as expected: "+ref.getLocalpart());
		}
		
		if (ref.getNamespace().length() != 0) {
			fail("Namespace should not be set");
		}
		
		if (ref.getLocatedParticipant() != null) {
			fail("Located participant should not be set");
		}
	}

	@org.junit.Test
	public void testLocalPartLocated() {
		ModelReference ref=new ModelReference();
		
		String located="located";
		String localpart="test";
		String testname=localpart+ModelReference.LOCATED_REFERENCE_SEPARATOR+located;
		
		ref.setName(testname);
		
		if (ref.getName().equals(testname) == false) {
			fail("Name not as expected");
		}

		if (ref.getLocalpart().equals(localpart) == false) {
			fail("Localpart not as expected: "+ref.getLocalpart());
		}
		
		if (ref.getNamespace().length() != 0) {
			fail("Namespace should not be set");
		}
		
		if (ref.getLocatedParticipant() == null) {
			fail("Located participant should be set");
		}
		
		if (ref.getLocatedParticipant().equals(located) == false) {
			fail("Located participant not as expected: "+ref.getLocatedParticipant());
		}
	}

	@org.junit.Test
	public void testFullQualifiedName() {
		ModelReference ref=new ModelReference();
		String localpart="lp";
		String namespace="test.name";
		String testname=namespace+"."+localpart;
		
		ref.setName(testname);
		
		if (ref.getName().equals(testname) == false) {
			fail("Name not as expected");
		}

		if (ref.getLocalpart().equals(localpart) == false) {
			fail("Localpart not as expected: "+ref.getLocalpart());
		}
		
		if (ref.getNamespace().equals(namespace) == false) {
			fail("Namespace not as expected: "+ref.getNamespace());
		}
		
		if (ref.getLocatedParticipant() != null) {
			fail("Located participant should not be set");
		}
	}

	@org.junit.Test
	public void testFullQualifiedNameLocated() {
		ModelReference ref=new ModelReference();
		String localpart="lp";
		String namespace="test.name";
		String located="located";
		String testname=namespace+"."+localpart+
				ModelReference.LOCATED_REFERENCE_SEPARATOR+located;
		
		ref.setName(testname);
		
		if (ref.getName().equals(testname) == false) {
			fail("Name not as expected");
		}

		if (ref.getLocalpart().equals(localpart) == false) {
			fail("Localpart not as expected: "+ref.getLocalpart());
		}
		
		if (ref.getNamespace().equals(namespace) == false) {
			fail("Namespace not as expected: "+ref.getNamespace());
		}
		
		if (ref.getLocatedParticipant() == null) {
			fail("Located participant should be set");
		}
		
		if (ref.getLocatedParticipant().equals(located) == false) {
			fail("Located participant not as expected: "+ref.getLocatedParticipant());
		}
	}
}
