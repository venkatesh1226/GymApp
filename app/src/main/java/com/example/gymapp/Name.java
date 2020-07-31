package com.example.gymapp;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable;

public class Name implements Parcelable {
   private String imageUrl;
    private String name;
    private String description;

    public Name(String imageUrl, String name, String description) {
        this.imageUrl = imageUrl;
        this.name = name;

        this.description = description;
    }

    protected Name(Parcel in) {
        imageUrl = in.readString();
        name = in.readString();
        description = in.readString();
    }

    public static final Creator<Name> CREATOR = new Creator<Name>() {
        @Override
        public Name createFromParcel(Parcel in) {
            return new Name(in);
        }

        @Override
        public Name[] newArray(int size) {
            return new Name[size];
        }
    };

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setName(String name) {
        this.name = name;
    }



    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getName() {
        return name;
    }



    public String getDescription() {
        return description;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(imageUrl);
        parcel.writeString(name);
        parcel.writeString(description);
    }
}
