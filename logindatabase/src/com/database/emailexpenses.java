package com.database;

import static com.database.Constants.TABLE_NAME2;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.util.Log;

public class emailexpenses extends Activity implements OnClickListener{
	EventsData db=LogindatabaseActivity.returndbreference();
	private static String[] FROM2 = {"EXPENSEID","PEOPLE_USERID","EXPENSENAME","AMOUNT"};
	Button send;
    EditText address, subject, emailtext;
    int userid1;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
		 Log.d("msg1", "hello");
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.emailexpenses);
	        send=(Button) findViewById(R.id.emailsendbutton);
	        address=(EditText) findViewById(R.id.emailaddress);
	        //can even set text the address
	        subject=(EditText) findViewById(R.id.emailsubject);
	        emailtext=(EditText) findViewById(R.id.emailtext);
	        Bundle bundle=getIntent().getExtras();
	        userid1=bundle.getInt("USERID");
	        System.out.println(userid1);
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
			
				emailtext.setText(builder);
				
				
			}
	        send.setOnClickListener(this);
	                       
	                        
	                                      
	                        }
	   
	    
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		final Intent emailIntent = new Intent(android.content.Intent.ACTION_SEND);
        
        emailIntent.setType("plain/text");
   
        emailIntent.putExtra(android.content.Intent.EXTRA_EMAIL, new String[]{ address.getText().toString()});
 
        emailIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, subject.getText());
 
        emailIntent.putExtra(android.content.Intent.EXTRA_TEXT, emailtext.getText());

      emailexpenses.this.startActivity(Intent.createChooser(emailIntent, "Send mail..."));

	}
	}
	        
	        
	        
	