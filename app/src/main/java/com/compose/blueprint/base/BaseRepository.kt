package com.compose.blueprint.base

import com.compose.blueprint.data.network.NetworkResult
import org.json.JSONObject
import retrofit2.Response
import java.io.IOException
/**
 * Jai Khambhayta
 */
open class BaseRepository {
    suspend fun <T : Any> safeApiCall(
        call: suspend () -> Response<T>,
        errorContext: String
    ): NetworkResult<T>? {
        return safeApiResult(call)

    }

    private suspend fun <T : Any> safeApiResult(call: suspend () -> Response<T>): NetworkResult<T> {
        try {
            val response = call.invoke()
            if (response.isSuccessful) return NetworkResult.Success(response.body()!!)
            return NetworkResult.Error(IOException(setErrorMessage(response)))
        } catch (exception: IOException) {
            return NetworkResult.Error(exception)
        }

    }

    private fun <T : Any> setErrorMessage(response: Response<T>): String {
        val code = response.code().toString()
        val message = try {
            val jObjError = JSONObject(response.errorBody()?.string())
            jObjError.getJSONObject("error").getString("message")
        } catch (e: Exception) {
            e.message
        }
        return if (message.isNullOrEmpty()) " error code = $code " else "$message "
    }


}
