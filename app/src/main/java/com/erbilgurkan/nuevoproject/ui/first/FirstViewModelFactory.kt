package com.erbilgurkan.nuevoproject.ui.first

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erbilgurkan.nuevoproject.data.repositories.Repository

@Suppress("UNCHECKED_CAST")
class FirstViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return FirstViewModel(repository) as T
    }
}