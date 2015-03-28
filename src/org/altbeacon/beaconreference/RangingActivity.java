package org.altbeacon.beaconreference;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.os.RemoteException;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.altbeacon.beacon.AltBeacon;
import org.altbeacon.beacon.Beacon;
import org.altbeacon.beacon.BeaconConsumer;
import org.altbeacon.beacon.BeaconManager;
import org.altbeacon.beacon.BeaconParser;
import org.altbeacon.beacon.RangeNotifier;
import org.altbeacon.beacon.Region;

public class RangingActivity extends Activity implements OnClickListener {
	protected static final String TAG = "RangingActivity";
	int minorValue;
	Beacon beacon;
	long entranceTime;
	long receptionTime;
	long doctorOfficeTime;
	long waitingRoomTime;
	long exitTime;
	Button stop;

	long timeline1,t1;
	String time1;
	long timeline2;
	String time2;
	long timeline3;
	String time3;
	long timeline4;
	String time4;
	long totaltime;
	String total;
	
	String UserName;

	Activity myActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		entranceTime = 0;
		receptionTime = 0;
		waitingRoomTime = 0;
		doctorOfficeTime = 0;
		exitTime = 0;
		setContentView(R.layout.activity_ranging);
		stop = (Button) findViewById(R.id.Button01);
		stop.setOnClickListener(this);
		
	}

	@Override
	protected void onDestroy() {
		super.onDestroy();
	}

	@Override
	protected void onPause() {
		super.onPause();
		// Tell the Application not to pass off ranging updates to this activity
		((BeaconReferenceApplication) this.getApplication())
				.setRangingActivity(null);
	}

	@Override
	protected void onResume() {
		super.onResume();
		// Tell the Application to pass off ranging updates to this activity
		((BeaconReferenceApplication) this.getApplication())
				.setRangingActivity(this);
		getWindow().setSoftInputMode(
				WindowManager.LayoutParams.SOFT_INPUT_STATE_HIDDEN);
	}

	public void didRangeBeaconsInRegion(Collection<Beacon> beacons,
			Region region) {

		if (beacons.size() >= 1) {

			String beaconCount = "Size " + beacons.size();
			Log.d("Size", beaconCount);
			nearerBeacon(beacons);

			/*
			 * for (Beacon beacon : beacons) { logToDisplay("Beacon Id = " +
			 * beacon.toString() + "\n" + " Is at distance = " +
			 * beacon.getDistance() + "\n" + "Location of the beacon = Entrance"
			 * + "\n"+"RSSI"+beacon.getRssi()); }
			 */

		}

	}

	private void logToDisplay(final String line) {
		runOnUiThread(new Runnable() {
			public void run() {
				EditText beacon = (EditText) RangingActivity.this
						.findViewById(R.id.rangingText);

				beacon.append(line + "\n");
			}
		});
	}

	public void nearerBeacon(Collection<Beacon> beacons) {
		// for (Beacon beacon : beacons) {

		List beaconList = new ArrayList<Beacon>(beacons);
		beacon = (Beacon) beaconList.get(0);
		String beaconId = beacon.toString();
		String[] parts = beaconId.split(":");
		String dd = parts[3];
		Log.d("part4", dd);
		minorValue = Integer.parseInt(parts[3].trim());

		Calendar cal = Calendar.getInstance();
		cal.getTime();

		if (beacon.getRssi() >= -60) {
			/*
			 * List beaconList = new ArrayList<Beacon>(beacons); beacon =
			 * (Beacon) beaconList.get(0);
			 */

			if (minorValue == 1) {

				if (entranceTime == 0) {
					// Log.d("Location", "Entrance");
					entranceTime = cal.getTimeInMillis();
					// time1 = Long.toString(entranceTime);
					logToDisplay(" Is at location = Entrance " + "\n"
							+ "At Time =" + entranceTime);
				} else {
					/*
					 * logToDisplayEntrance("Beacon <= 60" + beacon.toString() +
					 * " is about " + " Is at location = Entrance " + "RSSI" +
					 * beacon.getRssi());
					 */
				}

			} else if (minorValue == 2) {

				if (receptionTime == 0 && entranceTime != 0) {
					receptionTime = cal.getTimeInMillis();
					// Log.d("Location", "reception");
					logToDisplay(" Is at location = Reception " + "\n"
							+ "At Time =" + receptionTime);

				} else {
					/*
					 * logToDisplay("Beacon <= 60" + beacon.toString() +
					 * " is about " + " Is at location = Reception " + "RSSI" +
					 * beacon.getRssi());
					 */
				}

			} else if (minorValue == 3) {

				if (waitingRoomTime == 0 && entranceTime != 0
						&& receptionTime != 0) {
					// Log.d("Location", "Doctor");
					waitingRoomTime = cal.getTimeInMillis();
					logToDisplay(" Is at location = Waiting Room " + "\n"
							+ "At Time =" + waitingRoomTime);
				} else {
					/*
					 * logToDisplay("Beacon <= 60" + beacon.toString() +
					 * " is about " + " Is at location = Waiting Room " + "RSSI"
					 * + beacon.getRssi());
					 */
				}

			} else if (minorValue == 4) {

				if (doctorOfficeTime == 0 && entranceTime != 0
						&& receptionTime != 0 && waitingRoomTime != 0) {
					// Log.d("Location", "Doctor");
					doctorOfficeTime = cal.getTimeInMillis();
					logToDisplay(" Is at location = Doctor office " + "\n"
							+ "At Time =" + doctorOfficeTime);
				} else {
					/*
					 * logToDisplay("Beacon <= 60" + beacon.toString() +
					 * " is about " + " Is at location = Doctor office " +
					 * "RSSI" + beacon.getRssi());
					 */
				}

			} else if (minorValue == 5) {

				if (exitTime == 0 && doctorOfficeTime != 0
						&& receptionTime != 0 && waitingRoomTime != 0) {
					// Log.d("Location", "Doctor");
					exitTime = cal.getTimeInMillis();
					logToDisplay(" Is at location = Exit " + "\n"
							+ "At Time =" + exitTime);
				} else {
					/*
					 * logToDisplay("Beacon <= 60" + beacon.toString() +
					 * " is about " + " Is at location = Doctor office " +
					 * "RSSI" + beacon.getRssi());
					 */
				}

			}

		} else if (beacon.getRssi() < -60 && beacon.getRssi() >= -70) {
			if (minorValue == 1) {

				// entranceTime = cal.getTimeInMillis();
				// Log.d("Location", "Entrance");
				/*
				 * logToDisplay("Beacon 60 - 70" + beacon.toString() +
				 * " is about " + " Is at location = Entrance " + "RSSI" +
				 * beacon.getRssi() + "At Time =" + entranceTime);
				 */

			} else if (minorValue == 2) {

				// receptionTime = cal.getTimeInMillis();
				// Log.d("Location", "reception");
				/*
				 * logToDisplay("Beacon 60 - 70" + beacon.toString() +
				 * " is about " + " Is at location = Reception " + "RSSI" +
				 * beacon.getRssi() + "At Time =" + receptionTime);
				 */

			} else if (minorValue == 3) {

				// Log.d("Location", "Doctor");
				// doctorOfficeTime = cal.getTimeInMillis();
				/*
				 * logToDisplay("Beacon 60 - 70" + beacon.toString() +
				 * " is about " + " Is at location = Waiting Room " + "RSSI" +
				 * beacon.getRssi() + "At Time =" + waitingRoomTime);
				 */

			} else if (minorValue == 4) {

				// Log.d("Location", "Doctor");
				// doctorOfficeTime = cal.getTimeInMillis();
				/*
				 * logToDisplay("Beacon 60 - 70" + beacon.toString() +
				 * " is about " + " Is at location = Doctor office " + "RSSI" +
				 * beacon.getRssi() + "At Time =" + doctorOfficeTime);
				 */

			}
			else if (minorValue == 5) {

				// Log.d("Location", "Doctor");
				// doctorOfficeTime = cal.getTimeInMillis();
				/*
				 * logToDisplay("Beacon 60 - 70" + beacon.toString() +
				 * " is about " + " Is at location = Doctor office " + "RSSI" +
				 * beacon.getRssi() + "At Time =" + doctorOfficeTime);
				 */

			}

		}

		

		
		//Log.d("Time Line 1", Long.toString(timeline1));
		
		//timeline1 = 12345678910L;
		timeline1 = (receptionTime - entranceTime)/1000;
		time1 = Long.toString(timeline1);
		timeline2 = (waitingRoomTime - receptionTime)/1000;
		//timeline2 = 12366678910L;
		time2 = Long.toString(timeline2);
		timeline3 = (doctorOfficeTime - waitingRoomTime)/1000;
		//timeline3 = 12345888910L;
		time3 = Long.toString(timeline3);
		//timeline4 = 12345888910L;
		timeline4 = (exitTime - doctorOfficeTime)/1000;
		time4 = Long.toString(timeline4);
		totaltime = timeline1 + timeline2 + timeline3 + timeline4;
		total = Long.toString(totaltime);

		/*
		 * t1 = 5; time1 = Long.toString(t1); t2 = 10; t3 = 15; t4 = t1 + t2 +
		 * t3 ; Log.d("Time Mine 1",time1)
		 */;

	}

	@Override
	public void onClick(View v) {

		// Log.d("Time1",time1);
		Intent user_profile = new Intent(myActivity, UserView.class);
		user_profile.putExtra("TimeLine1", time1);
		user_profile.putExtra("TimeLine2", time2);
		user_profile.putExtra("TimeLine3", time3);
		user_profile.putExtra("TimeLine4", time4);
		user_profile.putExtra("Total", total);
		//user_profile.putExtra("UserName", UserName);

		startActivity(user_profile);
	}
}
