package com.example.dogsimage.service

import retrofit2.Response
import retrofit2.http.GET

interface RandonImageService {

    @GET("breeds/image/random")
    suspend fun randomDogImage(): Response<RandomDogImageDto>

    @GET("breeds/list/all")
    suspend fun getDogBreedsList(): Response<DogsListDto>

}