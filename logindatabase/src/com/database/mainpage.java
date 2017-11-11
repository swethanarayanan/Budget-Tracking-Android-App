package com.database;


import static com.database.Constants.TABLE_NAME1;
import static com.database.Constants.TABLE_NAME2;
import static com.database.Constants.USERID;

import java.util.Calendar;

import android.app.Activity;
import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class mainpage extends Activity implements OnClickListener
{
	Button b1,b2,b3,b4,b5,b6,b7,b8;
	EditText t1;
	float money;
	String firstname,lastname;
	EventsData db=LogindatabaseActivity.returndbreference();
	private static String[] FROM1 = {"USERID","BUDGETLIMIT","BONUSPOINTS"};
	private static String[] FROM2 = {"PEOPLE_USERID","AMOUNT"};
	int bonuspoints;
	int userid1;
	int moneyspent;
	int moneyremaining;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Calendar rightNow = Calendar.getInstance();
		Bundle bundle=getIntent().getExtras();
		userid1=bundle.getInt("USERID");
		firstname=bundle.getString("FIRSTNAME");
		lastname=bundle.getString("LASTNAME");
		bonuspoints=bundle.getInt("BONUSPOINTS");
		int flag=0;
		int month=rightNow.get(Calendar.MONTH)+1;
		Log.d("MONTH", String.valueOf(month));
		int date;
		switch(month)
		{
		case 1:
		case 3:
		case 5:
		case 7:
		case 8:
		case 10:
		case 12: 
		{
			date=rightNow.get(Calendar.DATE);
			if(date==31)
				flag=1;
			Log.d("DATE", String.valueOf(date));
		}
		break;
		case 2:
		{
			date=rightNow.get(Calendar.DATE);
			if(date==28)
				flag=1;
			Log.d("DATE", String.valueOf(date));

		}
		break;
		case 4:
		case 6:
		case 9:
		case 11:
		
		{

			date=rightNow.get(Calendar.DATE);
				if(date==30)
					flag=1;
				Log.d("DATE", String.valueOf(date));
		}
		break;
		
		}
		Log.d("msg2", "hi2");
		if(flag==1)
		{
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
			Log.d("msg3", "hi3");
			final Cursor c1=database.query(TABLE_NAME1,FROM1,null, null, null, null, null);
			startManagingCursor(c1);
		
			
			if(c1.moveToFirst())
			{
				do
				{
					int i=0;
					if(c1.getInt(i)==userid1)
					{
						moneyremaining=c1.getInt(i+1)-moneyspent;
					}
				}while(c1.moveToNext());
			}
			//database.close();
			if(moneyremaining>=0)
			{
				database=db.getWritableDatabase();
				ContentValues value=new ContentValues();
				value.put("BONUSPOINTS", bonuspoints++);
				database.update(TABLE_NAME1,value,USERID+"="+ userid1, null);
				value.clear();
				database.close();
			}
		}
		
		//calendar
		Log.d("msg", "hi");
		setContentView(R.layout.mainpage);
		
		t1=(EditText)findViewById(R.id.welcome);
		t1.setText("Welcome"+" "+firstname+" "+lastname+",");
					
		b1=(Button)findViewById(R.id.button1);
		b2=(Button)findViewById(R.id.button2);
		b3=(Button)findViewById(R.id.button3);
		b4=(Button)findViewById(R.id.button4);
		b5=(Button)findViewById(R.id.button5);
		b6=(Button)findViewById(R.id.button6);
		b7=(Button)findViewById(R.id.button7);
		b8=(Button)findViewById(R.id.button8);
		
		
		b1.setOnClickListener(this);
		b2.setOnClickListener(this);
		b3.setOnClickListener(this);
		b4.setOnClickListener(this);
		b5.setOnClickListener(this);
		b6.setOnClickListener(this);
		b7.setOnClickListener(this);
		b8.setOnClickListener(this);



	}
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch(v.getId())
		{
		//about
		case R.id.button1: {
			
			Intent i=new Intent(mainpage.this,what.class);
			startActivity(i);

		}
		break;
		//add/edit a budget
		case R.id.button2: {
			
			Intent i=new Intent(mainpage.this,Addbudget.class);
			  Bundle bundle=new Bundle();
				 bundle.putInt("USERID",userid1);
				 i.putExtras(bundle);
			startActivity(i);

		}
		break;
		//add an expense to an existing budget
		case R.id.button3: {
			Intent i=new Intent(mainpage.this,Addexpense.class);
			Bundle bundle=new Bundle();
			 bundle.putInt("USERID",userid1);
			 i.putExtras(bundle);
			startActivity(i);

		}
		break;
		//View expenses
		case R.id.button4: {
			
						
			Intent i=new Intent(mainpage.this,Viewexpense.class);
			 Bundle bundle=new Bundle();
			 bundle.putInt("USERID",userid1);
			 i.putExtras(bundle);
			startActivity(i);

		}
		break;
		//View money left
		case R.id.button5: {
			
			Intent i=new Intent(mainpage.this,Viewmoneyleft.class);
			Bundle bundle=new Bundle();
			 bundle.putInt("USERID",userid1);
			 i.putExtras(bundle);
			startActivity(i);
			
		}
		break;
		//view bonus points
		case R.id.button6: {
			Intent i=new Intent(mainpage.this,Viewbonuspoints.class);
			Bundle bundle=new Bundle();
			 bundle.putInt("USERID",userid1);
			 bundle.putInt("BONUSPOINTS", bonuspoints);
			 i.putExtras(bundle);
			startActivity(i);

		}
		break;
		case R.id.button7: {
		//to be done
			finish();
		}
		break;
		case R.id.button8: {
			//to be done
			Intent i=new Intent(mainpage.this,emailexpenses.class);
			Log.d("msg", "hello");
			Bundle bundle=new Bundle();
			 bundle.putInt("USERID",userid1);
			
			 i.putExtras(bundle);
			startActivity(i);

			}
			break;

		}
	}


}
