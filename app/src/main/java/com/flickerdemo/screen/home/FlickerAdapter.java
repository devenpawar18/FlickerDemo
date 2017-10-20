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

public class FlickerAdapter extends RecyclerView.Adapter<FlickerAdapter.FlickrViewHolder> {
    private static List<Photo> mPhotos;
    private int mRowLayout;
    private Context mContext;
    private static OnListItemClickListener mOnListItemClickListener;

    public static class FlickrViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.image_flickr)
        ImageView mFlickrImage;

        public FlickrViewHolder(View pView) {
            super(pView);
            ButterKnife.bind(this, pView);

            pView.setOnClickListener(this);
        }

        @Override
        public void onClick(final View pView) {
            Photo item = mPhotos.get(this.getLayoutPosition());
            mOnListItemClickListener.onItemClick(item);
        }
    }

    public FlickerAdapter(List<Photo> pPhotos, int pRowLayout, Context pContext, final OnListItemClickListener pOnListItemClickListener) {
        mPhotos = pPhotos;
        this.mRowLayout = pRowLayout;
        this.mContext = pContext;
        mOnListItemClickListener = pOnListItemClickListener;
    }

    @Override
    public FlickerAdapter.FlickrViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        final View view = LayoutInflater.from(parent.getContext()).inflate(mRowLayout, parent, false);
        return new FlickrViewHolder(view);
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

    public interface OnListItemClickListener {
        void onItemClick(final Photo pPhoto);
    }
}