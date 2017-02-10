package android.alexeylyuchenko.com.stylightapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey on 2/8/2017.
 */

public class ApiPostsResponse {

    @Getter @Setter
    @SerializedName("posts")
    private List<Post> posts;

}
