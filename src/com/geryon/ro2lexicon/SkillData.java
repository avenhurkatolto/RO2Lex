package com.geryon.ro2lexicon;

import java.util.Locale;

//import android.util.Log;

public class SkillData {
	public String chClass = "";
	public String chSkill = "";
	public int skillNr = 0;
	public int maxLvl = 0;
	public String desc = "";
	public int current = 0;


public SkillData (String inClass, String inSkill, int inSkillNr, int inMaxLvl, String inDesc){
	this.chClass = inClass;
	this.chSkill = inSkill;
	this.skillNr = inSkillNr;
	this.maxLvl = inMaxLvl;
	this.desc = inDesc;
	
}
public SkillData (){
	this.chClass = "";
	this.chSkill = "";
	this.skillNr = 0;
	this.maxLvl = 0;
	this.desc = "";
}

public String inc(){
	String skillCount ="";
	if (this.current == this.maxLvl){
		if (this.skillNr <=3){
			this.current = 1;
			} else {
				this.current = 0;
			} 
	} else {
		this.current++; 
	}
	skillCount = this.current + "/"+ this.maxLvl;
	return skillCount;
}
public void initialize(){
	if (this.skillNr <=3){
		this.current = 1;
	}
}

public String getFirst(){
	if (this.skillNr <=3){
		return 1 + "/" + this.maxLvl;
	} else return 0 + "/" + this.maxLvl;
}


public void setClass(String inClass){
	this.chClass = inClass;
	}

public void setSkill(String inSkill){
	this.chSkill = inSkill;
	}

public void setSkillNr(int inSkillNr){
	this.skillNr = inSkillNr;
	}

public void setMaxLvl(int inMaxLvl){
	this.maxLvl = inMaxLvl;
	}

public void setDesc(String inDesc){
	this.desc = inDesc;
	}

public String getChClass(){
	return this.chClass;
	}

public String getSkill(){
	return this.chSkill;
	}

public String getDesc(){
	return this.desc;
	}

public int getSkillNr(){
	return this.skillNr;
	}

public int getMaxLvl(){
	return this.maxLvl;
	}

public String getPicname(){
	String tempname = this.chSkill.replace(":", "");
	tempname = tempname.replace("'", "");
	tempname = tempname.replaceAll(" ","_").toLowerCase(Locale.US);
	//Log.w("picString", tempname);
	return "skill_"+tempname;
	}

public void setCurrent(int inSet){
	this.current = inSet;
}
 public int getCurrent(){
	 return this.current;
 }


}