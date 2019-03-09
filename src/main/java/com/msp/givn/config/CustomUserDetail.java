package com.msp.givn.config;

import com.msp.givn.dto.FunctionDTO;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Data
public class CustomUserDetail extends org.springframework.security.core.userdetails.User implements Serializable {

    private int userId;

    private String email;

    private String avatar;

    private List<FunctionDTO> functionDTOList;

    public CustomUserDetail(
            String username, String password
            , int userId, String email, String avatar
            , List<FunctionDTO> functionDTOList
            , boolean enabled, boolean accountNonExpired
            , boolean credentialsNonExpired, boolean accountNonLocked
            , Collection<? extends GrantedAuthority> authorities) {
        super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
        this.functionDTOList = functionDTOList;
        this.userId = userId;
        this.email = email;
        this.avatar = avatar;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public List<FunctionDTO> getFunctionDTOList() {
        return functionDTOList;
    }

    public void setFunctionDTOList(List<FunctionDTO> functionDTOList) {
        this.functionDTOList = functionDTOList;
    }


}
