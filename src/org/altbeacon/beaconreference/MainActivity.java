package org.altbeacon.beaconreference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import org.altbeacon.beaconreference.R;


public class MainActivity extends Activity implements OnClickListener {

	Button sign_up,sign_in;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		sign_in = (Button)findViewById(R.id.SignIn);
		sign_up = (Button)findViewById(R.id.SignUp);
		
		sign_in.setOnClickListener(this);
		sign_up.setOnClickListener(this);
		
	}
	
	public void onClick(View v){
		
		if(v.getId() == R.id.SignIn)
		{
		Intent sign_in = new Intent(this,LoginActivity.class);
		startActivity(sign_in);
		}
		
		if(v.getId() == R.id.SignUp)
		{
		Intent sign_up = new Intent(this,RegisterActivity.class);
		startActivity(sign_up);
		}
	}
}

