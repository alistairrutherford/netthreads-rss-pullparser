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

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

/**
 * This is a _simple_ XML Pull parser for RSS xml feed.
 * 
 * Type: RSS Data
 * 
 * Sample URL:
 * 
 */
@SuppressWarnings("unused")
public class RssPullParser implements PullParser<RssData>
{
	public static final String TEXT_LINK_DELIMETER = ";";
	public static final String TEXT_TITLE_DELIMETER = " ";

	private XmlPullParserFactory factory = null;
	private XmlPullParser parser = null;

	// In tag
	private boolean inChannel = false;
	private boolean inChannelTitle = false;
	private boolean inChannelLink = false;
	private boolean inChannelDescription = false;
	private boolean inChannelPublishDate = false;
	private boolean inChannelImage = false;
	private boolean inChannelImageTitle = false;
	private boolean inChannelImageLink = false;
	private boolean inChannelImageUrl = false;

	private boolean inItem = false;
	private boolean inItemId = false;
	private boolean inItemTitle = false;
	private boolean inItemLink = false;
	private boolean inItemDescription = false;
	private boolean inItemCategory = false;
	private boolean inItemPublishDate = false;

	// Record values.
	private String rssChannelTitle;
	private String rssChannelLink;
	private String rssChannelDescription;
	private String rssChannelPublish_date;
	private String rssChannelImage_title;
	private String rssChannelImage_link;
	private String rssChannelImageUrl;
	private String rssItemId;
	private String rssItemTitle;
	private String rssIteLink;
	private String rssItemDescription;
	private String rssItempublishDate;
	private String rssItemCategory;

	/**
	 * Setup fields.
	 * 
	 */
	public RssPullParser()
	{
		reset();
	}

