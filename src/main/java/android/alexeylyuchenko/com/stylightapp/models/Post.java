package android.alexeylyuchenko.com.stylightapp.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey on 2/8/2017.
 */

public class Post {

    @Setter @Getter
    @SerializedName("title")
    private String title;

    @Setter @Getter
    @SerializedName("teaserImage")
    private String teaserImage;

    @Setter @Getter
    @SerializedName("category")
    private Category category;

}
