package de.vsis.clh;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.hardware.Camera.Face;
import android.util.Log;

public class MySQLiteHelper extends SQLiteOpenHelper {

	// fields
	public static final String PERSON_ID = "_id";
	public static final String PERSON_PICTURES = "PERSON_PICTURES";
	public static final String PERSON_FIRSTNAME = "PERSON_FIRSTNAME";
	public static final String PERSON_LASTNAME = "PERSON_LASTNAME";
	public static final String PERSON_AGE = "PERSON_AGE";
	public static final String PERSON_CURSE_OF_STUDIES = "PERSON_CURSE_OF_STUDIES";
	public static Face PERSON_FACE;

	private static final String DATABASE_NAME = "peopleWithPictures.db";
	public static final String DATABASE_TABLE = "people";
	private static final int DATABASE_VERSION = 1;
	static String TITEL = "Scanned People";
	static String VALUE = "value";

	// constructor
	public MySQLiteHelper(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	// SQL Statement to create a new database.
	private static final String DATABASE_CREATE = " create table "
			+ DATABASE_TABLE + " (" + PERSON_ID
			+ " integer primary key autoincrement, " + PERSON_PICTURES
			+ " text not null, " + PERSON_FIRSTNAME + " text not null, "
			+ PERSON_LASTNAME + " text not null, " + PERSON_AGE
			+ " text not null, " + PERSON_CURSE_OF_STUDIES + " text not null "
			+ ")";

	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL(DATABASE_CREATE);
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// Log the version Update
		Log.w("TaskDBAdapter", "Upgrading from version " + oldVersion + " to "
				+ newVersion + ", which will destroy all old data");

		// Creating a new version by dropping the old version
		db.execSQL("Drop Table if it exists " + DATABASE_TABLE);
		// Creating a new db
		onCreate(db);
	}

}
