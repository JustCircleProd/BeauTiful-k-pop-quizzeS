package ru.justcircleprod.onlybtsfuns.ui.categories

import androidx.lifecycle.ViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import ru.justcircleprod.onlybtsfuns.data.AppRepository
import ru.justcircleprod.onlybtsfuns.data.room.constants.AppDatabaseConstants
import ru.justcircleprod.onlybtsfuns.data.room.constants.DifficultyState
import javax.inject.Inject

@HiltViewModel
class CategoriesViewModel @Inject constructor(private val repository: AppRepository) : ViewModel() {
    suspend fun getDifficultyState() = withContext(Dispatchers.IO) {
        DifficultyState.fromInt(
            repository.getSetting(AppDatabaseConstants.DIFFICULTY_SETTING_ID).state
        )
    }
}