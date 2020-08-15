package com.laioffer.tinnews.ui.search;

import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.laioffer.tinnews.R;
import com.laioffer.tinnews.databinding.SearchNewsItemBinding;
import com.laioffer.tinnews.model.Article;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class SearchNewsAdapter extends RecyclerView.Adapter<SearchNewsAdapter.SearchNewsViewHolder>{
    private List<Article> articles = new ArrayList<>();
    interface ItemCallback {
        void onOpenDetails(Article article);
        void setFavorite(Article article);
    }

    private ItemCallback itemCallback;

    public void setItemCallback(ItemCallback listener) {
        this.itemCallback = listener;
    }

    // 1. Supporting data:
    public void setArticles(List<Article> newsList) {
        articles.clear();
        articles.addAll(newsList);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public SearchNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.search_news_item, parent, false);
        return new SearchNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SearchNewsViewHolder holder, int position) {
        Article article= articles.get(position);
        if(article.favorite)
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_24dp);
        else
            holder.favoriteImageView.setImageResource(R.drawable.ic_favorite_border_24dp);
        holder.favoriteImageView.setOnClickListener(v->itemCallback.setFavorite(article));
        holder.itemTitleTextView.setText(article.title);
        holder.itemTitleTextView.setMinLines(1);
        holder.itemTitleTextView.setMaxLines(3);
        holder.itemTitleTextView.setTextSize(20);
        holder.itemTitleTextView.setTextColor(Color.WHITE);
        Picasso.get().load(article.urlToImage).into(holder.itemImageView);
        holder.itemView.setOnClickListener(v -> itemCallback.onOpenDetails(article));
    }

    @Override
    public int getItemCount() {
        return articles.size();
    }
    // 2. Adapter overrides:
    // TODO

    // 3. SearchNewsViewHolder:

    public static class SearchNewsViewHolder extends RecyclerView.ViewHolder{
        ImageView favoriteImageView;
        ImageView itemImageView;
        TextView itemTitleTextView;

        public SearchNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            SearchNewsItemBinding binding = SearchNewsItemBinding.bind(itemView);
            favoriteImageView = binding.searchItemFavorite;
            itemImageView = binding.searchItemImage;
            itemTitleTextView = binding.searchItemTitle;

        }
    }
}
