package cn.itrip.search.beans.vo.hote;

import org.apache.solr.client.solrj.beans.Field;

public class ItripHotelVO {

    @Field("id")
        private Long id;

    @Field("hotelName")
    private String hotelName;

    @Field("address")
    private String address;

    @Field("hotelLevel")
    private Integer hotelLevel;

    @Field("redundantCityName")
    private String redundantCityName;

    @Field("redundantProvinceName")
    private String redundantProvinceName;

    @Field("redundantCountryName")
    private String redundantCountryName;

    @Field("maxPrice")
    private Double maxPrice;

    @Field("minPrice")
    private Double minPrice;

    @Field("extendPropertyNames")
    private String extendPropertyNames;

    @Field("extendPropertyPics")
    private String extendPropertyPics;

    @Field("tradingAreaNames")
    private String tradingAreaNames;

    @Field("featureNames")
    private String featureNames;

    @Field("isOkCount")
    private Integer isOkCount;
    @Field("commentCount")
    private Integer commentCount;
    @Field("avgScore")
    private Double avgScore;

    @Field("imgUrl")
    private String imgUrl;

    public void setId(Long id) {
        this.id = id;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setHotelLevel(Integer hotelLevel) {
        this.hotelLevel = hotelLevel;
    }

    public void setRedundantCityName(String redundantCityName) {
        this.redundantCityName = redundantCityName;
    }

    public void setRedundantProvinceName(String redundantProvinceName) {
        this.redundantProvinceName = redundantProvinceName;
    }

    public void setRedundantCountryName(String redundantCountryName) {
        this.redundantCountryName = redundantCountryName;
    }

    public void setMaxPrice(Double maxPrice) {
        this.maxPrice = maxPrice;
    }

    public void setMinPrice(Double minPrice) {
        this.minPrice = minPrice;
    }

    public void setExtendPropertyNames(String extendPropertyNames) {
        this.extendPropertyNames = extendPropertyNames;
    }

    public void setExtendPropertyPics(String extendPropertyPics) {
        this.extendPropertyPics = extendPropertyPics;
    }

    public void setTradingAreaNames(String tradingAreaNames) {
        this.tradingAreaNames = tradingAreaNames;
    }

    public void setFeatureNames(String featureNames) {
        this.featureNames = featureNames;
    }

    public void setIsOkCount(Integer isOkCount) {
        this.isOkCount = isOkCount;
    }

    public void setCommentCount(Integer commentCount) {
        this.commentCount = commentCount;
    }

    public void setAvgScore(Double avgScore) {
        this.avgScore = avgScore;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public Long getId() {
        return id;
    }

    public String getHotelName() {
        return hotelName;
    }

    public String getAddress() {
        return address;
    }

    public Integer getHotelLevel() {
        return hotelLevel;
    }

    public String getRedundantCityName() {
        return redundantCityName;
    }

    public String getRedundantProvinceName() {
        return redundantProvinceName;
    }

    public String getRedundantCountryName() {
        return redundantCountryName;
    }

    public Double getMaxPrice() {
        return maxPrice;
    }

    public Double getMinPrice() {
        return minPrice;
    }

    public String getExtendPropertyNames() {
        return extendPropertyNames;
    }

    public String getExtendPropertyPics() {
        return extendPropertyPics;
    }

    public String getTradingAreaNames() {
        return tradingAreaNames;
    }

    public String getFeatureNames() {
        return featureNames;
    }

    public Integer getIsOkCount() {
        return isOkCount;
    }

    public Integer getCommentCount() {
        return commentCount;
    }

    public Double getAvgScore() {
        return avgScore;
    }

    public String getImgUrl() {
        return imgUrl;
    }

}
