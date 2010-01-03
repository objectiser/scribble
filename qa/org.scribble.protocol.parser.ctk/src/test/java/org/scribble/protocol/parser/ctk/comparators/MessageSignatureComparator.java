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
package org.scribble.protocol.parser.ctk.comparators;

import java.util.Comparator;
import org.scribble.core.model.*;
import org.scribble.protocol.parser.ctk.ComparatorUtil;

public class MessageSignatureComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		MessageSignature m=(MessageSignature)arg0;
		MessageSignature e=(MessageSignature)arg1;
		
		if (m.getOperation() == null &&
				e.getOperation() == null) {
			// Matches
		} else if (m.getOperation() != null &&
				e.getOperation() != null &&
				m.getOperation().equals(e.getOperation())) {
			// Matches
		} else {
			return(1);
		}
		
		if (m.getTypes().size() != e.getTypes().size()) {
			return(1);
		}
		
		Comparator<ModelObject> trcomp=
				ComparatorUtil.getComparator(TypeReference.class);
		
		for (int i=0; i < m.getTypes().size(); i++) {
			if (trcomp.compare(m.getTypes().get(i), e.getTypes().get(i)) != 0) {
				return(1);
			}
		}
		
		return(0);
	}
}
