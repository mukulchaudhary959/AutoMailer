package com.mukul.automailer.mail;

import java.util.List;

public record SendMailResponse(String message, int sentCount, List<String> recipients) { }
