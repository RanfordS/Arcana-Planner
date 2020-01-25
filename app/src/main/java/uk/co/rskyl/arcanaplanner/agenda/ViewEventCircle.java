package uk.co.rskyl.arcanaplanner.agenda;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import uk.co.rskyl.arcanaplanner.R;

public class ViewEventCircle extends View
{
	private Paint backgroundPaint;
	private Paint foregroundPaint;
	private int backgroundColor;
	private int foregroundColor;
	private Float dp;
	
	public ViewEventCircle (Context context, AttributeSet attributes)
	{
		super (context, attributes);
		TypedArray attributeArray = context.obtainStyledAttributes (attributes,
																	R.styleable.ViewEventCircle,
																	0,
																	0);
		dp = context.getResources ()
					.getDisplayMetrics ()
					.density;
		
		setHue (attributeArray.getFloat (R.styleable.ViewEventCircle_hue, 0f));
		attributeArray.recycle ();
	}
	
	public void setHue (float hue)
	{
		float[] backgroundHSV = {hue, 0.50f, 0.80f};
		float[] foregroundHSV = {hue, 0.25f, 0.85f};
		backgroundColor = Color.HSVToColor (backgroundHSV);
		foregroundColor = Color.HSVToColor (foregroundHSV);
		backgroundPaint = new Paint (Paint.ANTI_ALIAS_FLAG);
		foregroundPaint = new Paint (Paint.ANTI_ALIAS_FLAG);
		backgroundPaint.setColor (backgroundColor);
		foregroundPaint.setColor (foregroundColor);
		
		invalidate ();
		requestLayout ();
	}
	
	@Override
	protected void onDraw (Canvas canvas)
	{
		super.onDraw (canvas);
		float cx = getWidth ()/2;
		float cy = getHeight ()/2;
		float rM = Math.min (cx, cy);
		float rm = rM - 4*dp;
		canvas.drawCircle (cx, cy, rM, backgroundPaint);
		canvas.drawCircle (cx, cy, rm, foregroundPaint);
	}
}
