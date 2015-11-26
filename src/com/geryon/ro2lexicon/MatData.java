package com.geryon.ro2lexicon;

import java.util.Locale;

import android.util.Log;

public class MatData {
	int value;
	int price;
	String name;
	
	public MatData (String inName,int inPrice){
		this.value = 0;
		this.price = inPrice;
		this.name = inName;
	}
	
	public void setValue (int inValue){
		this.value = inValue;
	}	
	
	public int getValue(){
		return this.value;
	}
	
	public int getPrice(){
		return this.price;
	}
	
	public String getName(){
		return this.name;
	}
	
	public String getPicname(){
		String tempname = this.name.replace(":", "");
		tempname = tempname.replace("'", "");
		tempname = tempname.replaceAll(" ","_").toLowerCase(Locale.US);
		Log.w("picString", tempname);
		return "mat_"+tempname;
		}
}