package uk.co.rskyl.arcanaplanner;

import android.content.DialogInterface;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.rskyl.arcanaplanner.agenda.AgendaEvent;
import uk.co.rskyl.arcanaplanner.agendaeventsummary.AESAdapter;
import uk.co.rskyl.arcanaplanner.completionbar.ViewCompletionBar;

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
		
		RecyclerView recyclerView = findViewById (R.id.recyclerview);
		RecyclerView.LayoutManager layoutManager = new LinearLayoutManager (this);
		recyclerView.setLayoutManager (layoutManager);
		recyclerView.setHasFixedSize (true);
		
		RecyclerView.Adapter adapter = new AESAdapter (data);
		recyclerView.setAdapter (adapter);
		
		recyclerView.addItemDecoration (new DividerItemDecoration (this, DividerItemDecoration.VERTICAL));
		
		final ViewCompletionBar viewCompletionBar = findViewById (R.id.ViewCompletionBar);
		
		findViewById (R.id.button).setOnClickListener (new View.OnClickListener ()
		{
			@Override public void onClick (View view)
			{
				viewCompletionBar.setProgress ((float) Math.random ());
			}
		});
		
		if (savedInstanceState != null)
		{
			viewCompletionBar.setProgress (savedInstanceState.getFloat ("progress"));
		}
	}
	
	@Override protected void onSaveInstanceState (@NonNull Bundle outState)
	{
		super.onSaveInstanceState (outState);
		ViewCompletionBar viewCompletionBar = findViewById (R.id.ViewCompletionBar);
		outState.putFloat ("progress", viewCompletionBar.getProgress ());
	}
}
