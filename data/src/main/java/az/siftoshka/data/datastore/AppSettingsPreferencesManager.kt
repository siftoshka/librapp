package az.siftoshka.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.dataStore
import kotlinx.coroutines.flow.Flow

class AppSettingsPreferencesManager(context: Context) {
    private companion object {
        private const val DB_APP_SETTINGS = "siftoshka.librapp.db.app_settings"
    }

    private var dataStore: DataStore<AppSettingsPreferencesData>
    private val Context.dataStore by dataStore(
        fileName = DB_APP_SETTINGS,
        serializer = AppSettingsPreferencesSerializer
    )

    init {
        dataStore = context.dataStore
    }

    fun getAppSettings(): Flow<AppSettingsPreferencesData> {
        return dataStore.data
    }

    suspend fun deleteAll() {
        dataStore.updateData {
            AppSettingsPreferencesData()
        }
    }

    suspend fun changeLoginStatus(isLoggedIn: Boolean) {
        dataStore.updateData {
            it.copy(isLoggedIn = isLoggedIn)
        }
    }
}