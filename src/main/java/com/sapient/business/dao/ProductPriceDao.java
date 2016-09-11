/**
 * 
 */
package com.sapient.business.dao;

import java.util.Map;

import com.sapient.business.vo.response.ProductPriceJsonResponse;

/**
 * @author Ragubathy Jayaraju
 *
 */
public interface ProductPriceDao {

    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    boolean isProductExist(String productName) throws Exception;

    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    Map<String, ProductPriceJsonResponse> getProductDetails(String productName) throws Exception;

}
