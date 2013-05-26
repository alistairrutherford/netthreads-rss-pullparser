package com.netthreads.rss;


/**
 * Traffic data factory.
 * 
 */
public class RssDataFactory implements DataFactory<RssData>
{
	@Override
	public RssData createRecord()
	{
		return new RssData();
	}

}
