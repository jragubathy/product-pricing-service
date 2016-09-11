/**
 * 
 */
package com.sapient.business.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.sapient.business.client.PricingServiceClient;
import com.sapient.business.dao.ProductPriceDao;
import com.sapient.business.exception.ProductPriceException;
import com.sapient.business.service.ProductPriceService;
import com.sapient.business.vo.response.ProductPriceJsonResponse;
import com.sapient.business.vo.response.ProductPriceResponse;

/**
 * @author Ragubathy Jayaraju
 *
 */
@Service
public class ProductPriceServiceImpl implements ProductPriceService {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceServiceImpl.class);

    @Autowired
    private ProductPriceDao productPriceDao;

    @Autowired
    private PricingServiceClient pricingServiceClient;

    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    private boolean isProductExist(String productName) throws Exception {

        try {

            return productPriceDao.isProductExist(productName);

        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage());
            throw e;
        }
    }

    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    public ProductPriceResponse getProductDetails(String productName) throws Exception {

        ProductPriceResponse productCatalogResponse = new ProductPriceResponse();
        List<ProductPriceJsonResponse> responseList = null;
        JSONObject jsonObject;

        try {

            if (isProductExist(productName)) {

                Map<String, ProductPriceJsonResponse> productPriceMap = productPriceDao.getProductDetails(productName);

                ResponseEntity<String> response = pricingServiceClient.getProductDetails(productName);

                if (response != null && StringUtils.hasText(response.getBody())) {

                    try {
                        jsonObject = new JSONObject(response.getBody());
                        if (jsonObject.has("items")) {
                            JSONArray myResponse = jsonObject.getJSONArray("items");

                            if (myResponse.length() > 0) {
                                String prductName = myResponse.getJSONObject(0).getString("productName");
                                String prductType = myResponse.getJSONObject(0).getString("productType");
                                String prductDesc = myResponse.getJSONObject(0).getString("productDescription");
                                ProductPriceJsonResponse productPriceJsonResponse = productPriceMap.get(prductName);
                                productPriceJsonResponse.setProductDescription(prductDesc);
                                productPriceJsonResponse.setProductType(prductType);
                                responseList = new ArrayList<ProductPriceJsonResponse>(1);
                                responseList.add(productPriceJsonResponse);
                                productCatalogResponse.setItems((responseList));
                            }
                        }
                    } catch (JSONException e) {
                        throw new ProductPriceException("JSON Exception. Please try again later.");
                    }
                }
            } else {
                throw new ProductPriceException("Products Not Found in the system");
            }

        } catch (ProductPriceException pce) {
            LOGGER.error(pce.getLocalizedMessage(), pce);
            throw pce;
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            throw e;
        }

        return productCatalogResponse;
    }

}
