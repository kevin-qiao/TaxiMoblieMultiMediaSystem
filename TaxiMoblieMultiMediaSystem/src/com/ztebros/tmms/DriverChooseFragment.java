package com.ztebros.tmms;

import java.util.List;

import com.ztebros.tmms.data.DriverInfoProvider;
import com.ztebros.tmms.data.DriverInfoProvider.Certificate;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ListView;

public class DriverChooseFragment extends ListFragment implements OnClickListener {

	DriverListAdapter mAdapter;
	private List<Certificate> mDrivers;
	
	OnDriverSelectListener mSelectListener;
	OnCancelClickListener mCancelListener;
	
	public interface OnDriverSelectListener{
		public void onDirverSelect();
	}
	
	public interface OnCancelClickListener{
		public void onCancelClick();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		mDrivers = DriverInfoProvider.getInfoList();
		mAdapter = new DriverListAdapter(getActivity(), mDrivers);
		
	}
	
	private void initHeaderView(){
		LayoutInflater inflater = LayoutInflater.from(this.getActivity());
		View headerView = inflater.inflate(R.layout.driver_header, null);
		getListView().addHeaderView(headerView);
		Button cancel = (Button)headerView.findViewById(R.id.cancel);
		cancel.setOnClickListener(this);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		initHeaderView();
		setListAdapter(mAdapter);
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			mSelectListener = (OnDriverSelectListener)activity;
			mCancelListener = (OnCancelClickListener)activity;
		}catch(ClassCastException e){
			throw new ClassCastException(activity.toString() + 
					" must implement OnDriverSelectListener");
		}
	}

	@Override
	public void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		mSelectListener.onDirverSelect();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId()){
		case R.id.cancel:
			mCancelListener.onCancelClick();
			break;
		}
	}
}
