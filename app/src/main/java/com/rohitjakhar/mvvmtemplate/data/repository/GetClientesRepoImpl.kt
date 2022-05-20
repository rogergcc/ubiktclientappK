package com.rohitjakhar.mvvmtemplate.data.repository

import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharacterDataDto
import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharactersDto
import com.rohitjakhar.mvvmtemplate.data.remote.webservice.SevidorWebApi
import com.rohitjakhar.mvvmtemplate.domain.repository.ClientesRepository
import javax.inject.Inject

class GetClientesRepoImpl @Inject constructor(
    private val sevidorWebApi: SevidorWebApi,
) : ClientesRepository {


//    override suspend fun getData(): Response<GetDataDto> {
//        return sevidorWebApi.getData()
//    }


    override suspend fun getData(): CharactersDto {
        return sevidorWebApi.getData()
    }
}
