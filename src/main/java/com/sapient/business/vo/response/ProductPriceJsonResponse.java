/**
 * 
 */
package com.sapient.business.vo.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonRootName;

/**
 * @author Ragubathy Jayaraju
 *
 */
@JsonRootName(value = "product")
@JsonInclude(Include.NON_NULL)
public class ProductPriceJsonResponse {

    @JsonProperty
    private String productName;

    @JsonProperty
    private String productType;

    @JsonProperty
    private String productPrice;

    @JsonProperty
    private String productDescription;

    @JsonProperty
    private String createdBy;

    @JsonProperty
    private String createdOn;

    @JsonProperty
    private String modifiedBy;

    @JsonProperty
    private String modifiedOn;

    /**
     * @return the productName
     */
    public String getProductName() {
        return productName;
    }

    /**
     * @param productName
     *            the productName to set
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     * @return the productType
     */
    public String getProductType() {
        return productType;
    }

    /**
     * @param productType
     *            the productType to set
     */
    public void setProductType(String productType) {
        this.productType = productType;
    }

    /**
     * @return the productDescription
     */
    public String getProductDescription() {
        return productDescription;
    }

    /**
     * @param productDescription
     *            the productDescription to set
     */
    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    /**
     * @return the createdBy
     */
    public String getCreatedBy() {
        return createdBy;
    }

    /**
     * @param createdBy
     *            the createdBy to set
     */
    public void setCreatedBy(String createdBy) {
        this.createdBy = createdBy;
    }

    /**
     * @return the modifiedBy
     */
    public String getModifiedBy() {
        return modifiedBy;
    }

    /**
     * @param modifiedBy
     *            the modifiedBy to set
     */
    public void setModifiedBy(String modifiedBy) {
        this.modifiedBy = modifiedBy;
    }

    /**
     * @return the createdOn
     */
    public String getCreatedOn() {
        return createdOn;
    }

    /**
     * @param createdOn
     *            the createdOn to set
     */
    public void setCreatedOn(String createdOn) {
        this.createdOn = createdOn;
    }

    /**
     * @return the modifiedOn
     */
    public String getModifiedOn() {
        return modifiedOn;
    }

    /**
     * @param modifiedOn
     *            the modifiedOn to set
     */
    public void setModifiedOn(String modifiedOn) {
        this.modifiedOn = modifiedOn;
    }

    /**
     * @return the productPrice
     */
    public String getProductPrice() {
        return productPrice;
    }

    /**
     * @param productPrice
     *            the productPrice to set
     */
    public void setProductPrice(String productPrice) {
        this.productPrice = productPrice;
    }

}
