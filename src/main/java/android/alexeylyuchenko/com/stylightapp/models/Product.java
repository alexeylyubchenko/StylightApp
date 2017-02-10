package android.alexeylyuchenko.com.stylightapp.models;

import com.google.gson.annotations.SerializedName;

import java.util.List;

import lombok.Getter;
import lombok.Setter;

/**
 * Created by Alexey on 2/8/2017.
 */

public class Product {

    @Setter @Getter
    @SerializedName("price")
    private double price;

    @Setter @Getter
    @SerializedName("name")
    private String name;

    @Setter @Getter
    @SerializedName("images")
    private List<Image> images;
}
