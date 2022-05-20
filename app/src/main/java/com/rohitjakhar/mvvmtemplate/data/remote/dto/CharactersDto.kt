package com.rohitjakhar.mvvmtemplate.data.remote.dto

import com.google.gson.annotations.SerializedName
import com.rohitjakhar.mvvmtemplate.data.local.model.DataEntity

//data class GetDataDto(
//    @SerializedName("result")
//    val photo: String
//)

data class CharactersDto(
    val count: Int,
    val `data`: List<CharacterDataDto>,
    val nextPage: String,
    val totalPages: Int
)

data class CharacterDataDto(
    var id: Int,
    val allies: List<Any>,
    val enemies: List<Any>,
    val films: List<Any>,
    val imageUrl: String,
    val name: String,
    val parkAttractions: List<Any>,
    val shortFilms: List<Any>,
    val tvShows: List<Any>,
    val url: String,
    val videoGames: List<Any>
)

