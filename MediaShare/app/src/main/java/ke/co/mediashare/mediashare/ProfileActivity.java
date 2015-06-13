package ke.co.mediashare.mediashare;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;


public class ProfileActivity extends ActionBarActivity {
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] drawer_list_items;
    private int[] drawer_image_list;
    private Toolbar toolbar;
    private RecyclerView recyclerView;
    private RecyclerView.Adapter recylerAdapter;
    private RecyclerView.LayoutManager recyclerLayoutManager;
    private String name = "Dennis Mwebia";
    private String email = "mwebia@live.com";
    private int photo = R.drawable.read_later;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        // Defining Toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_drawer);
        setSupportActionBar(toolbar);

        // Set Navigation List image drawables
        drawer_image_list = new int[]{R.drawable.ic_home, R.drawable.ic_profile, R.drawable.ic_order_history, R.drawable.ic_wish_list, R.drawable.ic_action_io};
        // Set the Navigation List Items
        drawer_list_items = getResources().getStringArray(R.array.navigation_drawer_array);

        // Set the Navigation RecycleView
        recyclerView = (RecyclerView) findViewById(R.id.navigation_recycler_view);
        recyclerView.setHasFixedSize(true);
        recylerAdapter = new MediaShareListAdapter(drawer_list_items, drawer_image_list, name, email, photo);
        recyclerView.setAdapter(recylerAdapter);
        recyclerLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(recyclerLayoutManager);

        // Set the DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);

        // Set the ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar, R.string.my_profile, R.string.my_profile) {
            public void onDrawerOpened(View drawerView) {
                super.onDrawerOpened(drawerView);
                toolbar.setTitle("Home");
            }

            public void onDrawerClosed(View drawerView) {
                super.onDrawerClosed(drawerView);
                toolbar.setTitle("My Account");
            }
        };

        drawerLayout.setDrawerListener(actionBarDrawerToggle);
        actionBarDrawerToggle.syncState();
    }
    
}
