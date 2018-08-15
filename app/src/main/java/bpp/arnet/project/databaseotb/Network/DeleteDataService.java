package bpp.arnet.project.databaseotb.Network;

import android.content.Context;

import bpp.arnet.project.databaseotb.Network.Config.RetrofitBuilder;
import bpp.arnet.project.databaseotb.Network.Interfaces.DeleteDataInterface;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class DeleteDataService {

    private DeleteDataInterface deleteDataInterface;

    public DeleteDataService(Context context){

        deleteDataInterface = RetrofitBuilder.builder (context)
                .create (DeleteDataInterface.class);
    }

    public void doDeleteData(String id, Callback callback){

        deleteDataInterface.hapusData (id).enqueue (callback);
    }
}
