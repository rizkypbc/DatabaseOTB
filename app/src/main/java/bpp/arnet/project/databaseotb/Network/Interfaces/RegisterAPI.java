package bpp.arnet.project.databaseotb.Network.Interfaces;

import bpp.arnet.project.databaseotb.Model.Value;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {

    @FormUrlEncoded
    @POST("hapusData.php")
    Call<Value> hapus(@Field("id") String id);

}
