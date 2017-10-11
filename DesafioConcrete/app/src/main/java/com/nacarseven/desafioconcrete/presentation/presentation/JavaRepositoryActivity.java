package com.nacarseven.desafioconcrete.presentation.presentation;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;

import com.nacarseven.desafioconcrete.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class JavaRepositoryActivity extends AppCompatActivity implements JavaRepositoryView {


    //region FIELDS
    private JavaRepositoryPresenter presenter;

    @BindView(R.id.activity_java_repository_rcv_items)
    RecyclerView rcvItems;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_java_repository);
        ButterKnife.bind(this);

        presenter = new JavaRepositoryPresenter(this);

    }
}
