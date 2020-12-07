////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business.exception;

public class RestaurantBillException extends Throwable{
    public String errorMsg;

    public RestaurantBillException(String errorMsg){
        this.errorMsg = errorMsg;
    }

    public String getErrorMsg() {
        return errorMsg;
    }
}
