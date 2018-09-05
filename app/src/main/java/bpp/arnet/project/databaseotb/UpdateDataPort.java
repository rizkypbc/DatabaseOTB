package bpp.arnet.project.databaseotb;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import bpp.arnet.project.databaseotb.Model.Value;
import bpp.arnet.project.databaseotb.Network.Interfaces.RegisterAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class UpdateDataPort extends AppCompatActivity {

    public static final String URL = "http://192.168.1.14/otb/crud/";
    private EditText editTextCore, editTextUser, editTextDirection,
            editTextKeterangan, editTextIdPort;
    private Button btnTambahUpdateDataPort;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_update_data_port);

        editTextCore = (EditText) findViewById (R.id.editTextCore);
        editTextUser = (EditText) findViewById (R.id.editTextUser);
        editTextDirection = (EditText) findViewById (R.id.editTextDirection);
        editTextKeterangan = (EditText) findViewById (R.id.editTextKeterangan);
        editTextIdPort = (EditText) findViewById (R.id.editTextIdPort);

        btnTambahUpdateDataPort = (Button) findViewById (R.id.btnUpdateDataPort);


        Intent intent = getIntent ();

        String id_port = intent.getStringExtra ("id_port");
        String core = intent.getStringExtra ("core");
        String user = intent.getStringExtra ("user");
        String direction = intent.getStringExtra ("direction");
        String keterangan = intent.getStringExtra ("keterangan");

        editTextCore.setText (core);
        editTextUser.setText (user);
        editTextDirection.setText (direction);
        editTextKeterangan.setText (keterangan);
        editTextIdPort.setText (id_port);

        btnTambahUpdateDataPort.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                String id_port = editTextIdPort.getText ().toString ();
                String core = editTextCore.getText ().toString ();
                String user = editTextUser.getText ().toString ();
                String direction = editTextDirection.getText ().toString ();
                String keterangan = editTextKeterangan.getText ().toString ();

                Retrofit retrofit = new Retrofit.Builder ()
                        .baseUrl (URL)
                        .addConverterFactory (GsonConverterFactory.create ())
                        .build ();
                RegisterAPI apiUpdate = retrofit.create (RegisterAPI.class);
                Call<Value> call = apiUpdate.updateDataPort (id_port, core, user, direction, keterangan);
                call.enqueue (new Callback<Value> () {
                    @Override
                    public void onResponse(Call<Value> call, Response<Value> response) {

                        String value = response.body().getValue();
                        String message = response.body().getMessage();

                        if (value.equals ("1")){

                            Toast.makeText (UpdateDataPort.this, message, Toast.LENGTH_LONG).show ();
                            UpdateDataPort.this.finish ();

                        } else {

                            Toast.makeText (UpdateDataPort.this, message, Toast.LENGTH_LONG).show ();

                        }
                    }

                    @Override
                    public void onFailure(Call<Value> call, Throwable t) {

                        t.printStackTrace ();
                        Toast.makeText (UpdateDataPort.this, "An Error Occured", Toast.LENGTH_SHORT).show ();
                    }
                });
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_update_data_port, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId ()) {

            case R.id.delete_data_port:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder (this);
                alertDialog.setTitle ("Peringatan");
                alertDialog.setMessage ("Apakah Anda yakin ingin menghapus data ini ?")
                        .setCancelable (false)
                        .setPositiveButton ("Hapus", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {



                                String id = editTextIdPort.getText ().toString ();
                                Retrofit retrofit = new Retrofit.Builder ()
                                        .baseUrl (URL)
                                        .addConverterFactory (GsonConverterFactory.create ())
                                        .build ();

                                RegisterAPI api = retrofit.create (RegisterAPI.class);
                                Call<Value> call = api.hapusDataPort (id);
                                call.enqueue (new Callback<Value> () {
                                    @Override
                                    public void onResponse(Call<Value> call, Response<Value> response) {

                                        String value = response.body ().getValue ();
                                        String message = response.body ().getMessage ();
                                        if (value.equals ("1")) {

                                            Toast.makeText (UpdateDataPort.this, message, Toast.LENGTH_LONG).show ();
                                            UpdateDataPort.this.finish ();

                                        } else {

                                            Toast.makeText (UpdateDataPort.this, message, Toast.LENGTH_LONG).show ();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Value> call, Throwable t) {

                                        t.printStackTrace ();
                                        Toast.makeText (UpdateDataPort.this, "An Error Occured", Toast.LENGTH_SHORT).show ();
//                                        HomeActivity.start (DetailOTB.this);
//                                        DetailOTB.this.finish ();
                                    }
                                });
                            }
                        }).setNegativeButton ("Batal", new DialogInterface.OnClickListener () {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        dialog.cancel ();
                    }
                });
                AlertDialog dialog = alertDialog.create ();
                dialog.show ();
                break;
        }
        return super.onOptionsItemSelected (item);
    }
}
