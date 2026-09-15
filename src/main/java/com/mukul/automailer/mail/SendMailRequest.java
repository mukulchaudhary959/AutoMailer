package com.mukul.automailer.mail;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.util.List;

public record SendMailRequest(
        @NotEmpty(message = "recipients must contain at least one email address")
        List<@Email(message = "each recipient must be a valid email address") String> recipients,
        @NotBlank(message = "subject is required")
        @Size(max = 200, message = "subject must not exceed 200 characters")
        String subject,
        @NotBlank(message = "body is required")
        String body
) { }
