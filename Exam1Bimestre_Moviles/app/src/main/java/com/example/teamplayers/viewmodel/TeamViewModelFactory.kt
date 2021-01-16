package com.example.teamplayers.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class TeamViewModelFactory @Inject constructor(
        private val teamViewModel: TeamViewModel
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(TeamViewModel::class.java)) {
            return teamViewModel as T
        }
        throw IllegalArgumentException("Unknown class name")
    }
}
