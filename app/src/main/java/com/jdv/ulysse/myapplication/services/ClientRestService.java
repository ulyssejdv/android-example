package com.jdv.ulysse.myapplication.services;

import com.jdv.ulysse.myapplication.models.Client;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by ulysse on 25/11/2017.
 */

public interface ClientRestService {

    @GET("/rest/client")
    Call<List<Client>> getClients();

    @POST("rest/client")
    Call<Void> addClient(@Body Client client);
}
