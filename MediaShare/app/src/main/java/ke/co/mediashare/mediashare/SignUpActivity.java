package ke.co.mediashare.mediashare;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.MediaShareDatabaseAdapter;

public class SignUpActivity extends Fragment implements View.OnClickListener {
	private EditText first_name;
	private EditText last_name;
	private EditText email_address;
	private EditText password;
	private EditText confirm_password;
	private CheckBox terms_checkbox;
	private Context DIALOG_CONTEXT;
	private ProgressDialog progressDialog;
	private MediaShareDatabaseAdapter mediaShareDatabaseAdapter;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
		View view = inflater.inflate(R.layout.activity_signup, container, false);

		mediaShareDatabaseAdapter = new MediaShareDatabaseAdapter(getActivity());

		DIALOG_CONTEXT = getActivity();
		first_name = (EditText) view.findViewById(R.id.edit_text_first_name);
		last_name = (EditText) view.findViewById(R.id.edit_text_last_name);
		email_address = (EditText) view.findViewById(R.id.edit_text_email_address);
		password = (EditText) view.findViewById(R.id.edit_text_password);
		confirm_password = (EditText) view.findViewById(R.id.edit_text_confirm_password);
		terms_checkbox = (CheckBox) view.findViewById(R.id.checkbox_terms_of_service);
		Button create_account = (Button) view.findViewById(R.id.btn_create_account);
		create_account.setOnClickListener(this);
		return view;
	}

	@Override
	public void onClick(View view) {
		createUser();
	}

	// Method for Creating a new User
	public void createUser() {
		try {
			if (TextUtils.isEmpty(first_name.getText().toString())) {
				first_name.setError("You must provide your first name");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your first name", Toast.LENGTH_LONG).show();
			} else if (TextUtils.isEmpty(last_name.getText().toString())) {
				last_name.setError("You must provide your last name");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your last name", Toast.LENGTH_LONG).show();
			} else if (TextUtils.isEmpty(email_address.getText().toString())) {
				email_address.setError("You must provide your email address");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your email address", Toast.LENGTH_LONG).show();
			} else if (TextUtils.isEmpty(password.getText().toString())) {
				password.setError("You must provide your password");
				Toast.makeText(DIALOG_CONTEXT, "You must provide your password", Toast.LENGTH_LONG).show();
			} else if (!terms_checkbox.isChecked()) {
				Toast.makeText(getActivity(), "Please agree to our terms and conditions", Toast.LENGTH_LONG).show();
			} else if (!confirm_password.getText().toString().matches(password.getText().toString())) {
				Toast.makeText(getActivity(), "Please confirm your password again", Toast.LENGTH_LONG).show();
			} else {


				AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
					@Override
					protected void onPreExecute() {
						progressDialog = new ProgressDialog(DIALOG_CONTEXT);
						progressDialog.setTitle("SignUp");
						progressDialog.setMessage("Creating Account.....");
						progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
						progressDialog.setCancelable(false);
						progressDialog.setIndeterminate(true);
						progressDialog.show();
					}

					@Override
					protected Void doInBackground(Void... params) {
						createAccount();
						return null;
					}

					@Override
					protected void onPostExecute(Void result) {
						progressDialog.dismiss();

						Toast.makeText(DIALOG_CONTEXT, "Account has been successfully created", Toast.LENGTH_LONG).show();

						Intent intent = new Intent(DIALOG_CONTEXT, DiscoverBooksActivity.class);
						intent.putExtra("profile_first_name", first_name.getText().toString());
						intent.putExtra("profile_last_name", last_name.getText().toString());
						intent.putExtra("profile_email", email_address.getText().toString());
						startActivity(intent);

						// RESET
						first_name.setText("");
						last_name.setText("");
						email_address.setText("");
						password.setText("");
						confirm_password.setText("");


					}

				};
				task.execute((Void[]) null);


			}


		} catch (Exception ex) {

			ex.printStackTrace();
		}


	}

	public void createAccount() {
		try {
			mediaShareDatabaseAdapter.addUser(first_name.getText().toString(), last_name.getText().toString(), email_address.getText().toString(), password.getText().toString());
			Log.d("MediaShare", "Record has been saved");
		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}
}
