package com.geryon.ro2lexicon;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.View;

public class RO2Lex extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_ro2_lex);
		File file = new File(this.getDatabasePath("skills.db").getPath());
    	Log.d("file", String.valueOf(file));
     	if (!file.exists()) {
    		Log.d("fileExist", "nem");
     		
	    	InputStream myInput;
			try {
				myInput = this.getBaseContext().getAssets().open("skills.db");
	    	// Path to the just created empty db
			String tempDir = new String(this.getDatabasePath("testvalue.db").getPath());
	        File dir = new File(tempDir.substring(0, tempDir.lastIndexOf(File.separator)));
	    	Log.d("dir", String.valueOf(dir));
	    	dir.mkdirs();
			String outFileName = this.getDatabasePath("skills.db").getPath();
			Log.d("oFN", outFileName);
	    	//Open the empty db as the output stream
	    	OutputStream myOutput = new FileOutputStream(outFileName);
	 
	    	//transfer bytes from the inputfile to the outputfile
	    	byte[] buffer = new byte[1024];
	    	int length;
	    	while ((length = myInput.read(buffer))>0){
	    		myOutput.write(buffer, 0, length);
	    	}
	 
	    	//Close the streams
	    	myOutput.flush();
	    	myOutput.close();
	    	myInput.close();
			} catch (IOException e) {
			        				 
        	}
    	}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.ro2_lex, menu);
		return true;
	}
public void startSkills(View view){
	Intent myIntent = new Intent(RO2Lex.this, Skills.class);
	//myIntent.putExtra("key", value); //Optional parameters
	RO2Lex.this.startActivity(myIntent);
}
public void startMats(View view){
	Intent myIntent = new Intent(RO2Lex.this, Mats.class);
	//myIntent.putExtra("key", value); //Optional parameters
	RO2Lex.this.startActivity(myIntent);
}
public void start_about(View view){
	Intent myIntent = new Intent(RO2Lex.this, About.class);
	//myIntent.putExtra("key", value); //Optional parameters
	RO2Lex.this.startActivity(myIntent);
}
}