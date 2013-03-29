package com.ztebros.tmms.data;

import java.util.ArrayList;
import java.util.List;

public class DriverInfoProvider {
	
	private String mPath;
	private static List<Certificate> certs;

	public DriverInfoProvider(String path){
		mPath = path;
		updateDriverInfo();
	}
	
	public boolean updateDriverInfo(){
		return true;
	}
	
	public static List<Certificate> getInfoList(){
		tempInitList();
		return certs;
	}
	
	private static void tempInitList(){
		certs = new ArrayList<Certificate>();
		
		Certificate cert = new Certificate();
		cert.photo = "/sdcard/tmms/driver/image/a.png";
		cert.name = "Marc";
		cert.carNum = "123456";
		cert.certNum = "9527";
		certs.add(cert);
		
		cert = new Certificate();
		cert.photo = "/sdcard/tmms/driver/image/b.png";
		cert.name = "Maria";
		cert.carNum = "123654";
		cert.certNum = "9528";
		certs.add(cert);
		
		cert = new Certificate();
		cert.photo = "/sdcard/tmms/driver/image/c.png";
		cert.name = "Joy";
		cert.carNum = "321654";
		cert.certNum = "9529";
		certs.add(cert);
	}
	
	public static class Certificate{
		private String photo;
		private String name;
		private String carNum;
		private String certNum;
		
		public void setPhoto(String photo){
			this.photo = photo;
		}
		
		public String getPhoto(){
			return photo;
		}
		
		public void setName(String name){
			this.name = name;
		}
		
		public String getName(){
			return name;
		}
		
		public void setCarNum(String number){
			carNum = number;
		}
		
		public String getCarNum(){
			return carNum;
		}
		
		public void setCertNum(String number){
			certNum = number;
		}
		
		public String getCertNum(){
			return certNum;
		}
	}
}
