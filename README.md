# Auto Mailer API

A Java 17+ / Spring Boot API that sends a plain-text email from `mukulchaudhary0303@gmail.com` to every address supplied in the request.

## Gmail setup

Gmail does not accept your normal account password for SMTP. Turn on 2-Step Verification for the sending account, create a Google **App Password**, then set it in your terminal. Never put this password in source code.

PowerShell (current terminal):

```powershell
$env:GMAIL_APP_PASSWORD = "your-16-character-google-app-password"
mvn spring-boot:run
```

Optionally set `GMAIL_USERNAME` if you later use a different sender. The API runs at `http://localhost:8080`.

## Send email

`POST /api/mail/send`

```json
{
  "recipients": [
    "first@example.com",
    "second@example.com"
  ],
  "subject": "Welcome",
  "body": "Hello! This is an automatically sent message."
}
```

PowerShell example:

```powershell
Invoke-RestMethod -Method Post -Uri "http://localhost:8080/api/mail/send" -ContentType "application/json" -Body '{"recipients":["first@example.com","second@example.com"],"subject":"Welcome","body":"Hello! This is an automatically sent message."}'
```

Each recipient is sent an individual message, so recipients cannot see each other's addresses. Duplicate addresses are sent only once.
