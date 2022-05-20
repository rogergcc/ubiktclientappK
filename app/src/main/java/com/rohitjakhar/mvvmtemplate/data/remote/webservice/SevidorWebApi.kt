package com.rohitjakhar.mvvmtemplate.data.remote.webservice

import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharacterDataDto
import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharactersDto
import retrofit2.Response
import retrofit2.http.GET

interface SevidorWebApi {
//    @GET("endpoint")
//    suspend fun getData(): Response<GetDataDto>
    @GET("endpoint")
//    suspend fun getData(): List<GetDataDto>
    suspend fun getData(): CharactersDto

}
