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
import program.java.punch.andr.myapplication.data.model.Movie;
import program.java.punch.andr.myapplication.ui.main.interfaces.OnAddFavouriteClick;
import program.java.punch.andr.myapplication.utils.GlideApp;


public class MoviesAdapter extends RecyclerView.Adapter<MoviesAdapter.MoviesHolder> {

    private List<Movie> moviesList = new ArrayList<>();
    private Context ctx;
    private OnAddFavouriteClick mCallback;

    public MoviesAdapter(Context context, OnAddFavouriteClick listener) {
        ctx = context;
        mCallback = listener;
    }


    @NonNull
    @Override
    public MoviesHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_item_movie,
                parent, false);
        return new MoviesHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MoviesHolder holder, int position) {
        holder.movieTitle.setText(moviesList.get(position).getTitle());
        GlideApp.with(ctx).load(moviesList.get(position).getPoster())
                .placeholder(R.drawable.noimage).into(holder.thumbnailImg);

        holder.favouriteImg.setOnClickListener(view -> mCallback
                .OnAddFavouriteMovieClick(moviesList.get(position)));

    }

    @Override
    public int getItemCount() {
        return moviesList.size();
    }


    class MoviesHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movie_title)
        TextView movieTitle;

        @BindView(R.id.thumbnail)
        ImageView thumbnailImg;

        @BindView(R.id.favourite)
        ImageButton favouriteImg;


        public MoviesHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }
    }

    public void addMoviesToAdapter(List<Movie> l) {
        moviesList.clear();
        moviesList.addAll(l);
        notifyDataSetChanged();
    }

    public void clearMovies() {
        moviesList.clear();
        notifyDataSetChanged();
    }

}
