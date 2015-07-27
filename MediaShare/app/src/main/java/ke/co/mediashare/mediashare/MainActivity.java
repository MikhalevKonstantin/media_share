package ke.co.mediashare.mediashare;

import android.content.Intent;
import android.content.res.AssetManager;
import android.graphics.Typeface;
import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.MediaShareDatabaseAdapter;


public class MainActivity extends Activity {
    private Button start_button;
    private AssetManager assetManager;
    private TextView tag_line, app_name;
    private MediaShareDatabaseAdapter adapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Instantiate AssetsManager
        assetManager = getAssets();
        adapter = new MediaShareDatabaseAdapter(this);

        app_name = (TextView)findViewById(R.id.app_name_text);
        tag_line = (TextView)findViewById(R.id.tag_line_text);
        start_button = (Button) findViewById(R.id.btn_start);

        // Setting the Button's Typeface
        app_name.setTypeface(Typeface.createFromAsset(assetManager, "fonts/Capture_it.ttf"));
        tag_line.setTypeface(Typeface.createFromAsset(assetManager, "fonts/Existence_UnicaseLight.otf"));
        start_button.setTypeface(Typeface.createFromAsset(assetManager, "fonts/RobotoCondensedLight.ttf"));

    }

    public void loginActivity(View view) {
        Intent login_intent = new Intent(this, LoginActivity.class);
        startActivity(login_intent);

        overridePendingTransition(R.anim.activity_in, R.anim.activity_out);
    }

}
