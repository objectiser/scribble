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
package org.scribble.protocol.model;

import org.scribble.core.model.ModelReference;

/**
 * This class represents the protocol notation.
 */
public class ProtocolNotation extends org.scribble.core.model.AbstractNotation {

	public static final String NOTATION_CODE="spr";
	
	/**
	 * This is the protocol notation constructor.
	 */
	public ProtocolNotation() {
		super(NOTATION_CODE, "Protocol");
	}
	
	/**
	 * This method returns the initial description associated
	 * with the supplied reference.
	 * 
	 * @param ref The reference
	 * @return The initial description, or null if no description
	 */
	public String getInitialDescription(ModelReference ref) {
		StringBuffer buf=new StringBuffer();
		
		buf.append("namespace ");
		buf.append(ref.getNamespace());
		buf.append(";\r\n\r\n");
		
		buf.append("protocol ");
		buf.append(ref.getLocalpart());
		
		if (ref.getLocatedRole() != null) {
			buf.append(ModelReference.LOCATED_REFERENCE_SEPARATOR);
			buf.append(ref.getLocatedRole());
			buf.append(" {\r\n\trole\tP;\r\n\r\n}\r\n");
		} else {		
			buf.append(" {\r\n\trole\tP1, P2;\r\n\r\n}\r\n");
		}
		
		return(buf.toString());
	}
}
