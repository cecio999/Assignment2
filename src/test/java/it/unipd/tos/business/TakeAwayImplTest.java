////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.business;

import static org.junit.Assert.assertEquals;

import it.unipd.tos.business.exception.TakeAwayBillException;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.User;
import org.junit.Test;

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
 assertEquals(7.75, takeAwayImpl.getOrderPrice(items, user),0.0);
        } catch (TakeAwayBillException e){
            e.getErrorMsg();
        }
    }
        
    @Test
    public void testSimpleSumWithNoElements(){
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        User user = new User("Francesco", "Dallan", 21);
        try {
 assertEquals(0, takeAwayImpl.getOrderPrice(items, user),0.0);
        } catch (TakeAwayBillException e){
            e.getErrorMsg();
        }
    }

    @Test
    public void testOrderWithMoreThanFiveIceCreams(){
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.types.Gelato,"Bacio",0.5));
        items.add(new MenuItem(MenuItem.types.Gelato, "Pinguino", 1.0));
        items.add(new MenuItem(MenuItem.types.Gelato, "Coppa Nafta", 1.0));
        items.add(new MenuItem(MenuItem.types.Gelato, "Stracciatella", 1.0));
        items.add(new MenuItem(MenuItem.types.Gelato, "Vaniglia", 2.0));
        items.add(new MenuItem(MenuItem.types.Gelato, "Pistacchio", 1.0));
        User user = new User("Francesco", "Dallan", 21);
        try {
            assertEquals(6.75, takeAwayImpl.getOrderPrice(items, user),0.0);
        } catch (TakeAwayBillException e){
            e.getErrorMsg();
        }
    }

    @Test
    public void testTotalMoreThanFiftyWithIceCreamAndPudding(){
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.types.Gelato,"Coppa Costosa", 20));
        items.add(new MenuItem(MenuItem.types.Gelato, "Coppa Random", 20));
        items.add(new MenuItem(MenuItem.types.Budino, "Budino Costoso", 20));
        User user = new User("Francesco", "Dallan", 21);
        try {
            assertEquals(54, takeAwayImpl.getOrderPrice(items, user),0.0);
        } catch (TakeAwayBillException e){
            e.getErrorMsg();
        }
    }

    @Test
    public void testExceptionMessage(){
        TakeAwayBillException e = new TakeAwayBillException("Limite di elementi superato");
        assertEquals("Limite di elementi superato", e.getErrorMsg());
    }

    @Test(expected = TakeAwayBillException.class)
    public void testMoreThan30ElementsOrder() throws TakeAwayBillException{
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        for(int i = 0; i < 31; i++) {
            items.add(new MenuItem(MenuItem.types.Gelato, "Coppa Nutella", 6));
        }
        User user = new User("Francesco", "Dallan", 21);
        takeAwayImpl.getOrderPrice(items,user);
        }

    @Test
    public void testCommissionIfLessThan10(){
        TakeAwayImpl takeAwayImpl = new TakeAwayImpl();
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.types.Bevanda,"Aranciata",3));
        User user = new User("Francesco", "Dallan", 21);
        try {
            assertEquals(3.5, takeAwayImpl.getOrderPrice(items, user),0.0);
        } catch (TakeAwayBillException e){
            e.getErrorMsg();
        }
    }

    @Test
    public void testGiftTenOrders(){
        List<Order> orders = new ArrayList<Order>();
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.types.Bevanda,"Aranciata",3));
        User user1 = new User("Francesco","Dallan",21);
        User user2 = new User("Alice","Santo",12);
        User user3 = new User("Mattei","Martino",17);
        User user4 = new User("Ciao","Mondo",1);
        for(int i = 0; i < 20; i++) {
            User user = new User("User"+i,"User"+i,17);
            orders.add(new Order(items, 18, 3.5, user));
        }
        orders.add(new Order(items, 18, 3.5, user1));
        orders.add(new Order(items, 18, 3.5, user2));
        orders.add(new Order(items, 15, 3.5, user3));
        orders.add(new Order(items, 18, 3.5, user4));
        items.add(new MenuItem(MenuItem.types.Bevanda,"Coca Cola",2));
        orders.add(new Order(items, 18, 5.5, user1));
        orders.add(new Order(items, 14, 5.5, user2));
        orders.add(new Order(items, 18, 5.5, user3));
        orders.add(new Order(items, 16, 5.5, user4));
        TakeAwayImpl toTest = new TakeAwayImpl();
        toTest.giftTenOrders(orders);
        int countZero = 0;
        for (Order o: orders) {
            if(o.getPrice() == 0)
            {
                countZero++;
            }
        }
        assertEquals(10,countZero);
    }
    @Test
    public void testGiftLessThanTenOrders(){
        List<Order> orders = new ArrayList<Order>();
        List<MenuItem> items = new ArrayList<MenuItem>();
        items.add(new MenuItem(MenuItem.types.Bevanda,"Aranciata",3));
        for(int i = 0; i < 5; i++) {
            User user = new User("User"+i,"User"+i,17);
            orders.add(new Order(items, 18, 3.5, user));
        }
        TakeAwayImpl toTest = new TakeAwayImpl();
        toTest.giftTenOrders(orders);
        int countZero = 0;
        for (Order o: orders) {
            if(o.getPrice() == 0)
            {
                countZero++;
            }
        }
        assertEquals(5,countZero);
    }
}
