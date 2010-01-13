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
package org.scribble.protocol.ctk.comparators;

import java.util.Comparator;
import org.scribble.protocol.model.*;

public class ChannelComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		Channel m=(Channel)arg0;
		Channel e=(Channel)arg1;
		
		if (m.getName().equals(e.getName()) == false) {
			return(1);
		}
		
		if (m.getFromParticipant() == null || e.getFromParticipant() == null) {
			if (m.getFromParticipant() != e.getFromParticipant()) {
				return(1);
			}
		} else if (m.getFromParticipant().getName().equals(e.getFromParticipant().getName()) == false) {
			return(1);
		}
		
		if (m.getToParticipant() == null || e.getToParticipant() == null) {
			if (m.getToParticipant() != e.getToParticipant()) {
				return(1);
			}
		} else if (m.getToParticipant().getName().equals(e.getToParticipant().getName()) == false) {
			return(1);
		}
		
		return(0);
	}
}
