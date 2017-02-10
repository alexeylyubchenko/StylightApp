package android.alexeylyuchenko.com.stylightapp.adapters;

import android.alexeylyuchenko.com.stylightapp.R;
import android.alexeylyuchenko.com.stylightapp.models.Post;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alexey on 2/9/2017.
 */

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{

    private List<Post> postList;
    private Context context;

    public PostsAdapter(Context context) {
        this.context = context;
        this.postList = new ArrayList<Post>();
    }

    public void clearData() {
        postList.clear();
    }

    public void addPosts(List<Post> list) {
        postList.addAll(list);
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.vertical_item, parent, false);

        ViewHolder holder = new ViewHolder(view);

        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Post item = postList.get(position);

        Picasso.with(context).load(item.getTeaserImage()).into(holder.imageViewV);
        holder.titleTextView.setText(item.getCategory().getName());
        holder.descTextView.setText(item.getTitle());

    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewV;
        public TextView titleTextView;
        public TextView descTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageViewV = (ImageView) itemView.findViewById(R.id.imageViewV);
            this.titleTextView = (TextView) itemView.findViewById(R.id.titleTextView);
            this.descTextView = (TextView) itemView.findViewById(R.id.descTextView);

        }
    }
}
