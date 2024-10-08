package com.rohitjakhar.mvvmtemplate.domain.usecases

import com.rohitjakhar.mvvmtemplate.data.mappers.toDomain
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterModel
import com.rohitjakhar.mvvmtemplate.domain.repository.ClientesRepository
import com.rohitjakhar.mvvmtemplate.util.ApiResource
import com.rohitjakhar.mvvmtemplate.util.ErrorType
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class GetPopularClientesUseCase @Inject constructor(private val repository: ClientesRepository) {

//    suspend operator fun invoke(): Resource<DataModel> {
//        return repository.getData()
//    }

    //Dejar el funcionalidad aqui o pasarlo a Repo
    operator fun invoke(): Flow<ApiResource<CharacterModel>> = flow {
        try {
            emit(ApiResource.Loading())

            val data = repository.getData().toDomain()

//            if (data.count == 0)
//                emit(ApiResource.Error(ErrorType.EMPTY_DATA, "No Data"))
//            else
            emit(ApiResource.Success(data))

        } catch (e: HttpException) {
            emit(ApiResource.Error(ErrorType.UNKNOWN, "An unexpected error occured"))
//            emit(Resource.Error(ErrorType.UNKNOWN?: "An unexpected error occured"))
        } catch (e: IOException) {
            emit(ApiResource.Error(ErrorType.NO_INTERNET))
        } catch (e: Exception) {
            emit(ApiResource.Error(message = e.localizedMessage))
        }
    }

}