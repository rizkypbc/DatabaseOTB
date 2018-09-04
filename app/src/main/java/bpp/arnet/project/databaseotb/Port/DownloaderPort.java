package bpp.arnet.project.databaseotb.Port;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;

import bpp.arnet.project.databaseotb.Connector;

public class DownloaderPort extends AsyncTask<Void, Void, String> {

    Context context;
    String urlAddress;
    RecyclerView recyclerView;

    public DownloaderPort(Context context, String urlAddress, RecyclerView recyclerView) {
        this.context = context;
        this.urlAddress = urlAddress;
        this.recyclerView = recyclerView;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute ();

    }

    @Override
    protected String doInBackground(Void... voids) {
        return this.downloadData();
    }

    @Override
    protected void onPostExecute(String jsonData) {
        super.onPostExecute (jsonData);

        if (jsonData == null){

            Toast.makeText (context, "Unsuccesfull, Unable to Retrieve", Toast.LENGTH_SHORT).show ();
        } else {

            new DataParserPort (context, jsonData, recyclerView).execute ();
        }
    }

    private String downloadData(){

        HttpURLConnection con = Connector.connect(urlAddress);
        if (con == null) {

            return null;
        }
        try {

            InputStream is = new BufferedInputStream (con.getInputStream());
            BufferedReader br = new BufferedReader(new InputStreamReader (is));

            String line;
            StringBuffer jsonData = new StringBuffer();

            while ((line = br.readLine()) != null) {

                jsonData.append(line + "\n");
            }

            br.close();
            is.close();

            return jsonData.toString();


        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

}
