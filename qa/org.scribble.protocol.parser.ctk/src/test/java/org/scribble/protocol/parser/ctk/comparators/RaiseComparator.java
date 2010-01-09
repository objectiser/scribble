/*
 * Copyright 2009-10 Scribble.org
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
import org.scribble.protocol.model.*;
import org.scribble.protocol.parser.ctk.ComparatorUtil;

public class RaiseComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		Raise m=(Raise)arg0;
		Raise e=(Raise)arg1;
		
		if (m.getType() != null && e.getType() != null &&
				m.getParticipants().size() == e.getParticipants().size()) {
			Comparator<ModelObject> c=ComparatorUtil.getComparator(TypeReference.class);
			
			if (c.compare(m.getType(), e.getType()) == 0) {
				Comparator<ModelObject> pc=ComparatorUtil.getComparator(Participant.class);
				
				for (int i=0; i < m.getParticipants().size(); i++) {
					if (pc.compare(m.getParticipants().get(i),
									e.getParticipants().get(i)) != 0) {
						return(1);
					}
				}
				
				return(0);
			}
		}
		
		return(1);
	}
}
