package uk.co.rskyl.arcanaplanner;

import android.os.Bundle;
import android.util.Log;

import java.util.Calendar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.rskyl.arcanaplanner.agenda.AgendaEvent;
import uk.co.rskyl.arcanaplanner.agendaeventsummary.AESAdapter;

public
class MainActivity extends AppCompatActivity
{
	@Override protected void onCreate (@Nullable Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		
		setContentView (R.layout.activity_main);
		
		Calendar c0 = Calendar.getInstance ();
		c0.set (2020, Calendar.JANUARY, 21, 16, 0);
		Calendar c1 = Calendar.getInstance ();
		c1.set (2020, Calendar.JANUARY, 21, 17, 30);
		Calendar c2 = Calendar.getInstance ();
		c2.set (2020, Calendar.JANUARY, 21, 17, 30);
		Calendar c3 = Calendar.getInstance ();
		c3.set (2020, Calendar.JANUARY, 21, 18, 30);
		
		AgendaEvent[] data =
			{
				new AgendaEvent (c0, c1),
				new AgendaEvent (c2, c3)
			};
		Log.i ("--[R]", data[0].startTimeString);
		Log.i ("--[R]", data[0].endTimeString);
		Log.i ("--[R]", data[1].startTimeString);
		Log.i ("--[R]", data[1].endTimeString);
		
		RecyclerView recyclerView = findViewById (R.id.recyclerview);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (this);
		recyclerView.setLayoutManager (layoutManager);
		recyclerView.setHasFixedSize (true);
		
		
		Log.i ("--[R]", "!make");
		RecyclerView.Adapter adapter = new AESAdapter (data);
		Log.i ("--[R]", "!set");
		recyclerView.setAdapter (adapter);
		Log.i ("--[R]", "!done");
		
		recyclerView.addItemDecoration (new DividerItemDecoration (this, DividerItemDecoration.VERTICAL));
		
		
		/*
		ProgressBar inbuiltProgess = findViewById (R.id.agenda_event_summary_progressBar2);
		//inbuiltProgess.setMin (0);
		inbuiltProgess.setMax (10);
		inbuiltProgess.setProgress (7);
		//ConstraintLayout constraintLayout = findViewById (R.id.agenda_event_summary_layout);
		TextView endTimeTextView = findViewById (R.id.agenda_event_summary_endTime);
		 */
	}
}
