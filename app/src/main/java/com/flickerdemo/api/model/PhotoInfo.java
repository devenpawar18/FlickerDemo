package com.flickerdemo.api.model;

import com.google.gson.annotations.SerializedName;

public class PhotoInfo {
    @SerializedName("photos")
    private Photos mPhotos;

    public Photos getPhotos() {
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

        final PhotoInfo photoInfo = (PhotoInfo) pO;

        return this.mPhotos != null ? this.mPhotos.equals(photoInfo.mPhotos) : photoInfo.mPhotos == null;
    }

    @Override
    public int hashCode() {
        return this.mPhotos != null ? this.mPhotos.hashCode() : 0;
    }
}
