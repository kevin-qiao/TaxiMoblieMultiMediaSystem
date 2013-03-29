package com.ztebros.tmms;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.widget.TextView;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;

public class AutoScrollAdsView extends TextView implements OnClickListener {

	private static final float STEP = 2;
	
	private float mTextLength = 0f;
	private float mWidth = 0f;
	private float mStep = 0f;
	private float mY = 0f;
	private float mMinMarqueeLength = 0.0f;
	private float mMaxMarqueeLength = 0.0f;
	public boolean mScroll = false;
	private Paint mPaint = null;
	private String mText = "";
	
	public AutoScrollAdsView(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
		setOnClickListener(this);
	}

	public AutoScrollAdsView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		// TODO Auto-generated constructor stub
		setOnClickListener(this);
	}

	public AutoScrollAdsView(Context context, AttributeSet attrs) {
		super(context, attrs);
		// TODO Auto-generated constructor stub
		setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if(mScroll){
			stopScroll();
		}else{
			startScroll();
		}
	}

	@Override
	public void onRestoreInstanceState(Parcelable state) {
		// TODO Auto-generated method stub
		super.onRestoreInstanceState(state);
	}

	@Override
	public Parcelable onSaveInstanceState() {
		// TODO Auto-generated method stub
		return super.onSaveInstanceState();
	}
	
	public void initView(WindowManager windowManager){
		mPaint = getPaint();
		mText = getText().toString();
		mTextLength = mPaint.measureText(mText);
		mWidth = getWidth();
		
		if(mWidth == 0){
			if(windowManager != null){
				DisplayMetrics dm = new DisplayMetrics();
				windowManager.getDefaultDisplay().getMetrics(dm);
				mWidth = dm.widthPixels;
			}
		}
		
		mStep = mTextLength;
		mMinMarqueeLength = mWidth + mTextLength;
		mMaxMarqueeLength = mWidth + mTextLength * 2;
		mY = getTextSize() + getPaddingTop();
	}
	
	public static class SavedState extends BaseSavedState{

		public boolean scroll = false;
		public float step = 0f;
		
		public SavedState(Parcel arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
			Bundle bundle = new Bundle();
			bundle = arg0.readBundle();
			scroll = bundle.getBoolean("scroll");
			step = bundle.getFloat("step");
		}

		public SavedState(Parcelable arg0) {
			super(arg0);
			// TODO Auto-generated constructor stub
		}
		
		@Override
		public void writeToParcel(Parcel dest, int flags) {
			// TODO Auto-generated method stub
			super.writeToParcel(dest, flags);
			Bundle bundle = new Bundle();
			bundle.putBoolean("scroll", scroll);
			bundle.putFloat("step", step);
			dest.writeBundle(bundle);
		}

		public static final Parcelable.Creator<SavedState> CREATOR = 
			new Parcelable.Creator<AutoScrollAdsView.SavedState>() {

				@Override
				public SavedState createFromParcel(Parcel source) {
					// TODO Auto-generated method stub
					return new SavedState(source);
				}

				@Override
				public SavedState[] newArray(int size) {
					// TODO Auto-generated method stub
					return new SavedState[size];
				}
			};
	}

	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
		canvas.drawText(mText, mMinMarqueeLength - mStep, mY, mPaint);
		
		if(!mScroll){
			return ;
		}
		
		mStep += STEP;
		if(mStep > mMaxMarqueeLength){
			mStep = mTextLength;
		}
		
		invalidate();
	}
	
	public void startScroll(){
		mScroll = true;
		invalidate();
	}
	
	public void stopScroll(){
		mScroll = false;
		invalidate();
	}
}
