package ke.co.mediashare.mediashare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.MediaShareDatabaseAdapter;


public class SignInActivity extends Fragment implements View.OnClickListener {
	private Button signIn_button;
	private EditText email_text;
	private EditText password_text;
	private Context DIALOG_CONTEXT;
	private ProgressDialog progressDialog;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View view = inflater.inflate(R.layout.activity_signin, container, false);

		DIALOG_CONTEXT = getActivity();
		email_text = (EditText) view.findViewById(R.id.text_login_email);
		password_text = (EditText) view.findViewById(R.id.text_login_password);
		// Instantiate SignIn Button
		signIn_button = (Button) view.findViewById(R.id.button_signin);
		// Set SignIn Button onClickListener
		signIn_button.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View view) {
		userSignIn();
	}

	// Method for User signIn
	public void userSignIn() {
		try {
			if (TextUtils.isEmpty(email_text.getText().toString())) {
				email_text.setError("You must provide your email");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your email", Toast.LENGTH_SHORT).show();

			} else if (TextUtils.isEmpty(password_text.getText().toString())) {
				password_text.setError("You must provide your password");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your password", Toast.LENGTH_SHORT).show();
			} else {
				try {
					String email = email_text.getText().toString();
					String password = password_text.getText().toString();

					MediaShareDatabaseAdapter mediaShareDatabaseAdapter = new MediaShareDatabaseAdapter(getActivity().getApplicationContext());
					String returned_password = mediaShareDatabaseAdapter.newSignIn(email);
					if (password.equals(returned_password)) {
						Intent intent = new Intent(getActivity(), DiscoverBooksActivity.class);
						startActivity(intent);
						resetSignIn();
						Toast.makeText(getActivity(), "Welcome", Toast.LENGTH_SHORT).show();
					} else {
						Toast.makeText(getActivity(), "User does not exist. Try again", Toast.LENGTH_SHORT).show();
					}
				} catch (Exception e) {
					Log.d("Database Error", e.getMessage());
				}


			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}


	}

	public void resetSignIn() {
		email_text.setText("");
		password_text.setText("");
	}


}
