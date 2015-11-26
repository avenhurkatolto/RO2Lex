package com.geryon.ro2lexicon;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.NavUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class About extends Activity {
	
	public Button button_about;
	public TextView textview_about;
	public TextView textview_donators;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_about);
		// Show the Up button in the action bar.
		setupActionBar();
		button_about = (Button) findViewById(R.id.button_donators);
		textview_about = (TextView) findViewById(R.id.textview_about);
		textview_donators = (TextView) findViewById(R.id.textview_donators);
}

	@TargetApi(Build.VERSION_CODES.HONEYCOMB)
	private void setupActionBar() {
		if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB) {
			getActionBar().setDisplayHomeAsUpEnabled(true);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.about, menu);
		return true;
	}

	public void switch_donators(View v){
		//Toast.makeText(getBaseContext(), "Shit", Toast.LENGTH_LONG).show();
		//button_about.setText("Works");
		if (button_about.getText().toString().equalsIgnoreCase("Donators")){
		button_about.setText("About");
		textview_about.setVisibility(TextView.GONE);
		textview_donators.setVisibility(TextView.VISIBLE);
		} else {
		button_about.setText("Donators");
		textview_about.setVisibility(TextView.VISIBLE);
		textview_donators.setVisibility(TextView.GONE);
		}
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