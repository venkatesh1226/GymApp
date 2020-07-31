package com.example.gymapp;

import android.os.Parcel;
import android.os.Parcelable;

public class Training implements Parcelable {
    Name name;
    Boolean isAcomplished;
    Integer minutes;
    String Day;

    public Training(Name name, Boolean isAcomplished, Integer minutes, String day) {
        this.name = name;
        this.isAcomplished = isAcomplished;
        this.minutes = minutes;
        Day = day;
    }

    protected Training(Parcel in) {
        name = in.readParcelable(Name.class.getClassLoader());
        byte tmpIsAcomplished = in.readByte();
        isAcomplished = tmpIsAcomplished == 0 ? null : tmpIsAcomplished == 1;
        if (in.readByte() == 0) {
            minutes = null;
        } else {
            minutes = in.readInt();
        }
        Day = in.readString();
    }

    public static final Creator<Training> CREATOR = new Creator<Training>() {
        @Override
        public Training createFromParcel(Parcel in) {
            return new Training(in);
        }

        @Override
        public Training[] newArray(int size) {
            return new Training[size];
        }
    };

    public Name getName() {
        return name;
    }

    public Boolean getAcomplished() {
        return isAcomplished;
    }

    public Integer getMinutes() {
        return minutes;
    }

    public String getDay() {
        return Day;
    }

    public void setName(Name name) {
        this.name = name;
    }

    public void setAcomplished(Boolean acomplished) {
        isAcomplished = acomplished;
    }

    public void setMinutes(Integer minutes) {
        this.minutes = minutes;
    }

    public void setDay(String day) {
        Day = day;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeParcelable(name, i);
        parcel.writeByte((byte) (isAcomplished == null ? 0 : isAcomplished ? 1 : 2));
        if (minutes == null) {
            parcel.writeByte((byte) 0);
        } else {
            parcel.writeByte((byte) 1);
            parcel.writeInt(minutes);
        }
        parcel.writeString(Day);
    }
}
