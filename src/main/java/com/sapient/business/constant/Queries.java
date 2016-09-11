/**
 * 
 */
package com.sapient.business.constant;

/**
 * @author Ragubathy Jayaraju
 *
 */
public interface Queries {

    String PRODUCT_EXISTS = "select count(*) from PRODUCT_PRICE where product_name = ?";

    String GET_PRODUCT = "SELECT product_name, product_price, created_on, created_by, updated_on, updated_by"
            + " from PRODUCT_PRICE where product_name = ?";

}
