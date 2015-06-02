package ke.co.mediashare.mediashare;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.app.Activity;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;


public class MainActivity extends Activity {
    private TextView hello_text;
    private TextView welcome_text;
    private TextView tag_line_text;
    private TextView slide_text;
    private TextView media_share_text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Setting Typeface for the Start Screen's TextViews
        hello_text = (TextView) findViewById(R.id.hello_text);
        Typeface typeface = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensedBold.ttf");
        hello_text.setTypeface(typeface);

        welcome_text = (TextView) findViewById(R.id.welcome_text);
        Typeface typeface1 = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensedRegular.ttf");
        welcome_text.setTypeface(typeface1);

        media_share_text = (TextView) findViewById(R.id.media_share_text);
        Typeface typeface4 = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensedBold.ttf");
        media_share_text.setTypeface(typeface4);


        tag_line_text = (TextView) findViewById(R.id.tag_line_text);
        Typeface typeface2 = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensedRegular.ttf");
        tag_line_text.setTypeface(typeface2);

        slide_text = (TextView) findViewById(R.id.slide_text);
        Typeface typeface3 = Typeface.createFromAsset(getAssets(), "fonts/RobotoCondensedLight.ttf");
        slide_text.setTypeface(typeface3);


    }

    public void loginActivity(View view) {
        Intent login_intent = new Intent(this, LoginActivity.class);
        startActivity(login_intent);

        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
