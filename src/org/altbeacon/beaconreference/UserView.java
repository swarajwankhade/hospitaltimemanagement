package org.altbeacon.beaconreference;

import org.apache.http.Header;

import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class UserView extends Activity {

	TextView time1, time2, time3, total,time4;
	serverConnect sConnect;
	String Timeline1, Timeline2, Timeline3, TotalTime,username,Timeline4;

	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.user_view);

		/*
		 * Intent intent = getIntent(); String Timeline1 =
		 * intent.getStringExtra("TimeLine1");
		 */
		/*
		 * String Timeline2 = intent.getStringExtra("TimeLine2"); String
		 * Timeline3 = intent.getStringExtra("TimeLine3"); String TotalTime =
		 * intent.getStringExtra("Total");
		 */

		Intent intent1 = getIntent();
		Timeline1 = intent1.getStringExtra("TimeLine1");
		Timeline2 = intent1.getStringExtra("TimeLine2");
		Timeline3 = intent1.getStringExtra("TimeLine3");
		Timeline4 = intent1.getStringExtra("TimeLine4");
		TotalTime = intent1.getStringExtra("Total");
		//username = intent1.getStringExtra("UserName");

		// Log.d("Time1",Timeline1);
		//username = "apeksha02";
		time1 = (TextView) findViewById(R.id.entrance);
		time2 = (TextView) findViewById(R.id.reception);
		time3 = (TextView) findViewById(R.id.waiting);
		time4 = (TextView) findViewById(R.id.doctor);
		total = (TextView) findViewById(R.id.total);

		time1.setText(Timeline1 + " seconds");
		time2.setText(Timeline2 + " seconds");
		time3.setText(Timeline3 + " seconds");
		time4.setText(Timeline4 + " seconds");
		total.setText(TotalTime + " seconds");

		//insertTime();

	}

	/*public void insertTime() {
		
		sConnect.timeline(Timeline1.toString(), Timeline2.toString(), Timeline3.toString(), TotalTime.toString(),username.toString(),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub
						Log.d("Onsuccess Login", "SUCCESS");
						Intent login = new Intent(myActivity,
								RangingActivity.class);
						startActivity(login);

					}

					@Override
					public void onFailure(int arg0, Header[] arg1, byte[] arg2,
							Throwable arg3) {
						// TODO Auto-generated method stub
						if (arg2 != null) {
							String s = new String(arg2);
							Log.v("register", "failure " + s);
						}
						arg3.fillInStackTrace();
						Log.d("respose", "value=" + arg2);
						Log.v("register", " failcode " + arg0);
						Log.d("OnFailure", "FAILURE");
					}
				});

	}*/

}
