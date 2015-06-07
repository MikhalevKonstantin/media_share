package ke.co.mediashare.mediashare;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.app.ActionBar;
import android.support.v7.app.ActionBar.Tab;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.Users;


public class LoginActivity extends ActionBarActivity {
    private static final String TAG = "media_share";
    private Button signIn;
    private EditText first_name;
    private EditText last_name;
    private EditText email_address;
    private EditText password;
    private EditText confirm_password;
    private CheckBox terms_checkbox;
    private Button create_account;
    private Context context;
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        setUpTabs(savedInstanceState);


    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        //            save the selected tab's index so it's re-selected on orientation change
        outState.putInt("tabIndex", getSupportActionBar().getSelectedNavigationIndex());
    }

    private void setUpTabs(Bundle savedInstanceState) {
        ActionBar actionBar = getSupportActionBar();
        actionBar.setBackgroundDrawable(new ColorDrawable(getResources().getColor(R.color.action_bar_color)));
        actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);

        Tab signIn_tab = actionBar.newTab();
        Tab signUp_tab = actionBar.newTab();

        SignInActivity signInActivity = new SignInActivity();
        signIn_tab.setText("LOGIN").setContentDescription("login_tab").setTabListener(new MediaShareTabListener<SignInActivity>(signInActivity));

        SignUpActivity signUpActivity = new SignUpActivity();
        signUp_tab.setText("CREATE ACCOUNT").setContentDescription("sign_up_tab").setTabListener(new MediaShareTabListener<SignUpActivity>(signUpActivity));

        actionBar.addTab(signIn_tab);
        actionBar.addTab(signUp_tab);

        if (savedInstanceState != null) {
            Log.i(TAG, "setting selected tab from saved bundle");
//            get the saved selected tab's index and set that tab as selected
            actionBar.setSelectedNavigationItem(savedInstanceState.getInt("tabIndex", 0));
        }
    }


}
