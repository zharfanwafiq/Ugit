package com.orainjistudio.ugit.data.viewmodel

import androidx.lifecycle.ViewModel
import com.orainjistudio.ugit.data.repository.GithubUserRepository

class MainViewModel(githubUserRepository: GithubUserRepository) : ViewModel() {
    val getGithubUser = githubUserRepository.showGithubUserList()
}