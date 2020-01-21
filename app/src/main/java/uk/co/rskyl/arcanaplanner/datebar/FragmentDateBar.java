package uk.co.rskyl.arcanaplanner.datebar;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import uk.co.rskyl.arcanaplanner.R;

public class FragmentDateBar extends Fragment
{
	private ViewModelDateBar viewModel;
	
	@Nullable
	@Override
	public View onCreateView (
		@NonNull LayoutInflater inflater,
		@Nullable ViewGroup container,
		@Nullable Bundle savedInstanceState)
	{
		viewModel = ViewModelProviders.of (this).get (ViewModelDateBar.class);
		View root = inflater.inflate (R.layout.bar_date, container, false);
		final TextView barTextView = root.findViewById (R.id.bar_date_textview);
		viewModel.getText ().observe (this, new Observer<String> ()
		{
			@Override
			public void onChanged (String s)
			{
				barTextView.setText (s);
			}
		});
		
		return root;
	}
}
