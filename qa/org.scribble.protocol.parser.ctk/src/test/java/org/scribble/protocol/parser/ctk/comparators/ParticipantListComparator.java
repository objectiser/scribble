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

public class ParticipantListComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		ParticipantList m=(ParticipantList)arg0;
		ParticipantList e=(ParticipantList)arg1;
		
		if (m.getParticipants().size() == e.getParticipants().size()) {
			
			for (int i=0; i < m.getParticipants().size(); i++) {
				if (m.getParticipants().contains(e.getParticipants().get(i)) == false ||
						e.getParticipants().contains(m.getParticipants().get(i)) == false) {
					return(1);
				}
			}
		} else {
			return(1);
		}
		
		return(0);
	}
}
