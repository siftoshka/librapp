package az.siftoshka.presentation.uikit.utils

import android.content.Context
import android.content.Intent
import androidx.core.net.toUri

internal fun Context.openUrl(url: String?) {
    val uri = url?.toUri() ?: return
    try {
        val intent = Intent(Intent.ACTION_VIEW, uri)
        startActivity(intent)
    } catch (_: Exception) {

    }
}