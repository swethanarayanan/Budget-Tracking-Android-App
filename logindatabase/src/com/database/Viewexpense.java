package com.database;



import android.app.Activity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import android.widget.TextView;

import static com.database.Constants.TABLE_NAME2;


public class Viewexpense extends Activity 
{
	EventsData db=LogindatabaseActivity.returndbreference();
	private static String[] FROM2 = {"EXPENSEID","PEOPLE_USERID","EXPENSENAME","AMOUNT"};
	

	int userid1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
	        
		Bundle bundle=getIntent().getExtras();
        userid1=bundle.getInt("USERID");
        System.out.println(userid1);
		setContentView(R.layout.viewexpense);
		SQLiteDatabase database = db.getReadableDatabase();
		final Cursor c2=database.query(TABLE_NAME2, FROM2, null, null, null, null, null);
		startManagingCursor(c2);
		if(c2.moveToFirst())
		{
			StringBuilder builder = new StringBuilder("\n" );
			do
			{
				int i=0;
				
				if(Integer.valueOf(c2.getString(i+1))==userid1)
				{
					//how to align
					builder.append(c2.getString(i+2)).append("\t\t\t\t").append(c2.getString(i+3)).append("$").append("\n");
									
				}
				
				
			}while(c2.moveToNext());
			TextView t1 = (TextView) findViewById(R.id.text);
			t1.setText(builder);
			
			
		}
		
	}
	

}