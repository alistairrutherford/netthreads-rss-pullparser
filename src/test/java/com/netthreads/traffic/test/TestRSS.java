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
package com.netthreads.traffic.test;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import com.netthreads.rss.OpmlData;
import com.netthreads.rss.OpmlDataFactory;
import com.netthreads.rss.OpmlPullParser;
import com.netthreads.rss.RssData;
import com.netthreads.rss.RssDataFactory;
import com.netthreads.rss.RssPullParser;
import com.netthreads.rss.StreamParser;
import com.netthreads.rss.StreamParserImpl;

/**
 * Simple test to pull RSS traffic data items from UK Highways Agency RSS feed.
 * 
 * This test illustrates the pipeline approach to pulling standard RSS items.
 * 
 * 
 */
public class TestRSS
{
	private static final String OPML_FILE_NAME = "/feeds.opml";

	@Test
	public void testReadRSS() throws XmlPullParserException
	{
		// ---------------------------------------------------------------
		// ---------------------------------------------------------------
		// Fetch Traffic RSS Definitions
		// ---------------------------------------------------------------
		// ---------------------------------------------------------------

		StreamParser<OpmlData> opmlStreamParser = new StreamParserImpl<OpmlData>();
		opmlStreamParser.addParser(new OpmlPullParser(opmlStreamParser.getParser()));

		List<OpmlData> opmlDataList = new ArrayList<OpmlData>();

		InputStream inputStream = this.getClass().getResourceAsStream(OPML_FILE_NAME);
		OpmlDataFactory opmlDataFactory = new OpmlDataFactory();

		opmlStreamParser.fetch(inputStream, opmlDataList, opmlDataFactory);

		org.junit.Assert.assertTrue(opmlDataList.size() > 0);

		// ---------------------------------------------------------------
		// ---------------------------------------------------------------
		// Fetch RSS Data
		// ---------------------------------------------------------------
		// ---------------------------------------------------------------
		StreamParser<RssData> rssStreamParser = new StreamParserImpl<RssData>();

		// ---------------------------------------------------------------
		// We add pull parsers to extract base RSS.
		// ---------------------------------------------------------------
		rssStreamParser.addParser(new RssPullParser());

		for (OpmlData opmlData : opmlDataList)
		{
			try
			{
				System.out.println(opmlData.getXmlUrl());

				// Load data
				URL urlEntity = new URL(opmlData.getXmlUrl());

				InputStream entityStream = urlEntity.openStream();

				List<RssData> list = new LinkedList<RssData>();
				RssDataFactory dataFactory = new RssDataFactory();

				rssStreamParser.fetch(entityStream, list, dataFactory);

				org.junit.Assert.assertTrue(list.size() > 0);

				for (RssData data : list)
				{
					System.out.println(data.toString());
				}
			}
			catch (IOException e)
			{
				org.junit.Assert.assertTrue(false);
			}
		}
	}

}
