package com.database;

import static com.database.Constants.TABLE_NAME1;
import static com.database.Constants.TABLE_NAME2;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

public class Viewmoneyleft extends Activity
{
	EventsData db=LogindatabaseActivity.returndbreference();
	private static String[] FROM1 = {"USERID","BUDGETLIMIT"};
	private static String[] FROM2 = {"PEOPLE_USERID","AMOUNT"};
	
	int userid1;
	int moneyspent;
	int moneyremaining;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Bundle bundle=getIntent().getExtras();
        userid1=bundle.getInt("USERID");
		setContentView(R.layout.viewmoneyleft);
		SQLiteDatabase database = db.getReadableDatabase();
		final Cursor c2=database.query(TABLE_NAME2,FROM2,null, null, null, null, null);
		startManagingCursor(c2);
	
		
		if(c2.moveToFirst())
		{
			do
			{
				int i=0;
				if(Integer.valueOf(c2.getString(i+0))==userid1)
				{
					moneyspent+=Integer.valueOf(c2.getString(i+1));
				}
				
			}while(c2.moveToNext());
			
			
		}
		
		final Cursor c1=database.query(TABLE_NAME1,FROM1,null, null, null, null, null);
		startManagingCursor(c1);
	
		
		if(c1.moveToFirst())
		{
			do
			{
				int i=0;
				if(Integer.valueOf(c1.getString(i+0))==userid1)
				{
					moneyremaining=Integer.valueOf(c1.getString(i+1))-moneyspent;
				}
			}while(c1.moveToNext());
		}
		
		database.close();
		 AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		  alertDialog.setTitle("Virtual wallet");
		  alertDialog.setMessage("The money left in your wallet is "+moneyremaining+"$");
		  if(moneyremaining<0)
		  {   moneyremaining= -moneyremaining;
			  alertDialog.setMessage("Warning : You have exceeded your budget limit by "+moneyremaining+"$");
		  }
		 alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface  dialog, int which) {
		 
		       //here you can add functions
		    	  startGame(which);
		 
		    }

			private void startGame(int which) {
				// TODO Auto-generated method stub
				/*Log.d("msg","clicked on"+which);
				Intent i=new Intent(Viewmoneyleft.this,mainpage.class);
				startActivity(i);*/
				finish();
			}

			 });
		  alertDialog.show();

		
	}

}
