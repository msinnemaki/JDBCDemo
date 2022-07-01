package com.Persistence;
import com.Utils.ConnectionManager;
import  com.Utils.CustomDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class USERDAO implements CustomDAOInterface<User>
{
    Connection connection;

    public USERDAO()
    {
        connection = ConnectionManager.getConnection();
    }



    @Override
    public Integer create(User user)
    {
        //note that this is a 'create' method
        //however we are inserting into our table

        try
        {
            String sql = "INSERT INTO users (user_id, first_name, last_name, email) VALUES (default,?,?,?) ";
            PreparedStatement pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setString(1, user.getFirst_name());
            pstat.setString(2, user.getLast_name());
            pstat.setString(3, user.getEmail());

            pstat.executeUpdate();

            ResultSet rs = pstat.getGeneratedKeys();

            //the cursor is initially placed before the first element in the Result Set
            //You need to advance it once to access the first element
            rs.next();

            return rs.getInt(1);

        }
        catch (Exception e)
        {
            System.out.println();
        }

        return null;
    }

    @Override
    public User read(Integer id)
    {
        //reading from the table based on the id

        try
        {
            String sql = "SELECT * FROM users WHERE user_id = ?";

            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setInt(1, id);

            //we are creating an instance of User b/c we will have to
            //return a user
            //we will basically build this user from the db

            User user = new User();

            ResultSet rs = pstat.executeQuery();

            while (rs.next())
            {
                user.setUser_id(rs.getInt("user_id"));
                user.setFirst_name(rs.getString("first_name"));
                user.setLast_name(rs.getString("last_name"));
                user.setEmail(rs.getString("email"));
            }

            return user;
        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }


        return null;
    }

    @Override
    public User update(User user)
    {
        //here we are updating the database for a user
        //in this case we are allowing for a user to update their email


        try
        {
            String sql = "UPDATE user SET email - ? WHERE user_id = ?";
            PreparedStatement pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            pstat.setString(1, user.getEmail());
            pstat.setInt(2, user.getUser_id());

            pstat.executeUpdate();

            ResultSet rs = pstat.getGeneratedKeys();

            while (rs.next())
            {
                user.setEmail(rs.getString("email"));
            }

            return user;

        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public boolean delete(Integer id)
    {
        try
        {
            String sql = "DELETE FROM users WHERE user_ID = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setInt(1,id);

            //if pstat.execute() returns a reseult set it will be true
            //if it does NOT it will be false
            return  pstat.execute();

        }

        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return true;
    }


    public User checkLoginInfo(String firstName,String lastName)
    {
        try
        {
            String sql = "SELECT * FROM users WHERE first_name = ?";
            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setString(1,firstName);

            ResultSet rs = pstat.executeQuery();

            if(rs.next() && rs.getString("last_name").equals(lastName))
            {
                return new User(rs.getInt("user_id"),
                        rs.getString("first_name"),
                        rs.getString("last_name"),
                        rs.getString("email"));
            }
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }



        return null;
    }
}
