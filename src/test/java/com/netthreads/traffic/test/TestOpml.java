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

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;
import org.xmlpull.v1.XmlPullParserException;

import com.netthreads.rss.OpmlData;
import com.netthreads.rss.OpmlDataFactory;
import com.netthreads.rss.OpmlPullParser;
import com.netthreads.rss.StreamParser;
import com.netthreads.rss.StreamParserImpl;

public class TestOpml
{
	private static final String OPML_FILE_NAME = "/feeds.opml";

	@Test
	public void testReadOpml() throws FileNotFoundException, XmlPullParserException
	{
		StreamParser<OpmlData> opmlStreamParser = new StreamParserImpl<OpmlData>();
		opmlStreamParser.addParser(new OpmlPullParser(opmlStreamParser.getParser()));

		List<OpmlData> opmlDataList = new ArrayList<OpmlData>();

		InputStream inputStream = this.getClass().getResourceAsStream(OPML_FILE_NAME);
		OpmlDataFactory opmlDataFactory = new OpmlDataFactory();

		opmlStreamParser.fetch(inputStream, opmlDataList, opmlDataFactory);

		org.junit.Assert.assertTrue(opmlDataList.size() > 0);
	}

}
