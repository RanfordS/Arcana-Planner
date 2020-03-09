package uk.co.rskyl.arcanaplanner.completionbar;

import android.content.Context;
import android.content.res.Resources;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewOutlineProvider;

import uk.co.rskyl.arcanaplanner.R;

public class ViewCompletionBar extends View
{
	private Float progress;
	
	private Paint backgroundPaint;
	private int backgroundColor;
	private Paint foregroundPaint;
	private int foregroundColor;
	private Float dp;
	
	private class OutlineProviderCompletionBar extends ViewOutlineProvider
	{
		@Override
		public void getOutline (View view, Outline outline)
		{
			int w = getWidth ();
			int h = getHeight ();
			int z = 0;
			outline.setRoundRect (z, z,
								  w, h,
								  h/2);
		}
	}
	
	public ViewCompletionBar (Context context, AttributeSet attributes)
	{
		super (context, attributes);
		TypedArray attributeArray = context.getTheme ()
										   .obtainStyledAttributes (attributes,
																	R.styleable.ViewCompletionBar,
																	0,
																	0);
		
		progress = attributeArray.getFloat (R.styleable.ViewCompletionBar_progress, 0.5f);
		
		Resources resources = context.getResources ();
		backgroundColor = resources.getColor (R.color.colorNearBlack);
		foregroundColor = resources.getColor (R.color.colorNearWhite);
		dp = resources.getDisplayMetrics ().density;
		
		attributeArray.recycle ();
		
		setOutlineProvider (new OutlineProviderCompletionBar ());
		init ();
	}
	
	public void setProgress (Float progress)
	{
		this.progress = progress;
		invalidate ();
		requestLayout ();
	}
	
	public Float getProgress ()
	{
		return progress;
	}
	
	private void init ()
	{
		backgroundPaint = new Paint (Paint.ANTI_ALIAS_FLAG);
		backgroundPaint.setColor (backgroundColor);
		
		foregroundPaint = new Paint (Paint.ANTI_ALIAS_FLAG);
		foregroundPaint.setColor (foregroundColor);
	}
	/*/
	@Override
	protected void onMeasure (int widthMeasureSpec, int heightMeasureSpec)
	{
		int minw = (int) (3*dp + 0.5f);
		int minh = (int) (3*dp + 0.5f);
		int w = resolveSizeAndState (minw, widthMeasureSpec, 1);
		int h = resolveSizeAndState (minh, heightMeasureSpec, 1);
		setMeasuredDimension (w, h);
	}
	//*/
	@Override
	protected void onDraw (Canvas canvas)
	{
		/*/
		 b               w-b
		0| h/2      w-h/2 |w
		|| |            | ||
		 ################## -- h
		##::::::::::::::::##-- h-b
		#::::::::::::::::::#
		##::::::::::::::::##-- b
		 ################## -- 0
		     |            |
		   h-b ---------> w-b
		 */
		super.onDraw (canvas);
		float w = getWidth ();
		float h = getHeight ();
		float b = 1*dp;
		float z = 0;
		float p = h + (w - h)*progress - b;
		canvas.drawRoundRect (z, z,
							  w, h,
							  h/2, h/2,
							  backgroundPaint);
		if (h > 1.0f)
		{
			canvas.drawRoundRect (b, b,
								  p, h - b,
								  h/2 - b, h/2 - b,
								  foregroundPaint);
		}
	}
}
