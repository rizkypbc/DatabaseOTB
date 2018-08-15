package bpp.arnet.project.databaseotb;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.media.Image;
import android.net.Uri;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;
import java.io.Serializable;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import bpp.arnet.project.databaseotb.Model.OTB;

public class DetailOTB extends AppCompatActivity {

    private TextView namaOTB, lokasiOTB, tipeOTB, arahOTB,
    rakOTB, kapasitasOTB, dataPortOTB, idOTB, pathPhoto;
    private ImageView photoOTB;

    SharedPreferences sharedPreferences;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail_otb);

        namaOTB = (TextView)findViewById (R.id.textViewDataNamaOTB);
        lokasiOTB = (TextView)findViewById (R.id.textViewDataLokasi);
        tipeOTB = (TextView)findViewById (R.id.textViewDataTipe);
        arahOTB = (TextView)findViewById (R.id.textViewDataArah);
        rakOTB = (TextView)findViewById (R.id.textViewDataRak);
        kapasitasOTB = (TextView)findViewById (R.id.textViewDataKapasitas);
        dataPortOTB = (TextView)findViewById (R.id.textViewDataPort);
        idOTB = (TextView)findViewById (R.id.textViewId);

        pathPhoto = (TextView)findViewById (R.id.DetailPathPhoto);
        dataPortOTB.setEnabled (false);

        photoOTB = (ImageView)findViewById (R.id.imagePhoto);


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
//        String urlPhoto = "http://192.168.1.44/otb/img/" + photo;

        String urlPhoto = "http://192.168.1.17/otb/img/" + photo;
        urlPhoto = urlPhoto.replaceAll (" ", "%20");
        Picasso.with (this)
                .load (urlPhoto)
                .placeholder (R.drawable.ic_launcher_background)
                .error (android.R.drawable.stat_notify_error)
                .into (photoOTB);




    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater ();
        inflater.inflate (R.menu.menu_crud, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId ()){

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

                String kapasitasValue =  kapasitasOTB.getText ().toString ();
                intent.putExtra ("kapasitas", kapasitasValue);

                String dataPortValue = dataPortOTB.getText ().toString ();
                intent.putExtra ("data_port", dataPortValue);

                String lokasiValue = lokasiOTB.getText ().toString ();
                intent.putExtra ("lokasi",lokasiValue);

                String pathPhotoValue = pathPhoto.getText ().toString ();
                intent.putExtra ("path", pathPhotoValue);

                String idValue = idOTB.getText ().toString ();
                intent.putExtra ("id", idValue);

                this.startActivity (intent);

                break;
        }

        return super.onOptionsItemSelected (item);
    }



}
