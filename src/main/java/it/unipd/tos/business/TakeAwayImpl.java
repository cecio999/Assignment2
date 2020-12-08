////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////

package it.unipd.tos.business;

import it.unipd.tos.model.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import it.unipd.tos.model.MenuItem;
import it.unipd.tos.model.Order;
import it.unipd.tos.model.User;
import it.unipd.tos.business.exception.TakeAwayBillException;

class TakeAwayImpl implements TakeAwayBill{

    public double addCommission(double sum, List<MenuItem>itemsOrdered) {
        if (sum < 10 && itemsOrdered.size()>0)
        {
            sum += 0.5;
        }
        return sum;
    }

    public double getOrderPrice(List<MenuItem> itemsOrdered, User user)
            throws TakeAwayBillException {
        if(itemsOrdered.size()>30)
        {
            throw new TakeAwayBillException("Limite di elementi superato");
        }
        double cheaperIceCream = Double.MAX_VALUE;
        int nIceCream = 0;
        int priceICP = 0;
        for (MenuItem i: itemsOrdered) {
            if(i.getItemType() == MenuItem.types.Gelato) {
                nIceCream++;
                priceICP += i.getPrice();
                if(i.getPrice()<cheaperIceCream) {
                    cheaperIceCream = i.getPrice();
                }
            } else if(i.getItemType() == MenuItem.types.Budino)
            {
                priceICP += i.getPrice();
            }
        }
        double sum = 0;
        for (MenuItem i: itemsOrdered) {
            sum += i.getPrice();
        }

        if(nIceCream>5)
        {
            sum -= cheaperIceCream/2;
        }

        if(priceICP > 50)
        {
            sum = sum - 0.1*sum;
        }

        sum = addCommission(sum,itemsOrdered);

        return sum;
    }

    public void giftTenOrders(List<Order> orders){
        int counter = 0;
        int rand = 0;
        int diffUser = 0;
        List<User> users = new ArrayList<User>();
        for (Order o: orders) {
            if (!users.contains(o.getUser())) {
                users.add(o.getUser());
            }
        }
        diffUser = users.size();
        for(int i = diffUser-1; i >= 0; i--)
        {
            users.remove(i);
        }
        while(counter < 10 && counter < diffUser) {
            rand = ((int)(Math.random()*1000)) % orders.size();
            if (orders.get(rand).getUserAge() < 18 && orders.get(rand).getTime() == 18) {
                if (!users.contains(orders.get(rand).getUser())) {
                    orders.get(rand).setPrice(0);
                    users.add(orders.get(rand).getUser());
                    counter++;
                }
            }
        }
    }
};

