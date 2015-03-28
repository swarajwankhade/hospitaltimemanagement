package org.altbeacon.beaconreference;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;
import org.altbeacon.beaconreference.R;

public class UserActivity extends Activity{
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.successful_login);
		Toast.makeText(getBaseContext(), "Login Successfull", Toast.LENGTH_LONG).show();
		//sConnect = new serverConnect(this);
	}

}