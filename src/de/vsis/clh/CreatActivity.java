package de.vsis.clh;

import java.io.File;

import de.vsis.clh.cam.CameraActivity;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Matrix;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

public class CreatActivity extends Activity {

	private PersonDataSource datasource = new PersonDataSource(this);

	/**
	 * sollte die Activity mit einer Id als Extra aufgerufen werden, werden die
	 * Daten aus der Datenbank abgerufen und in die Eingabefelder eingetragen
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource.open();
		setContentView(R.layout.activity_creat);
		Intent intent = getIntent();
		String id = intent.getStringExtra(ChangeActivity.EXTRA_MESSAGE);
		if (!id.equals("")) {
			person = datasource.getPersonByID(id);
			EditText editText = (EditText) findViewById(R.id.creat_firstname);
			editText.setText(person.getFirstName());
			editText = (EditText) findViewById(R.id.creat_lastname);
			editText.setText(person.getLastName());
			editText = (EditText) findViewById(R.id.creat_age);
			editText.setText(person.getAge());
			editText = (EditText) findViewById(R.id.creat_courses);
			editText.setText(person.getPersonCurseOfStudies());
			if (person.getPictureLocation() != "") {
				Uri selectedImage = Uri.parse(person.getPictureLocation());
				getContentResolver().notifyChange(selectedImage, null);
				ImageView imageView = (ImageView) findViewById(R.id.test_image);
				ContentResolver cr = getContentResolver();
				Bitmap bitmap;
				try {
					bitmap = android.provider.MediaStore.Images.Media
							.getBitmap(cr, selectedImage);

					imageView.setImageBitmap(bitmap);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private Uri imageUri = Uri.parse("");
	private final static int TAKE_PICTURE = 1;
	private Person person = new Person();

	// public void takePhoto2(View view) {
	//
	// Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
	// R.drawable.bla_bla);
	//
	// ImageView imageView = (ImageView) findViewById(R.id.test_image);
	// imageView.setImageBitmap(bitmap);
	// }

	/**
	 * wird vom Button Datenspeichern aufgerufen weist der Person die
	 * eingegebenen Daten zu und kommuniziert mit der Datenbank- sollte die
	 * Person bereits eine ID besitzen, wird der Datenbank eintrag aktualisiert,
	 * ansonsten wird ein neuer eintrag erstellt
	 * 
	 * @param view
	 */
	public void save(View view) {

		EditText editText = (EditText) findViewById(R.id.creat_firstname);
		String firstName = editText.getText().toString();
		editText = (EditText) findViewById(R.id.creat_lastname);
		String lastName = editText.getText().toString();
		editText = (EditText) findViewById(R.id.creat_age);
		String age = editText.getText().toString();
		editText = (EditText) findViewById(R.id.creat_courses);
		String courses = editText.getText().toString();

		// konfiguriet die Person entsprechend der Eingaben
		person.setFirstName(firstName);
		person.setLastName(lastName);
		person.setAge(age);
		person.setPersonCurseOfStudies(courses);
		person.setPictureLocation(imageUri.toString());

		// ist die Person noch nicht in der der Datenbank, wird sie eingetragen
		// ansonsten wird sie aktualisiert
		if (person.getId().equals(""))
			person = datasource.createPerson(person);
		else
			datasource.updatePerson(person);
		this.onBackPressed();

	}

	/**
	 * wird aufgerufen, wenn der Button mit der Aufschrift "Bild machen"
	 * gedrückt wird
	 * 
	 * @param view
	 */
	public void takePhoto(View view) {

		// überprüft, ob die Person bereits in die Datenbank eingetragen ist
		// sollte noch kein Eintrag vorhanden sein, wird einer angelegt
		// (id == "" bedeutet noch kein Datenbankeintrag
		if (person.getId().equals(""))
			person = datasource.createPerson(person);

		File path = new File(Environment.getExternalStorageDirectory()
				.getPath() + "/CLH/Preview/");
		path.mkdirs();		
		File photo = new File(path, person.getId() + ".jpg");
		
		imageUri = Uri.fromFile(photo);
		System.out.println(photo.getAbsolutePath());
		// startet die CameraActivity
		Intent intent = new Intent(this, CameraActivity.class);
		intent.putExtra(ChangeActivity.EXTRA_MESSAGE, person.getId());
		intent.putExtra(MediaStore.EXTRA_OUTPUT, photo.getAbsolutePath());
		startActivityForResult(intent, TAKE_PICTURE);
	}

	/**
	 * wird aufgerufen nachdem die Erlern-Phase abgeschlossen ist
	 * 
	 * und weist das bild der ImageView zu
	 */
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		super.onActivityResult(requestCode, resultCode, data);
		switch (requestCode) {
		case TAKE_PICTURE:
			if (resultCode == Activity.RESULT_OK) {
				Uri selectedImage = imageUri;
				getContentResolver().notifyChange(selectedImage, null);
				ImageView imageView = (ImageView) findViewById(R.id.test_image);
				ContentResolver cr = getContentResolver();
				Bitmap bitmap;
				try {
					bitmap = android.provider.MediaStore.Images.Media
							.getBitmap(cr, selectedImage);

					imageView.setImageBitmap(bitmap);

				} catch (Exception e) {
					e.printStackTrace();
				}
			} else {
				// if (person.empty())
				// datasource.deletePerson(person);
			}
		}
	}
	@Override
	public void onBackPressed() {
		setResult(RESULT_OK);
	    super.onBackPressed();
	}
	
	@Override
	public void finish() {
		setResult(RESULT_OK);
		super.finish();
	}
}
