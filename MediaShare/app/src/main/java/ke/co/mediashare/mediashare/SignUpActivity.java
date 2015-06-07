package ke.co.mediashare.mediashare;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import io.realm.Realm;
import ke.co.mediashare.mediashare.ke.co.mediashare.mediashare.database.Users;


public class SignUpActivity extends Fragment {
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
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceBundle) {
        View view = inflater.inflate(R.layout.activity_signup, container, false);

        first_name = (EditText) getActivity().findViewById(R.id.edit_text_first_name);
        last_name = (EditText) getActivity().findViewById(R.id.edit_text_last_name);
        email_address = (EditText) getActivity().findViewById(R.id.edit_text_email_address);
        password = (EditText) getActivity().findViewById(R.id.edit_text_password);
        confirm_password = (EditText) getActivity().findViewById(R.id.edit_text_confirm_password);
        terms_checkbox = (CheckBox) getActivity().findViewById(R.id.checkbox_terms_of_service);
        create_account = (Button) getActivity().findViewById(R.id.btn_create_account);
        create_account.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createUser();
            }
        });

        return view;
    }

//    public void onClick(View view) {
//        createUser();
//    }

    public void createUser() {
        try {
            if (terms_checkbox.isChecked()) {

                AsyncTask<Void, Void, Void> task = new AsyncTask<Void, Void, Void>() {
                    @Override
                    protected void onPreExecute() {
                        progressDialog = new ProgressDialog(getActivity().getApplicationContext());
                        progressDialog.setTitle("SignUp");
                        progressDialog.setMessage("Creating Account.....");
                        progressDialog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
                        progressDialog.setCancelable(false);
                        progressDialog.setIndeterminate(true);
                        progressDialog.show();
                    }

                    @Override
                    protected Void doInBackground(Void... params) {
                        try {
                            Realm realm = Realm.getInstance(getActivity().getApplicationContext());
                            realm.beginTransaction();
                            Users users = realm.createObject(Users.class);

                            users.setFirst_name(first_name.getText().toString());
                            users.setLast_name(last_name.getText().toString());
                            users.setEmail_address(email_address.getText().toString());
                            users.setPassword(password.getText().toString());

                            realm.commitTransaction();

                            Toast.makeText(getActivity().getApplicationContext(), "Account has been successfully created", Toast.LENGTH_LONG).show();

                            Thread.sleep(5000);
                        } catch (Exception ex) {

                            ex.printStackTrace();
                        }

                        return null;
                    }

                    @Override
                    protected void onPostExecute(Void result) {
                        progressDialog.dismiss();
                    }

                };
                task.execute((Void[]) null);


            } else {
                Toast.makeText(context, "Make sure the passwords match and you have agreed to our terms", Toast.LENGTH_LONG).show();
            }


        } catch (Exception ex) {

            ex.printStackTrace();
        }


    }
}
