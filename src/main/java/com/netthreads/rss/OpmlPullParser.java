/**
 * Copyright (C) 2008 Alistair Rutherford, Glasgow, Scotland, UK, www.netthreads.co.uk
 * 
 */
package com.netthreads.rss;

import org.xmlpull.v1.XmlPullParser;

/**
 * This is a simple XML Pull parser for Traffic OPML xml definitions.
 * 
 * Type: OPML
 * 
 * Sample URL:
 * 
 * view-source:http://www.bbc.co.uk/travelnews/tpeg/tpeg.opml
 * 
 */
public class OpmlPullParser implements PullParser<OpmlData>
{
	private XmlPullParser parser = null;

	// In tag
	private boolean inOpml = false;
	private boolean inHead = false;
	private boolean inBody = false;
	private boolean inOutline = false;

	private String text;
	private String title;
	private String xmlUrl;
	private String type;
	private String language;
	private String htmlUrl;
	private String meta;

	public OpmlPullParser(XmlPullParser parser)
	{
		this.parser = parser;
	}

	/**
	 * Process start tag
	 * 
	 * @param tag
	 */
	public boolean processStartTag(String tag)
	{
		boolean target = false;

		if (tag.equals(OpmlData.TEXT_OPML))
		{
			inOpml = true;
		}
		else if (tag.equals(OpmlData.TEXT_HEAD))
		{
			inHead = true;
		}
		else if (tag.equals(OpmlData.TEXT_BODY))
		{
			inBody = true;
		}
		else if (tag.equals(OpmlData.TEXT_OUTLINE))
		{
			inOutline = true;

			text = parser.getAttributeValue(null, OpmlData.TEXT_TEXT);
			title = parser.getAttributeValue(null, OpmlData.TEXT_TITLE);
			xmlUrl = parser.getAttributeValue(null, OpmlData.TEXT_XMLURL);
			type = parser.getAttributeValue(null, OpmlData.TEXT_TYPE);
			language = parser.getAttributeValue(null, OpmlData.TEXT_LANGUAGE);
			htmlUrl = parser.getAttributeValue(null, OpmlData.TEXT_HTMLURL);
			meta = parser.getAttributeValue(null, OpmlData.TEXT_META);

			target = true;
		}

		return target;
	}

	/**
	 * Process start tag
	 * 
	 * @param tag
	 * 
	 * @return True if hit end of item.
	 */
	public boolean processEndTag(String tag)
	{
		boolean target = false;

		if (tag.equals(OpmlData.TEXT_OPML))
		{
			inOpml = false;
		}
		else if (tag.equals(OpmlData.TEXT_HEAD))
		{
			inHead = false;
		}
		else if (tag.equals(OpmlData.TEXT_BODY))
		{
			inBody = false;
		}
		else if (tag.equals(OpmlData.TEXT_OUTLINE))
		{
			inOutline = false;

			target = true;
		}

		return target;
	}

	/**
	 * Collect text values depending on conditions.
	 * 
	 * @param text
	 */
	public void processText(String text)
	{
		// No Text in this format
	}

	/**
	 * Returns true when we are inside one of the target blocks.
	 * 
	 * @return Status of target block.
	 */
	public boolean inTarget()
	{
		boolean state = inOpml || inBody || inHead || inOutline;
		return state;
	}

	@Override
	public void populateRecord(OpmlData data)
	{
		data.setText(text);
		data.setTitle(title);
		data.setXmlUrl(xmlUrl);
		data.setType(type);
		data.setLanguage(language);
		data.setHtmlUrl(htmlUrl);
		data.setMeta(meta);
	}

	@Override
	public void reset()
	{
		text = "";
		title = "";
		xmlUrl = "";
		type = "";
		language = "";
		htmlUrl = "";
		meta = "";
	}
}
