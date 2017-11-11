package com.database;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import static com.database.Constants.TABLE_NAME2;


public class Addexpense extends Activity implements OnClickListener
{
	EventsData db=LogindatabaseActivity.returndbreference();
	Button b1;
	Spinner s1;
	EditText t1;
	float expense_amt=0;
	String s;
	int userid1;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.addexpense);
		 Bundle bundle=getIntent().getExtras();
	        userid1=bundle.getInt("USERID");
		
		findViews();
		setAdapters();
		
		s1.setOnItemSelectedListener(new MyOnItemSelectedListener());
		b1.setOnClickListener(this);
	}
	
	public class MyOnItemSelectedListener implements OnItemSelectedListener
	{

		public void onItemSelected(AdapterView<?> parent, View v, int pos,
				long id) {
			// TODO Auto-generated method stub
			Toast.makeText(parent.getContext(), "The category is "+ parent.getItemAtPosition(pos).toString(), Toast.LENGTH_LONG).show();
			s= parent.getItemAtPosition(pos).toString();
		}

		public void onNothingSelected(AdapterView<?> arg0) {
			// TODO Auto-generated method stub
			//do nothing
		}
		
	}
	private void findViews()
	{
		b1=(Button)findViewById(R.id.ok);
		s1=	(Spinner)findViewById(R.id.category);
		t1=(EditText)findViewById(R.id.amount);
		
	}
	
	private void setAdapters()
	{
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.categories,
				android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(
				android.R.layout.simple_spinner_dropdown_item);
		s1.setAdapter(adapter);
	}
	
	public void onClick(View v) {
		// TODO Auto-generated method stub
		expense_amt=Float.valueOf(t1.getText().toString());
		SQLiteDatabase database=db.getWritableDatabase();
		android.content.ContentValues value=new android.content.ContentValues();
		
		value.put("PEOPLE_USERID", userid1);
		value.put("EXPENSENAME", s);
		value.put("AMOUNT", expense_amt);
		
		database.insertOrThrow(TABLE_NAME2, null, value);
		
		value.clear();
		database.close();
	
		finish();
	}

}
