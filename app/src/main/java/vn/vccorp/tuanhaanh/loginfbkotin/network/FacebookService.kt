package vn.vccorp.tuanhaanh.loginfbkotin.network

import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query
import vn.vccorp.tuanhaanh.loginfbkotin.models.Profile

interface FacebookService {
    @GET("me")
    fun getInfoUser(
        @Query("fields") fields: String?, @Query(
            "access_token"
        ) accessToken: String?
    ): Call<Profile?>?
}