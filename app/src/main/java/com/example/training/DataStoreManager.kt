import android.content.Context
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.dataStore by preferencesDataStore(name = "high_score")

class ScoreRepository(private val context: Context) {

    private val HIGH_SCORE_KEY = intPreferencesKey("high_score")

    // for take the high score
    val highScoreFlow: Flow<Int> = context.dataStore.data
        .map { preferences ->
            preferences[HIGH_SCORE_KEY] ?: 0
        }

    // To save the high score
    suspend fun saveHighScore(score: Int) {
        context.dataStore.edit { preferences ->
            preferences[HIGH_SCORE_KEY] = score
        }
    }

    suspend fun resetHighScore() {
        context.dataStore.edit { preferences ->
            preferences[HIGH_SCORE_KEY] = 0 // Skoru 0'a sıfırlıyoruz
        }
    }
}
