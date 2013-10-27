package de.vsis.clh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class PersonDataSource {

	// Database fields
	private SQLiteDatabase database;
	public MySQLiteHelper dbHelper;
	private String[] allPersons = { MySQLiteHelper.PERSON_ID,
			MySQLiteHelper.PERSON_PICTURES, MySQLiteHelper.PERSON_FIRSTNAME,
			MySQLiteHelper.PERSON_LASTNAME, MySQLiteHelper.PERSON_AGE,
			MySQLiteHelper.PERSON_CURSE_OF_STUDIES, };

	// constructor
	public PersonDataSource(Context context) {
		dbHelper = new MySQLiteHelper(context);
	}

	// open the database
	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public Person createPerson(Person person) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.PERSON_FIRSTNAME, person.getFirstName());
		values.put(MySQLiteHelper.PERSON_LASTNAME, person.getLastName());
		values.put(MySQLiteHelper.PERSON_AGE, person.getAge());
		values.put(MySQLiteHelper.PERSON_CURSE_OF_STUDIES,
				person.getPersonCurseOfStudies());
		values.put(MySQLiteHelper.PERSON_PICTURES, person.getPictureLocation());
		long insertId = database.insert(MySQLiteHelper.DATABASE_TABLE, null,
				values);
		Cursor cursor = database.query(MySQLiteHelper.DATABASE_TABLE,
				allPersons, MySQLiteHelper.PERSON_ID + " = " + insertId, null,
				null, null, null);
		cursor.moveToFirst();
		Person newPerson = cursorToPerson(cursor);
		return newPerson;
	}

	public void deletePerson(Person person) {
		String id = person.getId();
		System.out.println("Comment deleted with id: " + id);
		database.delete(MySQLiteHelper.DATABASE_TABLE, MySQLiteHelper.PERSON_ID
				+ " = " + id, null);
	}

	public void deleteAllPersons() {
		List<Person> persons = getAllPersons();
		for (Person p : persons) {
			deletePerson(p);

		}
	}

	public Person getPersonByID(String id) {
		Person p = new Person();
		String sql = "select * from " + MySQLiteHelper.DATABASE_TABLE
				+ " where " + MySQLiteHelper.PERSON_ID + " = " + id;

		SQLiteDatabase db = dbHelper.getWritableDatabase();
		Cursor cursor = db.rawQuery(sql, null);
		cursor.moveToFirst();
		p = cursorToPerson(cursor);
		cursor.close();
		return p;
	}

	public List<Person> getAllPersons() {
		List<Person> persons = new ArrayList<Person>();
		Cursor cursor = database.query(MySQLiteHelper.DATABASE_TABLE,
				allPersons, null, null, null, null, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person person = cursorToPerson(cursor);
			persons.add(person);
			cursor.moveToNext();
		}

		cursor.close();
		return persons;
	}

	public List<Person> getPersons(Person person) {
		List<Person> persons = new ArrayList<Person>();
		String sql = getSQLQuery(person);

		Cursor cursor = database.rawQuery(sql, null);
		cursor.moveToFirst();
		while (!cursor.isAfterLast()) {
			Person singlePerson = cursorToPerson(cursor);
			persons.add(singlePerson);
			cursor.moveToNext();
		}

		cursor.close();
		return persons;
	}

	public void updatePerson(Person person) {
		ContentValues values = new ContentValues();
		values.put(MySQLiteHelper.PERSON_FIRSTNAME, person.getFirstName());
		values.put(MySQLiteHelper.PERSON_LASTNAME, person.getLastName());
		values.put(MySQLiteHelper.PERSON_AGE, person.getAge());
		values.put(MySQLiteHelper.PERSON_CURSE_OF_STUDIES,
				person.getPersonCurseOfStudies());
		values.put(MySQLiteHelper.PERSON_PICTURES, person.getPictureLocation());
		database.update(MySQLiteHelper.DATABASE_TABLE, values,
				MySQLiteHelper.PERSON_ID + "=" + person.getId(), null);

	}

	private Person cursorToPerson(Cursor cursor) {
		Person person = new Person();
		person.setId(cursor.getString(0));
		person.setPictureLocation(cursor.getString(1));
		person.setFirstName(cursor.getString(2));
		person.setLastName(cursor.getString(3));
		person.setAge(cursor.getString(4));
		person.setPersonCurseOfStudies(cursor.getString(5));
		return person;
	}

	private String getSQLQuery(Person person) {
		String sql = "select * from " + MySQLiteHelper.DATABASE_TABLE;
		if (!person.empty())
			sql = sql + " where ";
		if (!person.getFirstName().equals("")) {
			sql = sql + MySQLiteHelper.PERSON_FIRSTNAME + " = '"
					+ person.getFirstName() + "'";
			person.setFirstName("");
			if (!person.empty())
				sql = sql + " AND ";
		}
		if (!person.getLastName().equals("")) {
			sql = sql + MySQLiteHelper.PERSON_LASTNAME + " = '"
					+ person.getLastName() + "'";
			person.setLastName("");
			if (!person.empty())
				sql = sql + " AND ";
		}
		if (!person.getAge().equals("")) {
			sql = sql + MySQLiteHelper.PERSON_AGE + " = '" + person.getAge()
					+ "'";
			person.setAge("");
			if (!person.empty())
				sql = sql + " AND ";
		}
		if (!person.getPersonCurseOfStudies().equals(""))
			sql = sql + MySQLiteHelper.PERSON_CURSE_OF_STUDIES + " = '"
					+ person.getPersonCurseOfStudies() + "'";
		return sql;
	}
	
		public boolean deleteDir(File dir) {
			if(dir.isDirectory()) {
				String[] entries = dir.list();
				
				for (int x=0; x<entries.length; x++) {
				File aktFile =  new File(dir.getPath(), entries[x]);
				deleteDir(aktFile);
				}
				if (dir.delete())
					return true;
				else
					return false;
			}
			else{
				if (dir.delete())
					return true;
				else
					return false;
			}
		}

}
