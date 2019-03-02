package com.msp.givn.service.user.impl;

import com.msp.givn.entity.PasswordResetToken;
import com.msp.givn.entity.User;
import com.msp.givn.repository.user.PasswordResetTokenRepository;
import com.msp.givn.service.user.PasswordResetTokenService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.UUID;

@Service
public class PasswordResetTokenServiceImpl implements PasswordResetTokenService {

    @Autowired
    private PasswordResetTokenRepository passwordResetTokenRepository;

    @Override
    @Transactional
    public PasswordResetToken findByToken(String token) {
        return passwordResetTokenRepository.findByToken(token);
    }

    @Override
    @Transactional
    public PasswordResetToken save(PasswordResetToken passwordResetToken) {
        return passwordResetTokenRepository.save(passwordResetToken);
    }

    @Override
    @Transactional
    public void deleteById(int id) {
        passwordResetTokenRepository.deleteById(id);
    }

    @Override
    public PasswordResetToken createNewResetPasswordToken(User user) {
        String token = UUID.randomUUID().toString();
        PasswordResetToken passwordResetToken = new PasswordResetToken(token, user);
        save(passwordResetToken);
        return passwordResetToken;
    }

    @Override
    public boolean validateExpiry(PasswordResetToken passwordResetToken) {
        Calendar calendar = Calendar.getInstance();
        if ((passwordResetToken.getExpiryDate().getTime() - calendar.getTime().getTime() < 0)) {
            return false;
        }
        return true;
    }
}
