package com.kamajabu.infmusicgallery.model;

import com.kamajabu.infmusicgallery.R;

import java.util.ArrayList;

/**
 * Created by Kamil Buczel on 24.04.2017.
 */

public class DataLoader {

    static public ArrayList loadData() {
        ArrayList<Image> images = new ArrayList<>();

        images.add(new Image("cat", R.drawable.cat1));
        images.add(new Image("Another cat", R.drawable.cat2));
        images.add(new Image("Yet another cat", R.drawable.cat3));

        images.add(new Image("Oh look! A cat.", R.drawable.cat4));
        images.add(new Image("Guess what.", R.drawable.cat5));
        images.add(new Image("A fish? Nah, a cat.", R.drawable.cat6));

        images.add(new Image("It could be a cat.", R.drawable.food1));
        images.add(new Image("Food.", R.drawable.food2));
        images.add(new Image("Hungry yet?", R.drawable.food3));

        images.add(new Image("Tomato potato.", R.drawable.food4));
        images.add(new Image("Bird.", R.drawable.nature1));
        images.add(new Image("Forest", R.drawable.nature2));

        return images;
    }
}
