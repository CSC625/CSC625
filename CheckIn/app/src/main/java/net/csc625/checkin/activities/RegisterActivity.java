package net.csc625.checkin.activities;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import net.csc625.checkin.R;
import net.csc625.checkin.models.dto.AccountDetailsDTO;
import net.csc625.checkin.models.exceptions.ErrorResponse;
import net.csc625.checkin.models.pojos.User;
import net.csc625.checkin.services.UserService;
import net.csc625.checkin.utils.Constants;
import net.csc625.checkin.utils.OrbitUserPreferences;
import net.csc625.checkin.utils.ServerCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RegisterActivity extends AppCompatActivity {

    private FirebaseAuth mAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private int mYear,mMonth,mDay;

    public static Intent createIntent(Context context) {
        Intent i = new Intent(context, RegisterActivity.class);
        return i;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        /*RoleService roleService = new RoleService(this);
        roleService.viewRoles(this);
        mapRoles.clear();
        roleSpinner = (Spinner) findViewById(R.id.roleSpinner);*/

        CardView mEmailSignInButton = (CardView) findViewById(R.id.register_button);
        mEmailSignInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createAccount();
            }
        });

        TextView dateTextView = (TextView) findViewById(R.id.date);

        mAuth = FirebaseAuth.getInstance();
        mAuthListener = new FirebaseAuth.AuthStateListener() {
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                FirebaseUser user = firebaseAuth.getCurrentUser();
                if (user != null) {
                    // User is signed in
                    Log.d("LOGIN", "onAuthStateChanged:signed_in:" + user.getUid());
                } else {
                    // User is signed out
                    Log.d("LOGIN", "onAuthStateChanged:signed_out");
                }
                // ...
            }
        };

        dateTextView.setOnTouchListener(new View.OnTouchListener() {

            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if(event.getAction() == MotionEvent.ACTION_UP){
                    // Initialize a new date picker dialog fragment
                    DialogFragment dFragment = new DatePickerFragment();

                    // Show the date picker dialog fragment
                    dFragment.show(getFragmentManager(), "Date Picker");
                    return true;
                }
                return false;

            }
        });

        final RelativeLayout cancelButton = (RelativeLayout) findViewById(R.id.cancel_action);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                finish();
            }

        });

        TextView mPasswordView = (TextView) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent) {
                if (id == EditorInfo.IME_ACTION_DONE) {
                    createAccount();
                    return true;
                }
                return false;
            }
        });
    }

    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }

    private void createAccount() {

        // Store values at the time of the login attempt.
        TextView emailTextView = (TextView) findViewById(R.id.email);
        TextView passwordTextView = (TextView) findViewById(R.id.password);
        String email = emailTextView.getText().toString();
        String password = passwordTextView.getText().toString();
        EditText firstNameEditText = (EditText)findViewById(R.id.firstName);
        EditText lastNameEditText = (EditText)findViewById(R.id.lastName);
        TextView dobTextView = (TextView) findViewById(R.id.date);
        String firstName = firstNameEditText.getText().toString();
        String lastName = lastNameEditText.getText().toString();
        String dob = dobTextView.getText().toString();

        View focusView = null;

        // Check for a valid first name, if the user entered one.
        if (TextUtils.isEmpty(firstName)) {
            firstNameEditText.setError(getString(R.string.error_field_required));
            focusView = firstNameEditText;
            focusView.requestFocus();
            return;
        }

        // Check for a valid last name, if the user entered one.
        if (TextUtils.isEmpty(lastName)) {
            lastNameEditText.setError(getString(R.string.error_field_required));
            focusView = lastNameEditText;
            focusView.requestFocus();
            return;
        }

        // Check for a valid dob, if the user entered one.
        if (dob.equals("")) {
            dobTextView.setError(getString(R.string.error_field_required));
            focusView = dobTextView;
            focusView.requestFocus();
            return;
        } else {
            dobTextView.setError(null);
        }

        // Check for a valid email address.
        if (TextUtils.isEmpty(email)) {
            emailTextView.setError(getString(R.string.error_field_required));
            focusView = emailTextView;
            focusView.requestFocus();
            return;
        } else if (!isEmailValid(email)) {
            emailTextView.setError(getString(R.string.error_invalid_email));
            focusView = emailTextView;
            focusView.requestFocus();
            return;
        }

        // Check for a valid password, if the user entered one.
        if (TextUtils.isEmpty(password)) {
            passwordTextView.setError(getString(R.string.error_invalid_password));
            focusView = passwordTextView;
            focusView.requestFocus();
            return;
        } else if (!isPasswordValid(password)) {
            passwordTextView.setError(getString(R.string.error_invalid_password));
            focusView = passwordTextView;
            focusView.requestFocus();
            return;
        }

        createAccount(email, password);
    }

    private boolean isEmailValid(String email) {
        //TODO: Replace this with your own logic
        return email.contains("@");
    }

    private boolean isPasswordValid(String password) {
        //TODO: Replace this with your own logic
        return password.length() > 5;
    }

    private void createAccount(final String email, String password) {
        Log.d("LOGIN", "createAccount:" + email);
        if (!validateForm()) {
            return;
        }

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        Log.d("LOGIN", "createUserWithEmail:onComplete:" + task.isSuccessful());

                        // If sign in fails, display a message to the user. If sign in succeeds
                        // the auth state listener will be notified and logic to handle the
                        // signed in user can be handled in the listener.
                        if (!task.isSuccessful()) {
                            Toast.makeText(RegisterActivity.this, R.string.registrationFailed,
                                    Toast.LENGTH_SHORT).show();
                        }
                        else
                        {

                            UserService userService = new UserService(RegisterActivity.this);
                            final OrbitUserPreferences orbitPref = new OrbitUserPreferences(RegisterActivity.this);
                            String userUID = mAuth.getCurrentUser().getUid();
                           // String r = (String) ( (Spinner) findViewById(R.id.roleSpinner) ).getSelectedItem();
                            EditText firstName = (EditText)findViewById(R.id.firstName);
                            EditText lastName = (EditText)findViewById(R.id.lastName);
                            TextView dob = (TextView) findViewById(R.id.date);
                            //Role role = mapRoles.get(r);
                            // Get current date
                            Date dateObj = new Date();
                            String date = new SimpleDateFormat(Constants.DATE_FORMAT_LAST_LOGIN).format(dateObj);
                            User user = new User(email, userUID, date, Constants.USER_INVALID_ATTEMPTS, Constants.USER_ACTIVE, firstName.getText().toString(), lastName.getText().toString());
                            AccountDetailsDTO accountDetails = new AccountDetailsDTO(user, firstName.getText().toString(), lastName.getText().toString(), dob.getText().toString());

                            // Add user to database
                            userService.addUser(accountDetails, new ServerCallback<User>() {
                                @Override
                                public void onSuccess(User result) {
                                    Toast.makeText(RegisterActivity.this, R.string.newAccountCreated,
                                            Toast.LENGTH_SHORT).show();
                                    orbitPref.storePreference("loggedUser", result);
                                    setResult(0);
                                    finish();
                                }

                                @Override
                                public void onFail(ErrorResponse errorMessage) {
                                    Toast.makeText(RegisterActivity.this, "Failed to add user to DB.",
                                            Toast.LENGTH_SHORT).show();

                                    setResult(1);
                                    finish();

                                }
                            });
                        }
                    }
                });
    }

    private boolean validateForm()
    {
        return true;
    }

    public static class DatePickerFragment extends DialogFragment implements DatePickerDialog.OnDateSetListener{

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState){
            final Calendar calendar = Calendar.getInstance();
            int year = calendar.get(Calendar.YEAR);
            int month = calendar.get(Calendar.MONTH);
            int day = calendar.get(Calendar.DAY_OF_MONTH);

            /*
                We should use THEME_HOLO_LIGHT, THEME_HOLO_DARK or THEME_TRADITIONAL only.

                The THEME_DEVICE_DEFAULT_LIGHT and THEME_DEVICE_DEFAULT_DARK does not work
                perfectly. This two theme set disable color of disabled dates but users can
                select the disabled dates also.

                Other three themes act perfectly after defined enabled date range of date picker.
                Those theme completely hide the disable dates from date picker object.
             */
            DatePickerDialog dpd = new DatePickerDialog(getActivity(),
                    AlertDialog.THEME_HOLO_LIGHT,this,year,month,day);

            // Add 3 days to Calendar
            calendar.add(Calendar.DATE, 1);


            // Set the Calendar new date as maximum date of date picker
            dpd.getDatePicker().setMaxDate(calendar.getTimeInMillis());

            // Subtract 6 days from Calendar updated date
            calendar.add(Calendar.DATE, -10000);

            calendar.set(1945, 0, 1);//Year,Mounth -1,Day
            // Set the Calendar new date as minimum date of date picker
            dpd.getDatePicker().setMinDate(calendar.getTimeInMillis());

            // Return the DatePickerDialog
            return  dpd;
        }

        public void onDateSet(DatePicker view, int year, int month, int day){
            // Do something with the chosen date
            TextView date = (TextView) getActivity().findViewById(R.id.date);

            // Create a Date variable/object with user chosen date
            Calendar cal = Calendar.getInstance();
            cal.setTimeInMillis(0);
            cal.set(year, month, day, 0, 0, 0);
            Date chosenDate = cal.getTime();

            // Format the date using style and locale
            String formattedDate = new SimpleDateFormat(Constants.DATE_FORMAT).format(chosenDate);

            // Display the chosen date to app interface
            date.setText(formattedDate);
        }
    }
}
