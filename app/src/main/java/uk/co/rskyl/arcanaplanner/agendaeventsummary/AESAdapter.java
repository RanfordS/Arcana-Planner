package uk.co.rskyl.arcanaplanner.agendaeventsummary;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.rskyl.arcanaplanner.R;
import uk.co.rskyl.arcanaplanner.agenda.AgendaEvent;

public class AESAdapter extends RecyclerView.Adapter<AESAdapter.AESViewHolder>
{
	public static
	class AESViewHolder extends RecyclerView.ViewHolder
	{
		public TextView startTimeTextView;
		public TextView endTimeTextView;
		public View completionBarFragment;
		//public AgendaEvent event;
		
		public AESViewHolder (ConstraintLayout view)
		{
			super (view);
			startTimeTextView = view.findViewById (R.id.agenda_event_summary_startTime);
			endTimeTextView = view.findViewById (R.id.agenda_event_summary_endTime);
			//completionBarFragment = view.findViewById (R.id.agenda_event_summary_completion_bar);
			
			//Calendar tmp0 = Calendar.getInstance ();
			//Calendar tmp1 = Calendar.getInstance ();
			//event = new AgendaEvent (tmp0, tmp1);
		}
	}
	
	private AgendaEvent[] agendaEvents;
	
	public AESAdapter (AgendaEvent[] events)
	{
		agendaEvents = events;
	}
	
	@NonNull
	@Override
	public AESViewHolder onCreateViewHolder (@NonNull ViewGroup parent, int viewType)
	{
		ConstraintLayout layout = (ConstraintLayout)
			LayoutInflater.from (parent.getContext ())
						  .inflate (R.layout.agenda_event_summary, parent, false);
		AESViewHolder viewHolder = new AESViewHolder (layout);
		return viewHolder;
	}
	
	@Override
	public void onBindViewHolder (@NonNull final AESViewHolder holder,
								  int position)
	{
		AgendaEvent event = agendaEvents[position];
		//holder.event = event;
		holder.startTimeTextView.setText (event.startTimeString);
		holder.endTimeTextView.setText (event.endTimeString);
	}
	
	@Override
	public int getItemCount ()
	{
		return agendaEvents.length;
	}
}
