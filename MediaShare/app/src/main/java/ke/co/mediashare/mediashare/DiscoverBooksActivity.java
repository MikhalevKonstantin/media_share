package ke.co.mediashare.mediashare;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.GestureDetector;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class DiscoverBooksActivity extends ActionBarActivity {
	Toolbar toolbar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		MediaShareViewPageAdapter pagerAdapter;
		SlidingTabLayout slidingTabLayout;
		ViewPager viewPager;
		CharSequence[] tabTitles = {"New Releases", "Best Sellers", "Recommendations"};
		int number_of_tabs = 3;
		Fragment[] fragments;
		SearchView searchView;

		setContentView(R.layout.activity_discover_books);
		// Setting up Fragments
		NewReleasesActivity newReleasesActivity = new NewReleasesActivity();
		BestSellersActivity bestSellersActivity = new BestSellersActivity();
		RecommendedBooksActivity recommendedBooksActivity = new RecommendedBooksActivity();

		// Assigning instantiated Fragments to the Fragments array
		fragments = new Fragment[]{newReleasesActivity, bestSellersActivity, recommendedBooksActivity};

		// Setting up Toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar_discover_books);
		toolbar.inflateMenu(R.menu.menu_discover_books);
		setSupportActionBar(toolbar);

		// Setting up SearchView
		searchView = (SearchView) toolbar.getMenu().findItem(R.id.action_search).getActionView();
		searchView.setQueryHint("Search Books");
		searchView.setIconified(true);

		pagerAdapter = new MediaShareViewPageAdapter(getSupportFragmentManager(), tabTitles, number_of_tabs, fragments);
		viewPager = (ViewPager) findViewById(R.id.viewPager_discover_books);
		viewPager.setAdapter(pagerAdapter);

		slidingTabLayout = (SlidingTabLayout) findViewById(R.id.tabs_discover_books);
		slidingTabLayout.setDistributeEvenly(true);
		slidingTabLayout.setViewPager(viewPager);
		slidingTabLayout.setViewPager(viewPager);

		slidingTabLayout.setCustomTabColorizer(new SlidingTabLayout.TabColorizer() {
			@Override
			public int getIndicatorColor(int position) {
				return getResources().getColor(R.color.TabScrollColor);
			}
		});

		setUpDrawer();

	}

	// Method for setting up a Drawer
	public void setUpDrawer() {
		final DrawerLayout drawerLayout;
		ActionBarDrawerToggle actionBarDrawerToggle;
		String[] drawer_list_items;
		int[] drawer_image_list;
		RecyclerView recyclerView;
		RecyclerView.Adapter recyclerAdapter;
		RecyclerView.LayoutManager recyclerLayoutManager;
		String name = "Dennis Mwebia";
		String email = "mwebia@live.com";
		int photo = R.drawable.ic_profile_image;

		// Set the DrawerLayout
		drawerLayout = (DrawerLayout) findViewById(R.id.books_drawer_layout);
		// Set Navigation List image drawables
		drawer_image_list = new int[]{R.drawable.ic_action_profile, R.drawable.ic_action_dictionary, R.drawable.ic_action_read_later, R.drawable.ic_action_library, R.drawable.ic_action_settings, R.drawable.ic_action_faq, R.drawable.ic_action_logout};
		// Set the Navigation List Items
		drawer_list_items = getResources().getStringArray(R.array.discover_books_drawer_array);

		// Set the Navigation RecycleView
		recyclerView = (RecyclerView) findViewById(R.id.discover_books_navigation_recycler_view);
		recyclerView.setHasFixedSize(true);
		recyclerAdapter = new MediaShareNavigationDrawerAdapter(drawer_list_items, drawer_image_list, name, email, photo);
		recyclerView.setAdapter(recyclerAdapter);

		// Defining GestureDetector Object
		final GestureDetector gestureDetector = new GestureDetector(this, new GestureDetector.SimpleOnGestureListener() {
			@Override
			public boolean onSingleTapUp(MotionEvent motionEvent) {

				return true;
			}

		});
		recyclerView.addOnItemTouchListener(new RecyclerView.OnItemTouchListener() {
			@Override
			public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
				View child = rv.findChildViewUnder(e.getX(), e.getY());
				if (child != null && gestureDetector.onTouchEvent(e)) {
					drawerLayout.closeDrawers();
					switch (rv.getChildPosition(child)) {
						case 1:
							Intent profileIntent = new Intent(DiscoverBooksActivity.this, ProfileActivity.class);
							startActivity(profileIntent);
							break;
						case 2:
							Intent dictionaryIntent = new Intent(DiscoverBooksActivity.this, DictionaryActivity.class);
							startActivity(dictionaryIntent);
							break;
						case 3:
							Intent readlaterIntent = new Intent(DiscoverBooksActivity.this, ReadLaterListActivity.class);
							startActivity(readlaterIntent);
							break;
						case 4:
							Intent libraryIntent = new Intent(DiscoverBooksActivity.this, MyLibraryActivity.class);
							startActivity(libraryIntent);
							break;
						case 5:
							Intent adminIntent = new Intent(DiscoverBooksActivity.this, AdminActivity.class);
							startActivity(adminIntent);
							break;

						case 7:
							AlertDialog.Builder logoutBuilder = new AlertDialog.Builder(DiscoverBooksActivity.this);
							logoutBuilder.setTitle("Logout");
							logoutBuilder.setMessage("Are you sure you want to logout?");
							logoutBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {

								}
							});
							logoutBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
								@Override
								public void onClick(DialogInterface dialog, int which) {

								}
							});

							AlertDialog alertDialog = logoutBuilder.create();
							alertDialog.show();
							break;
					}

					return true;
				}
				return false;
			}

			@Override
			public void onTouchEvent(RecyclerView rv, MotionEvent e) {

			}

			@Override
			public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {

			}
		});
		recyclerLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(recyclerLayoutManager);

		// Set the ActionBarDrawerToggle
		actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.title_activity_discover_books, R.string.title_activity_discover_books) {
			public void onDrawerOpened(View drawerView) {
				super.onDrawerOpened(drawerView);

			}

			public void onDrawerClosed(View drawerView) {
				super.onDrawerClosed(drawerView);

			}
		};

		drawerLayout.setDrawerListener(actionBarDrawerToggle);
		actionBarDrawerToggle.syncState();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_discover_books, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_search) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

}
