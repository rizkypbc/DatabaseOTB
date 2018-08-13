package bpp.arnet.project.databaseotb.Network;

import android.content.Context;

import bpp.arnet.project.databaseotb.Network.Config.RetrofitBuilder;
import bpp.arnet.project.databaseotb.Network.Interfaces.TambahDataInterface;
import retrofit2.Callback;

public class TambahDataService {

    private TambahDataInterface tambahDataInterface;

    public TambahDataService(Context context){

        tambahDataInterface = RetrofitBuilder.builder (context)
                .create (TambahDataInterface.class);
    }

    public void doTambahData(String nama, String tipe, String arah, String rak, String kapasitas, String data_port, String nama_lokasi, Callback callback){

        tambahDataInterface.getTambahData (nama, tipe, arah, rak, kapasitas, data_port, nama_lokasi).enqueue (callback);
    }
}
