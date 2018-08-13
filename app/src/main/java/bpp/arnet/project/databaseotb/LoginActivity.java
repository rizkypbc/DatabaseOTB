package bpp.arnet.project.databaseotb;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {

    //defining views
    private Button btnLogin;
    private EditText editTextEmailLogin, editTextPasswordLogin;
    private TextView textViewDaftar;

    //firebase auth project
    private FirebaseAuth firebaseAuth;

    //progress dialog
    private ProgressDialog progressDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_login);

        //getting firebase auth project
        firebaseAuth = FirebaseAuth.getInstance ();


        //if the objects getcurrentuser method is not null
        //means user is already logged in

        if (firebaseAuth.getCurrentUser () != null) {

            //close this activity
            finish ();
            //opening home activity
            startActivity (new Intent (this, HomeActivity.class));
        }

        //initializing views
        editTextEmailLogin = (EditText) findViewById (R.id.editTextEmailLogin);
        editTextPasswordLogin = (EditText) findViewById (R.id.editTextPasswordLogin);
        btnLogin = (Button) findViewById (R.id.buttonLogin);
        textViewDaftar = (TextView) findViewById (R.id.textViewDaftar);

        progressDialog = new ProgressDialog (this);

        //attaching click listener
        btnLogin.setOnClickListener (this);
        textViewDaftar.setOnClickListener (this);
    }

    //method for user login
    private void userLogin(){

        String emailLogin = editTextEmailLogin.getText ().toString ().trim ();
        String passwordLogin = editTextPasswordLogin.getText ().toString ().trim ();

        //checking if email and password are empty
        if (TextUtils.isEmpty (emailLogin)){
            Toast.makeText (this, "Please enter email", Toast.LENGTH_LONG).show ();
            return;
        }

        if (TextUtils.isEmpty (passwordLogin)){
            Toast.makeText (this, "Please enter password", Toast.LENGTH_LONG).show ();
            return;
        }

        //if the email and password are not empty
        //displaying a progress dialog

        progressDialog.setMessage ("Login Please Wait...");
        progressDialog.show ();

        //logging in the user
        firebaseAuth.signInWithEmailAndPassword (emailLogin, passwordLogin)
                .addOnCompleteListener (this, new OnCompleteListener<AuthResult> () {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {

                        progressDialog.dismiss ();
                        //if the task is successfull
                        if (task.isSuccessful ()){

                            //start the home activity
                            finish ();
                            startActivity (new Intent (getApplicationContext (), HomeActivity.class));
                        }
                    }
                });
    }

    @Override
    public void onClick(View v) {

        if (v == btnLogin){

            userLogin ();
        }

        if (v == textViewDaftar){

            finish ();
            startActivity (new Intent (this, MainActivity.class));
        }
    }
}
