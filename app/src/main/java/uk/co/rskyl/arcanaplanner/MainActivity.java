package uk.co.rskyl.arcanaplanner;

import android.os.Bundle;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

public
class MainActivity extends AppCompatActivity
{
	@Override protected
	void onCreate (@Nullable Bundle savedInstanceState)
	{
		super.onCreate (savedInstanceState);
		
		setContentView (R.layout.activity_main);
		
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
