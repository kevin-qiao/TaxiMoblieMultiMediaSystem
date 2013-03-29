package com.ztebros.tmms;

import java.util.List;

import com.ztebros.tmms.data.DriverInfoProvider.Certificate;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class DriverListAdapter extends BaseAdapter {

	private Context mContext;
	private List<Certificate> mDrivers;
	
	public DriverListAdapter(Context context, List<Certificate> drivers){
		mContext = context;
		mDrivers = drivers;
	}
	
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mDrivers.size();
	}

	@Override
	public Object getItem(int arg0) {
		// TODO Auto-generated method stub
		return mDrivers.get(arg0);
	}

	@Override
	public long getItemId(int arg0) {
		// TODO Auto-generated method stub
		return arg0;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder holder;
		LayoutInflater inflater = LayoutInflater.from(mContext);
		
		if(convertView == null){
			convertView = inflater.inflate(R.layout.driver_item, null);
			holder = new ViewHolder();
			holder.photo = (ImageView)convertView.findViewById(R.id.photo);
			holder.name = (TextView)convertView.findViewById(R.id.name);
			
			convertView.setTag(holder);
		}else{
			holder = (ViewHolder)convertView.getTag();
		}
		
		holder.name.setText(mDrivers.get(position).getName());
		setViewImage(holder.photo, mDrivers.get(position).getPhoto());
		
		return convertView;
	}

	private void setViewImage(ImageView v, String value){
		BitmapFactory.Options options = new BitmapFactory.Options();
		options.inSampleSize = 2;
		
		Bitmap bitmap = BitmapFactory.decodeFile(value, options);
		v.setImageBitmap(bitmap);
	}
	
	class ViewHolder{
		public ImageView photo;
		public TextView name;
	}
}
