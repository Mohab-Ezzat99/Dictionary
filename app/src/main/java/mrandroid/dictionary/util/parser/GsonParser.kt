package mrandroid.dictionary.util.parser

import com.google.gson.Gson
import eramo.bellout.util.parser.JsonParser
import java.lang.reflect.Type

class GsonParser(private val gson: Gson) : JsonParser {
    override fun <T> toJson(obj: T, type: Type): String? {
        return gson.toJson(obj, type)
    }

    override fun <T> fromJson(json: String, type: Type): T? {
        return gson.fromJson(json, type)
    }
}