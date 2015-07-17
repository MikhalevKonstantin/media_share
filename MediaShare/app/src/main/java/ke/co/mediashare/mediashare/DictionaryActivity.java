package ke.co.mediashare.mediashare;

import android.app.SearchManager;
import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class DictionaryActivity extends AppCompatActivity {
	ArrayAdapter<String> myAdapter;
	ListView listView;
	String[] dataArray = new String[] {"India","Kenya", "Pakistan", "SriLanka", "Nepal", "Japan", "Uganda", "Tanzania", "Gabon"};
	Toolbar toolbar;
	SearchView searchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_dictionary);

		toolbar = (Toolbar)findViewById(R.id.dictionary_toolbar);
		toolbar.inflateMenu(R.menu.menu_dictionary);
		toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
		setSupportActionBar(toolbar);

		listView = (ListView)findViewById(R.id.dictionary_listView);
		myAdapter = new ArrayAdapter<String>(this, R.layout.rows_dictionary_list_items, R.id.dictionary_textView, dataArray);
		listView.setAdapter(myAdapter);
		listView.setTextFilterEnabled(true);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_dictionary, menu);

		SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
		SearchView searchView = (SearchView) menu.findItem(R.id.action_dictionary_search).getActionView();

		searchView.setSearchableInfo(searchManager.getSearchableInfo(getComponentName()));
		searchView.setIconifiedByDefault(true);

		SearchView.OnQueryTextListener textChangeListener = new SearchView.OnQueryTextListener()
		{
			@Override
			public boolean onQueryTextChange(String newText)
			{
				// this is your adapter that will be filtered
				myAdapter.getFilter().filter(newText);
				System.out.println("on text chnge text: "+newText);
				return true;
			}
			@Override
			public boolean onQueryTextSubmit(String query)
			{
				// this is your adapter that will be filtered
				myAdapter.getFilter().filter(query);
				System.out.println("on query submit: "+query);
				return true;
			}
		};
		searchView.setOnQueryTextListener(textChangeListener);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_dictionary_search) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}
}
