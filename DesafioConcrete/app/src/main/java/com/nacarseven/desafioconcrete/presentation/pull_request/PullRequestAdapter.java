package com.nacarseven.desafioconcrete.presentation.pull_request;

import android.graphics.Bitmap;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.target.Target;
import com.nacarseven.desafioconcrete.R;
import com.nacarseven.desafioconcrete.common.helpers.DateFormatter;
import com.nacarseven.desafioconcrete.data.entities.PullRequest;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by nacarseven on 11/10/17.
 */

public class PullRequestAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region FIELDS
    private List<PullRequest> pulls;
    private Listener listener;
    private DateFormatter dateFormatter;
    //endregion

    //region CONSTRUCTOR
    public PullRequestAdapter(List<PullRequest> pulls, Listener listener) {
        this.pulls = pulls;
        this.listener = listener;
        dateFormatter = new DateFormatter();
    }

    //endregion

    //region METHODS

    //region OVERRIDDEN METHODS

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new ViewHolder(inflater.inflate(R.layout.activity_pull_request_item, parent, false));
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof PullRequestAdapter.ViewHolder) {
            ((PullRequestAdapter.ViewHolder) holder).bind(pulls.get(position));
        }
    }

    @Override
    public int getItemCount() {
        return pulls == null ? 0 : pulls.size();
    }

    //endregion

    //endregion

    //region INNER CLASS
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activity_pull_request_item_tvw_pr_title)
        TextView tvwPrTitle;
        @BindView(R.id.activity_pull_request_item_tvw_pr_body)
        TextView tvwPrBody;
        @BindView(R.id.activity_pull_request_item_civ_author)
        CircleImageView civAuthor;
        @BindView(R.id.activity_pull_request_item_tvw_author_username)
        TextView tvwAuthorUserName;
        @BindView(R.id.activity_pull_request_item_tvw_date)
        TextView tvwDate;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @OnClick(R.id.activity_pull_request_item_cons_root)
        void onClickItem() {
            PullRequest pr = pulls.get(getAdapterPosition());
            if (listener != null) listener.onClickItem(pr.getHtmlUrl());
        }

        private void bind(final PullRequest pr) {

            tvwDate.setText(pr.getDate() == null ? itemView.getResources().getString(R.string.no_data) :
                    dateFormatter.parseToString(pr.getDate().getTime(), DateFormatter.DATE_FORMAT_DATE));
            tvwPrTitle.setText(pr.getTitle().isEmpty() ? itemView.getResources().getString(R.string.no_title) : pr.getTitle());
            tvwPrBody.setText(pr.getBody().isEmpty() ? itemView.getResources().getString(R.string.no_body) : pr.getBody());
            tvwAuthorUserName.setText(pr.getAuthor().getLogin().isEmpty() ? "" : pr.getAuthor().getLogin());

            Glide.with(itemView.getContext())
                    .load(pr.getAuthor().getImageUrl())
                    .asBitmap()
                    .placeholder(R.drawable.img_sil)
                    .error(R.drawable.img_sil)
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            civAuthor.setImageResource(R.drawable.img_sil);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            civAuthor.setImageBitmap(resource);
                            return false;
                        }
                    }).into(civAuthor);

        }

    }
    //endregion

    //region INTERFACES
    public interface Listener {

        void onClickItem(String htmlUrl);

    }
    //endregion
}
