package com.rohitjakhar.mvvmtemplate.domain.repository

import com.rohitjakhar.mvvmtemplate.data.local.model.DataModel
import com.rohitjakhar.mvvmtemplate.data.remote.dto.GetDataDto
import com.rohitjakhar.mvvmtemplate.util.Resource

interface ClientesRepository {
//    suspend fun getData(): Resource<DataModel>
    suspend fun getData(): List<GetDataDto>
}
