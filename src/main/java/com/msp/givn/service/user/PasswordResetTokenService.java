package com.msp.givn.service.user;

import com.msp.givn.entity.PasswordResetToken;
import com.msp.givn.entity.User;

public interface PasswordResetTokenService {

    PasswordResetToken findByToken(String token);

    PasswordResetToken save(PasswordResetToken passwordResetToken);

    void deleteById(int id);

    PasswordResetToken createNewResetPasswordToken(User user);

    boolean validateExpiry(PasswordResetToken passwordResetToken);
}
