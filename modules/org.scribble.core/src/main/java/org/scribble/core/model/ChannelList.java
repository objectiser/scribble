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
package org.scribble.core.model;

/**
 * This class represents the list of channels declared within
 * a Scribble definition.
 */
public class ChannelList extends Activity {

	private static final long serialVersionUID = 1532837321091960033L;

	/**
	 * This method returns the list of channels.
	 * 
	 * @return The list of channels
	 */
	@Reference(containment=true)
	public java.util.List<Channel> getChannels() {
		return(m_channels);
	}
	
	private java.util.List<Channel> m_channels=new ContainmentList<Channel>(this, Channel.class);
}
