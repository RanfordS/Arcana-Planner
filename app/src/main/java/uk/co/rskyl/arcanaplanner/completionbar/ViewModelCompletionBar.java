package uk.co.rskyl.arcanaplanner.completionbar;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class ViewModelCompletionBar extends ViewModel
{
	private MutableLiveData<Float> progress;
	
	public ViewModelCompletionBar ()
	{
		progress = new MutableLiveData<> ();
		progress.setValue (0.5f);
	}
	
	public LiveData<Float> getProgress ()
	{
		return progress;
	}
}
