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

import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

/**
 * Stream Parser
 * 
 */
public interface StreamParser<T>
{
	public static final int WAITING = 0;
	public static final int BUSY = 1;
	public static final int DONE = 2;
	public static final int CANCELLED = 3;
	public static final int ERROR = 4;
	
	/**
	 * Parse data into a list of objects of type T.
	 * 
	 * @param inputStream
	 * @param list
	 * 
	 * @return State
	 */
	public int fetch(InputStream stream, List<T> list, DataFactory<T> dataFactory);
	
	/**
	 * Add parser to the parser pipeline.
	 * 
	 * @param pullParser
	 */
	@SuppressWarnings("rawtypes") 
	public void addParser(PullParser pullParser);
	
	/**
	 * Reset parser(s).
	 * 
	 */
	public void reset();
	
	/**
	 * Cancel fetch
	 */
	public void cancel();
	
	/**
	 * Return parser state.
	 * 
	 * @return The state code.
	 */
	public int getState();
	
	/**
	 * Alas, we need access to this for some stuff.
	 * 
	 * @return The pull parser.
	 */
	public XmlPullParser getParser();
}
