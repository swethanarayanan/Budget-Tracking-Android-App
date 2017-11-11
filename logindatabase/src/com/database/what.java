package com.database;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class what extends Activity implements OnClickListener
{
	
	Button b1;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.what);
        b1=(Button)findViewById(R.id.aboutok);
       
        b1.setOnClickListener(this);
        
        
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		finish();
	}
}
    