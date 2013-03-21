package com.ztebros.taximobilemultimediasystem;

import java.util.Timer;
import java.util.TimerTask;

import com.zetbros.taximobliemultimediasystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

/*
 * 驾驶员资格证信息窗体
 * 显示驾驶员照片和资格证相关信息
 */

public class DriverCertFragment extends Fragment implements OnClickListener, OnTouchListener{
	
	OnChooseButtonClickListener mChooseListener;
	
 	private boolean bVisible = false;
	private Timer timerShow = null;
	private ShowTimerTask showTimerTask = null;
 	
	static Button choose;
	Button good;
	Button satisfactory;
	Button unsatisfactory;
	
	public interface OnChooseButtonClickListener{
		public void onChooseClick();
	}

	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			mChooseListener = (OnChooseButtonClickListener)activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + 
					" must implement OnChooseButtonClickListener");
		}
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View view = inflater.inflate(R.layout.cert_info_pane, container, false);
		
		view.setOnTouchListener(this);
		
		choose = (Button)view.findViewById(R.id.choose_driver);
		good = (Button)view.findViewById(R.id.good);
		satisfactory = (Button)view.findViewById(R.id.satisfactory);
		unsatisfactory = (Button)view.findViewById(R.id.unsatisfactory);
		
		choose.setVisibility(View.INVISIBLE);
		
		choose.setOnClickListener(this);
		good.setOnClickListener(this);
		satisfactory.setOnClickListener(this);
		unsatisfactory.setOnClickListener(this);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if(showTimerTask != null){
			showTimerTask.cancel();
		}
		if(timerShow != null){
			timerShow.cancel();
			timerShow = null;
		}
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		timerShow = new Timer();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.choose_driver:
			mChooseListener.onChooseClick();
			break;
			
		case R.id.good:
			postFeedback();
			break;
			
		case R.id.satisfactory:
			postFeedback();
			break;
			
		case R.id.unsatisfactory:
			postFeedback();
			break;
		}
	}

	private void postFeedback(){
		Toast.makeText(getActivity(), getString(R.string.feedback_toast), Toast.LENGTH_LONG).show();
	}

	public boolean onTouch(View v, int actionDown) {
		// TODO Auto-generated method stub
		choose.setVisibility(View.VISIBLE);
		
		return true;
	}

	@Override
	public boolean onTouch(View arg0, MotionEvent arg1) {
		// TODO Auto-generated method stub
		
		if(!bVisible){
			bVisible = true;
			choose.setVisibility(View.VISIBLE);
			
			if(timerShow != null){
				if(showTimerTask != null){
					showTimerTask.cancel();
				}
				
				showTimerTask = new ShowTimerTask();
				timerShow.schedule(showTimerTask, 3000);
			}
		}
		
		return true;
	}
	
	static private Handler showHandler = new Handler(){

		@Override
		public void handleMessage(Message msg) {
			// TODO Auto-generated method stub
			super.handleMessage(msg);
			Bundle bundle = msg.getData();
			boolean visible = bundle.getBoolean("visible");
			if(visible){
				choose.setVisibility(View.VISIBLE);
			}else{
				choose.setVisibility(View.INVISIBLE);
			}
		}
		
	};
	
	class ShowTimerTask extends TimerTask{

		@Override
		public void run() {
			// TODO Auto-generated method stub
			bVisible = false;
			Message msg = new Message();
			Bundle bundle = new Bundle();
			bundle.putBoolean("visible", bVisible);
			showHandler.sendMessage(msg);
		}
	}
}
