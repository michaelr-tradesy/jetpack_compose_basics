package com.example.jetpackcomposebasics.appThemeState

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.example.jetpackcomposebasics.AppThemeState
import com.example.jetpackcomposebasics.DefaultAppSerializer
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

interface AppThemeDataStore {
    suspend fun listen(context: Context, callback: (AppThemeState) -> Unit)
    fun set(context: Context, state: AppThemeState)

    companion object {
        var instance : DefaultAppThemeDataStore = DefaultAppThemeDataStore()
    }
}

class DefaultAppThemeDataStore : AppThemeDataStore {

    private val appThemeKey = stringPreferencesKey("AppTheme")
    private lateinit var appThemeStateFlow: Flow<String>
    private val mainScope = MainScope()
    private val appSerializer = DefaultAppSerializer()
    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

    override suspend fun listen(context: Context, callback: (AppThemeState) -> Unit) {
        with(context) {
            appThemeStateFlow = dataStore.data.map { preferences ->
                with(appSerializer) {
                    preferences[appThemeKey] ?: ""
                }
            }

            appThemeStateFlow.collect {
                with(appSerializer) {
                    val value: AppThemeState? = it.toObject()
                    value?.let { callback(it) }
                }
            }
        }
    }

    override fun set(context: Context, state: AppThemeState) {
        mainScope.launch {
            with(context) {
                dataStore.edit { settings ->
                    with(appSerializer) {
                        val value = state.toJson()
                        settings[appThemeKey] = value
                    }
                }
            }
        }
    }
}