package entities;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class User {

    public static final String LOGIN = "login";
    public static final String ID = "id";


    private String login;
    private String id;

}
