package ke.co.mediashare.mediashare;

import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;


public class DashboardActivity extends ActionBarActivity {
    private Button btn_discover_books;
    private Button btn_my_profile;
    private Button admin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dashboard);

        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.action_bar_color)));

        admin = (Button) findViewById(R.id.btn_admin);
        btn_my_profile = (Button) findViewById(R.id.btn_my_profile);
        btn_discover_books = (Button) findViewById(R.id.btn_discover_books);
        btn_discover_books.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), DiscoverBooksActivity.class);
                startActivity(intent);
            }
        });

        // Setting onClick listener for My Profile button
        btn_my_profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                startActivity(intent);
            }
        });

        // Setting onClick listener for Admin
        admin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent().setClass(getApplicationContext(), AdminActivity.class);
                startActivity(intent);
            }
        });

    }

}
