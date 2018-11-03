package com.kaleb.githubapi.search;

import com.kaleb.githubapi.data.model.User;

import java.util.List;

public interface MainView {
    void addData(List<User> users);
    void showLoading(boolean loading);
    void showToast(String toast);
}
