package com.drew.Reddit.models;

/*
*   Holds all the information that is sent as an email for a user to verify their account after creation
* */
public class AuthEmail {
    private String subject;
    private String recipient;
    private String body;

    public AuthEmail() {
    }

    public AuthEmail(String subject, String recipient, String body) {
        this.subject = subject;
        this.recipient = recipient;
        this.body = body;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getRecipient() {
        return recipient;
    }

    public void setRecipient(String recipient) {
        this.recipient = recipient;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }
}
