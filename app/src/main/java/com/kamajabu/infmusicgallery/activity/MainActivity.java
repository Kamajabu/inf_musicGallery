package com.kamajabu.infmusicgallery.activity;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;

import com.kamajabu.infmusicgallery.R;
import com.kamajabu.infmusicgallery.adapter.GalleryAdapter;
import com.kamajabu.infmusicgallery.model.Image;
import com.kamajabu.infmusicgallery.musicmanager.SlideshowMusicFragment;

import java.util.ArrayList;

import static android.support.v7.widget.RecyclerView.SCROLL_STATE_IDLE;

public class MainActivity extends AppCompatActivity implements ConfigData {

    private String TAG = MainActivity.class.getSimpleName();
    private static final String endpoint = "http://api.androidhive.info/json/glide.json";
    private ArrayList<Image> images;
    private ProgressDialog pDialog;
    private GalleryAdapter mAdapter;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        recyclerView = (RecyclerView) findViewById(R.id.recycler_view);

        pDialog = new ProgressDialog(this);
        images = new ArrayList<>();
        mAdapter = new GalleryAdapter(getApplicationContext(), images) {
        };

        getSupportActionBar().hide();
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(getApplicationContext(), 2);

        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(mAdapter);

        recyclerView.addOnScrollListener(
                new RecyclerView.OnScrollListener() {
                    @Override
                    public void onScrollStateChanged(RecyclerView recyclerView, int state) {
                        super.onScrollStateChanged(recyclerView, state);

                        if (state == SCROLL_STATE_IDLE) {
                            GridLayoutManager glm = ((GridLayoutManager) recyclerView.getLayoutManager());
                            int lastVisiblePosition = glm.findLastVisibleItemPosition();
                            recyclerView.smoothScrollToPosition(lastVisiblePosition);
                        }
                    }
                }
        );

        recyclerView.addOnItemTouchListener(
                new GalleryAdapter.RecyclerTouchListener(
                        getApplicationContext(),
                        recyclerView, new GalleryAdapter.ClickListener() {
                    @Override
                    public void onClick(View view, int position) {
                        Bundle bundle = new Bundle();
                        bundle.putSerializable("images", images);
                        bundle.putInt("position", position);

                        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                        ft.setCustomAnimations(R.anim.anim_slide_in_top, R.anim.anim_slide_out_bottom);

                        SlideshowMusicFragment newFragment = SlideshowMusicFragment.newInstance();
                        newFragment.setArguments(bundle);
                        newFragment.show(ft, "slideshow");
                    }

                    @Override
                    public void onLongClick(View view, int position) {

                    }
                }

                ));

        populateModel();
    }

    private void populateModel() {
        images.clear();

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

        mAdapter.notifyDataSetChanged();
    }
}