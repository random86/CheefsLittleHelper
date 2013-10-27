package de.vsis.clh.cam;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.hardware.Camera;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.OrientationEventListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.FrameLayout;
import de.vsis.clh.ChangeActivity;

@SuppressLint("NewApi")
public class CameraActivity extends Activity {

	private static final String TAG = "CameraActivity";
	private Camera mCamera;
	private CameraPreview mPreview;
	OrientationEventListener myOrientationEventListener;
	private FrameLayout layout;
	public Button capture;
	public static CameraActivity instance;
	public String ID;
	public String imageDirection;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		// Hide the window title.
		instance = this;
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);

		Intent i = getIntent();
		ID = i.getStringExtra(ChangeActivity.EXTRA_MESSAGE);
		imageDirection = i.getStringExtra(MediaStore.EXTRA_OUTPUT);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Create an instance of Camera
		mCamera = getCameraInstance();

		layout = new FrameLayout(this);
		mPreview = new CameraPreview(this, mCamera, getIntent().getStringExtra(
				ChangeActivity.EXTRA_MESSAGE));
		layout.addView(mPreview);
		capture = new Button(this);
		capture.setText("Capture");
		capture.setEnabled(false);
		layout.addView(capture, new LayoutParams(LayoutParams.WRAP_CONTENT,
				LayoutParams.WRAP_CONTENT));

		setContentView(layout);

	}

	public Button getCapture() {
		return capture;
	}

	public void setCapture(Button capture) {
		this.capture = capture;
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		return true;
	}

	@Override
	public void onPause() {
		super.onPause();
		mCamera.stopPreview();
	}

	@Override
	public void onResume() {
		super.onResume();
		mCamera.startPreview();
	}

	/** A safe way to get an instance of the Camera object. */
	public static Camera getCameraInstance() {
		Camera c = null;
		try {
			c = Camera.open(); // attempt to get a Camera instance
		} catch (Exception e) {
			// Camera is not available (in use or does not exist)
		}
		return c; // returns null if camera is unavailable
	}

	public static CameraActivity getInstance() {
		return instance;
	}
	
	@Override
	public void onBackPressed() {
		setResult(RESULT_OK);
	    super.onBackPressed();
	}

	
	public void pictureSaved(int resultCode) {
		setResult(resultCode);
		onStop();
	}
	@Override
	public void finish() {
		setResult(RESULT_OK);
		super.finish();
	}
}
