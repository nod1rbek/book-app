package uz.abdukarimov.models;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class AuthLogin {
    private String username;
    private String password;
    private String role;
}
