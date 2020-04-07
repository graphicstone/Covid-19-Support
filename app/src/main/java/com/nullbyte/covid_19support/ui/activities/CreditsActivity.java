package com.nullbyte.covid_19support.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.nullbyte.covid_19support.R;
import com.nullbyte.covid_19support.adapters.DeveloperInfoAdapter;
import com.nullbyte.covid_19support.adapters.SpecialCreditsAdapter;
import com.nullbyte.covid_19support.constants.Constant;

import java.util.ArrayList;

public class CreditsActivity extends AppCompatActivity {

    private RecyclerView mDeveloperRecyclerView, mSpecialCreditsRecyclerView;
    private ArrayList<String> mName, mCreditsName, mCreditsFor;
    private Toolbar mToolbar;
    private TextView mGithubRepo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_credits);

        if (getSupportActionBar() != null)
            getSupportActionBar().hide();

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initViews();
        initList();

        mToolbar.setNavigationOnClickListener(view -> onBackPressed());
        mGithubRepo.setOnClickListener(view -> {
            Intent intent = new Intent(this, WebActivity.class);
            intent.putExtra("url", Constant.PROJECT_GITHUB_REPO);
            startActivity(intent);
        });

        mDeveloperRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mDeveloperRecyclerView.setAdapter(new DeveloperInfoAdapter(mName));

        mSpecialCreditsRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mSpecialCreditsRecyclerView.setAdapter(new SpecialCreditsAdapter(mCreditsName, mCreditsFor));
    }

    private void initList() {
        mName.add("Harishiv Singh");
        mName.add("Anant Raman");

        mCreditsName.add("astsiatsko (RapidAPI)");
        mCreditsName.add("Saiprasad Balasubramanian");
        mCreditsName.add("David Probst Jr");
        mCreditsName.add("Sayyam Mehmood");
        mCreditsName.add("Philipp Jahoda");
        mCreditsName.add("News API (newsapi.org)");

        mCreditsFor.add("Tracker data");
        mCreditsFor.add("Graph data");
        mCreditsFor.add("Lottie");
        mCreditsFor.add("Carousel view");
        mCreditsFor.add("Graph View");
        mCreditsFor.add("News");
    }

    private void initViews() {
        mName = new ArrayList<>();
        mCreditsName = new ArrayList<>();
        mCreditsFor = new ArrayList<>();
        mGithubRepo = findViewById(R.id.tv_github_repo);
        mToolbar = findViewById(R.id.toolbar_credits);
        mDeveloperRecyclerView = findViewById(R.id.rv_developer_info);
        mSpecialCreditsRecyclerView = findViewById(R.id.rv_special_credits);
    }
}
