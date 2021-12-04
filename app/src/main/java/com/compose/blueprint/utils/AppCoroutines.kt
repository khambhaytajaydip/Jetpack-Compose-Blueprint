package com.compose.blueprint.utils

import android.util.Log
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
/**
 * Jai Khambhayta
 */
object AppCoroutines {
    fun main(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.Main).launch {
        try {
            work()
        } catch (E: Exception) {
            Log.e("jai", "exception in io :::" + E.message)
            E.printStackTrace()
        }
    }

    fun io(work: suspend (() -> Unit)) = CoroutineScope(Dispatchers.IO).launch {
        try {
            work()
        } catch (E: Exception) {
            Log.e("jai", "exception in io :::" + E.message)
            E.printStackTrace()
        }
    }
}