package ke.co.mediashare.mediashare;

import android.content.DialogInterface;
import android.database.Cursor;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import ke.co.mediashare.mediashare.adapters.LibrariesExpandableListAdapter;
import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.Libraries;
import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.MediaShareDatabaseAdapter;
import ke.co.mediashare.mediashare.utils.IterableCursor;

public class MyLibraryActivity extends ActionBarActivity {
	List<String> libraryGroupList;
	List<String> libraryChildList;
	Map<String, List<String>> libraryCollection;
	MediaShareDatabaseAdapter databaseAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		databaseAdapter = new MediaShareDatabaseAdapter(this);

		if (databaseAdapter.listLibraries().getCount() == 0) {
			setContentView(R.layout.activity_my_library_empty);

			Toolbar toolbar;
			toolbar = (Toolbar) findViewById(R.id.toolbar_my_library_empty);
			toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
			setSupportActionBar(toolbar);

			Button addLibraryButton = (Button) findViewById(R.id.btnAddLibrary);
			addLibraryButton.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					addLibrary();
				}
			});

		} else {
			setUpLibraryView();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.menu_my_library, menu);

		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_add_library) {

			addLibrary();

			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	// Method for setting up Library view
	public void setUpLibraryView() {
		setContentView(R.layout.activity_my_library);

		Toolbar toolbar;
		toolbar = (Toolbar) findViewById(R.id.toolbar_my_library);
		toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
		toolbar.inflateMenu(R.menu.menu_my_library);
		setSupportActionBar(toolbar);

		createLibraryGroupList(); // Load Library names
		createLibraryCollection(); // Create collections

		ExpandableListView expandableListView = (ExpandableListView) findViewById(R.id.my_library_expView);
		LibrariesExpandableListAdapter expandableListAdapter = new LibrariesExpandableListAdapter(this, libraryGroupList, libraryCollection);
		expandableListView.setAdapter(expandableListAdapter);
	}


	// Method for starting a "New Library" dialog
	public void addLibrary() {
		final EditText newLibText = new EditText(this);
		newLibText.setHint("Enter library name");

		AlertDialog.Builder addLibraryDialog = new AlertDialog.Builder(this);
		addLibraryDialog.setTitle("Add Library");
		addLibraryDialog.setView(newLibText);
		addLibraryDialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {

			}
		});
		addLibraryDialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				MediaShareDatabaseAdapter databaseAdapter = new MediaShareDatabaseAdapter(MyLibraryActivity.this);
				databaseAdapter.addLibrary(newLibText.getText().toString());

				Toast.makeText(MyLibraryActivity.this, newLibText.getText().toString() + " has been added to your list of libraries", Toast.LENGTH_LONG).show();

				setUpLibraryView();

			}
		});
		AlertDialog libAlert = addLibraryDialog.create();
		libAlert.show();
	}

	// Method for creating a list of Library names from the database
	public void createLibraryGroupList() {
		libraryGroupList = new ArrayList<String>();
		Cursor libraries = databaseAdapter.listLibraries();
		for (Cursor cursor : new IterableCursor(libraries)) {
			String library = cursor.getString(cursor.getColumnIndex(Libraries.LIBRARY_NAME));
			libraryGroupList.add(library);
		}
	}

	// Method for Library items
	public void loadChildItems(String[] libraryNames) {
		libraryChildList = new ArrayList<String>();
		for (String libraryItem : libraryNames) {
			libraryChildList.add(libraryItem);
		}
	}

	// Method for aggregating Library collections
	public void createLibraryCollection() {
		String[] books = {"Linux Fundamentals", "VB.NET Book", "Mary"};
		libraryCollection = new LinkedHashMap<String, List<String>>();
		for (String bookItem : libraryGroupList) {
			loadChildItems(books);
			libraryCollection.put(bookItem, libraryChildList);
		}
	}
}
