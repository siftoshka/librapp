package az.siftoshka.presentation.uikit.utils

import android.content.Context
import androidx.annotation.StringRes
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource

/**
 * Helper class where we combine usage of Strings with usage of Resource values
 * Use it when you need to need both of the worlds on Data layer side or VM.
 */
sealed class UiText {
    data class DynamicString(val value: String): UiText()
    class StringResource(
        @StringRes val resId: Int,
        vararg val args: Any
    ): UiText()

    /**
     * When you are in composable, use this to get a string value
     */
    @Composable
    fun asString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> stringResource(resId, *args)
        }
    }

    /**
     * When you have context, you need to use it to get a string value
     */
    fun asString(context: Context): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> context.getString(resId, *args)
        }
    }

    @Suppress("Use it only in case when you know the value you need comes from String only!")
    fun findString(): String {
        return when(this) {
            is DynamicString -> value
            is StringResource -> resId.toString()
        }
    }
}