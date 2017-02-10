package android.alexeylyuchenko.com.stylightapp.presenters;

import android.alexeylyuchenko.com.stylightapp.MainActivityFragment;
import android.alexeylyuchenko.com.stylightapp.adapters.ProductsAdapter;
import android.alexeylyuchenko.com.stylightapp.models.ApiProductsResponse;
import android.alexeylyuchenko.com.stylightapp.retrofit.RetrofitFactory;
import android.alexeylyuchenko.com.stylightapp.retrofit.api.ProductsApi;
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

public class ProductsPresenter implements iMainPresenter{
    private static final String TAG = "ProductsPresenter";
    private RecyclerView mRecyclerView;
    private MainActivityFragment mView;
    private ProductsAdapter adapter;
    private int currentPage = 0;
    private boolean loadingNow = false;
    private int category;

    public ProductsPresenter(MainActivityFragment mView, int category, RecyclerView mRecyclerView) {
        this.mView = mView;
        this.category = category;
        this.mRecyclerView = mRecyclerView;
    }

    @Override
    public void setupRecyclerViews() {
        adapter = new ProductsAdapter(mView.getContext());
        this.setupRecycleView();
    }

    @Override
    public void init() {
        setupRecyclerViews();
    }

    @Override
    public void loadAllData() {
        setObserverForProducts();
    }

    private void setupRecycleView() {
        mRecyclerView.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(mView.getActivity());
        mRecyclerView.setLayoutManager(llm);
        llm.setOrientation(LinearLayoutManager.HORIZONTAL);
        mRecyclerView.setAdapter(adapter);

        this.setUpScrollingViewForProducts(llm);
    }

    private void setObserverForProducts() {

        ProductsApi apiService = RetrofitFactory.createRetrofitService(ProductsApi.class, Constants.API_URL);
        apiService.getProducts(Constants.API_REQ_HEADER_PARAM1_VAL,
                Constants.API_REQ_HEADER_PARAM2_VAL, category, Constants.pagePerItem, currentPage)
                .subscribeOn(Schedulers.newThread())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ApiProductsResponse>() {
                    @Override
                    public void onCompleted() {

                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Error downloading products: " + e.getMessage());
                    }

                    @Override
                    public void onNext(ApiProductsResponse apiProductsResponse) {
                        Log.d(TAG, "Products have been successfully downloaded!");
                        loadingNow = false;
                        displayProducts(apiProductsResponse.getProducts());
                    }
                });
    }

    private void displayProducts(List list) {
        if (currentPage == 0) {
            adapter.clear();
        }

        currentPage++;
        adapter.addProducts(list);
    }


    private void setUpScrollingViewForProducts(final LinearLayoutManager llm) {
        mRecyclerView.addOnScrollListener(new RecyclerView.OnScrollListener()
        {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy)
            {
                int pastVisiblesItems, visibleItemCount, totalItemCount;

                if (!loadingNow) {
                    if (dx > 0) //check for scroll down
                    {
                        visibleItemCount = llm.getChildCount();
                        totalItemCount = llm.getItemCount();
                        pastVisiblesItems = llm.findFirstVisibleItemPosition();


                        if ((visibleItemCount + pastVisiblesItems) >= totalItemCount) {
                            loadingNow = true;
                            currentPage++;
                            setObserverForProducts();
                        }
                    }
                }
            }
        });
    }

}
