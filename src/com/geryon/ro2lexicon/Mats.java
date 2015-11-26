package com.geryon.ro2lexicon;

import java.util.ArrayList;
import java.util.zip.Inflater;


import android.annotation.TargetApi;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.database.Cursor;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager.LayoutParams;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Mats extends Activity {
	
	private ListView listview_mats;
	private Button button_mats;
	private TextView textview_mats;
	private SkillsMatsDAO dao;
	public static ArrayList<MatData> matdata; 
	private MatData tempMat;
	private Cursor cursor;
	private MatAdapter adapter;
	private Context context;
	public ImageView mat_img;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_mats);
		// Show the Up button in the action bar.
		setupActionBar();
	listview_mats = (ListView) findViewById(R.id.listview_mats_mats);	
	button_mats = (Button) findViewById(R.id.button_mats_gilgamesh);
	textview_mats = (TextView) findViewById(R.id.textview_mats_sum);
	context = this;
	dao = new SkillsMatsDAO(getBaseContext());
	dao.open();
	loadlist();
	listview_mats.setOnItemClickListener(new OnItemClickListener()
	   {
	      @Override
	      public void onItemClick(AdapterView<?> adapter, View v, final int position,
	            long arg3) 
	      {
	    	  Log.w("ListClicked: ", String.valueOf(position));
	    	  LayoutInflater li = LayoutInflater.from(context);
				View promptsView = li.inflate(R.layout.mat_input, null);

				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
						context);

				// set prompts.xml to alertdialog builder
				alertDialogBuilder.setView(promptsView);

				final EditText userInput = (EditText) promptsView
						.findViewById(R.id.edittext_mats_dialog);

				// set dialog message
				alertDialogBuilder
					.setCancelable(false)
					.setPositiveButton("OK",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						// get user input and set it to result
						// edit text
						if (userInput.getText().toString().equalsIgnoreCase("")){
							Toast.makeText(context, "Please enter a value.", Toast.LENGTH_SHORT).show();
						} else {
					    setAdapterItemValue(Integer.valueOf(userInput.getText().toString()), position);
					    Log.w("Edittext input: ",String.valueOf(position)+userInput.getText().toString());
						}
					    }
					  })
					.setNegativeButton("Cancel",
					  new DialogInterface.OnClickListener() {
					    public void onClick(DialogInterface dialog,int id) {
						dialog.cancel();
					    }
					  });

				// create alert dialog
				AlertDialog alertDialog = alertDialogBuilder.create();

				// show it
				alertDialog.show();
	      }
	   });
	}
	public void loc_gil(View view){
		
		final Dialog mSplashDialog = new Dialog(context);
		
		mSplashDialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		mSplashDialog.setContentView(R.layout.activity_splashscreen);
		/*mat_img = (ImageView)mSplashDialog.findViewById(R.id.);
		mat_img.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
		mSplashDialog.dismiss();		
				
			}
		});*/
		if (getResources().getConfiguration().orientation == 1){
			
		}else {
			
		}
		mSplashDialog.getWindow().setFlags(LayoutParams.FLAG_FULLSCREEN, LayoutParams.FLAG_FULLSCREEN);
		mSplashDialog.getWindow().setLayout(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		mSplashDialog.setCancelable(true);
		mSplashDialog.show();
	}
	
	public void setAdapterItemValue(int inValue, int position){
		adapter.getItem(position).setValue(inValue);
		adapter.notifyDataSetChanged();
		textview_mats.setText(String.valueOf(getSum()));
	}
	
public int getSum(){
	int sum=0;
	for (int i = 0; i<listview_mats.getCount();i++){
	sum+=adapter.getItem(i).getValue()*adapter.getItem(i).getPrice();
	//Log.w("Adapter item: ", )
	}
	
	//Toast.makeText(getBaseContext(), String.valueOf(sum), Toast.LENGTH_LONG).show();
	return sum;
	}
	
	/**
	 * Set up the {@link android.app.ActionBar}, if the API is available.
	 */
	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}
private void loadlist(){
	matdata = new ArrayList<MatData>();
	cursor = dao.searchMats();
	Log.w("Cursor_size", String.valueOf(cursor.getCount()));
	
	for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
    	//  Log.w("Skill: ", cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNAME)));
    	  matdata.add(new MatData(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_MATNAME)),cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_MLPOINTS))));
    	  //tempMat = new MatData(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_MATNAME)),cursor.getInt(cursor.getColumnIndex(SQLiteHelper.COLUMN_MLPOINTS)));
    	     	    
      Log.w("Skills-size", String.valueOf(matdata.size()));
      adapter = new MatAdapter(this, R.layout.mats_list_item, matdata);
		ListView MatListView = (ListView)findViewById(R.id.listview_mats_mats);
		MatListView.setAdapter(adapter);  
	
	}
}
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.mats, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
}