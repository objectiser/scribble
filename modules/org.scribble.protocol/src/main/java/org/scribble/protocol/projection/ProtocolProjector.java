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
package org.scribble.protocol.projection;

import org.scribble.common.logging.Journal;
import org.scribble.protocol.model.*;

/**
 * This interface provides a projection capability, from a 'global'
 * to 'local' model, for the protocol notation.
 *
 */
public interface ProtocolProjector {

	/**
	 * This method projects a 'global' protocol model to a specified
	 * participant's 'local' protocol model.
	 * 
	 * @param model The 'global' protocol model
	 * @param participant The participant to project
	 * @param journal Journal for reporting issues
	 * @return The 'local' protocol model
	 */
	public ProtocolModel project(ProtocolModel model, Participant participant,
							Journal journal);
	
}
