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
package org.scribble.protocol.ctk;

import java.util.Comparator;

import org.scribble.protocol.ctk.comparators.*;
import org.scribble.protocol.model.*;

public class ComparatorUtil {

	private static java.util.Map<Class<? extends ModelObject>,Comparator<ModelObject>> m_comparators=
		new java.util.HashMap<Class<? extends ModelObject>,Comparator<ModelObject>>();

	static {
		m_comparators.put(Namespace.class, new NamespaceComparator());
		m_comparators.put(ProtocolModel.class, new ModelComparator());
		m_comparators.put(Protocol.class, new ProtocolComparator());
		m_comparators.put(Block.class, new BlockComparator());
		m_comparators.put(ParticipantList.class, new ParticipantListComparator());
		m_comparators.put(Participant.class, new ParticipantComparator());
		m_comparators.put(Interaction.class, new InteractionComparator());
		m_comparators.put(MessageSignature.class, new MessageSignatureComparator());
		m_comparators.put(TypeReference.class, new TypeReferenceComparator());
		m_comparators.put(ProtocolReference.class, new ProtocolReferenceComparator());
		m_comparators.put(ModelReference.class, new ModelReferenceComparator());
		m_comparators.put(LocatedName.class, new LocatedNameComparator());
		m_comparators.put(Raise.class, new RaiseComparator());
		m_comparators.put(TryEscape.class, new TryEscapeComparator());
		m_comparators.put(CatchBlock.class, new CatchBlockComparator());
		m_comparators.put(InterruptBlock.class, new InterruptBlockComparator());
		m_comparators.put(WhenBlock.class, new WhenBlockComparator());
		m_comparators.put(Choice.class, new ChoiceComparator());
		m_comparators.put(Parallel.class, new ParallelComparator());
		m_comparators.put(Repeat.class, new RepeatComparator());
		m_comparators.put(Optional.class, new OptionalComparator());
		m_comparators.put(Run.class, new RunComparator());
		m_comparators.put(DeclarationBinding.class, new DeclarationBindingComparator());
	}

	public static Comparator<ModelObject> getComparator(Class<? extends ModelObject> cls) {
		return(m_comparators.get(cls));
	}
}
