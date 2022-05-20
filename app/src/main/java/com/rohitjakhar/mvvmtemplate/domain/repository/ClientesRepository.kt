package com.rohitjakhar.mvvmtemplate.domain.repository

import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharacterDataDto
import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharactersDto

interface ClientesRepository {
//    suspend fun getData(): Resource<DataModel>
    suspend fun getData(): CharactersDto
}
