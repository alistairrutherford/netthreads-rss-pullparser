package com.netthreads.rss;

/**
 * Pull parser interface.
 * 
 */
public interface PullParser<T>
{
	/**
	 * Process start of tag.
	 * 
	 * @param tag
	 * 
	 * @return True if data extracted.
	 */
	public boolean processStartTag(String tag);

	/**
	 * Process end of tag.
	 * 
	 * @param tag
	 * 
	 * @return True if data extracted.
	 */
	public boolean processEndTag(String tag);

	/**
	 * Process text in tag.
	 * 
	 * @param text
	 */
	public void processText(String text);

	/**
	 * Populate a record with data read.
	 * 
	 * @param data
	 */
	public void populateRecord(T data);

	/**
	 * Reset parser data.
	 * 
	 */
	public void reset();

	/**
	 * Within a target tag.
	 * 
	 * @return True if processing a tag.
	 */
	public boolean inTarget();
}
