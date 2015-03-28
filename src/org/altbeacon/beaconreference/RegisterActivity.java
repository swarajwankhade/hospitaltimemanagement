package org.altbeacon.beaconreference;

import java.util.ArrayList;
import org.altbeacon.beaconreference.R;
import java.util.List;
import org.apache.http.Header;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.json.JSONException;
import org.json.JSONObject;


import com.loopj.android.http.AsyncHttpResponseHandler;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener {


	EditText input_fname, input_lname, input_username, input_password;
	Button register;
	Activity myActivity=this;

	/*
	 * private static String url_register =
	 * "http://localhost:8080/android_content/register_user.php";
	 * 
	 * private static final String TAG_SUCCESS = "success";
	 */

	serverConnect sConnect;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register_page);

		sConnect = new serverConnect(this);
		input_fname = (EditText) findViewById(R.id.fname);
		input_lname = (EditText) findViewById(R.id.lname);
		input_username = (EditText) findViewById(R.id.username);
		input_password = (EditText) findViewById(R.id.password);

		register = (Button) findViewById(R.id.register);
		register.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {

		// TODO Auto-generated method stub
		// Intent register = new Intent(this,UserPage.class);
		// startActivity(register);

		sConnect.registerUser(input_fname.getText().toString(), input_lname
				.getText().toString(), input_username.getText().toString(),
				input_password.getText().toString(),
				new AsyncHttpResponseHandler() {

					@Override
					public void onSuccess(int arg0, Header[] arg1, byte[] arg2) {
						// TODO Auto-generated method stub
						Log.d("Onsuccess", "SUCCESS");
						Intent register = new Intent(myActivity, MainActivity.class);
						startActivity(register);

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