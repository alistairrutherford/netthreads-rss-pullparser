package com.netthreads.rss;

import java.io.InputStream;
import java.util.List;

import org.xmlpull.v1.XmlPullParser;

/**
 * Stream Parser
 * 
 */
public interface StreamParser<T>
{
	public static final int WAITING = 0;
	public static final int BUSY = 1;
	public static final int DONE = 2;
	public static final int CANCELLED = 3;
	
	/**
	 * Parse data into a list of objects of type T.
	 * 
	 * @param inputStream
	 * @param list
	 * 
	 * @return State
	 */
	public int fetch(InputStream stream, List<T> list, DataFactory<T> dataFactory);
	
	/**
	 * Add parser to the parser pipeline.
	 * 
	 * @param pullParser
	 */
	@SuppressWarnings("rawtypes") 
	public void addParser(PullParser pullParser);
	
	/**
	 * Reset parser(s).
	 * 
	 */
	public void reset();
	
	/**
	 * Cancel fetch
	 */
	public void cancel();
	
	/**
	 * Alas, we need access to this for some stuff.
	 * 
	 * @return The pull parser.
	 */
	public XmlPullParser getParser();
}
