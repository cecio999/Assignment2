////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class MenuItem {
    public enum types{Gelato,Budino,Bevanda}
    types itemType;
    String name;
    double price;

    public MenuItem (types itemType, String name, double price){
        this.itemType = itemType;
        this.name = name;
        this.price = price;
    }

    public double getPrice(){
        return price;
    }
    public types getItemType(){return itemType;}
}
