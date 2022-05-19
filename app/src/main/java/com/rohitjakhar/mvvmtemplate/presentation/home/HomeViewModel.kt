package com.rohitjakhar.mvvmtemplate.presentation.home

import android.widget.Toast
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.mvvmtemplate.data.local.model.DataModel
import com.rohitjakhar.mvvmtemplate.domain.repository.DataRepo
import com.rohitjakhar.mvvmtemplate.domain.usecases.GetPopularClientesUseCase
import com.rohitjakhar.mvvmtemplate.util.ErrorType
import com.rohitjakhar.mvvmtemplate.util.Resource
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val dataRepo: DataRepo
    private val getPopularClientesUseCase: GetPopularClientesUseCase
) : ViewModel() {
//    fun getData() {
//        viewModelScope.launch(Dispatchers.IO) {
//            dataRepo.getData()
//        }
//    }

    private val _state = MutableStateFlow(UiState())
    val state: StateFlow<UiState> = _state.asStateFlow()


//    fun fetchMainScreenMovies() = liveData(Dispatchers.IO) {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success((repo.invoke())))
//        } catch (e: Exception) {
//            emit(Resource.Failure(e))
//        }
//    }


    init {
        viewModelScope.launch {
            getPopularClientesUseCase()
                .catch { cause -> _state.update { it.copy(error = cause.localizedMessage) } }
                .collect { result -> _state.update { UiState(data = result.data) } }
        }

//        getPopularClientesUseCase().onEach { result ->
//            when (result) {
//                is Resource.Success -> {
//                    _state.value = UiState(data = result.data ?: emptyList())
//                }
//                is Resource.Error -> {
////                    _state.value = UiState(
////                        error = (result.errorType.errorMessage)
////                    )
//                    _state.update { UiState(error = result.errorType.errorMessage) }
//                }
//                is Resource.Loading -> {
//                    _state.value = UiState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)

    }

    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val error = getPopularClientesUseCase().collect{
                it.errorType.errorMessage
            }

            _state.value = _state.value.copy(isLoading = false, error = error.toString())
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val data: List<DataModel>? = null,

//        val error: Error? = null
//        val coins: List<Coin> = emptyList(),
        val error: String = ""
    )




//    private fun loadData(refresh: Boolean = false) {
//        isRequesting.set(true)
//        mRepository.getListHeroes(refresh, object : DataSource.GetHeroesCallback {
//            override fun onSuccess(data: List<HeroesModel>) {
//                homeList.clear()
//                homeList.addAll(data.map { HomeModel(it.name ?: "-", it.bio ?: "-", it.imageurl ?: "-") })
//            }
//
//            override fun onError(errorMessage: String) {
//                showMessage.value = errorMessage
//                isRequesting.set(false)
//
//            }
//
//            override fun onFinish() {
//                isRequesting.set(false)
//            }
//        })
//
//    }

//    fun fetchHomeNearPlaces(latitude: String, longitude: String) = liveData(Dispatchers.IO) {
//        emit(Resource.Loading())
//        try {
//            emit(Resource.Success((repo.invoke(latitude,longitude))))
//        } catch (e: Exception) {
//            emit(Resource.Failure(e))
//        }
//    }



//    fun fetchRestaurants() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
//        emit(Resource.Loading())
//        try {
//            emit(repo.getRestaurants())
//        }catch (e: Exception){
//            emit(Resource.Failure(e))
//        }
//    }
//
//    fun getDisplayRestaurants(restaurants: List<Restaurant>): List<RestaurantDisplayItem> {
//        return restaurants.map { restaurant ->
//
//            return@map RestaurantDisplayItem(
//                id = restaurant.id,
//                displayName = "Restaurant ${restaurant.name}",
//
//                displayDistance = "at ${ restaurant.distance.roundToOneDecimalPlace() } KM distance",
//                imageUrl = restaurant.imageUrl,
//                type = when (restaurant.type) {
//                    "EAT_IN" -> RestaurantType.EAT_IN
//                    "TAKE_AWAY" -> RestaurantType.TAKE_AWAY
//                    else -> RestaurantType.DRIVE_THROUGH
//                },
//                latitude = restaurant.location.latitude,
//                longitude = restaurant.location.longitude
//            )
//        }
//    }


//    viewModel.fetchRestaurants().observe(viewLifecycleOwner) { result ->
//        when (result) {
//            is Resource.Loading -> {
//                showShimmerEffect()
//                TimberAppLogger.i("Resource Loading")
//            }
//            is Resource.Success -> {
//                hideShimmerEffect()
//                TimberAppLogger.i("Resource Success ${result.data} ")
//                if (result.data.isNullOrEmpty()) {
////                        binding.noData.show()
////                        binding.noDataLayout.noData.show()
//                    binding.emptyState.root.show()
//                } else {
//                    binding.emptyState.root.hide()
//                    // Parsing, filtering, displaying
//                    val restaurantParser = RestaurantParser()
//                    val restaurantRules = RestaurantRules()
//                    val parsedRestaurants = restaurantParser.parseRestaurants(result.data)
//                    val filteredRestaurants =
//                        restaurantRules.filterRestaurants(
//                            location?.latitude,
//                            location?.longitude,
//                            parsedRestaurants)
//                    //                        mAdapter.setData(result.data)
//                    displayRestaurants(filteredRestaurants)
//                }
//            }
//
//            is Resource.Failure -> {
//                hideShimmerEffect()
//                binding.emptyState.root.hide()
//                Toast.makeText(
//                    requireContext(),
//                    "Hubo un error al obtener registros",
//                    Toast.LENGTH_SHORT
//                ).show()
//                TimberAppLogger.e("Resource.Failure HomeFragmet ${result.exception} ")
//            }
//        }
//    }
//
//    val restaurantsDisplay = viewModel.getDisplayRestaurants(restaurants)
//    mAdapter.setData(restaurantsDisplay)
}
