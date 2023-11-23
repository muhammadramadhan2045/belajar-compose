package com.example.jetheroes.data

import com.example.jetheroes.model.Hero
import com.example.jetheroes.model.HeroesData

class HeroRepository {
    fun getHeroes(): List<Hero> {
        return HeroesData.heroes
    }
}