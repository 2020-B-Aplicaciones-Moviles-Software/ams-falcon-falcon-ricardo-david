package com.example.teamplayers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class PlayersViewModelFactory @Inject constructor(
        private val playersViewModel: PlayersViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(PlayersViewModel::class.java)) {
            return playersViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
