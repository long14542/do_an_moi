package javacore21.do_an.service;

import javacore21.do_an.entity.Customer;
import javacore21.do_an.entity.Signup;

import java.util.Map;
import java.util.Scanner;

public class CustomerService {
    private Map<String, Customer> customerMap;

    public CustomerService(Map<String, Customer> customerMap) {
        this.customerMap = customerMap;
    }

    public Customer customerInfo(Scanner scanner, Signup signup){
        System.out.println("Enter customer full name: ");
        String fullName = scanner.nextLine();
        System.out.println("Enter address: ");
        String address = scanner.nextLine();
        System.out.println("Enter phone number: ");
        String phoneNumber = scanner.nextLine();
        String email = signup.getEmailSignup();
        Customer customer =  new Customer(fullName, address, phoneNumber, email);
        customerMap.put(fullName, customer);
        return customer;
    }
}
