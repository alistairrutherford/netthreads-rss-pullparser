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
