package com.example.mypremierleague.data

import com.example.mypremierleague.model.FakeTeamDataSource
import com.example.mypremierleague.model.Team
import com.example.mypremierleague.model.TeamDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.flow.map

class RewardRepository {
    private val teamDetails = mutableListOf<TeamDetail>()

    init {
        if (teamDetails.isEmpty()) {
            FakeTeamDataSource.dummyTeams.forEach {
                teamDetails.add(TeamDetail(it))
            }
        }
    }

    fun searchTeam(query: String): List<Team>{
        return FakeTeamDataSource.dummyTeams.filter {
            it.title.contains(query, ignoreCase = true)
        }
    }


    fun getAllData():List<Team>{
        return FakeTeamDataSource.dummyTeams
    }


    fun getAllRewards(): Flow<List<TeamDetail>> {
        return flowOf(teamDetails)
    }

    fun getTeamDetailById(teamId: Long): TeamDetail{
        return teamDetails.first {
            it.team.id == teamId
        }
    }




    companion object {
        @Volatile
        private var instance: RewardRepository? = null

        fun getInstance(): RewardRepository =
            instance ?: synchronized(this) {
                RewardRepository().apply {
                    instance = this
                }
            }
    }
}