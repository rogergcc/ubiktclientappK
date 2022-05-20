package com.rohitjakhar.mvvmtemplate.domain.repository

import com.rohitjakhar.mvvmtemplate.data.local.model.DataEntity
import com.rohitjakhar.mvvmtemplate.util.ApiResource

interface DataRepo {
    suspend fun getData(): ApiResource<DataEntity>

}
