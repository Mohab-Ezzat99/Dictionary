package mrandroid.dictionary.domain.model

data class WordModel(
    var word: String,
    var phonetic: String,
    var phonetics: List<PhoneticsModel>,
    var origin: String,
    var meanings: List<MeaningsModel>
)

data class PhoneticsModel(
    var text: String,
    var audio: String
)

data class MeaningsModel(
    var partOfSpeech: String,
    var definitions: List<DefinitionsModel>
)

data class DefinitionsModel(
    var definition: String,
    var example: String,
    var synonyms: List<String>,
    var antonyms: List<String>
)