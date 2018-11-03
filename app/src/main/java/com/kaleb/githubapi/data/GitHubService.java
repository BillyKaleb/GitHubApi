package com.kaleb.githubapi.data;

import com.kaleb.githubapi.data.response.UserResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GitHubService {

    @GET("search/users")
    Call<UserResponse> listUsers(@Query("q") String user, @Query("page") int pageNumber);

}
