package com.rohitjakhar.mvvmtemplate.domain.usecases

import com.rohitjakhar.mvvmtemplate.data.mappers.toDomain
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterModel
import com.rohitjakhar.mvvmtemplate.domain.repository.ClientesRepository
import com.rohitjakhar.mvvmtemplate.util.ErrorType
import com.rohitjakhar.mvvmtemplate.util.ApiResource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

import retrofit2.HttpException
import java.io.IOException
class GetPopularClientesUseCase @Inject constructor(private val repository: ClientesRepository) {

//    suspend operator fun invoke(): Resource<DataModel> {
//        return repository.getData()
//    }



    operator fun invoke(): Flow<ApiResource<CharacterModel>> = flow {
        try {
            emit(ApiResource.Loading<CharacterModel>())
//            val coins = repository.getData(). { it.toCoin() }
            val data = repository.getData().toDomain()
            emit(ApiResource.Success<CharacterModel>(data))

        } catch(e: HttpException) {
            emit(ApiResource.Error(ErrorType.UNKNOWN,"An unexpected error occured"))
//            emit(Resource.Error(ErrorType.UNKNOWN?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(ApiResource.Error(ErrorType.NO_INTERNET))
        } catch (e: Exception) {
            emit (ApiResource.Error(message = e.localizedMessage))
        }
    }

}