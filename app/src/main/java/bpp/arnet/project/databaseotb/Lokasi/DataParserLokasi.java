package bpp.arnet.project.databaseotb.Lokasi;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bpp.arnet.project.databaseotb.Model.Lokasi;

public class DataParserLokasi extends AsyncTask<Void, Void, Integer> {

    Context contextLokasi;
    Spinner spinnerLokasi;
    String jsonDataLokasi;
    ProgressDialog progressDialogLokasi;
    ArrayList<String> arrayList = new ArrayList<> ();

    public DataParserLokasi(Context contextLokasi, Spinner spinnerLokasi, String jsonDataLokasi) {
        this.contextLokasi = contextLokasi;
        this.spinnerLokasi = spinnerLokasi;
        this.jsonDataLokasi = jsonDataLokasi;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute ();

        progressDialogLokasi = new ProgressDialog (contextLokasi);
        progressDialogLokasi.setTitle ("Parse");
        progressDialogLokasi.setMessage ("Parsing.... Mohon Tunggu Sebentar");
        progressDialogLokasi.show ();
    }

    @Override
    protected Integer doInBackground(Void... voids) {
        return this.parseDataLokasi();
    }

    @Override
    protected void onPostExecute(Integer integer) {
        super.onPostExecute (integer);

        progressDialogLokasi.dismiss ();

        if (integer == 0){

            Toast.makeText (contextLokasi, "Unable to Parse", Toast.LENGTH_SHORT).show ();
        } else {

            ArrayAdapter adapter = new ArrayAdapter (contextLokasi, android.R.layout.simple_list_item_1, arrayList);
            spinnerLokasi.setAdapter (adapter);
        }
    }

    private int parseDataLokasi(){

        try {

            JSONArray jsonArray = new JSONArray (jsonDataLokasi);
            JSONObject jsonObject = null;

            arrayList.clear ();
            Lokasi lokasi = null;

            for (int i = 0; i < jsonArray.length (); i++){

                jsonObject = jsonArray.getJSONObject (i);
                final String nama_lokasi = jsonObject.getString ("nama_lokasi");

                lokasi = new Lokasi ();
                lokasi.setNama_lokasi (nama_lokasi);

                arrayList.add (nama_lokasi);
            }

            return 1;
        } catch (JSONException ex){

            ex.printStackTrace ();
        }

        return 0;

    }
}
