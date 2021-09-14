package com.dev.utilis;

import java.sql.SQLException;
import java.util.InputMismatchException;

public class YourFoolException extends SQLException {
    public YourFoolException() {
        super("\nYou are not a user, you Third Rate Duelist");
    }

}

class TypeANumberIdiot extends InputMismatchException {

}
