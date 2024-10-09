package com.rohitjakhar.mvvmtemplate.presentation.home

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.rohitjakhar.mvvmtemplate.R
import com.rohitjakhar.mvvmtemplate.databinding.FragmentHomeBinding
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterDetails
import com.rohitjakhar.mvvmtemplate.presentation.common.launchAndCollect
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import okhttp3.internal.wait

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private val viewModel by viewModels<HomeViewModel>()

    private val mAdapterPlacesList by lazy {
        PlaceAdapter() { placeItem ->
            goToPlaceDetailsView(placeItem)
        }
    }

    private fun goToPlaceDetailsView(placeItem: CharacterDetails) {
//        TODO("Not yet implemented")
        Log.d(TAG, "place  $placeItem")
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.rvPlaces.apply {
            setHasFixedSize(true)
            layoutManager = LinearLayoutManager(context)
            adapter = mAdapterPlacesList
        }
        binding.btnGoDetails.setOnClickListener{

            findNavController().navigate(R.id.action_homeFragment_to_shopProductDetailFragment)
        }
//        mAdapterPlacesList.placeNearbyDetailsAction =

//        val state = viewModel.state.value


        viewLifecycleOwner.lifecycleScope.launch {
            viewModel.state.collect { state ->
                binding.progressBar.visibility = if (state.isLoading) View.VISIBLE else View.GONE
                binding.tvError.text = state.error
                mAdapterPlacesList.mItemsPlace = state.data ?: emptyList()
            }
        }


//        mainState.requestLocationPermission {
//            viewModel.onUiReady()
//        }

    }
    fun sampleof(){
        val a = 1
        val b = 2
        val c = a + b
        Log.d(TAG, "sampleof: $c")
    }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val TAG = "HomeFragment"
    }
}
