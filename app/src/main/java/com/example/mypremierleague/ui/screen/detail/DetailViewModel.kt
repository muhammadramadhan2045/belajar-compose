package com.example.mypremierleague.ui.screen.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypremierleague.data.RewardRepository
import com.example.mypremierleague.model.TeamDetail
import com.example.mypremierleague.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class DetailRewardViewModel(
    private val repository: RewardRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<TeamDetail>> =
        MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<TeamDetail>>
        get() = _uiState

    fun getRewardById(teamId: Long) {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            _uiState.value = UiState.Success(repository.getTeamDetailById(teamId))
        }
    }


}