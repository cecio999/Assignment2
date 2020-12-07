////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import it.unipd.tos.business.exception.RestaurantBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.User;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;

public class TakeAwayImplTest {

    @SuppressWarnings("checkstyle:LineLength")
    @Test
    public void testSimpleSumOfMenuItems(){
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.types.Bevanda,"Coca Cola",0.5));
        items.add(new MenuItem(MenuItem.types.Budino, "Pinguino", 1.0));
        items.add(new MenuItem(MenuItem.types.Gelato, "Coppa Nafta", 5.75));
        User user = new User("Francesco", "Dallan", 21);
        try {
 assertEquals(7.25, takeAwayImpl.getOrderPrice(items, user, new Date(500)),0.2);
        } catch (RestaurantBillException e){
            e.getErrorMsg();
        }
    }
        
    @Test
    public void testSimpleSumWithNoElements(){
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        User user = new User("Francesco", "Dallan", 21);
        try {
 assertEquals(0, takeAwayImpl.getOrderPrice(items, user, new Date(500)),0.2);
        } catch (RestaurantBillException e){
            e.getErrorMsg();
        }
    }
}
