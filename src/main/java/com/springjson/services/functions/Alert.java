package com.springjson.services.functions;

import org.springframework.stereotype.Component;

@Component
public class Alert {

    private String text;
    
    public String GetAlert(int method, int result) {

        if (result == 1) {
            this.text = "Successfully ";
            if (method == 1) {
                this.text = this.text + "created.";
            }
            if (method == 2) {
                this.text = this.text + "edited.";
            }
            if (method == 3) {
                this.text = this.text + "deleted.";
            }
            if (method == 4) {
                this.text = this.text + "logged.";
            }
            if (method == 5) {
                this.text = this.text + "logged out.";
            }
        }

        if (result != 1) {
            if (method == 1) {
                this.text = "Author not found.";
            }
            if (method == 2) {
                this.text = "This post can only be edited or deleted by the author.";
            }
            if (method == 3) {
                this.text = "Invalid username and password.";
            }
            if (method == 4) {
                this.text = "Unsuccessfully logged out.";
            }
        }
        return this.text;
    }
}
