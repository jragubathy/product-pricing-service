/**
 * 
 */
package com.sapient.business.controller;

import org.hibernate.validator.constraints.NotEmpty;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.sapient.business.constant.AppConstant;
import com.sapient.business.exception.ProductPriceException;
import com.sapient.business.service.ProductPriceService;
import com.sapient.business.util.ResponseUtil;
import com.sapient.business.vo.ServiceResponseVO;
import com.sapient.business.vo.response.ProductPriceResponse;

/**
 * @author Ragubathy Jayaraju
 *
 */
@RestController
@RequestMapping(value = AppConstant.PRODUCT, consumes = { MediaType.APPLICATION_JSON_VALUE }, produces = { MediaType.APPLICATION_JSON_VALUE })
public class ProductPriceController {

    private static final Logger LOGGER = LoggerFactory.getLogger(ProductPriceController.class);

    @Autowired
    ProductPriceService productPriceService;

    /**
     * 
     * @param productType
     * @return
     */
    @RequestMapping(value = AppConstant.PRODUCT_PRICE, method = RequestMethod.GET)
    public HttpEntity<? extends Object> getProductDetails(@NotEmpty @RequestParam("productName") String productName) {

        ProductPriceResponse productCatalogResponse = null;

        try {

            productCatalogResponse = productPriceService.getProductDetails(productName);

        } catch (ProductPriceException e) {
            LOGGER.error(e.getLocalizedMessage());
            return new ResponseEntity<ServiceResponseVO>(ResponseUtil.mapServiceResponse(true, e.getLocalizedMessage(),
                    null), HttpStatus.OK);
        } catch (Exception e) {
            LOGGER.error("The operation could not be completed ..");
            return new ResponseEntity<String>(HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<ServiceResponseVO>(ResponseUtil.mapServiceResponse(true, "Successfull Retrieved",
                productCatalogResponse.getItems()), HttpStatus.OK);
    }

    
}
