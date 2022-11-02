package activities;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class Activity2 {

    @Test
    void notEnoughFunds(){

        BankAccount account = new BankAccount(9);
        assertThrows(NotEnoughFundsException.class, () -> account.withdraw(11),"Balance must be greater than amount of withdrawal");
    }

    @Test
    void enoughFunds(){
        BankAccount account = new BankAccount(100);
        assertDoesNotThrow(()->account.withdraw(50));

    }



}
