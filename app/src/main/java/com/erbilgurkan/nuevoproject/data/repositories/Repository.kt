package com.erbilgurkan.nuevoproject.data.repositories

import com.erbilgurkan.nuevoproject.data.network.RetrofitApi

class Repository(
    private val api: RetrofitApi
) : SafeApiRequest() {
    suspend fun getFirstView() = apiRequest { api.getAllElements() }

    suspend fun getDetail(id: Int) = apiRequest { api.getDetail(id) }
}