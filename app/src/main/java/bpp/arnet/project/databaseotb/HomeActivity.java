package bpp.arnet.project.databaseotb;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener {

    //firebase auth project
    private FirebaseAuth firebaseAuth;


    //view objects
    private TextView textViewUserEmail;
    private ImageView buttonLogout, buttonLiatData;

    public static void start(Context context) {

        Intent intent = new Intent (context, HomeActivity.class);
        context.startActivity (intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_home);

        //initializing firebase authentication object
        firebaseAuth = FirebaseAuth.getInstance ();

        //if the user is not logged in
        //that means currentuser will return null
        if(firebaseAuth.getCurrentUser() == null){

            //closing this activity
            finish ();
            //starting login activity
            startActivity (new Intent (this, LoginActivity.class));
        }

        //getting current user
        FirebaseUser user = firebaseAuth.getCurrentUser ();

        //initializing views
        textViewUserEmail = (TextView) findViewById (R.id.textViewUserEmail);
        buttonLogout = (ImageView) findViewById (R.id.buttonLogout);
        buttonLiatData = (ImageView) findViewById (R.id.buttonLiatData);
        buttonLiatData.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent lokasi = new Intent (getApplicationContext (), ListLokasi.class);
                startActivity (lokasi);
            }
        });

        //displaying logged in username
        textViewUserEmail.setText ("Welcome " +user.getEmail ());

        //adding listener to button
        buttonLogout.setOnClickListener (this);
    }

    @Override
    public void onClick(View v) {

        //if logout is pressed
        if (v == buttonLogout) {

            //logging out the user
            firebaseAuth.signOut ();
            //closing activity
            finish ();
            //starting login activity
            startActivity (new Intent (this, LoginActivity.class));
        }
    }
}
