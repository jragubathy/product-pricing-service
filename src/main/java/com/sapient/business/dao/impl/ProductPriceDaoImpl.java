/**
 * 
 */
package com.sapient.business.dao.impl;

import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;

import com.sapient.business.constant.Queries;
import com.sapient.business.dao.BaseDao;
import com.sapient.business.dao.ProductPriceDao;
import com.sapient.business.vo.response.ProductPriceJsonResponse;

/**
 * @author Ragubathy Jayaraju
 *
 */
@Repository
public class ProductPriceDaoImpl extends BaseDao implements ProductPriceDao {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceDaoImpl.class);

    
    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    public boolean isProductExist(String productName) throws Exception {

        try {
            Integer list = jdbcTemplate.queryForObject(Queries.PRODUCT_EXISTS, Integer.class, productName);
            if (list > 0) {
                return true;
            }
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            throw e;
        }
        return false;
    }

    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    public Map<String, ProductPriceJsonResponse> getProductDetails(String productName) throws Exception {

        Map<String, ProductPriceJsonResponse> productPriceMap = new HashMap<String, ProductPriceJsonResponse>();
        ProductPriceJsonResponse productPriceJsonResponse = null;
        SimpleDateFormat sfd = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");

        try {
            SqlRowSet results = jdbcTemplate.queryForRowSet(Queries.GET_PRODUCT, productName);
            while (results.next()) {
                productPriceJsonResponse = new ProductPriceJsonResponse();
                productPriceJsonResponse.setProductName(results.getString("product_name"));
                productPriceJsonResponse.setProductPrice(results.getString("product_price"));
                productPriceJsonResponse.setCreatedBy(results.getString("created_by"));
                productPriceJsonResponse.setCreatedOn(sfd.format(results.getTimestamp("created_on")));
                productPriceJsonResponse.setModifiedBy(results.getString("updated_by"));
                productPriceJsonResponse.setModifiedOn(sfd.format(results.getTimestamp("updated_on")));
                productPriceMap.put(productPriceJsonResponse.getProductName(), productPriceJsonResponse);
            }
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            throw e;
        }
        return productPriceMap;
    }

}