	/**
	 * Process start tag
	 * 
	 * @param tag
	 */
	@Override
	public boolean processStartTag(String tag)
	{
		if (tag.equals(RssData.TEXT_ITEM))
		{
			inItem = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_ID))
		{
			inItemId = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_TITLE))
		{
			inItemTitle = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_LINK))
		{
			inItemLink = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_DESCRIPTION))
		{
			inItemDescription = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_PUBLISH_DATE))
		{
			inItemPublishDate = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_CATEGORY))
		{
			inItemCategory = true;
		}
		else if (tag.equals(RssData.TEXT_CHANNEL))
		{
			inChannel = true;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_TITLE))
		{
			inChannelTitle = true;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_LINK))
		{
			inChannelLink = true;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_DESCRIPTION))
		{
			inChannelDescription = true;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_PUBLISH_DATE))
		{
			inChannelPublishDate = true;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_IMAGE))
		{
			inChannelImage = true;
		}
		else if (!inItem && inChannel && inChannelImage && tag.equals(RssData.TEXT_TITLE))
		{
			inChannelImageTitle = true;
		}
		else if (!inItem && inChannel && inChannelImage && tag.equals(RssData.TEXT_LINK))
		{
			inChannelImageLink = true;
		}
		else if (!inItem && inChannel && inChannelImage && tag.equals(RssData.TEXT_URL))
		{
			inChannelImageUrl = true;
		}

		return false;
	}

	/**
	 * Process start tag
	 * 
	 * @param tag
	 */
	@Override
	public boolean processEndTag(String tag)
	{
		boolean ready = false;

		if (tag.equals(RssData.TEXT_ITEM))
		{
			inItem = false;

			ready = true;
		}
		else if (inItem && tag.equals(RssData.TEXT_ID))
		{
			inItemId = false;
		}
		else if (inItem && tag.equals(RssData.TEXT_TITLE))
		{
			inItemTitle = false;
		}
		else if (inItem && tag.equals(RssData.TEXT_LINK))
		{
			inItemLink = false;
		}
		else if (inItem && tag.equals(RssData.TEXT_DESCRIPTION))
		{
			inItemDescription = false;
		}
		else if (inItem && tag.equals(RssData.TEXT_PUBLISH_DATE))
		{
			inItemPublishDate = false;
		}
		else if (inItem && tag.equals(RssData.TEXT_CATEGORY))
		{
			inItemCategory = false;
		}
		else if (tag.equals(RssData.TEXT_CHANNEL))
		{
			inChannel = false;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_TITLE))
		{
			inChannelTitle = false;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_LINK))
		{
			inChannelLink = false;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_DESCRIPTION))
		{
			inChannelDescription = false;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_PUBLISH_DATE))
		{
			inChannelPublishDate = false;
		}
		else if (!inItem && inChannel && tag.equals(RssData.TEXT_IMAGE))
		{
			inChannelImage = false;
		}
		else if (!inItem && inChannel && inChannelImage && tag.equals(RssData.TEXT_TITLE))
		{
			inChannelImageTitle = false;
		}
		else if (!inItem && inChannel && inChannelImage && tag.equals(RssData.TEXT_LINK))
		{
			inChannelImageLink = false;
		}
		else if (!inItem && inChannel && inChannelImage && tag.equals(RssData.TEXT_URL))
		{
			inChannelImageUrl = false;
		}

		return ready;
	}

	/**
	 * Collect text values depending on conditions.
	 * 
	 * @param text
	 */
	@Override
	public void processText(String text)
	{
		if (inItem && inItemTitle)
		{
			rssItemTitle = text;
		}
		else if (inItem && inItemLink)
		{
			rssIteLink += text + TEXT_LINK_DELIMETER;
		}
		else if (inItem && inItemDescription)
		{
			rssItemDescription += text;
		}
		else if (inItem && inItemPublishDate)
		{
			rssItempublishDate = text;
		}
		else if (inItem && inItemCategory)
		{
			rssItemCategory += text + TEXT_LINK_DELIMETER;
		}
		else if (inItem && inItemId)
		{
			rssItemId = text;
		}
		else if (!inItem && inChannelTitle)
		{
			rssChannelTitle = text;
		}
		else if (!inItem && inChannelLink)
		{
			rssChannelLink = text;
		}
		else if (!inItem && inChannelDescription)
		{
			rssChannelDescription += text;
		}
		else if (!inItem && inChannelPublishDate)
		{
			rssChannelPublish_date = text;
		}
		else if (!inItem && inChannelImageTitle)
		{
			rssChannelImage_title = text;
		}
		else if (!inItem && inChannelImageLink)
		{
			rssChannelImage_link = text;
		}
		else if (!inItem && inChannelImageUrl)
		{
			rssChannelImageUrl = text;
		}
	}

	/**
	 * Build record from parsed data.
	 * 
	 */
	@Override
	public void populateRecord(RssData record)
	{
		// Populate record.
		record.setId(rssItemId);
		record.setCategory(rssItemCategory);
		record.setDescription(rssItemDescription);
		record.setLink(rssIteLink);
		record.setPubDate(rssItempublishDate);
		record.setTitle(rssItemTitle);

		// Reset parser fields.
		reset();
	}

	/**
	 * Reset parsed strings.
	 * 
	 */
	@Override
	public void reset()
	{
		rssChannelTitle = "";
		rssChannelLink = "";
		rssChannelDescription = "";
		rssChannelPublish_date = "";
		rssChannelImage_title = "";
		rssChannelImage_link = "";
		rssChannelImageUrl = "";
		rssItemId = "";
		rssItemTitle = "";
		rssIteLink = "";
		rssItemDescription = "";
		rssItempublishDate = "";
		rssItemCategory = "";
	}

	/**
	 * Inside tag
	 * 
	 * @return True if inside tag.
	 */
	@Override
	public boolean inTarget()
	{
		return inItem || inItemId || inItemTitle || inItemLink || inItemDescription || inItemCategory || inItemPublishDate || inChannelDescription || inChannel || inChannelTitle || inChannelLink || inChannelDescription || inChannelPublishDate || inChannelImage || inChannelImageTitle || inChannelImageLink || inChannelImageUrl;
	}

}
