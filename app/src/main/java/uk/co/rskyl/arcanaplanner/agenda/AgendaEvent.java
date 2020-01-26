package uk.co.rskyl.arcanaplanner.agenda;

import java.util.Calendar;

public class AgendaEvent
{
	public Calendar startTime;
	public Calendar endTime;
	public long durationInMillis;
	public String startTimeString;
	public String endTimeString;
	public float hue;
	
	public AgendaEvent (Calendar start, Calendar end)
	{
		int i = start.get (Calendar.HOUR);
		
		startTime = start;
		startTimeString = String.format ("%02d:%02d", start.get (Calendar.HOUR_OF_DAY), start.get (Calendar.MINUTE));
		endTime = end;
		endTimeString = String.format ("%02d:%02d", end.get (Calendar.HOUR_OF_DAY), end.get (Calendar.MINUTE));
		
		long millisStart = startTime.getTimeInMillis ();
		long millisEnd = endTime.getTimeInMillis ();
		durationInMillis = millisEnd - millisStart;
		
		hue = (float) (360*Math.random ());
	}
	
	public AgendaEvent ()
	{
		this (Calendar.getInstance (), Calendar.getInstance ());
	}
	/*
	public boolean isActive ()
	{
		Calendar current = Calendar.getInstance ();
		return startTime.before (current) && endTime.after (current);
	}*/
	
	/**
	 * Compares the start and end times of the event to determine if the event
	 * is currently active
	 *
	 * @return boolean indicating if the event is currently active.
	 */
	public boolean isActive ()
	{
		return isActive (Calendar.getInstance ());
	}
	
	/**
	 * Compares the start and end times of the event against the time provided and determines if the
	 * event would be active at the given time.
	 *
	 * @param when The Calendar time to test.
	 * @return boolean indicating if the event would be active at the given time.
	 */
	public boolean isActive (Calendar when)
	{
		return startTime.before (when) && endTime.after (when);
	}
	
	public float getProgress ()
	{
		return getProgress (Calendar.getInstance ());
	}
	
	public float getProgress (Calendar when)
	{
		long whenInMillis = when.getTimeInMillis ();
		long startInMillis = startTime.getTimeInMillis ();
		long endInMillis = endTime.getTimeInMillis ();
		
		long progressMillis = whenInMillis - startInMillis;
		long durationMillis = endInMillis  - startInMillis;
		double ratio = ((double) progressMillis)/((double) durationMillis);
		return (float) ratio;
	}
}
