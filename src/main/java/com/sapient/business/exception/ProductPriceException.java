/**
 * 
 */
package com.sapient.business.exception;

/**
 * @author Ragubathy Jayaraju
 *
 */
public class ProductPriceException extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = 1L;

    public ProductPriceException(String message) {
        super(message);
    }

    public ProductPriceException(Throwable originalException) {
        super(originalException);
    }

    public ProductPriceException(String message, Throwable originalException) {
        super(message, originalException);
    }

}
