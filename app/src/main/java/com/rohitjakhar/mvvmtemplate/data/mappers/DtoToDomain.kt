package com.rohitjakhar.mvvmtemplate.data.mappers

import com.rohitjakhar.mvvmtemplate.data.local.model.DataEntity
import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharacterDataDto
import com.rohitjakhar.mvvmtemplate.data.remote.dto.CharactersDto
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterDetails
import com.rohitjakhar.mvvmtemplate.domain.model.CharacterModel


/**
 * Created on mayo.
 * year 2022 .
 */

fun CharactersDto.toDomain() = CharacterModel(
 count = count,
 data = data.map { it.toDomain() }
)

fun CharacterDataDto.toDomain() = CharacterDetails(
 id = id,
 profileImageUrl = imageUrl,
 characterName = name,
)


//fun GetDataDto.toDomain(): DataEntity {
// return DataEntity(photo = this.photo)
//}
