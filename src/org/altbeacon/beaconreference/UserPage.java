package org.altbeacon.beaconreference;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

public class UserPage extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.successful_login);
		Toast.makeText(getBaseContext(), "Registeration Successfull", Toast.LENGTH_LONG).show();

	}
}
