package com.orainjistudio.ugit.data.api

object UrlEndPoint {
    const val BASE_URL = "https://api.github.com/"
    const val SEARCH_URL = "search/users?q={username}"
    const val DETAIL_URL = "users/{username}"
    const val LIST_FOLLOWER = "users/{username}/followers"
    const val LIST_FOLLOWING = "users/{username}/following"
    const val ALL_USERS = "users"
}