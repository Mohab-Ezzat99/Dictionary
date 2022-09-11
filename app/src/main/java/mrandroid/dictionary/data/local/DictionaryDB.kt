package mrandroid.dictionary.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import mrandroid.dictionary.data.local.entity.WordEntity

@Database(
    entities = [WordEntity::class],
    version = 1,
    exportSchema = false
)
@TypeConverters(Converters::class)
abstract class DictionaryDB : RoomDatabase() {
    abstract val dao: DictionaryDao
}