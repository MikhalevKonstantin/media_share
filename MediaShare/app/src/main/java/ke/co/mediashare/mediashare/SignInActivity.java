package ke.co.mediashare.mediashare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


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
				email_text.setError("You must provide your email address");
				Toast.makeText(DIALOG_CONTEXT, "Please provide your email address", Toast.LENGTH_LONG).show();

			} else if (TextUtils.isEmpty(password_text.getText().toString())) {
				password_text.setError("You must provide your password");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your password", Toast.LENGTH_LONG).show();

			} else if (!email_text.getText().toString().equals("user@mediashare.com") && !password_text.getText().toString().equals("mediashare")) {
				Toast.makeText(getActivity(), "Incorrect password or email", Toast.LENGTH_LONG).show();
				email_text.setError("Incorrect email or password");
				password_text.setError("Incorrect password or email");

			} else if (email_text.getText().toString().equals("user@mediashare.com") && password_text.getText().toString().equals("mediashare")) {
				AsyncTask<Void, Void, Void> signInTask = new AsyncTask<Void, Void, Void>() {
					@Override
					protected void onPreExecute() {
						progressDialog = new ProgressDialog(DIALOG_CONTEXT);
						progressDialog.setTitle("SignIn");
						progressDialog.setMessage("Signing In.....");
						progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						progressDialog.setCancelable(false);
						progressDialog.setIndeterminate(true);
						progressDialog.show();
					}

					@Override
					protected Void doInBackground(Void... params) {
						Intent signInIntent = new Intent(getActivity(), DiscoverBooksActivity.class);
						startActivity(signInIntent);
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						progressDialog.dismiss();
						resetSignIn();
					}
				};
				signInTask.execute((Void[]) null);
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
