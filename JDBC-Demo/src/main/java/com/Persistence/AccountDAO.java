package com.Persistence;

import com.Utils.ConnectionManager;
import com.Utils.CustomDAOInterface;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class AccountDAO implements CustomDAOInterface<Account>
{
    Connection connection;

    public AccountDAO()
    {
        connection = ConnectionManager.getConnection();
    }

    @Override
    public Integer create(Account account)
    {
        try
        {
            String sql = "INSERT INTO accounts (acc_id, balance, type_id,user_id,opened) VALUES(default,?,?,?,?)";
            PreparedStatement pstat = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            pstat.setFloat(1,account.getBalance());
            pstat.setInt(2,account.getType_id());
            pstat.setInt(3,account.getUser_id());
            pstat.setDate(4,account.getOpened());

            pstat.executeUpdate();

            ResultSet rs = pstat.getGeneratedKeys();

            rs.next();

            return rs.getInt(1);
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }
        return null;
    }

    @Override
    public Account read(Integer id)
    {
        try
        {
            String sql = "SELECT * FROM accounts WHERE acc_id = ?";

            PreparedStatement pstat = connection.prepareStatement(sql);
            pstat.setInt(1, id);

            //we are creating an instance of User b/c we will have to
            //return a user
            //we will basically build this user from the db

            Account account = new Account();

            ResultSet rs = pstat.executeQuery();

            while (rs.next())
            {
                account.setBalance(rs.getFloat("balance"));
                account.setType_id(rs.getInt("type_id"));
                account.setUser_id(rs.getInt("user_id"));
                account.setOpened(rs.getDate("opened"));
            }

            return account;
        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return null;
    }

    @Override
    public Account update(Account account)
    {
        try
        {

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

        }
        catch (Exception e)
        {
            System.out.println(e.getMessage());
        }

        return false;
    }
}
