package com.erbilgurkan.nuevoproject.ui.second

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.erbilgurkan.nuevoproject.data.models.SecondModel
import com.erbilgurkan.nuevoproject.data.repositories.Repository
import com.erbilgurkan.nuevoproject.util.Coroutines
import kotlinx.coroutines.Job

class SecondViewModel(private val repository: Repository) : ViewModel() {
    private lateinit var job: Job

    private val _details = MutableLiveData<List<SecondModel>>()

    val details: LiveData<List<SecondModel>>
        get() = _details

    fun getDetails(id: Int) {
        job = Coroutines.ioThenMain(
            { repository.getDetail(id) },
            { _details.value = it })
    }

    override fun onCleared() {
        super.onCleared()
        if (::job.isInitialized) job.cancel()
    }
}
