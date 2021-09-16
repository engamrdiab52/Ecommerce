package com.example.core.data

interface IPreferenceHelper {
    fun setUserLoggedIn(loggedIn: Boolean)
    fun getUserLoggedIn(): Boolean
    fun clearPrefs()
}