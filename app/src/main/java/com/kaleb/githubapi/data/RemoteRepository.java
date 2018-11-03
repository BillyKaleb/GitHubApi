package com.kaleb.githubapi.data;

import com.kaleb.githubapi.GithubApiApplication;
import com.kaleb.githubapi.data.response.UserResponse;

import retrofit2.Call;

public class RemoteRepository {

    public Call<UserResponse> getUsers(String user, int page) {
        return GithubApiApplication.getGitHubService().listUsers(user, page);
    }
}
