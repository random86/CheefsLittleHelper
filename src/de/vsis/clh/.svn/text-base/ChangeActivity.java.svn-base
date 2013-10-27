package de.vsis.clh;

import java.util.List;
import java.util.Scanner;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;

public class ChangeActivity extends Activity {
	public final static String EXTRA_MESSAGE = "de.vsis.clh.ID";
	private PersonDataSource datasource = new PersonDataSource(this);
	private int requestCode;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		datasource.open();
		setContentView(R.layout.activity_change);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_change, menu);
		return true;
	}

	public void loadData(View v) {
		EditText editText = (EditText) findViewById(R.id.change_firstname);
		String firstName = editText.getText().toString();
		editText = (EditText) findViewById(R.id.change_lastname);
		String lastName = editText.getText().toString();
		editText = (EditText) findViewById(R.id.change_age);
		String age = editText.getText().toString();
		editText = (EditText) findViewById(R.id.change_courses);
		String courses = editText.getText().toString();

		Person p = new Person();

		p.setFirstName(firstName);
		p.setLastName(lastName);
		p.setAge(age);
		p.setPersonCurseOfStudies(courses);

		List<Person> persons = datasource.getPersons(p);
		int size = persons.size();
		switch (size) {
		case 0:
			AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
			alertDialog.setTitle("Nicht gefunden!");
			alertDialog
					.setMessage("In der Datenbank gibt es keinen Eintrag mit Ihren Spezifikationen");
			alertDialog.setPositiveButton("OK",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int id) {
							// User clicked OK button
						}
					});
			alertDialog.show();
			break;
		case 1:
			Intent intent = new Intent(ChangeActivity.this, CreatActivity.class);
			intent.putExtra(EXTRA_MESSAGE, persons.get(0).getId());
			startActivityForResult(intent, requestCode);
			break;
		default:
			final String[] personsArray = new String[size];
			for (int i = 0; i < size; i++) {
				personsArray[i] = persons.get(i).toString();
			}

			AlertDialog.Builder builder = new AlertDialog.Builder(this);
			builder.setTitle("Wähle eine Person");
			builder.setItems(personsArray,
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog, int which) {
							String selected = personsArray[which];
							Scanner s = new Scanner(selected);
							s.useDelimiter(" ");
							Intent intent = new Intent(ChangeActivity.this,
									CreatActivity.class);
							intent.putExtra(EXTRA_MESSAGE, s.next());
							startActivityForResult(intent, requestCode);
						}
					});
			builder.show();
			break;

		}
	}
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		super.onActivityResult(requestCode, resultCode, data);
		if(requestCode==Activity.RESULT_OK){
			this.finish();
		}
	}
}
