package com.example.homework3;

/**
*
* @assignment Homework3 
* @fileName ResultActivity.java 
* @author Stephen MacNeil 
*
*/

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class ResultActivity extends Activity   {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_result);
		
		ImageView view = (ImageView) findViewById(R.id.imageViewRes);
		TextView title = (TextView) findViewById(R.id.textView1);
		TextView caption = (TextView) findViewById(R.id.textView2);

		Intent intent = getIntent();
		String strTime = intent.getStringExtra("time"); 

		float time = Float.parseFloat(strTime);
		if (time<=20) {
			view.setImageResource(R.drawable.win);
			title.setText("Congratulations !!!");
		} else {
			view.setImageResource(R.drawable.loose);
			title.setText("What took you so long ?!");
		}
		caption.setText("It took you "+time+"s to finish the game.");	

	}
	
	public void done (View view) {
		finish();
	}
	

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.result, menu);
		return true;
	}

}
