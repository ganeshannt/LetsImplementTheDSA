package com.practise.designpattern.structural;


/**
 * Proxy pattern: A proxy pattern is a structural pattern. We use proxy when we don't want to expose real object and provide a proxy object to deal with.
 * Many times when you are dealing with remote servers and do lookup from the naming server it provides you a proxy object from remote server, not actual one.
 * Even in hibernate if remember we have the concept of lazy loading where whenever we load data from db, we get a proxy object of
 * a database and return it, however, if we get it, then only it gets loaded from a database. This is one of the best examples of a proxy object.
 * <p>
 * Another example can be Spring AOP where AOP objects are proxy and treated on advice aspects.
 * <p>
 * Different types of proxies are:
 * 1. Remote proxy: When you are dealing with remote system , you require remote object to interact with the system. Usually in past it gets used in ejb where beans gets created in container,
 * and a client gets an object using JNDI system.
 * 2. Virtual proxy: Delay the object creation until it is required, as explained, hibernate uses this.
 * 3. Protection proxy: this proxy is used when we are dealing with security system where before invoking system implementation, we want to check access.
 * 4. Smart proxy - Perform some additional steps before accessing an object (current example of ATM proxy do all the checks and then access actual object BankAccount).
 * <p>
 * Proxy pattern has mainly three components:
 * 1. A common interface
 * 2. Real Class
 * 3. Proxy class - this uses the real-class, and it is proxy of real class.
 * <p>
 * https://www.javadevjournal.com/java-design-patterns/proxy-design-pattern/
 * Example consider as Bank Account and ATM where to operate your bank account you have ATM which is proxy of doing process in bank account.
 */


interface Account {
    public void withdraw();

    void getAccountNumber();
}


class BankAccount implements Account {

    @Override
    public void withdraw() {
        System.out.println("withdraw amount");
    }

    @Override
    public void getAccountNumber() {
        System.out.println("Account number: 1234");
    }
}

class ATM implements Account { // This is a proxy to bank account object not real

    @Override
    public void withdraw() {
        //Access using an actual object. You can also have checks on withdraw to achieve authentication or pin is correct or not.
        BankAccount bankAccount = new BankAccount();
        bankAccount.withdraw();
    }

    @Override
    public void getAccountNumber() {

    }
}


public class ProxyDesignPattern {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.withdraw();
    }
}
