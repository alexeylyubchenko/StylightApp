package android.alexeylyuchenko.com.stylightapp;

import android.alexeylyuchenko.com.stylightapp.presenters.PostsPresenter;
import android.alexeylyuchenko.com.stylightapp.presenters.ProductsPresenter;
import android.alexeylyuchenko.com.stylightapp.utils.Constants;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    PostsPresenter postsPresenter1, postsPresenter2;
    ProductsPresenter productsPresenter1, productsPresenter2;
    RecyclerView recyclerView1, recyclerView2, recyclerView3, recyclerView4;

    public MainActivityFragment() {
    }

    public static MainActivityFragment newInstance() {
        Bundle args = new Bundle();
        args.putString(Constants.FRG_ARG_TAG, Constants.FRG_ARG_MAIN_TAG);
        MainActivityFragment fragment = new MainActivityFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        this.setupView(view);
    }

    private void setupView(View view) {

        recyclerView1 = (RecyclerView) view.findViewById(R.id.firstRecyclerView);
        recyclerView2 = (RecyclerView) view.findViewById(R.id.secondRecyclerView);
        recyclerView3 = (RecyclerView) view.findViewById(R.id.thirdRecyclerView);
        recyclerView4 = (RecyclerView) view.findViewById(R.id.forthRecyclerView);

        productsPresenter1 = new ProductsPresenter(this, Constants.API_REQ_PARAM_CAT_CLOTHING_VAL, recyclerView1);
        productsPresenter2 = new ProductsPresenter(this, Constants.API_REQ_PARAM_CAT_LAMPS_VAL, recyclerView3);

        postsPresenter1 = new PostsPresenter(this, Constants.API_REQ_PARAM_CAT_FASHION_VAL, recyclerView2);
        postsPresenter2 = new PostsPresenter(this, Constants.API_REQ_PARAM_CAT_LIFESTYLE_VAL, recyclerView4);

        productsPresenter1.init();
        productsPresenter1.loadAllData();
        productsPresenter2.init();
        productsPresenter2.loadAllData();
        postsPresenter1.init();
        postsPresenter1.loadAllData();
        postsPresenter2.init();
        postsPresenter2.loadAllData();

    }

}
