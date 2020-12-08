////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    List<MenuItem> orderItems;
    double price;
    int hours;
    User user;

    public Order(List<MenuItem> orderItems,int hours,double price,User user) {
        this.orderItems = new ArrayList<MenuItem>();
        for (MenuItem i: orderItems) {
            this.orderItems.add(i);
        }
        this.hours = hours;
        this.price = price;
        this.user = user;
    }

    public void setPrice(double price){
        this.price = price;
    }

    public int getTime(){
        return hours;
    }

    public int getUserAge(){
        return user.age;
    }

    public User getUser(){
        return user;
    }

    public double getPrice(){
        return price;
    }

}
