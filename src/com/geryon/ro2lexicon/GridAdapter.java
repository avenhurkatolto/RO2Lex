package com.geryon.ro2lexicon;

import java.util.ArrayList;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.Resources;
import android.os.Build;
//import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;


public class GridAdapter extends BaseAdapter {
	private Context context;
	private ArrayList<SkillData> skills;
	//private int maxSkills;
	private String Class;
	
	public GridAdapter(Context context, ArrayList<SkillData> inSkills, int max, String inClass) {
		this.context = context;
		this.skills = inSkills;
		//this.maxSkills = max;
		this.Class = inClass;
	}

	public View getView(int pos, View convertView, ViewGroup parent) {

		LayoutInflater inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

		View gridView;

		if (convertView == null) {

			gridView = new View(context);
			
			} else {
			gridView = (View) convertView;
		}
		gridView = inflater.inflate(R.layout.skills_griditem, null);
		
		gridView.setBackgroundResource(getBackgroundId(Class, pos));
		
		
		
		
		TextView textView = (TextView) gridView
				.findViewById(R.id.grid_item_label);
		if (skills.get(pos+1).getSkill().equalsIgnoreCase(""))
		{
			textView.setText("");
			
		} else {
		textView.setText(skills.get(pos+1).getFirst());
		}
		Resources r = context.getResources();
		int drawableId = r.getIdentifier(skills.get(pos+1).getPicname(), "drawable", "com.geryon.ro2lexicon");
		ImageView imageView = (ImageView) gridView
				.findViewById(R.id.grid_item_image);
		imageView.setImageResource(drawableId);
		return gridView;
	}

	@Override
	public int getCount() {
		return this.skills.size()-1;
	}

	@Override
	public SkillData getItem(int position) {
		return skills.get(position);
	}
	
	@Override
	public long getItemId(int position) {
		return position;
	}
	
	private int getBackgroundId(String inClass, int position){
	Resources r = context.getResources();
	if (getBackground(inClass).charAt(position) == '0')
	{
		return 0;
	}else
	{
	return r.getIdentifier("bg_skill_"+getBackground(inClass).charAt(position), "drawable", "com.geryon.ro2lexicon");
	}
	}
	
	
	private String getBackground(String inClass){ 
	String Class = inClass;
	String bg_code = "";
	if (Class.equalsIgnoreCase("assassin"))
	{
		bg_code = "222111111111113395768313768333000";
		}else if (Class.equalsIgnoreCase("beastmaster")){
		bg_code = "020768111111333045";
	
		}else if (Class.equalsIgnoreCase("crecentia")){
		bg_code = "220195195195198133980330";

		}else if (Class.equalsIgnoreCase("knight")){
		bg_code = "222111111111313465768111111333000";
		
		}else if (Class.equalsIgnoreCase("monk")){
		bg_code = "222111313768333000";
		
		}else if (Class.equalsIgnoreCase("priest")){
		bg_code = "222111111111111111111111111333000";
		
		}else if (Class.equalsIgnoreCase("ranger")){
		bg_code = "222111111111111111111111111333000";
		
		}else if (Class.equalsIgnoreCase("rogue")){
		bg_code = "222111111111111313768313768333000";
		
		}else if (Class.equalsIgnoreCase("sorcerer")){
		bg_code = "222111111111131121111111111333000";
		
		}else if (Class.equalsIgnoreCase("soulmaker")){
		bg_code = "22211111111111111119a330";
		
		}else if (Class.equalsIgnoreCase("warrior")){
		bg_code = "222111111111313768111111111333000";
		
		}else if (Class.equalsIgnoreCase("wizard")){
		bg_code = "222111111111111111313010768333000";
	
	}
	return bg_code;
}
}