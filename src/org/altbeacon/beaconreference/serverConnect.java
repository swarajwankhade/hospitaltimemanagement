package org.altbeacon.beaconreference;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.AsyncHttpResponseHandler;
import com.loopj.android.http.RequestParams;	

import android.content.Context;
import android.util.Log;

public class serverConnect {

	private String URL = "https://128.82.4.35/android_content/";

	ServerAsyncHttpClient mAssyncHttpClient;

	public serverConnect(Context context) {
		mAssyncHttpClient = new ServerAsyncHttpClient(context);

	}

	public void registerUser(String fname,String lname,String username,String password,
			AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams mRequestParams = new RequestParams();

		mRequestParams.add("fname", fname);
		mRequestParams.add("lname", lname);
		mRequestParams.add("username", username);
		mRequestParams.add("password", password);
		Log.v("serconnect", "user: " + username);
		mAssyncHttpClient.post(URL+"register_user.php", mRequestParams,asyncHttpResponseHandler);
		

	}
	
	public void loginUser(String username,String password,
			AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams mRequestParams = new RequestParams();

		mRequestParams.add("username", username);
		mRequestParams.add("password", password);
		Log.v("serconnect", "user: " + username);
		mAssyncHttpClient.post(URL+"login_user.php", mRequestParams,asyncHttpResponseHandler);
		

	}
	
	public void timeline(String Timeline1,String Timeline2,String Timeline3,String TotalTime,String username,
			AsyncHttpResponseHandler asyncHttpResponseHandler) {
		RequestParams mRequestParams = new RequestParams();

		mRequestParams.add("Timeline1", Timeline1);
		mRequestParams.add("Timeline2", Timeline2);
		mRequestParams.add("Timeline3", Timeline3);
		mRequestParams.add("TotalTime", TotalTime);
		mRequestParams.add("Username", username);
		Log.v("serconnect", "Total Time: " + TotalTime);
		mAssyncHttpClient.post(URL+"login_user.php", mRequestParams,asyncHttpResponseHandler);
		

	}
}

