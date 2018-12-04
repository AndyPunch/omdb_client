package program.java.punch.andr.myapplication.adapters;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import program.java.punch.andr.myapplication.R;
import program.java.punch.andr.myapplication.model.Movie;
import program.java.punch.andr.myapplication.ui.favourite.interfaces.OnDeleteFavouriteClick;
import program.java.punch.andr.myapplication.utils.GlideApp;


public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteHolder> {
    private List<Movie> favouriteList = new ArrayList<>();
    private Context ctx;
    private OnDeleteFavouriteClick mCallback;

    public FavouriteAdapter(OnDeleteFavouriteClick listener) {
        mCallback = listener;
    }

    @NonNull
    @Override
    public FavouriteHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        ctx = parent.getContext();
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item_movie,
                parent, false);
        return new FavouriteHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteHolder holder, int position) {
        holder.movieTitle.setText(favouriteList.get(position).getTitle());
        GlideApp.with(ctx).load(favouriteList.get(position).getPoster())
                .placeholder(R.drawable.noimage).into(holder.thumbnailImg);

        holder.favouriteImg.setOnClickListener(view -> mCallback.OnDeleteFavouriteMovieClick
                (favouriteList.get(position)));

    }

    @Override
    public int getItemCount() {
        return favouriteList.size();
    }

    public class FavouriteHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.movie_title_favourite)
        TextView movieTitle;

        @BindView(R.id.thumbnail_favourite)
        ImageView thumbnailImg;

        @BindView(R.id.favourite_delete)
        ImageButton favouriteImg;

        public FavouriteHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }


    public void addMoviesToAdapter(List<Movie> l) {
        favouriteList.clear();
        favouriteList.addAll(l);
        notifyDataSetChanged();
    }

}
