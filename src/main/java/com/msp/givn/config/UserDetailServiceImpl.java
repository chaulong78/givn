package com.msp.givn.config;

import com.msp.givn.dao.function.FunctionDTODao;
import com.msp.givn.dto.FunctionDTO;
import com.msp.givn.entity.Role;
import com.msp.givn.entity.User;
import com.msp.givn.repository.user.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FunctionDTODao functionDTODao;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userRepository.findByUserName(userName);
        int roleId = user.getRoles().get(0).getId();
        List<FunctionDTO> functionDTOList = functionDTODao.findByRoleId(roleId);

        if (user == null) {
            throw new UsernameNotFoundException("User not found!");
        }

        List<Role> roleList = user.getRoles();
        List<GrantedAuthority> authorityList = new ArrayList<>();

        for (Role role : roleList) {
            authorityList.add(new SimpleGrantedAuthority(role.getName()));
        }

        boolean userEnabled = user.isEnabled();
        boolean roleEnabled = roleList.get(0).isEnabled();
        boolean enabled = userEnabled && roleEnabled;

        CustomUserDetail customUserDetail =
                new CustomUserDetail(
                        userName, user.getPassword()
                        , user.getId(), user.getEmail(), user.getAvatar()
                        , functionDTOList
                        , enabled, true, true, true, authorityList);

        return customUserDetail;
    }
}
