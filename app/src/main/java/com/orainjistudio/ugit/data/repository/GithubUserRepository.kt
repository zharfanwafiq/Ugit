package com.orainjistudio.ugit.data.repository

import androidx.lifecycle.LiveData
import com.orainjistudio.ugit.data.response.detailuser.DetailUser

interface GithubUserRepository {
    fun showGithubUserList(): LiveData<Result<ArrayList<DetailUser>>>
}