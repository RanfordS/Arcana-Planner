package uk.co.rskyl.arcanaplanner.agendaeventsummary;

import android.animation.TimeInterpolator;
import android.annotation.SuppressLint;
import android.transition.ChangeBounds;
import android.transition.Transition;
import android.transition.TransitionManager;
import android.transition.TransitionValues;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.Calendar;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.recyclerview.widget.RecyclerView;
import uk.co.rskyl.arcanaplanner.R;
import uk.co.rskyl.arcanaplanner.agenda.AgendaEvent;
import uk.co.rskyl.arcanaplanner.agenda.ViewEventCircle;
import uk.co.rskyl.arcanaplanner.completionbar.ViewCompletionBar;

public class AESAdapter extends RecyclerView.Adapter<AESAdapter.AESViewHolder>
{
	public static
	class AESViewHolder extends RecyclerView.ViewHolder
	{
		//  views
		public ConstraintLayout layout;
		public TextView startTimeTextView;
		public TextView endTimeTextView;
		public ViewCompletionBar completionBar;
		public ViewEventCircle iconImageView;
		//
		public AgendaEvent event;
		private ConstraintSet activeConstraintSet;
		private ConstraintSet inactiveConstraintSet;
		private Boolean eventActive;
		
		public AESViewHolder (ConstraintLayout view)
		{
			super (view);
			
			//  get the views of common elements
			layout = view;
			startTimeTextView = view.findViewById (R.id.agenda_event_summary_startTime);
			endTimeTextView = view.findViewById (R.id.agenda_event_summary_endTime);
			completionBar = view.findViewById (R.id.agenda_event_summary_completion);
			iconImageView = view.findViewById (R.id.agenda_event_summary_icon);
			//  construct and store the active and inactive constraint sets
			activeConstraintSet = new ConstraintSet ();
			inactiveConstraintSet = new ConstraintSet ();
			activeConstraintSet.clone (view);
			inactiveConstraintSet.clone (view);
			inactiveConstraintSet.setHorizontalBias (R.id.agenda_event_summary_endTime, 0.0f);
			inactiveConstraintSet.constrainHeight (R.id.agenda_event_summary_completion, 1);
			//  set parameters
			eventActive = true;
			
			//Calendar tmp0 = Calendar.getInstance ();
			//Calendar tmp1 = Calendar.getInstance ();
			//event = new AgendaEvent (tmp0, tmp1);
		}
		
		public void setEventActive (Boolean active)
		{
			if (active == eventActive)
			{
				return;
			}
			TransitionManager.beginDelayedTransition (layout);
			ConstraintSet constraintSet = active ? activeConstraintSet : inactiveConstraintSet;
			constraintSet.applyTo (layout);
			eventActive = active;
		}
		
		public Boolean getEventActive ()
		{
			return eventActive;
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
						  .inflate (R.layout.agenda_event_summary,
									parent,
									false);
		
		return new AESViewHolder (layout);
	}
	
	@Override
	public void onBindViewHolder (@NonNull final AESViewHolder holder,
								  int position)
	{
		AgendaEvent event = agendaEvents[position];
		holder.event = event;
		holder.startTimeTextView.setText (event.startTimeString);
		holder.endTimeTextView.setText (event.endTimeString);
		holder.iconImageView.setHue (event.hue);
		holder.completionBar.setProgress (0.0f);
		
		holder.eventActive = event.isActive ();
		if (holder.getEventActive ())
		{
			holder.completionBar.setProgress (event.getProgress ());
		}
		else
		{
			holder.inactiveConstraintSet.applyTo (holder.layout);
		}
		
		holder.iconImageView.setOnClickListener (new View.OnClickListener ()
		{
			@Override public void onClick (View view)
			{
				Boolean active = holder.getEventActive ();
				holder.setEventActive (!active);
			}
		});
	}
	
	@Override
	public int getItemCount ()
	{
		return agendaEvents.length;
	}
}
