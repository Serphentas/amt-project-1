package model;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String username;
    private String password; //chiffré
}
