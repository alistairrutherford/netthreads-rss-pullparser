/**
 * Copyright (C) 2008 Alistair Rutherford, Glasgow, Scotland, UK, www.netthreads.co.uk
 * 
 */
package com.netthreads.rss;

public class OpmlData extends OpmlBase
{
	public static final String TEXT_META_BOUNDS = "bounds";
	public static final String TEXT_Y = "Y";

	private String text = "";
	private String title = "";
	private String xmlUrl = "";
	private String meta = "";

	private String type = "";
	private String language = "";
	private String htmlUrl = "";

	public String getText()
	{
		return text;
	}

	public void setText(String text)
	{
		this.text = text;
	}

	public String getTitle()
	{
		return title;
	}

	public void setTitle(String title)
	{
		this.title = title;
	}

	public String getXmlUrl()
	{
		return xmlUrl;
	}

	public void setXmlUrl(String xmlUrl)
	{
		this.xmlUrl = xmlUrl;
	}

	public String getType()
	{
		return type;
	}

	public void setType(String type)
	{
		this.type = type;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getHtmlUrl()
	{
		return htmlUrl;
	}

	public void setHtmlUrl(String htmlUrl)
	{
		this.htmlUrl = htmlUrl;
	}

	public String getMeta()
	{
		if (meta == null)
			return "";
		else
			return meta;
	}

	public void setMeta(String meta)
	{
		this.meta = meta;
	}

	/**
	 * Override to make comparison for name.
	 * 
	 * @param The
	 *            object to compare.
	 */
	@Override
	public boolean equals(Object o)
	{
		OpmlData opmlData = (OpmlData) o;

		return this.getTitle().equals(opmlData.getTitle());
	}

}
