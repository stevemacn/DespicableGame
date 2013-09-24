package com.example.homework3;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

/**
*
* @assignment Homework3 
* @fileName MainActivity.java 
* @author Stephen MacNeil 
*
*/

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TableRow;

public class MainActivity extends Activity implements OnClickListener {

	private ArrayList<Integer> nameList;
	private ArrayList<Integer> iconList;
	private ArrayList<ImageView> imageList;
	
	private int iconCounter=0;
	private int iconPlaceHolder=0;

	private Integer icon;
	private long time;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		this.nameList = new ArrayList <Integer> (Arrays.asList(R.drawable.icon1, R.drawable.icon2, R.drawable.icon3, R.drawable.icon4));
		
		createIcons();
		generateIconArray();
		randomizeArray();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@SuppressWarnings("deprecation")
	public void randomizeArray() {
		
		this.time = System.currentTimeMillis();
		this.iconCounter=0;
		this.iconPlaceHolder=0;
		Collections.shuffle(this.nameList);

		Collections.shuffle(this.iconList);
		this.icon = this.nameList.get(0);
		Collections.shuffle(this.iconList);
		ImageView view = (ImageView) findViewById(R.id.imageView1);
		view.setImageResource(this.icon);
		
		int size = (int) getResources().getDimension(R.dimen.focusSize);
		view.setLayoutParams(new LinearLayout.LayoutParams(size, size));
		
		int count=0;
		for (int item: this.iconList) {
			this.imageList.get(count).setTag(item);
			this.imageList.get(count).setOnClickListener(this);
			this.imageList.get(count).setAlpha(255);
			this.imageList.get(count++).setImageResource(item);
		}
	}
	
	public void generateIconArray() {	
		this.iconList = new ArrayList<Integer>();
		
		for(int item : this.nameList ){		
			for (int i=0; i<4; i++) {		
				this.iconList.add(item);	
			}
		}
	}
	
	public void createIcons() {

		this.imageList = new ArrayList<ImageView>();
		int[] alpha = { R.id.tableRow1, R.id.tableRow2, R.id.tableRow3, R.id.tableRow4 };

		for (int item: alpha) {
			for (int b=0; b<4; b++) {
				ImageView imageView = new ImageView(this);
				int size = (int) getResources().getDimension(R.dimen.iconSize);
				imageView.setLayoutParams(new TableRow.LayoutParams(size, size));
				imageList.add(imageView);	
	    		View view = findViewById(item);
		    	((ViewGroup) view).addView(imageView);
			}
		}
	}
	
	@SuppressWarnings("deprecation")
	@Override
	public void onClick(View arg0) {
		ImageView arg1 = (ImageView) arg0;
		if (arg1.getTag().equals(this.icon)) {
			arg0.setOnClickListener(null);
			arg1.setAlpha(110);
			if (this.iconCounter++==3) {				
				if (this.iconPlaceHolder++==3) {
					Intent intent = new Intent(MainActivity.this, ResultActivity.class);
					float time = (float) ((System.currentTimeMillis()-this.time)/1000.0);
					intent.putExtra("time", Float.toString(time)  );
					startActivity(intent);
				} else {
					this.iconCounter=0;
					this.icon = this.nameList.get(this.iconPlaceHolder);
					ImageView view = (ImageView) findViewById(R.id.imageView1);
					view.setImageResource(this.icon);
				}
			}
		} 	
	}
	
	public void exitApp (View v) {
		Intent intent = new Intent(Intent.ACTION_MAIN);
		intent.addCategory(Intent.CATEGORY_HOME);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(intent);
	}
	
	public void restart (View v) {
		randomizeArray();
	}
}
