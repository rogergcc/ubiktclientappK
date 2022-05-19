package com.rohitjakhar.mvvmtemplate.data.repository

import com.rohitjakhar.mvvmtemplate.data.local.model.DataModel
import com.rohitjakhar.mvvmtemplate.data.remote.dto.GetDataDto
import com.rohitjakhar.mvvmtemplate.data.remote.dto.toDataModel
import com.rohitjakhar.mvvmtemplate.data.remote.webservice.SevidorWebApi
import com.rohitjakhar.mvvmtemplate.domain.repository.ClientesRepository
import com.rohitjakhar.mvvmtemplate.domain.repository.DataRepo
import com.rohitjakhar.mvvmtemplate.util.ErrorType
import com.rohitjakhar.mvvmtemplate.util.Resource
import retrofit2.Response
import java.net.SocketTimeoutException
import javax.inject.Inject

class GetClientesRepoImpl @Inject constructor(
    private val sevidorWebApi: SevidorWebApi
) : ClientesRepository {


//    override suspend fun getData(): Response<GetDataDto> {
//        return sevidorWebApi.getData()
//    }


    override suspend fun getData(): List<GetDataDto> {
        return sevidorWebApi.getData()
    }
}
