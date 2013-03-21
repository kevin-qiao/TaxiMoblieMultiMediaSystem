package com.ztebros.taximobilemultimediasystem;

import com.zetbros.taximobliemultimediasystem.R;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;
import android.view.Window;

public class TaxiMobileMMActivity extends FragmentActivity implements 
	DriverCertFragment.OnChooseButtonClickListener, 
	DriverChooseFragment.OnDriverSelectListener{
	
	DriverCertFragment certFragment;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.main_page);
		
		certFragment = new DriverCertFragment();
		getSupportFragmentManager().beginTransaction().add(R.id.driver_container, certFragment).commit();
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		
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

}
