package android.alexeylyuchenko.com.stylightapp.presenters;

import android.alexeylyuchenko.com.stylightapp.MainActivityFragment;
import android.alexeylyuchenko.com.stylightapp.adapters.PostsAdapter;
import android.alexeylyuchenko.com.stylightapp.models.ApiPostsResponse;
import android.alexeylyuchenko.com.stylightapp.retrofit.RetrofitFactory;
import android.alexeylyuchenko.com.stylightapp.retrofit.api.PostsApi;
import android.alexeylyuchenko.com.stylightapp.utils.Constants;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by Alexey on 2/10/2017.
 */

public class PostsPresenter implements iMainPresenter{

    private static final String TAG = "ProductsPresenter";
    private RecyclerView mRecyclerView;
    private MainActivityFragment mView;
    private PostsAdapter adapter;
    private int currentPage = 0;
    private boolean loadingNow = false;
    private String category;

    public PostsPresenter(MainActivityFragment mView, String category, RecyclerView mRecyclerView) {
        this.mView = mView;
        this.category = category;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public void setupRecyclerViews() {
        adapter = new PostsAdapter(mView.getContext());
        this.setupRecycleView();
    }

    @Override
    public void init() {
        setupRecyclerViews();
    }

    @Override
    public void loadAllData() {
        setObserverForPosts();
    }

    private void setupRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(mView.getActivity());
        mRecyclerView.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.VERTICAL);
        mRecyclerView.setAdapter(adapter);

        this.setUpScrollingViewForProducts(llm);
    }

    private void setObserverForPosts() {
        PostsApi serviceApi = RetrofitFactory.createRetrofitService(PostsApi.class, Constants.API_URL);
        serviceApi.getPosts(Constants.API_REQ_HEADER_PARAM1_VAL,
                Constants.API_REQ_HEADER_PARAM2_VAL, category, Constants.pagePerItem, currentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ApiPostsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error downloading products: " + e.getMessage());
                    }

                    @Override
                    public void onNext(ApiPostsResponse apiPostsResponse) {
                        Log.d(TAG, "Products have been successfully downloaded!");
                        loadingNow = false;
                        displayPosts(apiPostsResponse.getPosts());
                    }
                });
    }

    private void displayPosts(List list) {
        if (currentPage == 0) {
            adapter.clearData();
        }

        currentPage++;
        adapter.addPosts(list);
    }



    private void setUpScrollingViewForProducts(final LinearLayoutManager llm) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                int pastVisiblesItems, visibleItemCount, totalItemCount;

                if (!loadingNow) {
                    if (dy > 0) //check for scroll down
                    {
                        visibleItemCount = llm.getChildCount();
                        totalItemCount = llm.getItemCount();
                        pastVisiblesItems = llm.findFirstVisibleItemPosition();


                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loadingNow = true;
                            currentPage++;
                            setObserverForPosts();
                        }
                    }
                }
            }
        });
    }
}
