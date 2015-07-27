package ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by guidovanrossum on 08/07/15.
 */
public class MediaShareDatabaseAdapter extends SQLiteOpenHelper {
	public static final String DATABASE_NAME = "media_share.db";
	public static final int DATABASE_VERSION = 1;

	public MediaShareDatabaseAdapter(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {
		// Create Users Table
		db.execSQL("CREATE TABLE " + Users.TABLE_NAME + "("
				+ Users.USER_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
				Users.USER_FIRST_NAME + " TEXT," + Users.USER_LAST_NAME + " TEXT," +
				Users.USER_EMAIL + " TEXT," + Users.USER_PASSWORD + " TEXT" + ");");

		// Create Libraries Table
		db.execSQL("CREATE TABLE " + Libraries.TABLE_NAME + "(" + Libraries.LIBRARY_ID +
				" INTEGER PRIMARY KEY AUTOINCREMENT," + Libraries.LIBRARY_NAME + " TEXT);");
	}

	// Wrapper method for adding a new user
	public long addUser(String first_name, String last_name, String email, String password) {

		ContentValues users = new ContentValues();
		users.put(Users.USER_FIRST_NAME, first_name);
		users.put(Users.USER_LAST_NAME, last_name);
		users.put(Users.USER_EMAIL, email);
		users.put(Users.USER_PASSWORD, password);

		SQLiteDatabase database = getWritableDatabase();

		return database.insert(Users.TABLE_NAME, Users.USER_FIRST_NAME, users);
	}

	// Wrapper method for adding a new Library
	public long addLibrary(String library_name) {
		ContentValues libraries = new ContentValues();
		libraries.put(Libraries.LIBRARY_NAME, library_name);
		SQLiteDatabase database = getWritableDatabase();
		return database.insert(Libraries.TABLE_NAME, Libraries.LIBRARY_NAME, libraries);
	}

	// Wrapper method for listing Libraries
	public Cursor listLibraries() {
		SQLiteDatabase database = getReadableDatabase();
		return database.query(Libraries.TABLE_NAME, new String[]{Libraries.LIBRARY_NAME}, null, null, null, null, null);
	}

	// Wrapper method for returning a password
	public String newSignIn(String email) {
		SQLiteDatabase database = getReadableDatabase();
		Cursor cursor = database.query(Users.TABLE_NAME, null,
				Users.USER_EMAIL + "=?", new String[]{email}, null,
				null, null);
		if (cursor.getCount() < 1) {
			cursor.close();
			return "Client Does Not Exist";
		}
		cursor.moveToFirst();

		return cursor.getString(cursor.getColumnIndex(Users.USER_PASSWORD));
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub

	}

	public void onUpgrade() {
		// TODO Auto-generated method stub
		SQLiteDatabase db = getWritableDatabase();
		Log.w("LOG_TAG", "Upgrading database");
		// KILL PREVIOUS
		db.execSQL("DROP TABLE IF EXISTS " + Libraries.TABLE_NAME);
		db.execSQL("DROP TABLE IF EXISTS " + Users.TABLE_NAME);
		onCreate(db);

	}



}