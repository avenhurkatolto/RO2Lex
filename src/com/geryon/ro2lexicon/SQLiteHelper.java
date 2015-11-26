package com.geryon.ro2lexicon;



import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//import android.util.Log;

public class SQLiteHelper extends SQLiteOpenHelper{

	 public static final String TABLE_SKILLS = "Skills";
	 public static final String TABLE_MATS = "MLMats";
	  public static final String COLUMN_ID = "_id";
	  public static final String COLUMN_CLASS = "Class";
	  public static final String COLUMN_SKILLNAME = "SkillName";
	  public static final String COLUMN_SKILLNUMBER = "SkillNumber";
	  public static final String COLUMN_MAXLVL = "MaxLevel";
	  public static final String COLUMN_DESC = "SkillDesc";
	  public static final String COLUMN_MATNAME = "MatName";
	  public static final String COLUMN_MLPOINTS = "MLPoints";
	  	  
	  private static final String DATABASE_NAME = "skills.db";
	  private static final int DATABASE_VERSION = 1;

	  // Database creation sql statement
	  /*
	  private static final String DATABASE_CREATE = "create table "
	      + TABLE_SKILLS + "(" 
	      + COLUMN_ID + " integer primary key, " 
	      + COLUMN_CLASS + " text, "
	      + COLUMN_SKILLNAME + " text, " 
	      + COLUMN_SKILLNUMBER + " numeric, "
	      + COLUMN_MAXLVL + " numeric, "		
	      + COLUMN_DESC + " text); \n " 
	      + "create table "
	      + TABLE_MATS
	      + "("
	      + COLUMN_ID + " integer primary key, "
	      + COLUMN_SKILLNAME + " text, " 
	      + COLUMN_SKILLNUMBER + " numeric);";
	      */
	      
	  private static final String DATABASE_CREATE = "create table if not exists "
		      + TABLE_SKILLS + "(" 
		      + COLUMN_ID + " integer primary key, " 
		      + COLUMN_CLASS + " text, "
		      + COLUMN_SKILLNAME + " text, " 
		      + COLUMN_SKILLNUMBER + " numeric, "
		      + COLUMN_MAXLVL + " numeric, "		
		      + COLUMN_DESC + " text); ";
	  
	  
	  public SQLiteHelper(Context context) {
	    super(context, DATABASE_NAME, null, DATABASE_VERSION);
	  }

	  
	  @Override
	  public void onCreate(SQLiteDatabase database) {
	  	  database.execSQL(DATABASE_CREATE);
	  }

	  
	  @Override
	  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
	   /* Log.w(DBConnector.class.getName(),
	        "Upgrading database from version " + oldVersion + " to "
	            + newVersion + ", which will destroy all old data");*/
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_SKILLS);
	    db.execSQL("DROP TABLE IF EXISTS " + TABLE_MATS);
	    onCreate(db);
	  }
	
	
}
