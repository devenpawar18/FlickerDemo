package com.flickerdemo.screen.home;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.flickerdemo.R;
import com.flickerdemo.api.model.Photo;
import com.flickerdemo.api.model.PhotoInfo;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * HomeView - A Fragment which implements the HomeContract.View interface.
 */
public class HomeView extends Fragment implements HomeContract.View, FlickerAdapter.OnListItemClickListener {
    @Inject
    protected HomePresenter mPresenter;

    @BindView(R.id.photo_recycler_view)
    protected RecyclerView mRecyclerView;

    private FlickerAdapter mAdapter;
    private List<Photo> mPhotos = new ArrayList<>();

    @Inject
    public HomeView() {
        Timber.d("Reached");
    }

    @Override
    public void onCreate(@Nullable final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        ((HomeActivity) getActivity()).getHomeComponent().inject(this);
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable final ViewGroup container, @Nullable final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        ButterKnife.bind(this, view);

        GridLayoutManager gridLayoutManager = new GridLayoutManager(getActivity(), 3);
        this.mRecyclerView.setLayoutManager(gridLayoutManager);
        this.mAdapter = new FlickerAdapter(this.mPhotos, R.layout.fragment_home_flicker_list_item, this.getHomeActivity());
        this.mAdapter.setOnListClickListener(this);
        this.mRecyclerView.setAdapter(this.mAdapter);

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        this.mPresenter.takeView(this);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mPresenter.dropView();
    }

    @Override
    public Context getContext() {
        return this.getView().getContext();
    }

    @Override
    public HomeActivity getHomeActivity() {
        return (HomeActivity) getActivity();
    }

    @Override
    public void onItemClick(final View pView) {
        final int itemPosition = this.mRecyclerView.getChildLayoutPosition(pView);
        Photo item = mPhotos.get(itemPosition);
        Toast.makeText(this.getView().getContext(), "Position clicked is " + itemPosition + " with name " + item.getTitle(), Toast.LENGTH_SHORT).show();
    }

    @Override
    public void updateView(final PhotoInfo pPhotoInfo) {
        final List<Photo> photos = pPhotoInfo.getPhotos().getPhotos();
        if (photos != null && !photos.isEmpty()) {
            this.mPhotos.addAll(photos);
            this.mAdapter.notifyDataSetChanged();
        }
    }
}