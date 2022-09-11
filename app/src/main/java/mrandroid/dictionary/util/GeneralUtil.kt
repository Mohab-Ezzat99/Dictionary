package mrandroid.dictionary.util

import android.util.Log
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import mrandroid.dictionary.R
import mrandroid.dictionary.util.Constants.TAG
import mrandroid.dictionary.util.state.ApiState
import mrandroid.dictionary.util.state.UiText
import retrofit2.HttpException
import retrofit2.Response
import java.io.IOException

fun <T> toResultFlow(call: suspend () -> Response<T>): Flow<ApiState<T>> = flow {
    emit(ApiState.Loading())
    try {
        val response = call()
        if (response.isSuccessful) emit(ApiState.Success(response.body()))
        else emit(ApiState.Error(UiText.DynamicString(response.message())))
    } catch (e: HttpException) {
        Log.d(TAG, e.message.toString())
        emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
    } catch (e: IOException) {
        Log.d(TAG, e.message.toString())
        emit(ApiState.Error(UiText.StringResource(R.string.check_your_internet_connection)))
    } catch (e: Exception) {
        Log.d(TAG, e.message.toString())
        emit(ApiState.Error(UiText.StringResource(R.string.something_went_wrong)))
    }
}