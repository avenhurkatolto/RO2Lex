package com.geryon.ro2lexicon;

import java.util.ArrayList;

import android.util.Log;


public class SaveloadData {
	private String SaveType; 
	private String chClass;
	private String extraPoints;
	private String SlotName; //SaveSlot
	//private int position;
	private String search;
	private ArrayList<Integer> numberlist;
	private String key="";
	private String value ="";
	
	public SaveloadData(){
		this.SaveType = "";
		this.chClass = "";
		this.extraPoints = "";
		this.SlotName = "";
		//this.position = 0;
		this.search = "";
		this.key = "";
	}
	
	
	
	public SaveloadData(String inType, String inSlotName, ArrayList<Integer> inList, String inExtra_class, int inExtra_points){
		this.SaveType = inType;
		this.chClass = inExtra_class;
		this.extraPoints = String.valueOf(inExtra_points);
		this.SlotName = inSlotName;
		this.numberlist = inList;
	}
	
	
	public SaveloadData(String inType, String inSlotName, ArrayList<Integer> inList){
		this.SaveType = inType;
		this.SlotName = inSlotName;
		this.numberlist = inList;
	}
	
	
	public SaveloadData(String inType, String inSlotName, String inSearch){
		this.SaveType = inType;
		this.SlotName = inSlotName;
		this.search = inSearch;
	}
	
	
	public String createKey(){
		return this.SaveType+"-"+this.SlotName; 
		
	}
	
	public String createValue(){
		if (this.SaveType.equalsIgnoreCase("skill")){
			return this.chClass+this.extraPoints+listToCode(this.numberlist);
		} else if (this.SaveType.equalsIgnoreCase("mats")){
			return listToCode(this.numberlist);
		} else 	{
			return this.search;
		}
	}
	
	
	public String listToCode(ArrayList<Integer> inValues){
		String str ="";
		for (int i = 0; i<inValues.size(); i++){
			str+= inValues.get(i).toString()+"-";
		}
		Log.w("List-to-code: ",str);
		return str;
	}
	public void decodeKey(){
		if (this.SaveType.equalsIgnoreCase("skill")){
			
			
			
			
		} else if (this.SaveType.equalsIgnoreCase("mats")){
			
		} else if (this.SaveType.equalsIgnoreCase("mobsearch")){
		
		
		}
		
	}
	
	
	
	
	
	public ArrayList<Integer> codeToList(){
		ArrayList<Integer> Values = new ArrayList<Integer>();
		//int index1 = 0;
		if (this.SaveType.equalsIgnoreCase("skill")){
			StringBuilder strB = new StringBuilder(this.value);
			//index1 = strB.indexOf("-");
			while (strB.length() >0){
			Values.add(Integer.valueOf(strB.substring(0, strB.indexOf("-"))));
			strB.delete(0, strB.indexOf("-")+1);
			Log.w("StrB: ",strB.toString());
			}	
		} else if(this.SaveType.equalsIgnoreCase("mats")){
		StringBuilder strB = new StringBuilder(this.value);
		//index1 = strB.indexOf("-");
		while (strB.length() >0){
		Values.add(Integer.valueOf(strB.substring(0, strB.indexOf("-"))));
		strB.delete(0, strB.indexOf("-")+1);
		Log.w("StrB: ",strB.toString());
		}
		
	}
		return Values;
	}
}