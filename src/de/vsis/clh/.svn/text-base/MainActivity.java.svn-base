package de.vsis.clh;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void openRecognizActivity(View view) {
		Intent intent = new Intent(this, DisplayRecognizeActivity.class);
		if (checkCameraHardware(getApplicationContext())) {
			startActivity(intent);
			System.out.println("Found CAMERA");
		} else {
			Toast.makeText(getApplicationContext(), "Sie haben keine Camera",
					Toast.LENGTH_SHORT).show();
		}
	}

	public void openLernActivity(View view) {
		Intent intent = new Intent(this, CreatActivity.class);
		intent.putExtra(ChangeActivity.EXTRA_MESSAGE, "");
		startActivity(intent);
	}

	public void openEditActivity(View view) {
		Intent intent = new Intent(this, ChangeActivity.class);
		startActivity(intent);
	}

	public void openDatabaseActivity(View view) {
		Intent intent = new Intent(this, DatabaseActivity.class);
		startActivity(intent);
	}

	/** Check if this device has a camera */
	private boolean checkCameraHardware(Context context) {
		if (context.getPackageManager().hasSystemFeature(
				PackageManager.FEATURE_CAMERA)) {
			// this device has a camera
			return true;
		} else {
			// no camera on this device
			return false;
		}
	}

}
