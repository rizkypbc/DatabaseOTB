package bpp.arnet.project.databaseotb.Network.Interfaces;

import bpp.arnet.project.databaseotb.Model.BaseResponse;
import bpp.arnet.project.databaseotb.Network.Config.API;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface DeleteDataInterface {

    @FormUrlEncoded
    @POST(API.API_DELETE_DATA)
    Call<BaseResponse> hapusData(@Field ("id") String id);
}
