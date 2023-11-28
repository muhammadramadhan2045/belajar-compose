package com.example.mypremierleague.di

import com.example.mypremierleague.data.RewardRepository

object Injection {
    fun provideRepository(): RewardRepository {
        return RewardRepository.getInstance()
    }
}