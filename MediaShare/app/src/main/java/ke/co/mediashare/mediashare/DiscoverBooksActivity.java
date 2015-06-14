package ke.co.mediashare.mediashare;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
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

        setContentView(R.layout.activity_discover_books);
        // Setting up Fragments
        NewReleasesActivity newReleasesActivity = new NewReleasesActivity();
        BestSellersActivity bestSellersActivity = new BestSellersActivity();
        RecommendedBooksActivity recommendedBooksActivity = new RecommendedBooksActivity();

        // Assigning instantiated Fragments to the Fragments array
        fragments = new Fragment[]{newReleasesActivity, bestSellersActivity, recommendedBooksActivity};

        toolbar = (Toolbar) findViewById(R.id.toolbar_discover_books);
        setSupportActionBar(toolbar);

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
        DrawerLayout drawerLayout;
        ActionBarDrawerToggle actionBarDrawerToggle;
        String[] drawer_list_items;
        int[] drawer_image_list;
        RecyclerView recyclerView;
        RecyclerView.Adapter recyclerAdapter;
        RecyclerView.LayoutManager recyclerLayoutManager;
        String name = "Dennis Mwebia";
        String email = "mwebia@live.com";
        int photo = R.drawable.read_later;

        // Set Navigation List image drawables
        drawer_image_list = new int[]{R.drawable.ic_home, R.drawable.ic_profile, R.drawable.ic_order_history, R.drawable.ic_wish_list, R.drawable.ic_action_io};
        // Set the Navigation List Items
        drawer_list_items = getResources().getStringArray(R.array.discover_books_drawer_array);

        // Set the Navigation RecycleView
        recyclerView = (RecyclerView) findViewById(R.id.discover_books_navigation_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerAdapter = new MediaShareListAdapter(drawer_list_items, drawer_image_list, name, email, photo);
        recyclerView.setAdapter(recyclerAdapter);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        // Set the DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.books_drawer_layout);

        // Set the ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.my_profile, R.string.my_profile) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Discover Books");
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("Discover Books");
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }

}
