package com.ztebros.taximobilemultimediasystem;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.ListFragment;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DriverChooseFragment extends ListFragment {

	List<String> mDriverInfoList = new ArrayList<String>();
	ArrayAdapter<String> mAdapter;
	
	OnDriverSelectListener mSelectListener;
	
	public interface OnDriverSelectListener{
		public void onDirverSelect();
	}
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mAdapter = new ArrayAdapter<String>(getActivity(), 
				android.R.layout.simple_list_item_1, mDriverInfoList);
	}

	@Override
	public void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		setListAdapter(mAdapter);
		initListInfo();		
	}
	
	@Override
	public void onAttach(Activity activity) {
		// TODO Auto-generated method stub
		super.onAttach(activity);
		try{
			mSelectListener = (OnDriverSelectListener)activity;
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

	private void initListInfo(){
		mDriverInfoList.clear();
		mDriverInfoList.add("A");
		mDriverInfoList.add("B");
		mDriverInfoList.add("C");
		
		mAdapter.notifyDataSetChanged();
	}

}
