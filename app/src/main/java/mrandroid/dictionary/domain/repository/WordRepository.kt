package mrandroid.dictionary.domain.repository

import kotlinx.coroutines.flow.Flow
import mrandroid.dictionary.domain.model.WordModel
import mrandroid.dictionary.util.state.Resource

interface WordRepository {

    suspend fun getWordList(word: String): Flow<Resource<List<WordModel>>>

}