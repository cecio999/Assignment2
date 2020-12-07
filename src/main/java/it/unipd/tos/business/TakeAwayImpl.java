////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;

import java.util.Date;
import java.util.List;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import it.unipd.tos.business.exception.RestaurantBillException;

class TakeAwayImpl implements TakeAwayBill{
    public double getOrderPrice(List<MenuItem> itemsOrdered, User user, Date time)
            throws RestaurantBillException {
        double sum = 0;
        for (MenuItem i: itemsOrdered) {
            sum += i.getPrice();
        }
        return sum;
    }
};
