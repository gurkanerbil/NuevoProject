package com.erbilgurkan.nuevoproject.ui.first

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erbilgurkan.nuevoproject.util.Coroutines
import com.erbilgurkan.nuevoproject.data.models.FirstModel
import com.erbilgurkan.nuevoproject.data.repositories.Repository
import kotlinx.coroutines.Job

class FirstViewModel(private val repository: Repository) : ViewModel() {
    private lateinit var job: Job

    private val _firstViews = MutableLiveData<List<FirstModel>>()

    val firstViews: LiveData<List<FirstModel>>
        get() = _firstViews

    fun getFirstViews() {
        job = Coroutines.ioThenMain(
            { repository.getFirstView() },
            { _firstViews.value = it })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
