
package com.example.android.newsapp;

import java.util.ArrayList;
import java.util.List;
import javax.annotation.Generated;
import javax.validation.Valid;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Generated("org.jsonschema2pojo")
public class Response_ implements Parcelable
{

    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("userTier")
    @Expose
    private String userTier;
    @SerializedName("total")
    @Expose
    private int total;
    @SerializedName("startIndex")
    @Expose
    private int startIndex;
    @SerializedName("pageSize")
    @Expose
    private int pageSize;
    @SerializedName("currentPage")
    @Expose
    private int currentPage;
    @SerializedName("pages")
    @Expose
    private int pages;
    @SerializedName("orderBy")
    @Expose
    private String orderBy;
    @SerializedName("results")
    @Expose
    @Valid
    private List<Result> results = new ArrayList<Result>();
    public final static Parcelable.Creator<Response_> CREATOR = new Creator<Response_>() {


        @SuppressWarnings({
            "unchecked"
        })
        public Response_ createFromParcel(Parcel in) {
            Response_ instance = new Response_();
            instance.status = ((String) in.readValue((String.class.getClassLoader())));
            instance.userTier = ((String) in.readValue((String.class.getClassLoader())));
            instance.total = ((int) in.readValue((int.class.getClassLoader())));
            instance.startIndex = ((int) in.readValue((int.class.getClassLoader())));
            instance.pageSize = ((int) in.readValue((int.class.getClassLoader())));
            instance.currentPage = ((int) in.readValue((int.class.getClassLoader())));
            instance.pages = ((int) in.readValue((int.class.getClassLoader())));
            instance.orderBy = ((String) in.readValue((String.class.getClassLoader())));
            in.readList(instance.results, (com.example.android.newsapp.Result.class.getClassLoader()));
            return instance;
        }

        public Response_[] newArray(int size) {
            return (new Response_[size]);
        }

    }
    ;

    /**
     * 
     * @return
     *     The status
     */
    public String getStatus() {
        return status;
    }

    /**
     * 
     * @param status
     *     The status
     */
    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 
     * @return
     *     The userTier
     */
    public String getUserTier() {
        return userTier;
    }

    /**
     * 
     * @param userTier
     *     The userTier
     */
    public void setUserTier(String userTier) {
        this.userTier = userTier;
    }

    /**
     * 
     * @return
     *     The total
     */
    public int getTotal() {
        return total;
    }

    /**
     * 
     * @param total
     *     The total
     */
    public void setTotal(int total) {
        this.total = total;
    }

    /**
     * 
     * @return
     *     The startIndex
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * 
     * @param startIndex
     *     The startIndex
     */
    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * 
     * @return
     *     The pageSize
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 
     * @param pageSize
     *     The pageSize
     */
    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 
     * @return
     *     The currentPage
     */
    public int getCurrentPage() {
        return currentPage;
    }

    /**
     * 
     * @param currentPage
     *     The currentPage
     */
    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    /**
     * 
     * @return
     *     The pages
     */
    public int getPages() {
        return pages;
    }

    /**
     * 
     * @param pages
     *     The pages
     */
    public void setPages(int pages) {
        this.pages = pages;
    }

    /**
     * 
     * @return
     *     The orderBy
     */
    public String getOrderBy() {
        return orderBy;
    }

    /**
     * 
     * @param orderBy
     *     The orderBy
     */
    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }

    /**
     * 
     * @return
     *     The results
     */
    public List<Result> getResults() {
        return results;
    }

    /**
     * 
     * @param results
     *     The results
     */
    public void setResults(List<Result> results) {
        this.results = results;
    }

    public void writeToParcel(Parcel dest, int flags) {
        dest.writeValue(status);
        dest.writeValue(userTier);
        dest.writeValue(total);
        dest.writeValue(startIndex);
        dest.writeValue(pageSize);
        dest.writeValue(currentPage);
        dest.writeValue(pages);
        dest.writeValue(orderBy);
        dest.writeList(results);
    }

    public int describeContents() {
        return  0;
    }

}
