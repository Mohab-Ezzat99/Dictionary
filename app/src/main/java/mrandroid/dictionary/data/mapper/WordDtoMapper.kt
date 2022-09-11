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

fun WordDto.toWordEntity(): WordEntity {
    return WordEntity(
        word = word ?: "",
        phonetic = phonetic ?: "",
        phonetics = phonetics?.map { it.toPhoneticsModel() } ?: emptyList(),
        origin = origin ?: "",
        meanings = meanings?.map { it.toMeaningsModel() } ?: emptyList()
    )
}

fun PhoneticsDto.toPhoneticsModel(): PhoneticsModel {
    return PhoneticsModel(
        text = text ?: "",
        audio = audio ?: ""
    )
}

fun MeaningsDto.toMeaningsModel(): MeaningsModel {
    return MeaningsModel(
        partOfSpeech = partOfSpeech ?: "",
        definitions = definitions?.map { it.toDefinitionsModel() } ?: emptyList()
    )
}

fun DefinitionsDto.toDefinitionsModel(): DefinitionsModel {
    return DefinitionsModel(
        definition = definition ?: "",
        example = example ?: "",
        synonyms = synonyms ?: emptyList(),
        antonyms = antonyms ?: emptyList()
    )
}