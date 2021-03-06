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
package org.scribble.protocol.projection.impl;

import org.scribble.protocol.model.*;
import org.scribble.common.logging.Journal;

/**
 * This class provides the WhenBlock implementation of the
 * projector rule.
 */
public class WhenBlockProjectorRule extends AbstractBlockProjectorRule {

	/**
	 * This method determines whether the projection rule is
	 * appropriate for the supplied model object.
	 * 
	 * @param obj The model object to be projected
	 * @return Whether the rule is relevant for the
	 * 				model object
	 */
	public boolean isSupported(ModelObject obj) {
		return(obj.getClass() == WhenBlock.class);
	}
	
	/**
	 * This method creates a new block of the appropriate
	 * type.
	 * 
	 * @return The block
	 */
	protected Block createBlock() {
		return(new WhenBlock());
	}
	
	/**
	 * This method projects the supplied model object based on the
	 * specified role.
	 * 
	 * @param model The model object
	 * @param participant The participant
	 * @param l The model listener
	 * @return The projected model object
	 */
	public ModelObject project(ProjectorContext context, ModelObject model,
					Participant participant, Journal l) {
		WhenBlock ret=(WhenBlock)super.project(context,
				model, participant, l);
		WhenBlock source=(WhenBlock)model;

		if (ret != null && source.getMessageSignature() != null) {
			
			// Project the message signature
			ret.setMessageSignature((MessageSignature)
					context.project(source.getMessageSignature(),
							participant, l));
		}

		return(ret);
	}
	
	/**
	 * This method determines whether a block with empty content should be
	 * filtered out.
	 * 
	 * @return Whether an empty block should be filtered out
	 */
	protected boolean isFilterOutEmptyContent() {
		return(false);
	}
}
