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

public class InteractionComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		Interaction m=(Interaction)arg0;
		Interaction e=(Interaction)arg1;
		
		Comparator<ModelObject> mscomp=
			ComparatorUtil.getComparator(MessageSignature.class);
		
		Comparator<ModelObject> pcomp=
			ComparatorUtil.getComparator(Participant.class);
		
		if (mscomp.compare(m.getMessageSignature(),
					e.getMessageSignature()) != 0) {
			return(1);
		}
		
		if (m.getFromParticipant() == null) {
			if (e.getFromParticipant() != null) {
				return(1);
			}
		} else if (pcomp.compare(m.getFromParticipant(),
				e.getFromParticipant()) != 0) {
			return(1);
		}
		
		if (m.getToParticipant() == null) {
			if (e.getToParticipant() != null) {
				return(1);
			}
		} else if (pcomp.compare(m.getToParticipant(),
				e.getToParticipant()) != 0) {
			return(1);
		}
		
		return(0);
	}
}
