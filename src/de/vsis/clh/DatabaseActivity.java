package de.vsis.clh;

import java.io.File;
import java.util.List;

import android.app.AlertDialog;
import android.app.ListActivity;
import android.os.Bundle;
import android.os.Environment;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;

public class DatabaseActivity extends ListActivity {

	private PersonDataSource datasource;
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_database);
		
		
		
		initList();
		
		//call the database
//		getListAdapter();
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_database, menu);
		return true;
	}
	
	  @Override
	  protected void onResume() {
	    datasource.open();
	    super.onResume();
	  }

	  @Override
	  protected void onPause() {
	    datasource.close();
	    super.onPause();
	  }

	  public void deleteAll(View view){
		  datasource.deleteAllPersons();

		  initList();
//		  view.invalidate();

		  datasource.deleteDir(new File(Environment.getExternalStorageDirectory()
			.getPath() + "/CLH/"));


		  new AlertDialog.Builder(this).setTitle("Notification").setMessage("Database cleared").setPositiveButton("OK", null).show();
		  
	  }
	  
	  private void initList() {
		  datasource = new PersonDataSource(this);
			datasource.open();
			
			List<Person> values = datasource.getAllPersons();
			
			//Use the SimpleCursorAdapter to show the elements in the Listview
			ArrayAdapter<Person> adapter = new ArrayAdapter<Person>(this, 
					android.R.layout.simple_list_item_1, values);
			setListAdapter(adapter);
	  }
}