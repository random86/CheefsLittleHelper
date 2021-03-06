package de.vsis.clh;

//https://github.com/danieljonker/PersonFinder
import java.io.IOException;

import de.vsis.clh.cam.CameraRecognition;
import de.vsis.clh.cam.FaceView;

import android.app.Activity;
import android.app.AlertDialog;
import android.hardware.Camera;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.FrameLayout;

public class DisplayRecognizeActivity extends Activity {
	private FrameLayout layout;
	private FaceView faceView;
	private CameraRecognition mPreview;
	private Camera mCamera;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// Hide the window title.
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		super.onCreate(savedInstanceState);

		getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);

		// Create an instance of Camera
		mCamera = getCameraInstance();
		// Create our Preview view and set it as the content of our activity.
		try {
			layout = new FrameLayout(this);
			faceView = new FaceView(this);
			// mPreview = new Preview(this, faceView);
			mPreview = new CameraRecognition(this, mCamera, getIntent()
					.getStringExtra(ChangeActivity.EXTRA_MESSAGE));
//			layout.addView(faceView);
			layout.addView(mPreview);
			
			setContentView(layout);
		} catch (IOException e) {
			e.printStackTrace();
			new AlertDialog.Builder(this).setMessage(e.getMessage()).create()
					.show();
		}
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
}

// @SuppressLint("NewApi")
// public class DisplayRecognizeActivity extends Activity implements
// SurfaceHolder.Callback {
// Camera camera;
// SurfaceView surfaceView;
// SurfaceHolder surfaceHolder;
// boolean previewing = false;
//
// DrawingView drawingView;
// Face[] detectedFaces;
//
// final int RESULT_SAVEIMAGE = 0;
//
// /** Called when the activity is first created. */
// @Override
// public void onCreate(Bundle savedInstanceState) {
// super.onCreate(savedInstanceState);
// requestWindowFeature(Window.FEATURE_NO_TITLE);
// getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
// WindowManager.LayoutParams.FLAG_FULLSCREEN);
// setContentView(R.layout.activity_display_recognize);
// // setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
//
// getWindow().setFormat(PixelFormat.UNKNOWN);
// surfaceView = (SurfaceView) findViewById(R.id.camera_preview);
// surfaceHolder = surfaceView.getHolder();
// surfaceHolder.addCallback(this);
// surfaceHolder.setType(SurfaceHolder.SURFACE_TYPE_PUSH_BUFFERS);
//
// drawingView = new DrawingView(this);
// LayoutParams layoutParamsDrawing = new LayoutParams(
// LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
// this.addContentView(drawingView, layoutParamsDrawing);
// }
//
// FaceDetectionListener faceDetectionListener = new FaceDetectionListener() {
//
// @Override
// public void onFaceDetection(Face[] faces, Camera camera) {
//
// if (faces.length == 0) {
// drawingView.setHaveFace(false);
// } else {
// drawingView.setHaveFace(true);
// detectedFaces = faces;
// }
//
// drawingView.invalidate();
//
// }
// };
//
// @Override
// public void surfaceChanged(SurfaceHolder holder, int format, int width,
// int height) {
// // TODO Auto-generated method stub
// if (previewing) {
// camera.stopFaceDetection();
// camera.stopPreview();
// previewing = false;
// }
//
// if (camera != null) {
// try {
// camera.setPreviewDisplay(surfaceHolder);
// camera.startPreview();
//
// camera.startFaceDetection();
// previewing = true;
// } catch (IOException e) {
// // TODO Auto-generated catch block
// e.printStackTrace();
// }
// }
// }
//
// @Override
// public void surfaceCreated(SurfaceHolder holder) {
// // TODO Auto-generated method stub
// camera = Camera.open();
// camera.setFaceDetectionListener(faceDetectionListener);
// }
//
// @Override
// public void surfaceDestroyed(SurfaceHolder holder) {
// // TODO Auto-generated method stub
// camera.stopFaceDetection();
// camera.stopPreview();
// camera.release();
// camera = null;
// previewing = false;
// }
//
// private class DrawingView extends View {
//
// boolean haveFace;
// Paint drawingPaint;
//
// public DrawingView(Context context) {
// super(context);
// haveFace = false;
// drawingPaint = new Paint();
// drawingPaint.setColor(Color.GREEN);
// drawingPaint.setStyle(Paint.Style.STROKE);
// drawingPaint.setStrokeWidth(2);
// }
//
// public void setHaveFace(boolean h) {
// haveFace = h;
// }
//
// @Override
// protected void onDraw(Canvas canvas) {
// // TODO Auto-generated method stub
// if (haveFace) {
//
// // Camera driver coordinates range from (-1000, -1000) to (1000,
// // 1000).
// // UI coordinates range from (0, 0) to (width, height).
//
// int vWidth = getWidth();
// int vHeight = getHeight();
//
// for (int i = 0; i < detectedFaces.length; i++) {
//
// int l = detectedFaces[i].rect.left;
// int t = detectedFaces[i].rect.top;
// int r = detectedFaces[i].rect.right;
// int b = detectedFaces[i].rect.bottom;
// int left = (l + 1000) * vWidth / 2000;
// int top = (t + 1000) * vHeight / 2000;
// int right = (r + 1000) * vWidth / 2000;
// int bottom = (b + 1000) * vHeight / 2000;
// canvas.drawRect(left, top, right, bottom, drawingPaint);
// Paint myPaint = new Paint();
// myPaint.setColor(Color.GREEN);
// myPaint.setTextSize(12);
//
// canvas.drawText("Wurst", left, left, myPaint);
// }
// } else {
// canvas.drawColor(Color.TRANSPARENT);
// }
// }
//
// }
// }
