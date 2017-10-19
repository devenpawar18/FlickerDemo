package com.flickerdemo.api.model;

import com.google.gson.annotations.SerializedName;

import org.parceler.Parcel;

@Parcel
public class Photo {
    @SerializedName("id")
    String mId;
    @SerializedName("owner")
    String mOwner;
    @SerializedName("secret")
    String mSecret;
    @SerializedName("server")
    String mServer;
    @SerializedName("farm")
    int mFarm;
    @SerializedName("title")
    String mTitle;
    @SerializedName("ispublic")
    int mPublic;
    @SerializedName("isfriend")
    int mFriend;
    @SerializedName("isfamily")
    int mFamily;

    public String getId() {
        return this.mId;
    }

    public String getOwner() {
        return this.mOwner;
    }

    public String getSecret() {
        return this.mSecret;
    }

    public String getServer() {
        return this.mServer;
    }

    public int getFarm() {
        return this.mFarm;
    }

    public String getTitle() {
        return this.mTitle;
    }

    public int isPublic() {
        return this.mPublic;
    }

    public int isFriend() {
        return this.mFriend;
    }

    public int isFamily() {
        return this.mFamily;
    }

    @Override
    public boolean equals(final Object pO) {
        if (this == pO) {
            return true;
        }

        if (pO == null || getClass() != pO.getClass()) {
            return false;
        }

        final Photo photo = (Photo) pO;

        if (this.mFarm != photo.mFarm) {
            return false;
        }
        if (this.mPublic != photo.mPublic) {
            return false;
        }
        if (this.mFriend != photo.mFriend) {
            return false;
        }
        if (this.mFamily != photo.mFamily) {
            return false;
        }
        if (this.mId != null ? !mId.equals(photo.mId) : photo.mId != null) {
            return false;
        }
        if (this.mOwner != null ? !this.mOwner.equals(photo.mOwner) : photo.mOwner != null) {
            return false;
        }
        if (this.mSecret != null ? !this.mSecret.equals(photo.mSecret) : photo.mSecret != null) {
            return false;
        }
        if (this.mServer != null ? !this.mServer.equals(photo.mServer) : photo.mServer != null) {
            return false;
        }
        return mTitle != null ? mTitle.equals(photo.mTitle) : photo.mTitle == null;
    }

    @Override
    public int hashCode() {
        int result = this.mId != null ? this.mId.hashCode() : 0;
        result = 31 * result + (this.mOwner != null ? this.mOwner.hashCode() : 0);
        result = 31 * result + (this.mSecret != null ? this.mSecret.hashCode() : 0);
        result = 31 * result + (this.mServer != null ? this.mServer.hashCode() : 0);
        result = 31 * result + this.mFarm;
        result = 31 * result + (this.mTitle != null ? this.mTitle.hashCode() : 0);
        result = 31 * result + this.mPublic;
        result = 31 * result + this.mFriend;
        result = 31 * result + this.mFamily;
        return result;
    }
}
