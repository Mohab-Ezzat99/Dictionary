package mrandroid.dictionary.data.mapper

import mrandroid.dictionary.data.local.entity.WordEntity
import mrandroid.dictionary.data.remote.dto.DefinitionsDto
import mrandroid.dictionary.data.remote.dto.MeaningsDto
import mrandroid.dictionary.data.remote.dto.PhoneticsDto
import mrandroid.dictionary.data.remote.dto.WordDto
import mrandroid.dictionary.domain.model.DefinitionsModel
import mrandroid.dictionary.domain.model.MeaningsModel
import mrandroid.dictionary.domain.model.PhoneticsModel
import mrandroid.dictionary.domain.model.WordModel

fun WordEntity.toWordModel(): WordModel {
    return WordModel(
        word = word,
        phonetic = phonetic,
        phonetics = phonetics,
        origin = origin,
        meanings = meanings
    )
}