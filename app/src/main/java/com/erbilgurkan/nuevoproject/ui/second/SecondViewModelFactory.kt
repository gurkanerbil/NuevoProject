package com.erbilgurkan.nuevoproject.ui.second

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.erbilgurkan.nuevoproject.data.repositories.Repository

@Suppress("UNCHECKED_CAST")
class SecondViewModelFactory(private val repository: Repository) :
    ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return SecondViewModel(repository) as T
    }
}