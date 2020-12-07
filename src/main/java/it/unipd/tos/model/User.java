////////////////////////////////////////////////////////////////////
// [Francesco] [Dallan] [1193405]
////////////////////////////////////////////////////////////////////
package it.unipd.tos.model;

public class User {
    String userName;
    String userSurname;
    int age;

    public User(String userName,String userSurname,int age){
        if(age<0){
            age = 0;
        }
        this.userName = userName;
        this.userSurname = userSurname;
        this.age = age;
    }

}
