package com.example.mypremierleague.ui.screen.home

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mypremierleague.data.RewardRepository
import com.example.mypremierleague.model.Team
import com.example.mypremierleague.model.TeamDetail
import com.example.mypremierleague.ui.common.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

class HomeViewModel(
    private val repository: RewardRepository
) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState<List<TeamDetail>>> = MutableStateFlow(UiState.Loading)
    val uiState: StateFlow<UiState<List<TeamDetail>>>
        get() = _uiState


    private val _query = mutableStateOf("")
    val query: State<String> get() = _query
    private val _groupedReward = MutableStateFlow(
        repository.getAllData()
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    )

    val groupedReward: StateFlow<Map<Char, List<Team>>> get() = _groupedReward

    fun search(newQuery: String) {
        _query.value = newQuery
        _groupedReward.value = repository.searchTeam(_query.value)
            .sortedBy { it.title }
            .groupBy { it.title[0] }
    }

    fun getAllRewards() {
        viewModelScope.launch {
            repository.getAllRewards()
                .catch {
                    _uiState.value = UiState.Error(it.message.toString())
                }
                .collect { teamDetails ->
                    _uiState.value = UiState.Success(teamDetails)
                }
        }
    }
}