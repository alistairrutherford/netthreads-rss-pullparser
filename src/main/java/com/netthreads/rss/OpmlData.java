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
