package uk.co.rskyl.arcanaplanner.completionbar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import uk.co.rskyl.arcanaplanner.R;
import uk.co.rskyl.arcanaplanner.datebar.ViewModelDateBar;

public class FragmentCompletionBar extends Fragment
{
	private ViewModelCompletionBar viewModel;
	
	@Nullable
	@Override
	public View onCreateView (
		@NonNull LayoutInflater inflater,
		@Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState)
	{
		viewModel = ViewModelProviders.of (this).get (ViewModelCompletionBar.class);
		View root = inflater.inflate (R.layout.fragment_completion_bar, container, false);
		
		final float dp = inflater.getContext ().getResources ().getDisplayMetrics ().density;
		final float measure = inflater.getContext ().getResources ().getDimension (R.dimen.completion_bar_height);
		
		final ConstraintLayout constraintLayout = root.findViewById (R.id.completion_bar_layout);
		viewModel.getProgress ().observe (this, new Observer<Float> ()
		{
			@Override
			public void onChanged (Float progress)
			{
				ConstraintSet constraints = new ConstraintSet ();
				constraints.clone (constraintLayout);
				int dp4 = (int) (4*dp + 0.5f);
				int dp1 = (int)(dp + 0.5f);
				/*/
				constraints.connect (R.id.completion_bar_space, ConstraintSet.START,  ConstraintSet.PARENT_ID, ConstraintSet.START, dp4);
				constraints.connect (R.id.completion_bar_space, ConstraintSet.END,    ConstraintSet.PARENT_ID, ConstraintSet.END,    0);
				constraints.connect (R.id.completion_bar_space, ConstraintSet.TOP,    ConstraintSet.PARENT_ID, ConstraintSet.TOP,    0);
				constraints.connect (R.id.completion_bar_space, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
				//*/
				constraints.setHorizontalBias (R.id.completion_bar_space, progress);
				/*/
				constraints.connect (R.id.completion_bar_progress, ConstraintSet.START,  ConstraintSet.PARENT_ID,   ConstraintSet.START, dp1);
				constraints.connect (R.id.completion_bar_progress, ConstraintSet.END,    R.id.completion_bar_space, ConstraintSet.START, dp1);
				constraints.connect (R.id.completion_bar_progress, ConstraintSet.TOP,    ConstraintSet.PARENT_ID, ConstraintSet.TOP,     dp1);
				constraints.connect (R.id.completion_bar_progress, ConstraintSet.BOTTOM, ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM,  dp1);
				//*/
				constraints.applyTo (constraintLayout);
			}
		});
		
		return root;
	}
}
