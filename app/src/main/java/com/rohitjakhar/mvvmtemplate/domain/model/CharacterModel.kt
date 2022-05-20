package com.rohitjakhar.mvvmtemplate.domain.model


/**
 * Created on mayo.
 * year 2022 .
 */

data class CharacterModel(
    val count: Int?,
    val data:List<CharacterDetails>
)

data class CharacterDetails(
    val id: Int?,
    val profileImageUrl: String?,
    val characterName: String?,
)