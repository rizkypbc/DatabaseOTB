package bpp.arnet.project.databaseotb.Network.Interfaces;

import bpp.arnet.project.databaseotb.Model.Value;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface RegisterAPI {


    @FormUrlEncoded
    @POST("updateDataPort.php")
    Call<Value> updateDataPort(@Field ("id_port") String id_port,
                               @Field ("core") String core,
                               @Field ("user") String user,
                               @Field ("direction") String direction,
                               @Field ("keterangan") String keterangan);

    @FormUrlEncoded
    @POST("hapusDataPort.php")
    Call<Value> hapusDataPort(@Field("id_port") String id_port);


    @FormUrlEncoded
    @POST("hapusData.php")
    Call<Value> hapus(@Field("id") String id);

}
