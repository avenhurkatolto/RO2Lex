package com.geryon.ro2lexicon;

import java.util.ArrayList;

import com.actionbarsherlock.app.SherlockActivity;
import com.actionbarsherlock.view.Menu;
import com.actionbarsherlock.view.MenuItem;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;

import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.util.Log;


import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemLongClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.GridView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class Skills extends SherlockActivity{

	Spinner classSpin;
	Spinner addSpin;
	GridView gridView;
	private SkillsMatsDAO dao;
	private Cursor cursor;
	private ArrayList<SkillData> skills;
	private SkillData tempskill;
	private int max;
	private String Class = "";
	AlertDialog ad;
	TextView tv_skillCount;
	private int bonus = 0;
	private String saveName = "";
	private int slotPos = 0;
	private SaveloadData converter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_skills);
		// Show the Up button in the action bar.
		//setupActionBar();
		dao = new SkillsMatsDAO(getBaseContext());
		dao.open();
		ad = new AlertDialog.Builder(this).create();
		tv_skillCount = (TextView) findViewById(R.id.skillp_counter);
		classSpin = (Spinner) findViewById(R.id.classSelect);
		classSpin.setOnItemSelectedListener(new OnItemSelectedListener() {


			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				Class = parent.getItemAtPosition(pos).toString();
				// Log.w("Temp:", Class);

				loadgrid(Class, null);
				recalculate();
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		addSpin = (Spinner) findViewById(R.id.bonus_skillpoints);

		final Integer[] items = new Integer[]{0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		final ArrayAdapter<Integer> addAdapter = new ArrayAdapter<Integer>(this,android.R.layout.simple_spinner_item, items);
		addSpin.setAdapter(addAdapter);

		addSpin.setOnItemSelectedListener(new OnItemSelectedListener() {


			public void onItemSelected(AdapterView<?> parent, View view, int pos,
					long id) {
				bonus = items[pos];

				recalculate();
			}

			public void onNothingSelected(AdapterView<?> arg0) {

			}
		});
		gridView = (GridView) findViewById(R.id.skillGrid);
		gridView.setVisibility(View.GONE);

	}

	/*@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}*/

	public boolean onCreateOptionsMenu(Menu menu) {
		com.actionbarsherlock.view.MenuInflater inflater = getSupportMenuInflater();
		inflater.inflate(R.menu.skills, menu);
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

		case R.id.action_load:
			Toast.makeText(this, "Load", Toast.LENGTH_SHORT).show();
			//	popupLoadList();
			return true;



		case R.id.action_save:
			Toast.makeText(this, "Save", Toast.LENGTH_SHORT).show();
			popupSaveDialog();

			return true;

		}
		return super.onOptionsItemSelected(item);
	}

	private void popupSaveDialog(){
		LayoutInflater li = LayoutInflater.from(this);
		View promptsView = li.inflate(R.layout.dialog_save, null);
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				this);

		// set prompts.xml to alertdialog builder
		alertDialogBuilder.setView(promptsView);
		final EditText userInput = (EditText) promptsView
				.findViewById(R.id.editText_dialog_save);


		alertDialogBuilder
		.setCancelable(false)
		.setPositiveButton("OK",
				new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog,int id) {
				// get user input and set it to result
				// edit text
				//Toast.makeText(getBaseContext(), userInput.getText(), Toast.LENGTH_LONG).show();
				saveName = userInput.getText().toString();

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

	private void SaveSkills(){
		converter = new SaveloadData("skill", saveName, getIntegerArray(), Class, bonus);



	}
	
	private void saveDialog(){




	}

	private void loadDialog(){




	}


	private void recalculate(){
		int tempint=52;
		for (int i = 0; i<skills.size(); i++){
			tempint -= skills.get(i).getCurrent();
		}
		tempint += bonus;
		tv_skillCount.setText(String.valueOf(tempint));
	}
	private ArrayList<Integer> getIntegerArray(){
		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = 0; i < skills.size(); i++){
			list.add(skills.get(i).getCurrent());
			Log.w("List"+String.valueOf(i), String.valueOf(skills.get(i).getCurrent()));
		}
		return list;


	}


	protected void alertbox(int position)  
	{  
		new AlertDialog.Builder(this)  
		.setMessage(skills.get(position).getDesc())  
		.setTitle(skills.get(position).getSkill())  
		.setCancelable(true)  
		.setNeutralButton(android.R.string.ok,  
				new DialogInterface.OnClickListener() {  
			public void onClick(DialogInterface dialog, int whichButton){}  
		})  
		.show();  
	}


	public void loadgrid (String chSkill, String skillSet ){
		gridView.setVisibility(View.VISIBLE);
		skills = new ArrayList<SkillData>();
		//tempskill = new SkillData();


		if (skillSet == null) {
			cursor = dao.search(chSkill);
			// Log.w("Cursor_size", String.valueOf(cursor.getCount()));
			max = cursor.getCount();
			int temp = 0;

			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				//	    	  Log.w("Skill: ", cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNAME)));
				if ((temp = cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNUMBER))) > max){
					max = temp;
					//  Log.w("Max: ", String.valueOf(max));
				}
			}
			for (int i = 0; i<max+1;i++){
				skills.add(new SkillData());

			}  
			//   Log.w("Skills-size", String.valueOf(skills.size()));
			for(cursor.moveToFirst(); !cursor.isAfterLast(); cursor.moveToNext()) {
				//  Log.w("Skill: ", cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNAME)));

				tempskill = new SkillData();
				tempskill.setClass(cursor.getString(cursor.getColumnIndex(SQLiteHelper.COLUMN_CLASS)));
				tempskill.setSkill(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNAME)));
				tempskill.setMaxLvl(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_MAXLVL)));
				tempskill.setDesc(cursor.getString(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_DESC)));
				tempskill.setSkillNr(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNUMBER)));
				tempskill.initialize();
				skills.set(cursor.getInt(cursor.getColumnIndexOrThrow(SQLiteHelper.COLUMN_SKILLNUMBER)), tempskill);

				// Log.w("Skills-size", String.valueOf(skills.size()));
			}

		}else {
			//LOAD

		}
		gridView.setAdapter(new GridAdapter(this, skills, max, Class));

		gridView.setOnItemClickListener(new OnItemClickListener() {
			public void onItemClick(AdapterView<?> parent, View v,
					int position, long id) {
				if (skills.get(position+1).getSkill().equalsIgnoreCase("")){
					//	Toast.makeText(getBaseContext(), "Misclick, nothing's here :( ", Toast.LENGTH_SHORT).show();	

				}else {

					//skills.get(position+1).inc();
					//Log.w("Click! New value:", String.valueOf(skills.get(position+1).getCurrent()));
					TextView tv = (TextView) v.findViewById(R.id.grid_item_label);
					tv.setText(skills.get(position+1).inc());
					recalculate();
				}
			}
		});

		gridView.setOnItemLongClickListener(new OnItemLongClickListener() {
			public boolean onItemLongClick(AdapterView<?> parent, View v,
					int position, long id) {

				//skills.get(position+1).inc();
				//	Log.w("Long Click! New value:", String.valueOf(skills.get(position+1).getCurrent()));
				alertbox(position+1);

				return false;
			}
		});

	}
}