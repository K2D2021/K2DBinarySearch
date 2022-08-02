package ru.k2d.k2dbinarysearch

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import ru.k2d.k2dbinarysearch.repository.Repository

class MainViewModelFactory(private val repository: Repository) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return MainViewModel(repository) as T
    }
}