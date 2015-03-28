package org.altbeacon.beaconreference;

import org.apache.http.Header;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import org.altbeacon.beaconreference.R;

import com.loopj.android.http.AsyncHttpResponseHandler;

public class LoginActivity extends Activity implements OnClickListener {

	EditText username,password;
	Button login;
	serverConnect sConnect;
	Activity myActivity = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(org.altbeacon.beaconreference.R.layout.login);
		sConnect = new serverConnect(this);
		
		username = (EditText) findViewById(R.id.login_name);
		password = (EditText) findViewById(R.id.pwd);

		login = (Button) findViewById(R.id.login);
		login.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		// Intent register = new Intent(this,UserPage.class);
		// startActivity(register);

		sConnect.loginUser(username.getText().toString(),
				password.getText().toString(),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub
						Log.d("Onsuccess Login", "SUCCESS");
						Intent login = new Intent(myActivity, RangingActivity.class);
						login.putExtra("UserName", username.getText().toString());
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
		
	}

}

