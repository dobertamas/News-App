
package com.example.android.newsapp;

import javax.annotation.Generated;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Result implements Parcelable
{

    @SerializedName("id")
    @Expose
    private String id;
    @SerializedName("type")
    @Expose
    private String type;
    @SerializedName("sectionId")
    @Expose
    private String sectionId;
    @SerializedName("sectionName")
    @Expose
    private String sectionName;
    @SerializedName("webPublicationDate")
    @Expose
    private String webPublicationDate;
    @SerializedName("webTitle")
    @Expose
    private String webTitle;
    @SerializedName("webUrl")
    @Expose
    private String webUrl;
    @SerializedName("apiUrl")
    @Expose
    private String apiUrl;
    @SerializedName("isHosted")
    @Expose
    private boolean isHosted;
    public final static Parcelable.Creator<Result> CREATOR = new Creator<Result>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Result createFromParcel(Parcel in) {
            Result instance = new Result();
            instance.id = ((String) in.readValue((String.class.getClassLoader())));
            instance.type = ((String) in.readValue((String.class.getClassLoader())));
            instance.sectionId = ((String) in.readValue((String.class.getClassLoader())));
            instance.sectionName = ((String) in.readValue((String.class.getClassLoader())));
            instance.webPublicationDate = ((String) in.readValue((String.class.getClassLoader())));
            instance.webTitle = ((String) in.readValue((String.class.getClassLoader())));
            instance.webUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.apiUrl = ((String) in.readValue((String.class.getClassLoader())));
            instance.isHosted = ((boolean) in.readValue((boolean.class.getClassLoader())));
            return instance;
        }

        public Result[] newArray(int size) {
            return (new Result[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The id
     */
    public String getId() {
        return id;
    }

    /**
     * 
     * @param id
     *     The id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * 
     * @return
     *     The type
     */
    public String getType() {
        return type;
    }

    /**
     * 
     * @param type
     *     The type
     */
    public void setType(String type) {
        this.type = type;
    }

    /**
     * 
     * @return
     *     The sectionId
     */
    public String getSectionId() {
        return sectionId;
    }

    /**
     * 
     * @param sectionId
     *     The sectionId
     */
    public void setSectionId(String sectionId) {
        this.sectionId = sectionId;
    }

    /**
     * 
     * @return
     *     The sectionName
     */
    public String getSectionName() {
        return sectionName;
    }

    /**
     * 
     * @param sectionName
     *     The sectionName
     */
    public void setSectionName(String sectionName) {
        this.sectionName = sectionName;
    }

    /**
     * 
     * @return
     *     The webPublicationDate
     */
    public String getWebPublicationDate() {
        return webPublicationDate;
    }

    /**
     * 
     * @param webPublicationDate
     *     The webPublicationDate
     */
    public void setWebPublicationDate(String webPublicationDate) {
        this.webPublicationDate = webPublicationDate;
    }

    /**
     * 
     * @return
     *     The webTitle
     */
    public String getWebTitle() {
        return webTitle;
    }

    /**
     * 
     * @param webTitle
     *     The webTitle
     */
    public void setWebTitle(String webTitle) {
        this.webTitle = webTitle;
    }

    /**
     * 
     * @return
     *     The webUrl
     */
    public String getWebUrl() {
        return webUrl;
    }

    /**
     * 
     * @param webUrl
     *     The webUrl
     */
    public void setWebUrl(String webUrl) {
        this.webUrl = webUrl;
    }

    /**
     * 
     * @return
     *     The apiUrl
     */
    public String getApiUrl() {
        return apiUrl;
    }

    /**
     * 
     * @param apiUrl
     *     The apiUrl
     */
    public void setApiUrl(String apiUrl) {
        this.apiUrl = apiUrl;
    }

    /**
     * 
     * @return
     *     The isHosted
     */
    public boolean isIsHosted() {
        return isHosted;
    }

    /**
     * 
     * @param isHosted
     *     The isHosted
     */
    public void setIsHosted(boolean isHosted) {
        this.isHosted = isHosted;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(id);
        dest.writeValue(type);
        dest.writeValue(sectionId);
        dest.writeValue(sectionName);
        dest.writeValue(webPublicationDate);
        dest.writeValue(webTitle);
        dest.writeValue(webUrl);
        dest.writeValue(apiUrl);
        dest.writeValue(isHosted);
    }

    public int describeContents() {
        return  0;
    }

}
