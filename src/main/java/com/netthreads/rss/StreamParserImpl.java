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

import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * Stream parser implementation.
 * 
 */
public class StreamParserImpl<T> implements StreamParser<T>
{
	public static final String TEXT_LINK_DELIMETER = ";";
	public static final String TEXT_TITLE_DELIMETER = " ";

	private XmlPullParserFactory factory = null;
	private XmlPullParser parser = null;

	private List<PullParser<T>> parsers;

	// Control
	private int state = WAITING;

	public StreamParserImpl() throws XmlPullParserException
	{
		parsers = new LinkedList<PullParser<T>>();

		factory = XmlPullParserFactory.newInstance();

		factory.setNamespaceAware(false);

		parser = factory.newPullParser();
	}

	/**
	 * Fetch and parse data.
	 * 
	 * @param stream
	 * @param list
	 * @param dataFactory
	 */
	@Override
	public int fetch(InputStream stream, List<T> list, DataFactory<T> dataFactory)
	{
		state = BUSY;

		reset();

		try
		{
			// Assign stream of input.
			parser.setInput(stream, null);

			int type; // received event type

			// Create holding record
			T data = dataFactory.createRecord();

			while (((type = parser.nextToken()) != XmlPullParser.END_DOCUMENT) && (state != CANCELLED))
			{
				if (type == XmlPullParser.TEXT)
				{
					if (inTarget())
					{
						String text = parser.getText();

						if (text != null)
						{
							processText(text.trim());
						}
					}
				}
				else if (type == XmlPullParser.END_TAG)
				{
					String endTag = parser.getName();

					if (endTag != null)
					{
						if (processEndTag(endTag, data))
						{
							list.add(data);

							// Create a new holding record
							data = dataFactory.createRecord();
						}
					}
				}
				else if (type == XmlPullParser.START_TAG)
				{
					String startTag = parser.getName();

					if (startTag != null)
					{
						processStartTag(startTag, data);
					}
				}
			}
		}
		catch (XmlPullParserException e)
		{
			// Oops
			state = ERROR;
		}
		catch (IOException e)
		{
			// Oops
			state = ERROR;
		}

		if (state != CANCELLED && state != ERROR)
		{
			state = DONE;
		}

		return state;
	}

	/**
	 * Stops the handler process.
	 * 
	 */
	@Override
	public void cancel()
	{
		state = CANCELLED;
	}

	/**
	 * Reset parser state.
	 * 
	 */
	@Override
	public void reset()
	{
		for (PullParser<T> parser : parsers)
		{
			parser.reset();
		}
	}

	/**
	 * Process start tag.
	 * 
	 * @param tag
	 */
	public T processStartTag(String tag, T data)
	{
		boolean ready = false;

		for (PullParser<T> parser : parsers)
		{
			ready |= parser.processStartTag(tag);
		}

		if (ready)
		{
			for (PullParser<T> parser : parsers)
			{
				parser.populateRecord(data);
			}
		}

		return data;
	}

	/**
	 * Process end tag.
	 * 
	 * @param tag
	 */
	public boolean processEndTag(String tag, T data)
	{
		boolean ready = false;

		for (PullParser<T> parser : parsers)
		{
			ready |= parser.processEndTag(tag);
		}

		if (ready)
		{
			for (PullParser<T> parser : parsers)
			{
				parser.populateRecord(data);
			}
		}

		return ready;
	}

	/**
	 * Process text.
	 * 
	 * @param text
	 */
	public void processText(String text)
	{
		for (PullParser<T> parser : parsers)
		{
			parser.processText(text);
		}
	}

	/**
	 * In target item.
	 * 
	 * @return In target item.
	 */
	public boolean inTarget()
	{
		boolean status = false;

		for (PullParser<T> parser : parsers)
		{
			if (parser.inTarget())
			{
				status = true;
				break;
			}
		}

		return status;
	}

	/**
	 * Add parser to our parser pipeline.
	 * 
	 */
	@SuppressWarnings(
	{
			"rawtypes", "unchecked"
	})
	@Override
	public void addParser(PullParser pullParser)
	{
		parsers.add(pullParser);
	}

	/**
	 * Have to implement this unfortunately.
	 * 
	 */
	@Override
	public XmlPullParser getParser()
	{
		return parser;
	}

	/**
	 * Return parser state code.
	 * 
	 */
	@Override
	public int getState()
	{
		return state;
	}

}
