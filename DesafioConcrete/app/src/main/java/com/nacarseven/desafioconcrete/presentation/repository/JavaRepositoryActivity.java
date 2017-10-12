package com.nacarseven.desafioconcrete.presentation.repository;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.nacarseven.desafioconcrete.R;
import com.nacarseven.desafioconcrete.data.entities.PullRequest;
import com.nacarseven.desafioconcrete.data.entities.Repository;
import com.nacarseven.desafioconcrete.presentation.pull_request.PullRequestActivity;


import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JavaRepositoryActivity extends AppCompatActivity implements JavaRepositoryView {

    //region FIELDS
    private boolean pagingLoading;
    private JavaRepositoryAdapter adapter;
    private JavaRepositoryPresenter presenter;

    @BindView(R.id.activity_java_repository_rcv_items)
    RecyclerView rcvItems;
    @BindView(R.id.activity_java_repository_pgb)
    ProgressBar pgbLoading;
    @BindView(R.id.activity_java_repository_tvw_no_data)
    TextView tvwNoData;

    //endregion

    //region LIFECYCLE

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_repository);
        ButterKnife.bind(this);

        setupList();
        presenter = new JavaRepositoryPresenter(this);
        presenter.getRepositories(false);
    }

    //endregion

    //region METHODS

    //region PUBLIC METHODS

    @Override
    public void showLoading(boolean show) {
        pgbLoading.setVisibility(show ? View.VISIBLE : View.GONE);
        rcvItems.setVisibility(show ? View.GONE : View.VISIBLE);
    }

    @Override
    public void showPagingLoading(boolean show) {
        pagingLoading = show;

        if (show) {
            rcvItems.post(new Runnable() {
                @Override
                public void run() {
                    adapter.showLoading();
                }
            });
        } else {
            adapter.hideLoading();
        }
    }

    @Override
    public void showMessageError() {
        Toast.makeText(this, this.getString(R.string.error), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void showTextNoData(boolean show) {
        tvwNoData.setVisibility(show ? View.VISIBLE : View.GONE);
    }

    @Override
    public void loadRepositories(List<Repository> repositories) {
        adapter.setRepositories(repositories);
    }

    @Override
    public void loadPullRequest(String repositoryName, List<PullRequest> pullRequests) {
        if (pullRequests.size() != 0) {
            Intent intent = new Intent(this, PullRequestActivity.class);
            intent.putExtra("pulls", Parcels.wrap(pullRequests));
            intent.putExtra("repositoryName", repositoryName);
            startActivity(intent);
        } else {
            Toast.makeText(this, this.getString(R.string.there_are_no_prs), Toast.LENGTH_LONG).show();
        }
    }

    //endregion

    //region PRIVATE METHODS
    private void setupList() {
        adapter = new JavaRepositoryAdapter(new JavaRepositoryAdapter.Listener() {
            @Override
            public void onClickItem(String author, String repository) {
                presenter.getPullsRepository(author, repository);

            }
        });

        rcvItems.setLayoutManager(new LinearLayoutManager(this));
        rcvItems.setAdapter(adapter);

        rcvItems.addOnScrollListener(new RecyclerView.OnScrollListener() {
            private int visibleThreshold = 5;

            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {
                LinearLayoutManager layoutManager = (LinearLayoutManager) rcvItems.getLayoutManager();
                int totalItemCount = layoutManager.getItemCount();
                int lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition();

                if (!pagingLoading && totalItemCount <= (lastVisibleItemPosition + visibleThreshold)) {
                    presenter.getRepositories(true);
                }
            }
        });
    }

    //endregion

    //endregion
}
