package uk.co.rskyl.arcanaplanner.datebar;

import android.os.Handler;
import android.os.Message;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelDateBar extends ViewModel
{
	private MutableLiveData<String> dateText;
	private Timer timer;
	
	private TimerTask timerTask = new TimerTask ()
	{
		@Override public void run ()
		{
			handler.sendEmptyMessage (0);
		}
	};
	
	private final Handler handler = new Handler (new Handler.Callback ()
	{
		@Override public boolean handleMessage (Message message)
		{
			updateText ();
			return false;
		}
	});
	
	public ViewModelDateBar ()
	{
		dateText = new MutableLiveData<> ();
		timer = new Timer ();
		
		updateText ();
		
		// Set timer event for midnight
		Calendar calendar = Calendar.getInstance ();
		calendar.set (Calendar.HOUR_OF_DAY, 24);
		calendar.set (Calendar.MINUTE, 0);
		calendar.set (Calendar.SECOND, 0);
		
		timer.scheduleAtFixedRate (timerTask, calendar.getTime (), 24*60*60*1000);
	}
	
	public void updateText ()
	{
		SimpleDateFormat dateFormat = new SimpleDateFormat ("yyyy - MMM - dd");
		dateText.setValue (dateFormat.format (new Date ()));
	}
	
	public LiveData<String> getText ()
	{
		return dateText;
	}
}