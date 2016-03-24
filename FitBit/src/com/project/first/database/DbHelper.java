package com.project.first.database;


import com.project.first.database.DbContract.FitBit;
import com.project.first.database.DbContract.FitBit_One;
import com.project.first.database.DbContract.FitBit_four;
import com.project.first.database.DbContract.FitBit_three;
import com.project.first.database.DbContract.FitBit_two;
import com.project.first.util.Constants;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * this class helps me create a DB and its table
 * @author FitBit
 *
 */
public class DbHelper extends SQLiteOpenHelper {
	
	public static final String DATABASE_NAME = "FitBit.db";
	public static final int DATABASE_VERSION = 1;
	private static final String TAG = "DbHelper";
	
	private static final String TEXT_TYPE = " TEXT";
	private static final String COMMA_SEP = ",";
	private static final String SQL_CREATE_ENTRIES =
	    "CREATE TABLE " + FitBit.TABLE_NAME + " (" +
	    FitBit.COLUMN_NAME_RATE_ID + " INTEGER PRIMARY KEY," +
	    FitBit.COLUMN_NAME_TRACKER + TEXT_TYPE +COMMA_SEP +
	    FitBit.COLUMN_NAME_HEART_RATE + TEXT_TYPE +COMMA_SEP +
	    FitBit.COLUMN_NAME_DATE + TEXT_TYPE +
	     // Any other options for the CREATE command
	    " )";
	
	private static final String SQL_CREATE_ENTRIES_ONE =
		    "CREATE TABLE " + FitBit_One.TABLE_NAME + " (" +
		    FitBit_One.COLUMN_NAME_ACTIVITY_ID + " INTEGER PRIMARY KEY," +
		    FitBit_One.COLUMN_NAME_ACTIVITY + TEXT_TYPE +COMMA_SEP +
		    FitBit_One.COLUMN_NAME_DISTANCE + TEXT_TYPE +COMMA_SEP +
		    FitBit_One.COLUMN_NAME_DATE + TEXT_TYPE +
		     // Any other options for the CREATE command
		    " )";
	
	private static final String SQL_CREATE_ENTRIES_TWO =
		    "CREATE TABLE " + FitBit_two.TABLE_NAME + " (" +
		    FitBit_two.COLUMN_NAME_SLEEP_ID + " INTEGER PRIMARY KEY," +
		    FitBit_two.COLUMN_NAME_POSITION + TEXT_TYPE +COMMA_SEP +
		    FitBit_two.COLUMN_NAME_STATUS + TEXT_TYPE +COMMA_SEP +
		    FitBit_two.COLUMN_NAME_DATE + TEXT_TYPE +
		     // Any other options for the CREATE command
		    " )";
	
	private static final String SQL_CREATE_ENTRIES_THREE =
		    "CREATE TABLE " + FitBit_three.TABLE_NAME + " (" +
		    FitBit_three.COLUMN_NAME_BODY_ID + " INTEGER PRIMARY KEY," +
		    FitBit_three.COLUMN_NAME_BODY_DATE + TEXT_TYPE +COMMA_SEP +
		    FitBit_three.COLUMN_NAME_BODY_FAT + TEXT_TYPE +COMMA_SEP +
		    FitBit_three.COLUMN_NAME_DATE + TEXT_TYPE +
		     // Any other options for the CREATE command
		    " )";
	private static final String SQL_CREATE_ENTRIES_FOUR =
		    "CREATE TABLE " + FitBit_four.TABLE_NAME + " (" +
		    FitBit_four.COLUMN_NAME_FOOD_ID + " INTEGER PRIMARY KEY," +
		    FitBit_four.COLUMN_NAME_FOOD_ACTIVITY + TEXT_TYPE +COMMA_SEP +
		    FitBit_four.COLUMN_NAME_FOOD_BENCHMARK + TEXT_TYPE +COMMA_SEP +
		    FitBit_four.COLUMN_NAME_DATE + TEXT_TYPE +
		     // Any other options for the CREATE command
		    " )";

	public DbHelper(Context context) {
		super(context, DATABASE_NAME,null,DATABASE_VERSION);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		Log.i(TAG, Constants.log_info.onCreate);
		db.execSQL(SQL_CREATE_ENTRIES);
		db.execSQL(SQL_CREATE_ENTRIES_ONE);
		db.execSQL(SQL_CREATE_ENTRIES_TWO);
		db.execSQL(SQL_CREATE_ENTRIES_THREE);
		db.execSQL(SQL_CREATE_ENTRIES_FOUR);
		
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.i(TAG, Constants.log_info.onUpgrade);
		
	}
	
	
	
}