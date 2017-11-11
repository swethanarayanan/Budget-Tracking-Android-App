package com.database;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import static com.database.Constants.TABLE_NAME1;
public class newuser extends Activity implements OnClickListener{

	 EventsData db=LogindatabaseActivity.returndbreference();
	 Button b1;
	 EditText t1,t2,t3,t4,t5;
	 @Override
	 public void onCreate(Bundle savedInstanceState) {
		  System.out.println("Inside newuser activity");
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.newuser);
	          
	        b1=(Button)findViewById(R.id.signup2);
	        t1=(EditText)findViewById(R.id.firstname);
	        t2=(EditText)findViewById(R.id.lastname);
	        t3=(EditText)findViewById(R.id.username2);
	        t4=(EditText)findViewById(R.id.password2);
	        t5=(EditText)findViewById(R.id.email1);
	        
	        b1.setOnClickListener(this);
	        

}

	public void onClick(View v) {
		// TODO Auto-generated method stub
		SQLiteDatabase database=db.getWritableDatabase();
		android.content.ContentValues value=new android.content.ContentValues();
		
		
		value.put("firstname", t1.getText().toString());
		value.put("lastname", t2.getText().toString());
		value.put("username", t3.getText().toString());
		value.put("password", t4.getText().toString());
		value.put("email", t5.getText().toString());
		value.put("bonuspoints", 0);
		value.put("BUDGETLIMIT", 0);
		
		database.insertOrThrow(TABLE_NAME1, null, value);
		
		value.clear();
		database.close();
		
		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
		  alertDialog.setTitle("Status");
		  alertDialog.setMessage("Signed Up!");
		  alertDialog.setButton("Ok", new DialogInterface.OnClickListener() {
		      public void onClick(DialogInterface dialog, int which) {
		 
		       //here you can add functions
		    	startGame(which);
		    }

			private void startGame(int which) {
				// TODO Auto-generated method stub
				Log.d("msg","clicked on"+which);
				/*Intent i=new Intent(newuser.this,LogindatabaseActivity.class);
				startActivity(i);*/
				finish();
			} });
		  alertDialog.show();
	}
}