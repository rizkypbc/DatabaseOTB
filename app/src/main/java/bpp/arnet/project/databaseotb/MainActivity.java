package bpp.arnet.project.databaseotb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private EditText editTextEmail, editTextPassword, editTextConfirmPassword;
    private Button buttonSignup;
    private TextView textViewSignin;
    private ProgressDialog progressDialog;

    private FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);

        firebaseAuth = FirebaseAuth.getInstance ();

        //if getCurrentUser does not returns null
        if (firebaseAuth.getCurrentUser () != null) {

            //that means user is already logged in
            //so close this activity
            finish ();

            //and open home activity / profile
            startActivity (new Intent (getApplicationContext (), HomeActivity.class));
        }

        //initializing views

        textViewSignin = (TextView) findViewById (R.id.textViewSignin);
        String login = "Already Registered? <font color='#b53f41'><b>Login here<b></font>";
        SpannableStringBuilder spannableStringBuilder = new SpannableStringBuilder (Html.fromHtml (login));
        textViewSignin.setText (spannableStringBuilder);
        editTextEmail = (EditText) findViewById (R.id.editTextEmail);
        editTextPassword = (EditText) findViewById (R.id.editTextPassword);
        editTextConfirmPassword = (EditText)findViewById (R.id.editTextConfirmPassword);

        buttonSignup = (Button) findViewById (R.id.buttonSignup);
        progressDialog = new ProgressDialog (this);

        buttonSignup.setOnClickListener (this);
        textViewSignin.setOnClickListener (this);
    }

    private void registerUser() {

        //getting email and password from edit texts
        String email = editTextEmail.getText ().toString ().trim ();
        String password = editTextPassword.getText ().toString ().trim ();
        String confirm = editTextConfirmPassword.getText ().toString ().trim ();

        //checking if email and passwords are empty
        if (TextUtils.isEmpty (email) || !isEmailValid (email)) {
            editTextEmail.setError ("Data tidak sesuai dengan format Email");
            editTextEmail.requestFocus ();
            return;
        }

        if (TextUtils.isEmpty (password) || password.length () < 6) {
            editTextPassword.setError ("Password Minimal 6 karakter");
            editTextPassword.requestFocus ();
            return;
        }

        if (TextUtils.isEmpty (confirm)) {
            editTextConfirmPassword.setError ("Confirm Tidak Boleh Kosong");
            editTextConfirmPassword.requestFocus ();
            return;
        }

        if (!password.equals (confirm)){

            editTextConfirmPassword.setError ("Password do not match");
            editTextConfirmPassword.requestFocus ();
        } else {

            progressDialog.setMessage ("Registering Please Wait...");
            progressDialog.show ();

            //creating a new user
            firebaseAuth.createUserWithEmailAndPassword (email, password)
                    .addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            //checking if success
                            if (task.isSuccessful ()) {
                                //display some message here
                                Toast.makeText (MainActivity.this, "Successfully registered", Toast.LENGTH_LONG).show ();
                                editTextEmail.setText (null);
                                editTextPassword.setText (null);
                                editTextConfirmPassword.setText (null);
                            } else {
                                //display some message here
                                Toast.makeText (MainActivity.this, "Registration Error", Toast.LENGTH_LONG).show ();
                            }
                            progressDialog.dismiss ();

                        }
                    });

        }


        //if the email and password are not empty
        //displaying a progress dialog



    }

    @Override
    public void onClick(View v) {

        if (v == buttonSignup) {
            registerUser ();
        }

        if (v == textViewSignin) {

            //open login activity when user taps on the already registered textview
            finish ();
            startActivity (new Intent (this, LoginActivity.class));

        }
    }

    boolean isEmailValid(CharSequence email) {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches();
    }
}
