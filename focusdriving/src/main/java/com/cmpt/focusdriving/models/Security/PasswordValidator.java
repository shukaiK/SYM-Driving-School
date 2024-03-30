package com.cmpt.focusdriving.models.Security;

import org.springframework.stereotype.Component;
import java.util.regex.Pattern;

@Component
public class PasswordValidator {

    private final Pattern pattern;

    public PasswordValidator() {
        // Example pattern - adjust according to your needs
        this.pattern = Pattern.compile("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$");
    }

    public boolean validate(final String password) {
        return pattern.matcher(password).matches();
    }
}

