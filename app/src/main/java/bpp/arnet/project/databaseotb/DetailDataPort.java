package bpp.arnet.project.databaseotb;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import bpp.arnet.project.databaseotb.Port.DownloaderPort;

public class DetailDataPort extends AppCompatActivity {

    public String urlAddress, DATA_URL_NAMA_PORT;
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_detail_data_port);

        recyclerView = (RecyclerView) findViewById (R.id.recyclerView);
        recyclerView.setLayoutManager (new LinearLayoutManager (this));
    }

    @Override
    protected void onStart() {
        super.onStart ();

        String namaOTB = getIntent ().getStringExtra ("keyName");


        try {
            urlAddress = "http://192.168.1.14/otb/dataPort.php?nama=" + URLEncoder.encode (namaOTB, "UTF-8");
        } catch (UnsupportedEncodingException ex) {

            ex.printStackTrace ();
        }
        new DownloaderPort (DetailDataPort.this, urlAddress, recyclerView).execute ();
    }
}
