/**
 * 
 */
package com.sapient.business.service;

import com.sapient.business.vo.response.ProductPriceResponse;

/**
 * @author Ragubathy Jayaraju
 *
 */
public interface ProductPriceService {

    
    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    ProductPriceResponse getProductDetails(String productName) throws Exception;
    
}
