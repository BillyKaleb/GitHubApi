package com.kaleb.githubapi.search;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.kaleb.githubapi.EndlessRecyclerViewOnScrollListener;
import com.kaleb.githubapi.GithubApiApplication;
import com.kaleb.githubapi.R;
import com.kaleb.githubapi.RecyclerAdapter;
import com.kaleb.githubapi.data.model.User;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements MainView {

    private List<User> userList;
    private RecyclerAdapter recyclerAdapter;
    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private Button queryInput;
    private EditText inputField;
    private String inputResult = "";
    private EndlessRecyclerViewOnScrollListener endlessRecyclerViewOnScrollListener;
    private String TAG = "Check";
    private MainPresenter mainPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mainPresenter = new MainPresenter(this, GithubApiApplication.getRemoteRepository());

        setContentView(R.layout.activity_main);

        setSupportActionBar((Toolbar) findViewById(R.id.mainToolbar));

        recyclerView = findViewById(R.id.recyclerView);
        progressBar = findViewById(R.id.itemProgressBar);
        queryInput = findViewById(R.id.queryInput);
        inputField = findViewById(R.id.inputField);
        endlessRecyclerViewOnScrollListener = new EndlessRecyclerViewOnScrollListener() {
            @Override
            public void onLoadMore() {
                mainPresenter.getAllUser(inputResult);
            }
        };

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        userList = new ArrayList<>();
        recyclerAdapter = new RecyclerAdapter(userList);
        recyclerView.setAdapter(recyclerAdapter);

        //check later dude
        recyclerView.addOnScrollListener(endlessRecyclerViewOnScrollListener);

        queryInput.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                inputResult = inputField.getText().toString();
                userList.clear();
                recyclerAdapter.cleanList();
                endlessRecyclerViewOnScrollListener.resetListener();
                mainPresenter.resetPage();
                mainPresenter.getAllUser(inputResult);
            }
        });
    }

    @Override
    public void addData(List<User> users) {
        recyclerAdapter.addList(users);
    }

    @Override
    public void showLoading(boolean loading) {
        progressBar.setVisibility(loading? View.VISIBLE: View.GONE);
    }

    @Override
    public void showToast(String toast) {
        Toast.makeText(this, toast, Toast.LENGTH_SHORT).show();
    }
}
