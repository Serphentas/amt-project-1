package model;

import java.util.ArrayList;
import java.util.List;

public class Generator {
    static public List<Account> accountGen() {
        List<Account> result = new ArrayList<>();
        result.add(new Account("Dr. Seuss", "password"));
        result.add(new Account("Oscar Wilde", "1234"));
        result.add(new Account("Albert Einstein", "Pa$$w0rd"));
        return result;
    }
}
