package uz.abdukarimov.services;

import org.springframework.stereotype.Service;
import uz.abdukarimov.dao.LoginDao;
import uz.abdukarimov.models.AuthLogin;

import java.util.List;

@Service
public class LoginService {

    private final LoginDao authUserDao;

    public LoginService(LoginDao authUserDao) {
        this.authUserDao = authUserDao;
    }

    public List<AuthLogin> getAll() {
        return authUserDao.getAll();
    }

    public Boolean checkUser(AuthLogin login) {
        List<AuthLogin> users = getAll();
        for (AuthLogin user : users) {
            if(user.getUsername().equals(login.getUsername())&&user.getPassword().equals(login.getPassword())
            &&user.getRole().equals("ADMIN")){
                return true;
            }
        }
        return false;
    }
}
