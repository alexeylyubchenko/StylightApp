package android.alexeylyuchenko.com.stylightapp.models;

import com.google.gson.annotations.SerializedName;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey on 2/8/2017.
 */

public class Image {

    @Getter @Setter
    @SerializedName("url")
    private String url;

}
