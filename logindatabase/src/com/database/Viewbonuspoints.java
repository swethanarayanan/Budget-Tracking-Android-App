package com.database;

import android.app.Activity;
import android.os.Bundle;
import android.widget.RatingBar;


public class Viewbonuspoints extends Activity
{
	
	int userid1;
	int bonuspoints;
	RatingBar r1;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.viewbonuspoints);

		Bundle bundle=getIntent().getExtras();
		userid1=bundle.getInt("USERID");
		bonuspoints=bundle.getInt("BONUSPOINTS");
		r1=(RatingBar)findViewById(R.id.rating);
		r1.setRating(bonuspoints);
	}

}