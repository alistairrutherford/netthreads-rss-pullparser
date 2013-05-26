/**
 * Copyright (C) 2008 Alistair Rutherford, Glasgow, Scotland, UK, www.netthreads.co.uk
 *
 */
package com.netthreads.rss;

/**
 * RSS Traffic data data class.
 * 
 */
public class RssData
{
	// ------------------------------------------------------------------------
	// Constants
	// ------------------------------------------------------------------------
	public static final String TEXT_CHANNEL = "channel";
	public static final String TEXT_ID = "guid";
	public static final String TEXT_TITLE = "title";
	public static final String TEXT_LINK = "link";
	public static final String TEXT_URL = "url";
	public static final String TEXT_DESCRIPTION = "description";
	public static final String TEXT_IMAGE = "image";
	public static final String TEXT_ITEM = "item";
	public static final String TEXT_CATEGORY = "category";
	public static final String TEXT_PUBLISH_DATE = "pubDate";

	private String id;
	private String title;
	private String link;
	private String description;
	private String category;
	private String pubDate;

	public String getId()
	{
		return id;
	}

	public void setId(String id)
	{
		this.id = id;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getLink()
	{
		return link;
	}

	public void setLink(String link)
	{
		this.link = link;
	}

	public String getDescription()
	{
		return description;
	}

	public void setDescription(String description)
	{
		this.description = description;
	}

	public String getCategory()
	{
		return category;
	}

	public void setCategory(String category)
	{
		this.category = category;
	}

	public String getPubDate()
	{
		return pubDate;
	}

	public void setPubDate(String pubDate)
	{
		this.pubDate = pubDate;
	}

	@Override
	public String toString()
	{
		return id + ", " + title + ", " + link + ", " + description + ", " + category + ", " + pubDate + ", ";
	}
}