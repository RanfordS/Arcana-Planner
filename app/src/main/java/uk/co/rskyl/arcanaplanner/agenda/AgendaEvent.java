package uk.co.rskyl.arcanaplanner.agenda;

import java.util.Calendar;
import java.util.Date;

public class AgendaEvent
{
	public Calendar startTime;
	public Calendar endTime;
	public String startTimeString;
	public String endTimeString;
	
	public AgendaEvent (Calendar start, Calendar end)
	{
		int i = start.get (Calendar.HOUR);
		
		startTime = start;
		startTimeString = String.format ("%02d:%02d", start.get (Calendar.HOUR_OF_DAY), start.get (Calendar.MINUTE));
		endTime = end;
		endTimeString = String.format ("%02d:%02d", end.get (Calendar.HOUR_OF_DAY), end.get (Calendar.MINUTE));
	}
}
