package com.ztebros.tmms;

import java.io.File;

import com.ztebros.tmms.R;

import android.os.Bundle;
import android.os.Environment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;

public class TaxiMobileMMActivity extends FragmentActivity implements 
	DriverCertFragment.OnChooseButtonClickListener, 
	DriverChooseFragment.OnDriverSelectListener,
	DriverChooseFragment.OnCancelClickListener{
	
	private DriverCertFragment certFragment;
	private AutoScrollAdsView asav;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_page);
		
		certFragment = new DriverCertFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.driver_container, certFragment).commit();
		
		try {
			initLocal();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		asav = (AutoScrollAdsView)findViewById(R.id.scroll_ads);
		asav.initView(getWindowManager());
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		asav.startScroll();		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.taxi_moblie_mm, menu);
		return true;
	}

	@Override
	public void onChooseClick() {
		// TODO Auto-generated method stub
		DriverChooseFragment chooseFragment = new DriverChooseFragment();
		Bundle args = new Bundle();
		chooseFragment.setArguments(args);
		
		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		transaction.replace(R.id.driver_container, chooseFragment);
		transaction.addToBackStack(null);
		
		transaction.commit();
	}

	@Override
	public void onDirverSelect() {
		// TODO Auto-generated method stub
//		FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
		getSupportFragmentManager().popBackStack();
		
	}
	
	private boolean initLocal() throws Exception{
		//create folder 
		String state = Environment.getExternalStorageState();
		
		if(!state.equals(Environment.MEDIA_MOUNTED)){
			return false;
		}
		
		String sdPath = Environment.getExternalStorageDirectory().getPath();
		
		if(sdPath.isEmpty()){
			return false;
		}
		
		String rootPath = sdPath + "/" + Contracts.ROOT;
		
		try{
			File file = new File(rootPath);
			
			if(!file.exists()){
				file.mkdirs();
			}
		}catch(Exception ex){
			throw new Exception("File exception: " + ex.getMessage());
		}
		
		return true;
	}

	@Override
	public void onCancelClick() {
		// TODO Auto-generated method stub
		getSupportFragmentManager().popBackStack();
	}

}
