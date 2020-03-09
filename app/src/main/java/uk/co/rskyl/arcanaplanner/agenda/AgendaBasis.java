package uk.co.rskyl.arcanaplanner.agenda;

import java.util.Calendar;

public class AgendaBasis
{
	private String name;
	private String description;
	private Calendar startTime;
	private Calendar endTime;
	private Float hue;
	//tags
	
	public AgendaBasis ()
	{
		name = "";
		description = "";
		startTime = Calendar.getInstance ();
		endTime   = Calendar.getInstance ();
		
		long startMillis = startTime.getTimeInMillis ();
		final long millisInHour = 1000*60*60;
		startMillis /= millisInHour;
		startMillis *= millisInHour;
		
		startTime.setTimeInMillis (startMillis);
		endTime.setTimeInMillis (startMillis + millisInHour);
	}
	
	public Boolean isActive (Calendar atTime)
	{
		return startTime.before (atTime) && endTime.after (atTime);
	}
	
	public Boolean isActive ()
	{
		return isActive (Calendar.getInstance ());
	}
	
	public Float getProgress (Calendar atTime)
	{
		long tStart = startTime.getTimeInMillis ();
		double tDuration = endTime.getTimeInMillis () - tStart;
		double tProgress = atTime.getTimeInMillis () - tStart;
		return (float) (tProgress / tDuration);
	}
	
	public Float getProgress ()
	{
		return getProgress (Calendar.getInstance ());
	}
}
