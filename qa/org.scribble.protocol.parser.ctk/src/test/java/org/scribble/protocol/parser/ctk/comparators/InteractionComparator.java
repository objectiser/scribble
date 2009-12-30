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

public class InteractionComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		Interaction m=(Interaction)arg0;
		Interaction e=(Interaction)arg1;
		
		if (m.getMessageSignature().equals(e.getMessageSignature()) == false) {
			return(1);
		}
		
		if (m.getFromParticipant() == null) {
			if (e.getFromParticipant() != null) {
				return(1);
			}
		} else if (m.getFromParticipant().equals(e.getFromParticipant()) == false) {
			return(1);
		}
		
		if (m.getToParticipant() == null) {
			if (e.getToParticipant() != null) {
				return(1);
			}
		} else if (m.getToParticipant().equals(e.getToParticipant()) == false) {
			return(1);
		}
		
		return(0);
	}
}
