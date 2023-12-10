package com.orainjistudio.ugit.data.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MediatorLiveData
import com.orainjistudio.ugit.data.api.ApiService
import com.orainjistudio.ugit.data.response.detailuser.DetailUser
import com.orainjistudio.ugit.data.response.detailuser.ListUser
import com.orainjistudio.ugit.utils.AppExecutors
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class GithubUserRepositoryImpl private constructor(
    private val apiService: ApiService,
    private val appExecutors: AppExecutors
) : GithubUserRepository {

    private val listGithubUser = MediatorLiveData<Result<ArrayList<DetailUser>>>()

    override fun showGithubUserList(): LiveData<Result<ArrayList<DetailUser>>> {
        listGithubUser.value = Result.Loading
        apiService.getAllUsers()
            .enqueue(object : Callback<ArrayList<DetailUser>>{
                override fun onResponse(
                    call: Call<ArrayList<DetailUser>>,
                    response: Response<ArrayList<DetailUser>>
                ) {
                    val githubUser = response.body()
                    listGithubUser.postValue(Result.Success(githubUser as ArrayList<DetailUser>))
                }

                override fun onFailure(call: Call<ArrayList<DetailUser>>, t: Throwable) {
                    TODO("Not yet implemented")
                }

            })
        return listGithubUser
    }

    companion object {
        @Volatile
        private var instance: GithubUserRepositoryImpl? = null
        fun getInstance(
            apiService: ApiService,
            appExecutors: AppExecutors
        ): GithubUserRepositoryImpl =
            instance ?: synchronized(this) {
                instance ?: GithubUserRepositoryImpl(apiService, appExecutors)
            }.also { instance = it }
    }
}