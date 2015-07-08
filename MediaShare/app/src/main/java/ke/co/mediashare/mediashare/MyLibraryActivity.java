package ke.co.mediashare.mediashare;

import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;

public class MyLibraryActivity extends ActionBarActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_my_library);

		Toolbar toolbar;

		toolbar = (Toolbar)findViewById(R.id.toolbar_my_library);
		toolbar.setNavigationIcon(R.drawable.ic_action_arrow_left);
		setSupportActionBar(toolbar);
	}

}
