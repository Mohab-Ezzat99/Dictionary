package mrandroid.dictionary.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mrandroid.dictionary.data.local.DictionaryDao
import mrandroid.dictionary.data.mapper.toWordEntity
import mrandroid.dictionary.data.mapper.toWordModel
import mrandroid.dictionary.data.remote.DictionaryApi
import mrandroid.dictionary.domain.model.WordModel
import mrandroid.dictionary.domain.repository.WordRepository
import mrandroid.dictionary.util.state.ApiState
import mrandroid.dictionary.util.state.Resource
import mrandroid.dictionary.util.toResultFlow

class WordRepositoryImpl(
    private val dictionaryApi: DictionaryApi,
    private val dictionaryDao: DictionaryDao,
) : WordRepository {

    override suspend fun getWordList(word: String): Flow<Resource<List<WordModel>>> {
        return flow {
            emit(Resource.Loading())
            val cachedList = dictionaryDao.getWordList(word).map { it.toWordModel() }
            emit(Resource.Loading(data = cachedList))

            val result = toResultFlow { dictionaryApi.searchWord(word) }
            result.collect { apiState ->
                when (apiState) {
                    is ApiState.Loading -> Unit
                    is ApiState.Error ->
                        emit(Resource.Error(apiState.message!!, data = cachedList))
                    is ApiState.Success -> {
                        apiState.data?.let { list ->
                            dictionaryDao.deleteWordList(list.map { it.word ?: "" })
                            dictionaryDao.insertWordList(list.map { it.toWordEntity() })
                            val newestList = dictionaryDao.getWordList(word).map { it.toWordModel() }
                            emit(Resource.Success(newestList))
                        }
                    }
                }
            }
        }
    }

}