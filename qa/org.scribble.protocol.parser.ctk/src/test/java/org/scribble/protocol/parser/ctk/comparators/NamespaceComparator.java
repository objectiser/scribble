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
import org.scribble.protocol.model.*;

public class NamespaceComparator implements Comparator<ModelObject> {

	@Override
	public int compare(ModelObject arg0, ModelObject arg1) {
		Namespace m=(Namespace)arg0;
		Namespace e=(Namespace)arg1;
		
		if (m.getName() == null) {
			if (e.getName() == null) {
				return(0);
			} else {
				return(1);
			}
		} else {
			if (e.getName() == null) {
				return(-1);
			}
			return(m.getName().compareTo(e.getName()));
		}
	}
}
