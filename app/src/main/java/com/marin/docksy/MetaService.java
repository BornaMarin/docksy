package com.marin.docksy;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.PATCH;

public interface MetaService {

    @GET("metas/get")
    Call<MetaResponse> getMeta();

    @PATCH("metas/update")
    Call<MetaResponse> updateMeta(@Body MetaResponse meta);

}
