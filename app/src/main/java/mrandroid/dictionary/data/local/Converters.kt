package mrandroid.dictionary.data.local

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.reflect.TypeToken
import eramo.bellout.util.parser.JsonParser
import mrandroid.dictionary.data.local.entity.WordEntity
import mrandroid.dictionary.domain.model.MeaningsModel
import mrandroid.dictionary.domain.model.PhoneticsModel

@ProvidedTypeConverter
class Converters(private val jsonParser: JsonParser) {

    @TypeConverter
    fun toMeaningsListJson(carDataList: List<MeaningsModel>): String {
        return jsonParser.toJson(
            carDataList,
            object : TypeToken<ArrayList<MeaningsModel>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromMeaningsListJson(json: String): List<MeaningsModel> {
        return jsonParser.fromJson<ArrayList<MeaningsModel>>(
            json,
            object : TypeToken<ArrayList<MeaningsModel>>() {}.type
        ) ?: emptyList()
    }

    @TypeConverter
    fun toPhoneticsListJson(carDataList: List<PhoneticsModel>): String {
        return jsonParser.toJson(
            carDataList,
            object : TypeToken<ArrayList<PhoneticsModel>>() {}.type
        ) ?: "[]"
    }

    @TypeConverter
    fun fromPhoneticsListJson(json: String): List<PhoneticsModel> {
        return jsonParser.fromJson<ArrayList<PhoneticsModel>>(
            json,
            object : TypeToken<ArrayList<PhoneticsModel>>() {}.type
        ) ?: emptyList()
    }

}