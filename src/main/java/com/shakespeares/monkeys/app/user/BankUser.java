package com.shakespeares.monkeys.app.user;

public class BankUser {

    private String username;
    private String mailaddress;

    public BankUser(String username, String mailaddress) {
        this.username = username;
        this.mailaddress = mailaddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMailaddress() {
        return mailaddress;
    }

    public void setMailaddress(String mailaddress) {
        this.mailaddress = mailaddress;
    }
}
