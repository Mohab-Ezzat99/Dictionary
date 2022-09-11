package mrandroid.dictionary.data.remote

import mrandroid.dictionary.data.remote.dto.WordDto
import retrofit2.Response
import retrofit2.http.*

interface DictionaryApi {

    companion object {
        const val BASE_URL = "https://api.dictionaryapi.dev/api/v2/entries/en/"
    }

    @GET("{word}")
    suspend fun searchWord(@Path("word") word: String): Response<List<WordDto>>

}