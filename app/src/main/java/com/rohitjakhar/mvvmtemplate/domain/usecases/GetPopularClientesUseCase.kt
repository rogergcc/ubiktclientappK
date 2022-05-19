package com.rohitjakhar.mvvmtemplate.domain.usecases

import com.rohitjakhar.mvvmtemplate.data.local.model.DataModel
import com.rohitjakhar.mvvmtemplate.data.remote.dto.toDataModel
import com.rohitjakhar.mvvmtemplate.domain.repository.ClientesRepository
import com.rohitjakhar.mvvmtemplate.util.ErrorType
import com.rohitjakhar.mvvmtemplate.util.Resource
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

import retrofit2.HttpException
import java.io.IOException
class GetPopularClientesUseCase @Inject constructor(private val repository: ClientesRepository) {

//    suspend operator fun invoke(): Resource<DataModel> {
//        return repository.getData()
//    }



    operator fun invoke(): Flow<Resource<List<DataModel>>> = flow {
        try {
            emit(Resource.Loading<List<DataModel>>())
//            val coins = repository.getData(). { it.toCoin() }
            val data = repository.getData().map { it.toDataModel() }
            emit(Resource.Success<List<DataModel>>(data))

        } catch(e: HttpException) {
            emit(Resource.Error(ErrorType.UNKNOWN,"An unexpected error occured"))
//            emit(Resource.Error(ErrorType.UNKNOWN?: "An unexpected error occured"))
        } catch(e: IOException) {
            emit(Resource.Error(ErrorType.NO_INTERNET))
        } catch (e: Exception) {
            emit (Resource.Error(message = e.localizedMessage))
        }
    }

}