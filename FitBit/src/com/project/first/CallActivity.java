package com.project.first;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import org.json.JSONArray;
import org.json.JSONObject;

import android.annotation.SuppressLint;
import android.app.ActionBar;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.util.TypedValue;
import android.view.MenuItem;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.project.first.database.DbOperations;
import com.project.first.parser.JSONParser;

public class CallActivity extends Activity {

	String item;
	ProgressDialog pDialog;
	String output;
	//private static String url;
	TableLayout tl;
	TableRow tr;
	TextView companyTV,valueTV;
	TextView header1,header2;
	TableLayout Display_table;
	public static String[] key;
	public static String[] value;
	JSONArray jsonarray = null;;
	String curr_day,today_date;

	@SuppressLint("SimpleDateFormat")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_call);
		item = getIntent().getExtras().getString("itemvalue");
		Toast.makeText(this, item, Toast.LENGTH_SHORT).show();
		ActionBar actionBar = getActionBar();
		actionBar.setDisplayHomeAsUpEnabled(true);
		Display_table = (TableLayout) findViewById(R.id.Display_table);
		header1 = (TextView)findViewById(R.id.tv_header1);
        header2 = (TextView)findViewById(R.id.tv_header2);
        if(item.equalsIgnoreCase("Heart Rate")){
        header1.setText("Tracker");
        header2.setText("HeartRate");
        //url = "https://api.fitbit.com/1/user/-/heart/date/today.json";
        }
        else if(item.equalsIgnoreCase("Activity")){
        	header1.setText("Activity");
            header2.setText("Distance");
            //url = "https://api.fitbit.com/1/user/-/activities/date/today.json";
        }
        else if(item.equalsIgnoreCase("Sleep")){
        	header1.setText("Fat");
            header2.setText("Details");
        }
        else if(item.equalsIgnoreCase("Body")){
        	header1.setText("Date");
            header2.setText("Fat");
        }
        else{
        	header1.setText("Activity");
            header2.setText("Benchmark");
        }
        final Calendar c = Calendar.getInstance();
        int year = c.get(Calendar.YEAR);
        int month = c.get(Calendar.MONTH);
        int date = c.get(Calendar.DAY_OF_MONTH);
        curr_day = new StringBuilder()
        // Month is 0 based, just add 1
        .append(date).append("-").append(month + 1).append("-")
        .append(year).toString();
        
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, 1);
        SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
        today_date = format1.format(cal.getTime());
        
        /*today_date = new StringBuilder()
        // Month is 0 based, just add 1
        .append(year).append("-").append(month + 1).append("-")
        .append(date).toString();*/
		new GetData().execute();

	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
	    switch (item.getItemId()) {
	        case android.R.id.home:
	            // app icon in action bar clicked; go home
	            Intent intent = new Intent(this, MainActivity.class);
	            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
	            startActivity(intent);
	            return true;
	        default:
	            return super.onOptionsItemSelected(item);
	    }
	}

	class GetData extends AsyncTask<String, String, String> {
		DbOperations operations = new DbOperations(CallActivity.this);
		private static final String TAG = "GetData";
		JSONParser jsonParser = new JSONParser();
		
		@Override protected void onPreExecute() {
			super.onPreExecute();
			pDialog = new ProgressDialog(CallActivity.this);
			pDialog.setMessage("Loading...");
			pDialog.setIndeterminate(false);
			pDialog.setCancelable(true);
			pDialog.show();
		}

		@Override
		protected String doInBackground(String... params) {
			// TODO Auto-generated method stub
			JSONObject getjson = null;
			try{
				if(item.equalsIgnoreCase("Heart Rate")){
					getjson = jsonParser.makeHttpRequest("https://api.fitbit.com/1/user/-/heart/date/today.json","GET");
				}
				else if(item.equalsIgnoreCase("Activity"))
				{
					getjson = jsonParser.makeHttpRequest("https://api.fitbit.com/1/user/-/activities/date/today.json","GET");
				}
				else if(item.equalsIgnoreCase("Sleep")){
					getjson = jsonParser.makeHttpRequest("https://api.fitbit.com/1/user/-/sleep/date/today.json","GET");
				}
				else if(item.equalsIgnoreCase("Body")){
					getjson = jsonParser.makeHttpRequest("https://api.fitbit.com/1/user/-/body/log/fat/date/"+today_date+".json","GET");
				}
				else{
					getjson = jsonParser.makeHttpRequest("https://api.fitbit.com/1/user/-/foods/log/goal.json","GET");
				}
			Log.d("Create Response", getjson.toString());

			Log.i(TAG, ""+getjson.toString());
			if(item.equalsIgnoreCase("Heart Rate")){
			jsonarray = getjson.getJSONArray("average");
			key=new String[jsonarray.length()];
			value=new String[jsonarray.length()];
			for(int i=0;i<jsonarray.length();i++){

				JSONObject jsonresult  = jsonarray.getJSONObject(i);
				// Do something with phone data

				String mtracker = jsonresult.getString("tracker");
				String mheartrate = jsonresult.getString("heartRate");

						key[i]=mtracker;

						value[i]=mheartrate;
						
						operations.openDb();
						operations.createRow(key[i], value[i],curr_day);
						operations.closeDb();

						Log.i(TAG, ""+key[i]);
						Log.i(TAG, ""+value[i]);
					}
			}
			else if(item.equalsIgnoreCase("Activity")){
				JSONObject jsonsummary  = getjson.getJSONObject("summary");
				jsonarray = jsonsummary.getJSONArray("distances");
				key=new String[jsonarray.length()];
				value=new String[jsonarray.length()];
				for(int i=0;i<jsonarray.length();i++){

					JSONObject jsonresult  = jsonarray.getJSONObject(i);
					// Do something with phone data

					String mtracker = jsonresult.getString("activity");
					String mheartrate = jsonresult.getString("distance");

							key[i]=mtracker;

							value[i]=mheartrate;
							
							operations.openDb();
							operations.createRow_Activity(key[i], value[i],curr_day);
							operations.closeDb();

							Log.i(TAG, ""+key[i]);
							Log.i(TAG, ""+value[i]);
						}
				
			}
			else if(item.equalsIgnoreCase("Sleep")){
				key=new String[3];
				value=new String[3];
				int i = 0;
				String  tma = "Asleep";
				String  tsr = "Sleep";
				String  ttb = "InBed";
				JSONObject jsonsummary  = getjson.getJSONObject("summary");
				
				String mtma = jsonsummary.getString("totalMinutesAsleep");
				String mtsr = jsonsummary.getString("totalSleepRecords");
				String mttb = jsonsummary.getString("totalTimeInBed");
				
				key[i] = tma;
				value[i] = mtma;
				Log.i(TAG, ""+key[i]);
				Log.i(TAG, ""+value[i]);
				operations.openDb();
				operations.createRow_Sleep(key[i], value[i],curr_day);
				operations.closeDb();
				i++;
				
				key[i] = tsr;
				value[i] = mtsr;
				Log.i(TAG, ""+key[i]);
				Log.i(TAG, ""+value[i]);
				operations.openDb();
				operations.createRow_Sleep(key[i], value[i],curr_day);
				operations.closeDb();
				i++;
				
				
				key[i] = ttb;
				value[i] = mttb;
				Log.i(TAG, ""+key[i]);
				Log.i(TAG, ""+value[i]);
				operations.openDb();
				operations.createRow_Sleep(key[i], value[i],curr_day);
				operations.closeDb();
			}
			
			else if(item.equalsIgnoreCase("Body")){
				jsonarray = getjson.getJSONArray("fat");
				key=new String[jsonarray.length()];
				value=new String[jsonarray.length()];
				for(int i=0;i<jsonarray.length();i++){

					JSONObject jsonresult  = jsonarray.getJSONObject(i);
					// Do something with phone data

					String mdate = jsonresult.getString("date");
					String mfat = jsonresult.getString("fat");

							key[i]=mdate;

							value[i]=mfat;
							
							operations.openDb();
							operations.createRow_Body(key[i], value[i],curr_day);
							operations.closeDb();

							Log.i(TAG, ""+key[i]);
							Log.i(TAG, ""+value[i]);
						}
			}
			else{
				key=new String[6];
				value=new String[6];
				int i = 0;
				
				
				
				JSONObject jsongoals  = getjson.getJSONObject("goals");
				
				key[i] = "Goals";
				value[i] = "";
				i++;
				
				String cal = jsongoals.getString("calories");
				key[i] = "Calories";
				value[i] = cal;
				operations.openDb();
				operations.createRow_Food(key[i], value[i],curr_day);
				operations.closeDb();
				i++;
				
				key[i] = "Food Plan";
				value[i] = "";
				i++;
				
				JSONObject jsonplan  = getjson.getJSONObject("foodplan");
				String intensity = jsonplan.getString("intensity");
				key[i] = "Intensity";
				value[i] = intensity;
				operations.openDb();
				operations.createRow_Food(key[i], value[i],curr_day);
				operations.closeDb();
				i++;
				
				String estdate = jsonplan.getString("estimatedDate");
				key[i] = "Estimated Date";
				value[i] = estdate;
				operations.openDb();
				operations.createRow_Food(key[i], value[i],curr_day);
				operations.closeDb();
				i++;
				
				String persn = jsonplan.getString("personalized");
				key[i] = "Personalized";
				value[i] = persn;
				operations.openDb();
				operations.createRow_Food(key[i], value[i],curr_day);
				operations.closeDb();
			}
					//System.out.println(jsonarray_new);
			}
			catch(Exception e)
			{

			}
			return getjson.toString();
		}
		@Override
		protected void onPostExecute(String result) {
			pDialog.hide();
			output = result;
			fillCountryTable();
			//display();

		}

	}
	void fillCountryTable() {
		 
        TableRow row;
        TextView t1, t2;
        //Converting to dip unit
        int dip = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                (float) 1, getResources().getDisplayMetrics());
 
        for (int current = 0; current < key.length; current++) {
            row = new TableRow(this);
 
            t1 = new TextView(this);
            //t1.setTextColor(getResources().getColor(R.color.yellow));
            t2 = new TextView(this);
            //t2.setTextColor(getResources().getColor(R.color.dark_red));
 
            t1.setText(key[current]);
            t2.setText(value[current]);
 
            t1.setTypeface(null, 1);
            t2.setTypeface(null, 1);
 
            t1.setTextSize(15);
            t2.setTextSize(15);
 
            t1.setWidth(50 * dip);
            t2.setWidth(150 * dip);
            t1.setPadding(20*dip, 0, 0, 0);
            row.addView(t1);
            row.addView(t2);
            
 
            Display_table.addView(row, new TableLayout.LayoutParams(
                    LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
 
        }
    }
}
