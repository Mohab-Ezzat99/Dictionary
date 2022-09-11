package mrandroid.dictionary.data.remote.dto

import com.google.gson.annotations.SerializedName

data class WordDto(
    @SerializedName("word") var word: String? = null,
    @SerializedName("phonetic") var phonetic: String? = null,
    @SerializedName("phonetics") var phonetics: List<PhoneticsDto>? = null,
    @SerializedName("origin") var origin: String? = null,
    @SerializedName("meanings") var meanings: List<MeaningsDto>? = null
)

data class PhoneticsDto(
    @SerializedName("text") var text: String? = null,
    @SerializedName("audio") var audio: String? = null
)

data class MeaningsDto(
    @SerializedName("partOfSpeech") var partOfSpeech: String? = null,
    @SerializedName("definitions") var definitions: List<DefinitionsDto>? = null
)

data class DefinitionsDto(
    @SerializedName("definition") var definition: String? = null,
    @SerializedName("example") var example: String? = null,
    @SerializedName("synonyms") var synonyms: List<String>? = null,
    @SerializedName("antonyms") var antonyms: List<String>? = null
)