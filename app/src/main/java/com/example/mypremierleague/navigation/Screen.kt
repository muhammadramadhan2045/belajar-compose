package com.example.mypremierleague.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Profile : Screen("profile")
    object DetailReward : Screen("home/{rewardId}") {
        fun createRoute(teamId: Long) = "home/$teamId"
    }
}