package android.alexeylyuchenko.com.stylightapp.retrofit.api;

import android.alexeylyuchenko.com.stylightapp.models.ApiPostsResponse;
import android.alexeylyuchenko.com.stylightapp.utils.Constants;

import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by Alexey on 2/8/2017.
 */

public interface PostsApi {

    @GET(Constants.API_REQ_POSTS)
    public Observable<ApiPostsResponse> getPosts(@Header(Constants.API_REQ_HEADER_PARAM1) String header1,
                                                 @Header(Constants.API_REQ_HEADER_PARAM2) String header2,
                                                 @Query(Constants.API_REQ_PARAM_CAT) String categoryName,
                                                 @Query(Constants.API_REQ_PARAM_ITEMS) int itemsPerPage,
                                                 @Query(Constants.API_REQ_PARAM_PAGE) int page);

}
