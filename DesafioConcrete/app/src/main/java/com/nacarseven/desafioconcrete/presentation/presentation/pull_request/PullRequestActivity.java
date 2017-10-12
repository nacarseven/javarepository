package com.nacarseven.desafioconcrete.presentation.presentation.pull_request;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.nacarseven.desafioconcrete.R;
import com.nacarseven.desafioconcrete.presentation.data.entities.PullRequest;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by nacarseven on 11/10/17.
 */

public class PullRequestActivity extends AppCompatActivity {

    //region FIELDS
    private PullRequestPresenter presenter;

    @BindView(R.id.activity_pull_request_tvw_no_data)
    TextView tvwNoData;
    @BindView(R.id.activity_pull_request_pgb)
    ProgressBar pgbLoading;
    @BindView(R.id.activity_pull_request_rcv_items)
    RecyclerView rcvItems;

    //endregion

    //region LIFECYCLE
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        ButterKnife.bind(this);

        presenter = new PullRequestPresenter();

        List<PullRequest> pulls = Parcels.unwrap(getIntent().getParcelableExtra("pulls"));
        presenter.setPullRequests(pulls);

        init();

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.detachView();
    }

    //endregion

    //region METHODS

    //region PRIVATE METHODS

    private void init() {
        rcvItems.setLayoutManager(new LinearLayoutManager(this));

        if (presenter.getPullRequests() != null && presenter.getPullRequests().size() != 0) {
            setupList();
        }
        pgbLoading.setVisibility(View.GONE);
        tvwNoData.setVisibility(presenter.getPullRequests() != null && presenter.getPullRequests().size() != 0 ? View.GONE : View.VISIBLE);
    }

    private void setupList() {
        PullRequestAdapter adapter = new PullRequestAdapter(presenter.getPullRequests(), new PullRequestAdapter.Listener() {
            @Override
            public void onClickItem(String htmlUrl) {
               presenter.openLinkUrl(htmlUrl, getBaseContext());
            }
        });
        rcvItems.setAdapter(adapter);
    }

    //endregion

    //endregion

}
