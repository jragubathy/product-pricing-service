/**
 * 
 */
package com.sapient.business.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;

import com.sapient.business.exception.ProductPriceException;

/**
 * @author Ragubathy Jayaraju
 *
 */
@Component
public class PricingServiceClient {

    private static final Logger LOGGER = LoggerFactory.getLogger(PricingServiceClient.class);

    private RestTemplate restTemplate;

    @Value("${product.catalog.service}")
    private String pricingServiceURL;

    /**
     * 
     * @param productName
     * @return
     * @throws Exception
     */
    public ResponseEntity<String> getProductDetails(String productName) throws Exception {

        ResponseEntity<String> response = null;

        String url = pricingServiceURL + "/products/getProductDetails?productName=" + productName;

        final HttpEntity<Object> request = new HttpEntity<Object>(productName, getHttpheaders());

        try {

            response = getRestTemplate().exchange(url, HttpMethod.GET, request, String.class);

            if (response == null || response.getStatusCode().value() != HttpStatus.OK.value()) {
                throw new ProductPriceException("Not able to retrieve Product details");
            }

            if (response != null && StringUtils.hasText(response.getBody())) {
                return response;
            }

        } catch (ProductPriceException ppe) {
            LOGGER.error(ppe.getLocalizedMessage(), ppe);
            throw ppe;
        } catch (Exception e) {
            LOGGER.error(e.getLocalizedMessage(), e);
            throw e;
        }

        return response;

    }

    /**
     * 
     * @return
     */
    private HttpHeaders getHttpheaders() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON.toString());
        return httpHeaders;
    }

    /**
     * 
     * @return
     */
    private RestTemplate getRestTemplate() {
        if (restTemplate == null) {
            restTemplate = new RestTemplate();
        }
        return restTemplate;
    }

}
