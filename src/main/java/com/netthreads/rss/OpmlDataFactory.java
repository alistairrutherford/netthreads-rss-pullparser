package com.netthreads.rss;

import com.netthreads.rss.DataFactory;

/**
 * Traffic data factory.
 * 
 */
public class OpmlDataFactory implements DataFactory<OpmlData>
{
	@Override
	public OpmlData createRecord()
	{
		return new OpmlData();
	}

}
