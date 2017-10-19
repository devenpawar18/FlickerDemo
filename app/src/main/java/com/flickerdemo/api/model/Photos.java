package com.flickerdemo.api.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Photos {
    @SerializedName("page")
    private int mPage;
    @SerializedName("pages")
    private int mPages;
    @SerializedName("perpage")
    private int mPerPage;
    @SerializedName("total")
    private String mTotal;

    @SerializedName("photo")
    private List<Photo> mPhotos;

    public int getPage() {
        return this.mPage;
    }

    public int getPages() {
        return this.mPages;
    }

    public int getPerPage() {
        return this.mPerPage;
    }

    public String getTotal() {
        return this.mTotal;
    }

    public List<Photo> getPhotos() {
        return this.mPhotos;
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) {
            return true;
        }

        if (pO == null || getClass() != pO.getClass()) {
            return false;
        }

        final Photos photos = (Photos) pO;

        if (this.mPage != photos.mPage) {
            return false;
        }
        if (this.mPages != photos.mPages) {
            return false;
        }
        if (this.mPerPage != photos.mPerPage) {
            return false;
        }
        if (this.mTotal != null ? !this.mTotal.equals(photos.mTotal) : photos.mTotal != null) {
            return false;
        }
        return this.mPhotos != null ? this.mPhotos.equals(photos.mPhotos) : photos.mPhotos == null;
    }

    @Override
    public int hashCode() {
        int result = this.mPage;
        result = 31 * result + this.mPages;
        result = 31 * result + this.mPerPage;
        result = 31 * result + (this.mTotal != null ? this.mTotal.hashCode() : 0);
        result = 31 * result + (this.mPhotos != null ? this.mPhotos.hashCode() : 0);
        return result;
    }
}
