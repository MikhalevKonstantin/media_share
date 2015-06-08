package ke.co.mediashare.mediashare;

import android.graphics.drawable.ColorDrawable;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class ProfileActivity extends ActionBarActivity {
    private ListView navigation_list_view;
    private DrawerLayout drawerLayout;
    private ActionBarDrawerToggle actionBarDrawerToggle;
    private String[] drawer_list_items;
    private Integer[] drawer_image_list;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.action_bar_color)));

        // Set Navigation List image drawables
        drawer_image_list = new Integer[]{R.drawable.ic_home, R.drawable.ic_profile, R.drawable.ic_order_history, R.drawable.ic_wish_list};
        // Set the Navigation List Items
        drawer_list_items = getResources().getStringArray(R.array.navigation_drawer_array);

        // Set the Navigation ListView
        navigation_list_view = (ListView) findViewById(R.id.NavigationListView);

        // Set the DrawerLayout
        drawerLayout = (DrawerLayout) findViewById(R.id.DrawerLayout);

        // Set the ActionBarDrawerToggle
        actionBarDrawerToggle = new ActionBarDrawerToggle(this, drawerLayout, R.string.my_profile, R.string.my_profile);

        // Set the ArrayAdapter for the Navigation ListView
        MediaShareListAdapter adapter = new MediaShareListAdapter(this, drawer_image_list, drawer_list_items);
        navigation_list_view.setAdapter(adapter);
        drawerLayout.setDrawerListener(actionBarDrawerToggle);


    }

}
