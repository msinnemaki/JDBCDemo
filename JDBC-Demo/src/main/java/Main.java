import com.Persistence.*;
import com.Utils.ConnectionManager;

import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;

public class Main
{
    public static void main(String[] args)
    {

        USERDAO userdao = new USERDAO();
        AccountTypeDAO accountTypeDAO = new AccountTypeDAO();
        AccountDAO accountDAO = new AccountDAO();

        User benji = new User("Benji", "ReadyToGo","benji@isoutthebuilding.com");
        Integer benjiUserID = userdao.create(benji);

        AccountType accountType = new AccountType("Checking");
        Integer accountTypeID = accountTypeDAO.create(accountType);

        Account bankAccount = new Account(100.00F, accountTypeID, benjiUserID, Date.valueOf(LocalDate.now()));
        System.out.println(accountDAO.create(bankAccount));

//        User andrew = new User("Andrew","Jackson","ajackson@aol.com");
//
//        USERDAO userdao = new USERDAO();
//
//        System.out.println(userdao.create(andrew));

    }
}
