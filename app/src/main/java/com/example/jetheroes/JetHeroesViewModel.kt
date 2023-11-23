package com.example.jetheroes

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.jetheroes.data.HeroRepository
import com.example.jetheroes.model.Hero
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class JetHeroesViewModel(private val repo :HeroRepository):ViewModel() {
    private val _groupedHeroes= MutableStateFlow(
        repo.getHeroes()
            .sortedBy { it.name }
            .groupBy { it.name[0] }
    )

    val groupedHeroes:StateFlow<Map<Char,List<Hero>>> get() = _groupedHeroes
}

class ViewModelFactory(private val repo:HeroRepository):ViewModelProvider.NewInstanceFactory(){

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if(modelClass.isAssignableFrom(JetHeroesViewModel::class.java)){
            return JetHeroesViewModel(repo) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class"+modelClass.name)
    }
}