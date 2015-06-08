package ke.co.mediashare.mediashare;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


public class SignInActivity extends Fragment implements View.OnClickListener {
    private Button signIn_button;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_signin, container, false);

        // Instantiate SignIn Button
        signIn_button = (Button) view.findViewById(R.id.button_signin);
        // Set SignIn Button onClickListener
        signIn_button.setOnClickListener(this);
        return view;
    }

    @Override
    public void onClick(View view) {
        Intent intent = new Intent(view.getContext(), DashboardActivity.class);
        startActivity(intent);
    }


}
