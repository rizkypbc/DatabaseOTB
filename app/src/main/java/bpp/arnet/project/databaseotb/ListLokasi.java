package bpp.arnet.project.databaseotb;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

import bpp.arnet.project.databaseotb.Adapter.AdapterOTB;

import bpp.arnet.project.databaseotb.Model.Lokasi;
import bpp.arnet.project.databaseotb.Model.OTB;
import bpp.arnet.project.databaseotb.Util.Handler;

public class ListLokasi extends AppCompatActivity {

//    final static String API_LOKASI = "http://192.168.1.44/otb/dataLokasi.php";
    final static String API_LOKASI = "http://192.168.1.17/otb/dataLokasi.php";
    ListView listView;
    TextView textViewLokasi;
    List<OTB> otbList;
    List<Lokasi> lokasiList;
    JsonArrayRequest jsonArrayRequest;
    RequestQueue requestQueue;

//    private String JSON_URL = "http://192.168.1.44/otb/dataOtb.php?nama_lokasi=";
    private String JSON_URL = "http://192.168.1.17/otb/dataOtb.php?nama_lokasi=";

    private ProgressDialog m_ProgressDialog;
    private Spinner spnLokasi;
    private AdapterOTB adapterOTB;

    public static void start(Context context) {

        Intent intent = new Intent (context, ListLokasi.class);
        context.startActivity (intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_list_lokasi);
//        setTitle("DatabaseOTB");
        otbList = new ArrayList<> ();
        lokasiList = new ArrayList<> ();
        new GetLokasi ().execute ();

        spnLokasi = (Spinner) findViewById (R.id.spnLokasi);
        listView = (ListView) findViewById (R.id.listView);
        FloatingActionButton fab = findViewById (R.id.fab);

        fab.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick(View v) {

               Intent intent = new Intent (getApplicationContext (), TambahOTB.class);
               startActivity (intent);

            }
        });

        spnLokasi.setOnItemSelectedListener (new AdapterView.OnItemSelectedListener () {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

                String lokasi = spnLokasi.getSelectedItem ().toString ();
                StringRequest stringRequest = new StringRequest (Request.Method.GET, JSON_URL + URLEncoder.encode (lokasi),
                        new Response.Listener<String> () {
                            @Override
                            public void onResponse(String response) {


                                try {

                                    JSONObject jsonObject = new JSONObject (response);

                                    JSONArray jsonArray = jsonObject.getJSONArray ("otb");

                                    for (int i = 0; i < jsonArray.length (); i++) {

                                        JSONObject jsonObject1 = jsonArray.getJSONObject (i);
                                        OTB otb = new OTB (jsonObject1.getString ("nama"),
                                                jsonObject1.getString ("tipe"),
                                                jsonObject1.getString ("arah"),
                                                jsonObject1.getString ("rak"),
                                                jsonObject1.getString ("kapasitas"),
                                                jsonObject1.getString ("data_port"),
                                                jsonObject1.getString ("foto"),
                                                jsonObject1.getString ("nama_lokasi"));
                                        otbList.add (otb);
                                    }

                                    adapterOTB = new AdapterOTB (ListLokasi.this, otbList);
                                    listView.setAdapter (adapterOTB);

                                } catch (JSONException e) {

                                    e.printStackTrace ();
                                }
                                adapterOTB.notifyDataSetChanged ();

                            }
                        }, new Response.ErrorListener () {
                    @Override
                    public void onErrorResponse(VolleyError error) {

                        Toast.makeText (ListLokasi.this, error.getMessage (), Toast.LENGTH_SHORT).show ();
                    }
                });

                otbList.clear ();
                RequestQueue requestQueue = Volley.newRequestQueue (getApplicationContext ());
                requestQueue.add (stringRequest);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




//        recyclerViewLokasi = (RecyclerView) findViewById (R.id.recyclerViewOTB);
//        progressDialog = new ProgressDialog (ListLokasi.this);
//        otbList = new ArrayList<> ();
//        loadJSON ();


//        layoutManager = new LinearLayoutManager (ListLokasi.this, LinearLayoutManager.VERTICAL, false);
//        recyclerViewLokasi.setLayoutManager (layoutManager);
//        adapter = new AdapterOTB (ListLokasi.this, otbList);
//        recyclerViewLokasi.setAdapter (adapter);


    }

    private void populateSpinnerLokasi() {

        List<String> stringListLokasi = new ArrayList<> ();
        for (int i = 0; i < lokasiList.size (); i++) {

            stringListLokasi.add (lokasiList.get (i).getNama_lokasi ());
        }

        ArrayAdapter<String> adapterLokasi = new ArrayAdapter<String> (this, android.R.layout.simple_spinner_item, stringListLokasi);
        adapterLokasi.setDropDownViewResource (android.R.layout.simple_spinner_dropdown_item);
        spnLokasi.setAdapter (adapterLokasi);
    }

    private class GetLokasi extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute ();
            m_ProgressDialog = new ProgressDialog (ListLokasi.this);
            m_ProgressDialog.setMessage ("Fetching Data");
            m_ProgressDialog.show ();

        }

        @Override
        protected Void doInBackground(Void... voids) {

            Handler jsonParserLokasi = new Handler ();
            String jsonLokasi = jsonParserLokasi.makeServiceCall (API_LOKASI, Handler.GET);
            Log.e ("Response: ", "> " + jsonLokasi);

            if (jsonLokasi != null) {

                try {

                    JSONObject jsonObjectLokasi = new JSONObject (jsonLokasi);
                    if (jsonObjectLokasi != null) {

                        JSONArray jsonArrayLokasi = jsonObjectLokasi.getJSONArray ("result");
                        for (int i = 0; i < jsonArrayLokasi.length (); i++) {

                            JSONObject objectLokasi = (JSONObject) jsonArrayLokasi.get (i);
                            Lokasi lokasi = new Lokasi (
                                    objectLokasi.getString ("nama_lokasi"));
                            lokasiList.add (lokasi);
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace ();
                }
            } else {

                Log.e ("JSON Data", "Didn't receive any data from server!");
            }
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute (aVoid);
            if (m_ProgressDialog.isShowing ())
                m_ProgressDialog.dismiss ();
            populateSpinnerLokasi ();

        }
    }


//    @Override
//    protected void onStart() {
//        super.onStart ();
//        new DownloaderLokasi (ListLokasi.this, API_LOKASI, spnLokasi).execute ();
//
//    }

}
