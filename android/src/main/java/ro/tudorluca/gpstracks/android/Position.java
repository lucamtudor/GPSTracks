package ro.tudorluca.gpstracks.android;

import java.util.Date;

/**
 * Created by Tudor Luca on 12/12/14.
 */
public class Position {

    private long mUserId;
    private String mLatitude;
    private String mLongitude;
    private Date mDate;

    public Position() {
    }

    public Position(long userId, String latitude, String longitude, Date date) {
        mUserId = userId;
        mLatitude = latitude;
        mLongitude = longitude;
        mDate = date;
    }

    public long getUserId() {
        return mUserId;
    }

    public void setUserId(long userId) {
        mUserId = userId;
    }

    public String getLatitude() {
        return mLatitude;
    }

    public void setLatitude(String latitude) {
        mLatitude = latitude;
    }

    public String getLongitude() {
        return mLongitude;
    }

    public void setLongitude(String longitude) {
        mLongitude = longitude;
    }

    public Date getDate() {
        return mDate;
    }

    public void setDate(Date date) {
        mDate = date;
    }
}
