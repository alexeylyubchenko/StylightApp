package android.alexeylyuchenko.com.stylightapp.adapters;

import android.alexeylyuchenko.com.stylightapp.R;
import android.alexeylyuchenko.com.stylightapp.models.Product;
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

public class ProductsAdapter extends RecyclerView.Adapter<ProductsAdapter.ViewHolder> {

    private List<Product> productList;
    private Context context;

    public ProductsAdapter(Context context) {
        this.context = context;
        this.productList = new ArrayList<Product>();
    }

    public void addProducts(List<Product> list) {
        productList.addAll(list);
        notifyDataSetChanged();
    }

    public void clear() {
        productList.clear();
        notifyDataSetChanged();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Product item = productList.get(position);

        Picasso.with(context).load(item.getImages().get(0).getUrl()).into(holder.imageViewH);
        holder.nameTextView.setText(item.getName());
        holder.priceTextView.setText(String.valueOf(item.getPrice()));

    }

    @Override
    public int getItemCount() {
        return productList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageViewH;
        public TextView nameTextView;
        public TextView priceTextView;

        public ViewHolder(View itemView) {
            super(itemView);

            this.imageViewH = (ImageView) itemView.findViewById(R.id.imageViewH);
            this.nameTextView = (TextView) itemView.findViewById(R.id.nameTextView);
            this.priceTextView = (TextView) itemView.findViewById(R.id.priceTextView);

        }


    }
}
