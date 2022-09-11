package mrandroid.dictionary.data.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import mrandroid.dictionary.domain.model.MeaningsModel
import mrandroid.dictionary.domain.model.PhoneticsModel

@Entity
data class WordEntity(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val word: String,
    val phonetic: String,
    val phonetics: List<PhoneticsModel>,
    val origin: String,
    val meanings: List<MeaningsModel>
)