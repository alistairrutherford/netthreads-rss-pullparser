/**
 * -----------------------------------------------------------------------
 * Copyright 2013 - Alistair Rutherford - www.netthreads.co.uk
 * -----------------------------------------------------------------------
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
package com.netthreads.rss;

/**
 * Pull parser interface.
 * 
 */
public interface PullParser<T>
{
	/**
	 * Process start of tag.
	 * 
	 * @param tag
	 * 
	 * @return True if data extracted.
	 */
	public boolean processStartTag(String tag);

	/**
	 * Process end of tag.
	 * 
	 * @param tag
	 * 
	 * @return True if data extracted.
	 */
	public boolean processEndTag(String tag);

	/**
	 * Process text in tag.
	 * 
	 * @param text
	 */
	public void processText(String text);

	/**
	 * Populate a record with data read.
	 * 
	 * @param data
	 */
	public void populateRecord(T data);

	/**
	 * Reset parser data.
	 * 
	 */
	public void reset();

	/**
	 * Within a target tag.
	 * 
	 * @return True if processing a tag.
	 */
	public boolean inTarget();
}
