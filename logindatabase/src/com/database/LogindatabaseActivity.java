package com.database;

import static com.database.Constants.TABLE_NAME1;
//import static com.database.Constants.TABLE_NAME2;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.EditText;





//theme


public class LogindatabaseActivity extends Activity implements OnClickListener {
   
	private static String[] FROM = {"USERID","FIRSTNAME","LASTNAME", "USERNAME", "PASSWORD","EMAIL","BONUSPOINTS" };
	public static EventsData db;
	EditText t1,t2;
	Button bu1,bu2;
	

	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        Log.d("swe","button");
        db=new EventsData(this);
       
        t1=(EditText)findViewById(R.id.username1);
        Log.d("swe1","button1");
        t2=(EditText)findViewById(R.id.password1);
       
        bu1=(Button)findViewById(R.id.login);
        bu2=(Button)findViewById(R.id.signup1);
       
        
        bu1.setOnClickListener(this);
        bu2.setOnClickListener(this);
       
    }
    public static EventsData returndbreference()
    {
    	return db;
    }

	public void onClick(View v) {
		switch(v.getId())
		{
		 
		case R.id.login:{
			
			System.out.println("Logging in");
			
			SQLiteDatabase database = db.getReadableDatabase();
			
			String username=t1.getText().toString();
			Log.d("username",username);
			String password=t2.getText().toString();
			Log.d("password",password);
		
			
			final Cursor c=database.query(TABLE_NAME1, FROM, null, null, null, null, null);
			
			startManagingCursor(c);
			System.out.println(c.getCount());
			
			if(c.moveToFirst())
			{
				int flag=0;
				
				do
				{
					 int i=0;
					 final int c_userid=c.getInt(i+0);
					 final String c_firstname=c.getString(i+1);
					 final String c_lastname=c.getString(i+2);
					 final int c_bonuspoints=c.getInt(i+6);
					Log.d("username_db",c.getString(i+3));
					Log.d("password_db",c.getString(i+4));
					
				  if(username.equals(c.getString(i+3)))
					  
					  if(password.equals(c.getString(i+4)))
					  {
						  Log.d("status","correct");
						  AlertDialog alertDialog = new AlertDialog.Builder(this).create();
						  alertDialog.setTitle("Status");
						  alertDialog.setMessage("Logged In!");
						  alertDialog.setButton("Proceed", new DialogInterface.OnClickListener() {
						      public void onClick(DialogInterface dialog, int which) {
						 
						       //here you can add functions
						    	startGame(which);
						    }

							private void startGame(int which) {
								// TODO Auto-generated method stub
								Log.d("msg","clicked on"+which);
								Intent i=new Intent(LogindatabaseActivity.this,mainpage.class);
								  Bundle bundle=new Bundle();
								 bundle.putInt("USERID",c_userid);
								 bundle.putString("FIRSTNAME",c_firstname );
								 bundle.putString("LASTNAME",c_lastname );
								 bundle.putInt("BONUSPOINTS", c_bonuspoints);
								 i.putExtras(bundle);
								 
								startActivity(i);
							} });
						  alertDialog.show();
						  flag=1;
						  break;
					  }
				}while(c.moveToNext());
				if(flag==0)
				{
					 Log.d("status","incorrect");
				
					 AlertDialog alertDialog = new AlertDialog.Builder(this).create();
					  alertDialog.setTitle("Status");
					  alertDialog.setMessage("Wrong Username/password.Please enter again!");
					 alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
					      public void onClick(DialogInterface dialog, int which) {
					 
					       //here you can add functions
					    	  startGame(which);
					 
					    }

						private void startGame(int which) {
							// TODO Auto-generated method stub
							Log.d("msg","clicked on"+which);
							Intent i=new Intent(LogindatabaseActivity.this,LogindatabaseActivity.class);
							startActivity(i);
						} });
					  alertDialog.show();
					  
				}
			}
			
		}
		break;
		case R.id.signup1:{ 
			 Log.d("msg","msg");
			System.out.println("signing up");
			Intent i=new Intent(this,newuser.class);
		  
		    
			startActivity(i);	
			
		}
		break;
		}
	}
}