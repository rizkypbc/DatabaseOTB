package bpp.arnet.project.databaseotb.Network.Interfaces;

import bpp.arnet.project.databaseotb.Model.BaseResponse;
import bpp.arnet.project.databaseotb.Network.Config.API;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface TambahDataInterface {

    @FormUrlEncoded
    @POST(API.API_TAMBAH_DATA)
    Call<BaseResponse> getTambahData(
            @Field ("nama") String nama,
            @Field ("tipe") String tipe,
            @Field ("arah") String arah,
            @Field ("rak") String rak,
            @Field ("kapasitas") String kapasitas,
            @Field ("data_port") String data_port,
            @Field ("nama_lokasi") String nama_lokasi
    );
}
