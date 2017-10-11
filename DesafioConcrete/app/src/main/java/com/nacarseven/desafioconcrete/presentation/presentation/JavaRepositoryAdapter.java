package com.nacarseven.desafioconcrete.presentation.presentation;

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
import com.nacarseven.desafioconcrete.presentation.data.entities.Repository;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Created by Elaine on 11/10/2017.
 */

public class JavaRepositoryAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    //region CONSTANTS
    private final int VIEW_TYPE_LOADING = 0, VIEW_TYPE_ITEM = 1;

    //region FIELDS
    private List<Repository> repositories;
    private Listener listener;

    public JavaRepositoryAdapter(Listener listener) {
        this.listener = listener;
    }

    //region PUBLIC
    public void showLoading() {
        repositories.add(null);
        notifyItemInserted(repositories.size() - 1);
    }

    public void hideLoading() {
        if (repositories != null) {
            repositories.remove(repositories.size() - 1);
            notifyItemRemoved(repositories.size());
        }
    }

    //endregion

    //region PROPERTIES
    public void setRepositories(List<Repository> repositories) {
        this.repositories = repositories;
        notifyDataSetChanged();
    }
    //endregion

    //region OVERRIDDEN METHODS
    @Override
    public int getItemViewType(int position) {
        Repository rep = repositories.get(position);

        if (rep == null) {
            return VIEW_TYPE_LOADING;
        } else {
            return VIEW_TYPE_ITEM;
        }
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());

        if (viewType == VIEW_TYPE_LOADING) {
            return new AdapterProgressViewHolder(inflater.inflate(R.layout.custom_adapter_progress_view_holder, parent, false));
        } else if (viewType == VIEW_TYPE_ITEM) {
            return new ViewHolder(inflater.inflate(R.layout.activity_java_repository_item, parent, false));
        }
        return null;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        if (holder instanceof JavaRepositoryAdapter.ViewHolder) {
            ((JavaRepositoryAdapter.ViewHolder) holder).bind(repositories.get(position));
        }

    }

    @Override
    public int getItemCount() {
        return repositories == null ? 0 : repositories.size();
    }

    //region INNER CLASS
    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.activity_java_repository_item_tvw_repository_name)
        TextView tvwRepName;
        @BindView(R.id.activity_java_repository_item_tvw_repository_description)
        TextView tvwRepDescription;
        @BindView(R.id.activity_java_repository_item_civ_author_image)
        CircleImageView civAuthorImage;
        @BindView(R.id.activity_java_repository_item_tvw_author_username)
        TextView tvwAuthorUserName;
        @BindView(R.id.activity_java_repository_item_tvw_author_full_name)
        TextView tvwAuthorFullName;


        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

//        @OnClick(R.id.fragment_radar_school_item_tvw_arrow_icon)
//        void onOpenActionsClick() {
//            openReplies = !openReplies;
//            updateButtons();
//        }

        private void bind(final Repository repository) {
            tvwRepName.setText(repository.getName());
            tvwRepDescription.setText(repository.getDescription());
            tvwAuthorUserName.setText(repository.getAuthor().getLogin());
            tvwAuthorFullName.setText(String.format("%s %s", repository.getAuthor().getName(), repository.getAuthor().getFullName()));

            Glide.with(itemView.getContext())
                    .load(repository.getAuthor().getImageUrl())
                    .asBitmap()
                    .placeholder(R.drawable.img_sil)
                    .error(R.drawable.img_sil)
                    .listener(new RequestListener<String, Bitmap>() {
                        @Override
                        public boolean onException(Exception e, String model, Target<Bitmap> target, boolean isFirstResource) {
                            civAuthorImage.setImageResource(R.drawable.img_sil);
                            return false;
                        }

                        @Override
                        public boolean onResourceReady(Bitmap resource, String model, Target<Bitmap> target, boolean isFromMemoryCache, boolean isFirstResource) {
                            civAuthorImage.setImageBitmap(resource);
                            return false;
                        }
                    }).into(civAuthorImage);

        }

    }
    //endregion

    //region INTERFACES
    public interface Listener {

        void onClickItem();

    }
    //endregion
}
