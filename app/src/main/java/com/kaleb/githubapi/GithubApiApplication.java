package com.kaleb.githubapi;

import android.app.Application;

import com.kaleb.githubapi.data.GitHubService;
import com.kaleb.githubapi.data.RemoteRepository;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GithubApiApplication extends Application {

    private static GitHubService gitHubService;
    private static RemoteRepository remoteRepository;

    @Override
    public void onCreate() {
        super.onCreate();

        remoteRepository = new RemoteRepository();

        apiNetwork();
    }

    public void apiNetwork() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://api.github.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        gitHubService = retrofit.create(GitHubService.class);
    }

    public static GitHubService getGitHubService() {
        return gitHubService;
    }

    public static RemoteRepository getRemoteRepository() {
        return remoteRepository;
    }
}
