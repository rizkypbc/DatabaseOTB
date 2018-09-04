package bpp.arnet.project.databaseotb;

import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.util.Date;

import bpp.arnet.project.databaseotb.Model.BaseResponse;
import bpp.arnet.project.databaseotb.Model.Value;
import bpp.arnet.project.databaseotb.Network.Config.API;
import bpp.arnet.project.databaseotb.Network.DeleteDataService;
import bpp.arnet.project.databaseotb.Network.Interfaces.DeleteDataInterface;
import bpp.arnet.project.databaseotb.Network.Interfaces.RegisterAPI;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DetailOTB extends AppCompatActivity {

    private TextView namaOTB, lokasiOTB, tipeOTB, arahOTB,
            rakOTB, kapasitasOTB, dataPortOTB, idOTB, pathPhoto;
    private ImageView photoOTB;
    private Button buttonSavePhoto, btnDetailDataPort;

    public static final String URL = "http://aksesblk-samarinda.com/otb/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail_otb);

        namaOTB = (TextView) findViewById (R.id.textViewDataNamaOTB);
        lokasiOTB = (TextView) findViewById (R.id.textViewDataLokasi);
        tipeOTB = (TextView) findViewById (R.id.textViewDataTipe);
        arahOTB = (TextView) findViewById (R.id.textViewDataArah);
        rakOTB = (TextView) findViewById (R.id.textViewDataRak);
        kapasitasOTB = (TextView) findViewById (R.id.textViewDataKapasitas);
        dataPortOTB = (TextView) findViewById (R.id.textViewDataPort);
        idOTB = (TextView) findViewById (R.id.textViewId);

        pathPhoto = (TextView) findViewById (R.id.DetailPathPhoto);
        dataPortOTB.setEnabled (false);

        photoOTB = (ImageView) findViewById (R.id.imagePhoto);

        buttonSavePhoto = (Button) findViewById (R.id.buttonSavePhoto);
        btnDetailDataPort = (Button) findViewById (R.id.btnDetailDataPort);

        //Receive Data
        Intent intent = this.getIntent ();
        String id = intent.getExtras ().getString ("ID_KEY");
        String nama = intent.getExtras ().getString ("NAMA_KEY");
        String tipe = intent.getExtras ().getString ("TIPE_KEY");
        String arah = intent.getExtras ().getString ("ARAH_KEY");
        String rak = intent.getExtras ().getString ("RAK_KEY");
        String kapasitas = intent.getExtras ().getString ("KAPASITAS_KEY");
        String data_port = intent.getExtras ().getString ("DATA_PORT_KEY");
        String photo = intent.getExtras ().getString ("FOTO_KEY");
        String nama_lokasi = intent.getExtras ().getString ("LOKASI_KEY");

        //Bind Data
        idOTB.setText (id);
        namaOTB.setText (nama);
        lokasiOTB.setText (nama_lokasi);
        tipeOTB.setText (tipe);
        arahOTB.setText (arah);
        rakOTB.setText (rak);
        kapasitasOTB.setText (kapasitas);
        dataPortOTB.setText (data_port);
        pathPhoto.setText (photo);

        String urlPhoto = "http://aksesblk-samarinda.com/otb/img/" + photo;
        urlPhoto = urlPhoto.replaceAll (" ", "%20");
        Picasso.with (this)
                .load (urlPhoto)
                .placeholder (R.drawable.ic_launcher_background)
                .error (R.drawable.icon_image_no_available)
                .into (photoOTB);

        buttonSavePhoto.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                simpanPhoto ();
            }
        });



        btnDetailDataPort.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

                Intent intentNamaOTB = new Intent (DetailOTB.this, DetailDataPort.class);
                String getNamaOTB = namaOTB.getText ().toString ();
                intentNamaOTB.putExtra ("keyName", getNamaOTB);
                startActivity (intentNamaOTB);

            }
        });
    }



    public void simpanPhoto() {

        Intent intent = this.getIntent ();
        final String savePhoto = intent.getExtras ().getString ("FOTO_KEY");

        String saveUrlPhoto = "http://aksesblk-samarinda.com/otb/img/" + savePhoto;
        saveUrlPhoto = saveUrlPhoto.replaceAll (" ", "%20");

        Picasso.with (getApplicationContext ()).load (saveUrlPhoto).into (new Target () {
            @Override
            public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


                try {

                    String root = Environment.getExternalStorageDirectory ().toString ();
                    File myDir = new File (root + "/Download");
                    if (!myDir.exists ()) {

                        myDir.mkdirs ();
                    }

                    String name = savePhoto.toString ();
                    myDir = new File (myDir, name);
                    FileOutputStream outputStream = new FileOutputStream (myDir);
                    bitmap.compress (Bitmap.CompressFormat.JPEG, 90, outputStream);
                    outputStream.flush ();
                    outputStream.close ();
                    Toast.makeText (DetailOTB.this, "Berhasil Simpan Photo", Toast.LENGTH_LONG).show ();

                } catch (Exception e) {

                    e.printStackTrace ();
                }


            }

            @Override
            public void onBitmapFailed(Drawable errorDrawable) {

                Toast.makeText (DetailOTB.this, "Gagal Mendownload Photo, Periksa Koneksi Anda", Toast.LENGTH_SHORT).show ();
            }

            @Override
            public void onPrepareLoad(Drawable placeHolderDrawable) {

            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_crud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId ()) {

            case R.id.action_update:

                Intent intent = new Intent (this, UpdateOTB.class);
                String namaValue = namaOTB.getText ().toString ();
                intent.putExtra ("nama", namaValue);


                String tipeValue = tipeOTB.getText ().toString ();
                intent.putExtra ("tipe", tipeValue);

                String arahValue = arahOTB.getText ().toString ();
                intent.putExtra ("arah", arahValue);

                String rakValue = rakOTB.getText ().toString ();
                intent.putExtra ("rak", rakValue);

                String kapasitasValue = kapasitasOTB.getText ().toString ();
                intent.putExtra ("kapasitas", kapasitasValue);

                String dataPortValue = dataPortOTB.getText ().toString ();
                intent.putExtra ("data_port", dataPortValue);

                String lokasiValue = lokasiOTB.getText ().toString ();
                intent.putExtra ("lokasi", lokasiValue);

                String pathPhotoValue = pathPhoto.getText ().toString ();
                intent.putExtra ("path", pathPhotoValue);

                String idValue = idOTB.getText ().toString ();
                intent.putExtra ("id", idValue);

                this.startActivity (intent);

                break;

            case R.id.action_delete:

                AlertDialog.Builder alertDialog = new AlertDialog.Builder (this);
                alertDialog.setTitle ("Peringatan");
                alertDialog.setMessage ("Apakah Anda yakin ingin menghapus data ini ?").
                        setCancelable (false)
                        .setPositiveButton ("Hapus", new DialogInterface.OnClickListener () {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {

//                                deleteData ();
                                String id = idOTB.getText ().toString ();
                                Retrofit retrofit = new Retrofit.Builder ()
                                        .baseUrl (URL)
                                        .addConverterFactory (GsonConverterFactory.create ())
                                        .build ();

                                RegisterAPI api = retrofit.create (RegisterAPI.class);
                                Call<Value> call = api.hapus (id);
                                call.enqueue (new Callback<Value> () {
                                    @Override
                                    public void onResponse(Call<Value> call, Response<Value> response) {

                                        String value = response.body ().getValue ();
                                        String message = response.body ().getMessage ();
                                        if (value.equals ("1")) {

                                            Toast.makeText (DetailOTB.this, message, Toast.LENGTH_SHORT).show ();
                                            ListLokasi.start (DetailOTB.this);
                                            DetailOTB.this.finish ();
                                        } else {

                                            Toast.makeText (DetailOTB.this, message, Toast.LENGTH_SHORT).show ();
                                        }
                                    }

                                    @Override
                                    public void onFailure(Call<Value> call, Throwable t) {

                                        t.printStackTrace ();
                                        Toast.makeText (DetailOTB.this, "An Error Occured", Toast.LENGTH_SHORT).show ();
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
