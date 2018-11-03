package com.kaleb.githubapi.search;

import com.kaleb.githubapi.data.RemoteRepository;
import com.kaleb.githubapi.data.response.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainPresenter {

    private MainView view;
    private RemoteRepository remoteRepository;
    private int pageCounter = 1;

    public MainPresenter(MainView view, RemoteRepository remoteRepository) {
        this.view = view;
        this.remoteRepository = remoteRepository;
    }

    public void getAllUser(String user){
        view.showLoading(true);
        remoteRepository.getUsers(user, pageCounter).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.body() != null) {
                    if (response.body().getTotalCount() > 0){
                        view.addData(response.body().getUsers());
                        pageCounter++;
                    } else {
                        view.showToast("There's no user with that name here!");
                    }
                } else {
                    view.showToast("You reach the end of this Query!");
                }
                view.showLoading(false);
            }
            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                view.showToast("Error! Wait another minute before doing another search!");
                view.showLoading(false);
            }
        });
    }

    public void resetPage(){
        this.pageCounter = 1;
    }

    public void onCreate() { }
    public void onPause() { }
    public void onResume() { }
    public void onDestroy() { }

}
