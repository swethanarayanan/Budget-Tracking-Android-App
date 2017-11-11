package com.database;

import static com.database.Constants.TABLE_NAME1;
import static com.database.Constants.TABLE_NAME2;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class EventsData extends SQLiteOpenHelper{
	
	public static final String DATABASE_NAME="Budgetmgmt2";
	private static final int DATABASE_VERSION=2;
	public EventsData(Context context) {
		super(context,DATABASE_NAME , null, DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onCreate(SQLiteDatabase db) {
		// TODO Auto-generated method stub
		
		db.execSQL("CREATE TABLE People ( USERID INTEGER PRIMARY KEY AUTOINCREMENT, FIRSTNAME TEXT , LASTNAME TEXT,USERNAME TEXT UNIQUE,PASSWORD TEXT ,EMAIL TEXT UNIQUE,BUDGETNAME TEXT, BUDGETLIMIT NUMERIC, BONUSPOINTS NUMERIC);");
                      
		db.execSQL("CREATE TABLE Expenses (EXPENSEID INTEGER PRIMARY KEY AUTOINCREMENT,PEOPLE_USERID INTEGER ,EXPENSENAME TEXT,AMOUNT NUMERIC,Foreign Key (PEOPLE_USERID) references PEOPLE(USERID));");	
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldversion, int newversion) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME1);
		db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME2);
		
		onCreate(db);
		
	}
	
	
	

}
