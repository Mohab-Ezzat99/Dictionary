package mrandroid.dictionary.data.local

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import mrandroid.dictionary.data.local.entity.WordEntity

@Dao
interface DictionaryDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertWordList(wordList: List<WordEntity>)

    @Query("DELETE FROM WordEntity WHERE word IN(:words)")
    suspend fun deleteWordList(words: List<String>)

    @Query("SELECT * FROM WordEntity WHERE word LIKE '%' || :word || '%'")
    suspend fun getWordList(word: String): List<WordEntity>

}