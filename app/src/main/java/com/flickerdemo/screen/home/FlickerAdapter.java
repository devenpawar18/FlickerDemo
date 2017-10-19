package com.flickerdemo.screen.home;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.flickerdemo.api.model.Photo;
import com.flickerdemo.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlickerAdapter extends RecyclerView.Adapter<FlickerAdapter.FlickrViewHolder> implements View.OnClickListener {
    private List<Photo> mPhotos;
    private int mRowLayout;
    private Context mContext;
    private RecyclerView mRecyclerView;

    private OnListItemClickListener mOnListItemClickListener;

    public static class FlickrViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.image_flickr)
        ImageView mFlickrImage;

        public FlickrViewHolder(View pView) {
            super(pView);
            ButterKnife.bind(this, pView);
        }
    }

    public FlickerAdapter(RecyclerView pRecyclerView, List<Photo> mPhotos, int rowLayout, Context pContext) {
        this.mRecyclerView = pRecyclerView;
        this.mPhotos = mPhotos;
        this.mRowLayout = rowLayout;
        this.mContext = pContext;
    }

    @Override
    public FlickerAdapter.FlickrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        view.setOnClickListener(this);
        return new FlickrViewHolder(view);
    }

    @Override
    public void onClick(final View v) {
        final int itemPosition = this.mRecyclerView.getChildLayoutPosition(v);
        Photo item = mPhotos.get(itemPosition);
        this.mOnListItemClickListener.onItemClick(item, itemPosition);
    }

    @Override
    public void onBindViewHolder(FlickrViewHolder holder, final int position) {
        final Photo photo = mPhotos.get(position);
        String url = mContext.getResources().getString(R.string.flickr_image_url, String.valueOf(photo.getFarm()), photo.getServer(), photo.getId(), photo.getSecret());
        Glide.with(mContext).load(url).centerCrop().placeholder(R.drawable.flickr_photo).crossFade().into(holder.mFlickrImage);
    }

    @Override
    public int getItemCount() {
        return mPhotos.size();
    }

    public void setOnListClickListener(OnListItemClickListener pOnListItemClickListener) {
        this.mOnListItemClickListener = pOnListItemClickListener;
    }

    public interface OnListItemClickListener {
        void onItemClick(Photo pPhoto, int position);
    }
}