package com.ztebros.taximobilemultimediasystem;

import com.zetbros.taximobliemultimediasystem.R;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

/*
 * 驾驶员资格证信息窗体
 * 显示驾驶员照片和资格证相关信息
 */

public class DriverCertFragment extends Fragment implements OnClickListener {
	
	OnChooseButtonClickListener mChooseListener;
	
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
		Button choose = (Button)view.findViewById(R.id.choose_driver);
		choose.setOnClickListener(this);
		return view;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.choose_driver:
			mChooseListener.onChooseClick();
			break;
		}
	}

}
