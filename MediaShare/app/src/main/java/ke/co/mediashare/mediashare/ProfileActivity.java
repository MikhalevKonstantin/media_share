package ke.co.mediashare.mediashare;

import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.widget.ListView;


public class ProfileActivity extends ActionBarActivity {
    private ListView navigation_list_view;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] drawer_list_items;
    private Integer[] drawer_image_list;
    private Toolbar toolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        toolbar = (Toolbar)findViewById(R.id.toolbar);
        toolbar.setNavigationIcon(R.drawable.ic_action_home);
        setSupportActionBar(toolbar);

        // Set Navigation List image drawables
        drawer_image_list = new Integer[]{R.drawable.ic_profile, R.drawable.ic_order_history, R.drawable.ic_wish_list, R.drawable.ic_action_io};
        // Set the Navigation List Items
        drawer_list_items = getResources().getStringArray(R.array.navigation_drawer_array);

        // Set the Navigation ListView
        navigation_list_view = (ListView) findViewById(R.id.NavigationListView);

        // Set the DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);

        // Set the ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, toolbar,R.string.my_profile, R.string.my_profile);

        // Set the ArrayAdapter for the Navigation ListView
        MediaShareListAdapter adapter = new MediaShareListAdapter(this, drawer_image_list, drawer_list_items);
        navigation_list_view.setAdapter(adapter);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


    }

}
