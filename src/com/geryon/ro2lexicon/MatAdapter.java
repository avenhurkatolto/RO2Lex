package com.geryon.ro2lexicon;

import java.util.ArrayList;
import java.util.List;



import android.app.Activity;
import android.content.Context;
import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class MatAdapter extends ArrayAdapter<MatData>{
	private ArrayList<MatData> items;
	private int layoutResourceId;
	private Context context;
	MatHolder holder;
	public MatAdapter(Context context, int layoutResourceId, ArrayList<MatData> items) {
		super(context, layoutResourceId, items);
		this.layoutResourceId = layoutResourceId;
		this.context = context;
		this.items = items;
	}

	
	
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		
		holder = new MatHolder();
		holder.matdata = items.get(position);
		
		if(convertView==null){
		LayoutInflater inflater = ((Activity) context).getLayoutInflater();
		convertView = inflater.inflate(layoutResourceId, parent, false);
		
		 
		 } /*else {
			 
			 holder = (MatHolder) convertView.getTag();
			
		 }*/
		
		//	holder.matdata.setValue(position);
			holder.name = (TextView)convertView.findViewById(R.id.textview_mats_name);
			holder.value = (TextView)convertView.findViewById(R.id.textview_mats_value);
			Resources r = context.getResources();
			int drawableId = r.getIdentifier(items.get(position).getPicname(), "drawable", "com.geryon.ro2lexicon");
			ImageView imageView = (ImageView) convertView
					.findViewById(R.id.image_mats);
			imageView.setImageResource(drawableId);
			setupItem(holder);
			return convertView;
	}
	
	public class MatHolder {
		MatData matdata;
		TextView name;
		TextView value;
		ImageView matPic;
	}
	
	private void setupItem(MatHolder holder) {
		holder.name.setText(holder.matdata.getName()+ " ("+holder.matdata.getPrice()+"/pcs)");
		holder.value.setText(String.valueOf(holder.matdata.getValue()));
	}
	
public int getValue(){
	return holder.matdata.getValue();
}

public void setValue(int inValue){
	holder.matdata.setValue(inValue);
	
	}
}