package bpp.arnet.project.databaseotb.Port;

import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import bpp.arnet.project.databaseotb.Adapter.AdapterDataPort;
import bpp.arnet.project.databaseotb.Model.Port;

public class DataParserPort extends AsyncTask<Void, Void, Boolean> {

    ArrayList<Port> portArrayList = new ArrayList<> ();

    Context context;
    String jsonData;
    RecyclerView recyclerView;

    public DataParserPort(Context context, String jsonData, RecyclerView recyclerView) {
        this.context = context;
        this.jsonData = jsonData;
        this.recyclerView = recyclerView;
    }

    @Override
    protected Boolean doInBackground(Void... voids) {
        return this.parseData ();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute (aBoolean);

        if (aBoolean) {
            recyclerView.setAdapter (new AdapterDataPort (context, portArrayList));
        } else {
            Toast.makeText (context, "Unable to Parse", Toast.LENGTH_SHORT).show ();
        }
    }

    private Boolean parseData() {

        try {

            JSONArray jsonArray = new JSONArray (jsonData);
            JSONObject jsonObject;

            portArrayList.clear ();
            Port port;

            for (int i = 0; i < jsonArray.length (); i++) {

                jsonObject = jsonArray.getJSONObject (i);

                String idPort = jsonObject.getString ("id_port");
                String core = jsonObject.getString ("core");
                String user = jsonObject.getString ("user");
                String direction = jsonObject.getString ("direction");
                String keterangan = jsonObject.getString ("keterangan");
                String nama_otb = jsonObject.getString ("nama");

                port = new Port ();

                port.setId_port (idPort);
                port.setCore (core);
                port.setUser (user);
                port.setDirection (direction);
                port.setKeterangan (keterangan);
                port.setNama (nama_otb);

                portArrayList.add (port);
            }

            return true;
        } catch (JSONException ex) {

            ex.printStackTrace ();
        }

        return false;
    }
}
