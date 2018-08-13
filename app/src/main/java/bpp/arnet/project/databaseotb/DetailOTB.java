package bpp.arnet.project.databaseotb;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Patterns;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.regex.Pattern;

public class DetailOTB extends AppCompatActivity {

    private TextView namaOTB, lokasiOTB, tipeOTB, arahOTB,
    rakOTB, kapasitasOTB, dataPortOTB, dataPhoto;
    private ImageView photoOTB;



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
//        dataPhoto = (TextView)findViewById (R.id.textViewDataPhoto);

        dataPortOTB.setEnabled (false);

        photoOTB = (ImageView)findViewById (R.id.imagePhoto);

        //Receive Data
        Intent intent = this.getIntent ();
        String nama = intent.getExtras ().getString ("NAMA_KEY");
        String tipe = intent.getExtras ().getString ("TIPE_KEY");
        String arah = intent.getExtras ().getString ("ARAH_KEY");
        String rak = intent.getExtras ().getString ("RAK_KEY");
        String kapasitas = intent.getExtras ().getString ("KAPASITAS_KEY");
        String data_port = intent.getExtras ().getString ("DATA_PORT_KEY");
        String photo = intent.getExtras ().getString ("FOTO_KEY");
        String nama_lokasi = intent.getExtras ().getString ("LOKASI_KEY");

        //Bind Data
        namaOTB.setText (nama);
        lokasiOTB.setText (nama_lokasi);
        tipeOTB.setText (tipe);
        arahOTB.setText (arah);
        rakOTB.setText (rak);
        kapasitasOTB.setText (kapasitas);
        dataPortOTB.setText (data_port);
//        dataPhoto.setText (photo);
//        String urlPhoto = "http://192.168.1.44/otb/img/" + photo;

        String urlPhoto = "http://192.168.1.17/otb/img/" + photo;
        urlPhoto = urlPhoto.replaceAll (" ", "%20");
        Picasso.with (this)
                .load (urlPhoto)
                .placeholder (R.drawable.ic_launcher_background)
                .error (android.R.drawable.stat_notify_error)
                .into (photoOTB);

    }
}
