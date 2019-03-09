package com.msp.givn.service.register;

import com.msp.givn.dao.user.UserRegisterDTODao;
import com.msp.givn.dto.UserRegisterDTO;
import com.msp.givn.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class RegisterService {

    @Autowired
    private UserRegisterDTODao userRegisterDTODao;

    @Transactional
    public boolean checkExistsOfUsernameAndEmail(String username, String email) {
        List<User> userList = userRegisterDTODao.findByUsernameAndEmail(username, email);
        if (userList.size() > 0) {
            return true;
        }
        return false;
    }

    public boolean validateEmailPattern(String email) {
        Pattern pattern;
        Matcher matcher;
        String EMAIL_PATTERN = "^\\w+@[a-zA-Z_]+?\\.[a-zA-Z]{2,3}$";

        pattern = Pattern.compile(EMAIL_PATTERN);
        matcher = pattern.matcher(email);

        return matcher.matches();
    }

    public boolean checkIfPasswordIsMatch(String password, String passwordAgain) {
        if (password != null && passwordAgain != null) {
            if (password.equals(passwordAgain)) {
                return true;
            }

            return false;
        }

        return false;
    }

    public boolean checkPasswordLength(String password) {
        if (password.length() < 6 || password.length() > 100) {
            return false;
        }

        return true;
    }

    public boolean checkUsernameLength(String username) {
        if (username.length() < 6 || username.length() > 25) {
            return false;
        }

        return true;
    }

    public String validUserAndGetError(UserRegisterDTO userRegisterDTO) {
        String message = null;

        if (!validateEmailPattern(userRegisterDTO.getEmail())) {
            message = "Email không đúng định dạng";
        } else if (!checkUsernameLength(userRegisterDTO.getUsername())) {
            message = "Độ dài tên đăng nhập từ 6 đến 25 ký tự";
        } else if (!checkPasswordLength(userRegisterDTO.getPassword())) {
            message = "Độ dài mật khẩu từ 6 đến 100 ký tự";
        } else if (!checkIfPasswordIsMatch(userRegisterDTO.getPassword(), userRegisterDTO.getPasswordAgain())) {
            message = "Nhập lại mật khẩu không đúng";
        } else if (checkExistsOfUsernameAndEmail(userRegisterDTO.getUsername(), userRegisterDTO.getEmail())) {
            message = "Username hoặc email đã được sử dụng";
        }

        return message;
    }

    public String validPasswordReset(String password, String again) {
        String message = null;
        if (!checkPasswordLength(password)) {
            message = "Độ dài mật khẩu từ 6 đến 100 ký tự";
        } else if (!checkIfPasswordIsMatch(password, again)) {
            message = "Nhập lại mật khẩu không đúng";
        }

        return message;
    }
}
