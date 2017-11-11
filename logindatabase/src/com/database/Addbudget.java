package com.database;

import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import static com.database.Constants.TABLE_NAME1;
import static com.database.Constants.USERID;


public class Addbudget extends Activity implements OnClickListener
{
	EventsData db=LogindatabaseActivity.returndbreference();
	EditText t1,t2;
	Button b1;
	int userid1;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle=getIntent().getExtras();
        userid1=bundle.getInt("USERID");
        setContentView(R.layout.addbudget);
        b1=(Button)findViewById(R.id.done);
        t1=(EditText)findViewById(R.id.name);
        t2=(EditText)findViewById(R.id.spendinglimit);
        
        b1.setOnClickListener(this);
        
        
    }
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SQLiteDatabase database=db.getWritableDatabase();
		android.content.ContentValues value=new android.content.ContentValues();
		System.out.println(t1.getText().toString());
		System.out.println(t2.getText().toString());
		
		value.put("BUDGETNAME", t1.getText().toString());
		value.put("BUDGETLIMIT", t2.getText().toString());
				
	    System.out.println(userid1);
	    
		database.update(TABLE_NAME1,value,USERID+"="+ userid1, null);
	
	   //database.execSQL("update people set BUDGETNAME="+ budgetname1 + ",BUDGETLIMIT="+budgetlimit1+"WHERE USERID="+userid1 );
	    
		
				
		value.clear();
		database.close();
		
		finish();
	}
}
    
