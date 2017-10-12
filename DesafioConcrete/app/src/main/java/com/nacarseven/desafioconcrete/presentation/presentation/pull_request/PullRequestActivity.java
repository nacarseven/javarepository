package com.nacarseven.desafioconcrete.presentation.presentation.pull_request;

import android.os.Bundle;
import android.os.Parcel;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
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

public class PullRequestActivity extends AppCompatActivity implements PullRequestView {

    //region FIELDS

    private PullRequestPresenter presenter;

    @BindView(R.id.activity_pull_request_tvw_no_data)
    TextView tvwNoData;
    @BindView(R.id.activity_pull_request_pgb)
    ProgressBar pgbLoading;
    @BindView(R.id.activity_pull_request_rcv_items)
    RecyclerView rcvItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pull_request);
        ButterKnife.bind(this);

        presenter = new PullRequestPresenter(this);

        List<PullRequest> pulls = Parcels.unwrap(getIntent().getParcelableExtra("pulls"));
        presenter.setPullRequests(pulls);
    }

}
