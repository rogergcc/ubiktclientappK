package com.rohitjakhar.mvvmtemplate.presentation.home

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterDetails
import com.rohitjakhar.mvvmtemplate.domain.usecases.GetPopularClientesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
//    private val dataRepo: DataRepo
    private val getPopularClientesUseCase: GetPopularClientesUseCase,
) : ViewModel() {


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

    private fun generateMockData(): List<CharacterDetails> {
        return listOf(
            CharacterDetails(
                id = 1,
                profileImageUrl = "https://random-image-pepebigotes.vercel.app/api/random-image",
                characterName = "Character 1"
            ),
            CharacterDetails(
                id = 2,
                profileImageUrl = "https://random-image-pepebigotes.vercel.app/api/random-image",
                characterName = "Character 222222"
            ),
            CharacterDetails(
                id = 3,
                profileImageUrl = "https://random-image-pepebigotes.vercel.app/api/random-image",
                characterName = "Character 3"
            )
        )
    }

    init {

//        Log.d("TEST_LOGGER","HomeViewModel init: ")
//        _state.value = _state.value.copy(isLoading = true)
//        viewModelScope.launch(Dispatchers.IO) {
//            val mockData = generateMockData()
//            getPopularClientesUseCase()
//                .catch { cause -> _state.update { it.copy(error = cause.localizedMessage) } }
//                .collect { result ->
//                    _state.update {
////                        UiState(data = result.data?.data)
//                        UiState(data = mockData)
//                    }
//                }
//        }

//        getPopularClientesUseCase().onEach { result ->
//            when (result) {
//                is ApiResource.Success -> {
//                    _state.value = UiState(data = result.data.data ?: emptyList())
//                }
//                is ApiResource.Error -> {
//                    _state.value = UiState(
//                        error = (result.errorType.errorMessage)
//                    )
//                    _state.update { UiState(error = result.errorType.errorMessage) }
//                }
//                is ApiResource.Loading -> {
//                    _state.value = UiState(isLoading = true)
//                }
//            }
//        }.launchIn(viewModelScope)

    }

    fun fetchData() {
        Log.d("TEST_LOGGER", "HomeViewModel fetchData: ")
//        _state.value = _state.value.copy(isLoading = true)

        viewModelScope.launch(Dispatchers.IO) {
            _state.update { it.copy(isLoading = true) }
            val mockData = generateMockData()
            delay(2000)

            getPopularClientesUseCase()
                .catch { cause ->
                    _state.update {
                        it.copy(isLoading = false, error = cause.message.toString())
                    }
//                    _state.value = _state.value.copy(isLoading = false, error = cause.message.toString() )
                }
                .collect { result ->
                    _state.update {
//                        UiState(data = result.data?.data)
                        UiState(isLoading = false, data = mockData)
                    }

                }

        }
    }

    //        viewModelScope.launch(Dispatchers.IO) {
//            val mockData = generateMockData()
//            getPopularClientesUseCase()
//                .catch { cause -> _state.update { it.copy(error = cause.localizedMessage) } }
//                .collect { result ->
//                    when (result) {
//                        is ApiResource.Success -> {
////                            _state.value = UiState(data = result.data?.data ?: emptyList())
//                            _state.value = UiState(data = mockData)
//                        }
//
//                        is ApiResource.Error -> {
//                            _state.value = UiState(
//                                error = (result.errorType.errorMessage)
//                            )
//                            _state.update { UiState(error = result.errorType.errorMessage) }
//                        }
//
//                        is ApiResource.Loading -> {
//                            _state.value = UiState(isLoading = true)
//                        }
//                    }
//                }
//        }


    fun onUiReady() {
        viewModelScope.launch {
            _state.value = _state.value.copy(isLoading = true)
            val error = getPopularClientesUseCase().collect {
                it.errorType.errorMessage
            }

            _state.value = _state.value.copy(isLoading = false, error = error.toString())
        }
    }

    data class UiState(
        val isLoading: Boolean = false,
        val data: List<CharacterDetails>? = null,

//        val error: Error? = null
//        val coins: List<Coin> = emptyList(),
        val error: String = "",
    )


//    fun fetchRestaurants() = liveData(viewModelScope.coroutineContext + Dispatchers.Main) {
//        emit(Resource.Loading())
//        try {
//            emit(repo.getRestaurants())
//        }catch (e: Exception){
//            emit(Resource.Failure(e))
//        }
//    }
//


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
