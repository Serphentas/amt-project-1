package model;

import lombok.*;

@Value
@Getter
@Setter
@AllArgsConstructor
public class Account {
    private String username;
    private String password; //chiffré
}
