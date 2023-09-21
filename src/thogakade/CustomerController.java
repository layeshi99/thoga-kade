/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package thogakade;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 *
 * @author Niroth Samarawickram
 */
public class CustomerController {
    public static boolean updateCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Update Customer set name=?, address=?, salary=? where id=?");
        stm.setObject(4, customer.getId());
        stm.setObject(1, customer.getName());
        stm.setObject(2, customer.getAddress());
        stm.setObject(3, customer.getSalary());
        int res=stm.executeUpdate();
        return res>0;
    }    
    public static boolean addCustomer(Customer customer) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        PreparedStatement stm = connection.prepareStatement("Insert into Customer Values(?,?,?,?)");
        stm.setObject(1, customer.getId());
        stm.setObject(2, customer.getName());
        stm.setObject(3, customer.getAddress());
        stm.setObject(4, customer.getSalary());
        int res=stm.executeUpdate();
        return res>0;
    }
    public static Customer searchCustomer(String id) throws ClassNotFoundException, SQLException{
        Connection connection = DBConnection.getInstance().getConnection();
        Statement stm = connection.createStatement(); 
        String SQL="Select * From Customer where id='"+id+"'";
        ResultSet rst = stm.executeQuery(SQL);
        if(rst.next()){
            return new Customer(id, rst.getString("name"), rst.getString("address"),rst.getDouble("salary"));
        }
        return null;
    }
    public static boolean deleteCustomer(String id) throws ClassNotFoundException, SQLException{
        return DBConnection.getInstance().getConnection().createStatement().executeUpdate("Delete From Customer where id='"+id+"'")>0;
    }
    public static ArrayList<Customer>getAllCustomers() throws ClassNotFoundException, SQLException{
        ResultSet rst = DBConnection.getInstance().getConnection().createStatement().executeQuery("Select * From Customer");
        ArrayList <Customer>customerList=new ArrayList<>();
        while(rst.next()){
            customerList.add(new Customer(rst.getString("id"), rst.getString("name"), rst.getString("address"),rst.getDouble("salary")));
        }
        return customerList;
    }
}
