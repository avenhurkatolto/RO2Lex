package com.geryon.ro2lexicon;

import java.util.ArrayList;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class SkillsMatsDAO {

private SQLiteDatabase database;
private SQLiteHelper helper;
//private String[] allColumns = {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_ARTIST, SQLiteHelper.COLUMN_TITLE, SQLiteHelper.COLUMN_GAME, SQLiteHelper.COLUMN_SONG, SQLiteHelper.COLUMN_DESC};
//private MusicInfo tempInfo = new MusicInfo();
//private static final String TAG = "DAO";
private Utilities utils;

public SkillsMatsDAO(Context context){
	helper = new SQLiteHelper(context);
}

public void open() throws SQLException {
    database = helper.getWritableDatabase();
  }

public int getLastID(){
	//return database.query(SQLiteHelper.TABLE_MUSICINFO, new String[] {SQLiteHelper.COLUMN_ID}, null, null, null, null, null).toString();
	open();
	int lastId = 0;
	String query = "SELECT _id from MusicInfo order by _id DESC limit 1";
	Cursor c = database.rawQuery(query, null);
	if (c != null && c.moveToFirst()) {
	    lastId = c.getInt(0); //The 0 is the column index, we only have 1 column, so the index is 0
	}
	return lastId;
	
}


public Cursor searchMats(){
	Cursor mCursor = null;
			   /*mCursor = database.query(SQLiteHelper.TABLE_MATS, new String[] {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_MATNAME, SQLiteHelper.COLUMN_MLPOINTS,}, 
		     SQLiteHelper.COLUMN_CLASS + " like '%*%'", null, null, null, null, null);*/
	mCursor = database.query(SQLiteHelper.TABLE_MATS, new String[] {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_MATNAME, SQLiteHelper.COLUMN_MLPOINTS,}, 
		    null, null, null, null, null, null);
		Log.w("MAT-Cursor: ", String.valueOf(mCursor.getCount()));    
		  return mCursor;
		 
		 }
public Cursor search(String chClass){
	Cursor mCursor = null;
			
		   mCursor = database.query(SQLiteHelper.TABLE_SKILLS, new String[] {SQLiteHelper.COLUMN_ID, SQLiteHelper.COLUMN_CLASS, SQLiteHelper.COLUMN_SKILLNAME, SQLiteHelper.COLUMN_SKILLNUMBER,  SQLiteHelper.COLUMN_MAXLVL, SQLiteHelper.COLUMN_DESC }, 
		     SQLiteHelper.COLUMN_CLASS + " like '%" + chClass + "%'", null, null, null, null, null);
		    
		
		  return mCursor;
		 
		 }
	
public void close() {
    helper.close();
  }
/*
public void writeDB (SkillData input){
	utils = new Utilities();
	
		ContentValues values = new ContentValues();
		values.put(SQLiteHelper.COLUMN_ID, input.getId());
		values.put(SQLiteHelper.COLUMN_CLASS, input.getChClass() );// !!!
		values.put(SQLiteHelper.COLUMN_SKILLNAME, input.getSkill() );
		values.put(SQLiteHelper.COLUMN_MAXLVL , input.getMaxLvl());
		values.put(SQLiteHelper.COLUMN_SKILLNUMBER , input.getSkillNr() ); //!!!
		values.put(SQLiteHelper.COLUMN_DESC , input.getDesc());
		
		database.insert(SQLiteHelper.TABLE_SKILLS, null,values);
	}
	*/
	
	
	
	
}

	

