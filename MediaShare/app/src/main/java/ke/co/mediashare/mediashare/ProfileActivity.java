package ke.co.mediashare.mediashare;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

public class ProfileActivity extends ActionBarActivity {
	ArrayList<Integer> icons_list;
	ArrayList<String> text_list;

	RecyclerView recyclerView;
	RecyclerView.Adapter recyclerAdapter;
	RecyclerView.LayoutManager recyclerLayoutManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_profile);

		Toolbar toolbar;
		setUpListItems();

		// Defining Toolbar
		toolbar = (Toolbar) findViewById(R.id.toolbar);
		toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
		setSupportActionBar(toolbar);

		recyclerView = (RecyclerView) findViewById(R.id.profile_items_recyclerview);
		recyclerView.setHasFixedSize(true);
		recyclerAdapter = new MediaShareProfileListAdapter(icons_list, text_list);
		recyclerView.setAdapter(recyclerAdapter);
		recyclerLayoutManager = new LinearLayoutManager(this);
		recyclerView.setLayoutManager(recyclerLayoutManager);

	}

	public void setUpListItems() {
		icons_list = new ArrayList<>();
		icons_list.add(R.drawable.ic_action_invite_friends);
		icons_list.add(R.drawable.ic_action_share);
		icons_list.add(R.drawable.ic_action_settings);
		icons_list.add(R.drawable.ic_action_edit);

		text_list = new ArrayList<>();
		text_list.add("Invite Friends");
		text_list.add("Share App");
		text_list.add("Settings");
		text_list.add("Edit Profile");
	}

}
