package com.springjson.services.functions;

import java.util.ArrayList;
import java.util.List;
import com.springjson.services.daos.PersonDAO;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValidationData {

    private List<String> list_errors;

    @Autowired
    private PersonDAO personDAO;

    public List<String> ValidationPerson(String name, String email, String birthday, String salary, String gender, byte[] picture) {
        this.list_errors = new ArrayList<>();
        this.ValidationName(name);
        if (email != null) {
            this.ValidationEmail(email);
        }
        this.ValidationSalary(salary);
        this.ValidationBirthday(birthday);
        this.ValidationGender(gender);
        return this.list_errors;
    }

    private void ValidationName(String name) {
        if (name.equals("")) {
            this.list_errors.add("Name is empty.");
        } else {
            if (name.length() < 3) {
                this.list_errors.add("Name with invalid character quantity.");
            }
        }
    }

    private void ValidationEmail(String email) {
        if (email.equals("")) {
            this.list_errors.add("Email is empty.");
        } else {
            if (this.MatchEmail(email)) {
                if (personDAO.CheckEmailPerson(email) == false) {
                    this.list_errors.add("E-mail already registered.");
                }
            } else {
                this.list_errors.add("Invalid email.");
            }
        }

    }

    private boolean MatchEmail(String email) {
        String regex = "^([_a-zA-Z0-9-]+(\\.[_a-zA-Z0-9-]+)*@[a-zA-Z0-9-]+(\\.[a-zA-Z0-9-]+)*(\\.[a-zA-Z]{1,6}))?$";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

    private void ValidationSalary(String salary) {
        try {
            if (!"".equals(salary)) {
                Double.parseDouble(salary);
                if (Double.parseDouble(salary) < 0) {
                    this.list_errors.add("Invalid salary.");
                }
            } else {
                this.list_errors.add("Salary is empty.");
            }
        } catch (NumberFormatException e) {
            this.list_errors.add("Invalid salary.");
        }
    }

    private void ValidationBirthday(String birthday){
        if (!"".equals(birthday)) {
            try {
                //Calendar cal1 = Calendar.getInstance();
                //int year = Integer.parseInt(birthday.substring(0, 4));
                //int month = Integer.parseInt(birthday.substring(5, 7));
                //int day = Integer.parseInt(birthday.substring(8, 10));
                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                Date date = sdf.parse(birthday);
                //cal1.set(year, month, day);
            } catch (ParseException e) {
                list_errors.add("Invalid birthday.");
            }
        } else {
            list_errors.add("Birthday is empty.");
        }
    }

    private void ValidationGender(String gender) {
        if (gender != "") {
            if (!("M" != gender) || !("F" != gender)) {
                list_errors.add("Invalid gender.");
            }
        } else {
            list_errors.add("Empty gender.");
        }
    }

    public List<String> ValidationUser(String username, String password) {
        this.list_errors = new ArrayList<>();
        this.ValidationUsername(username);
        this.ValidationPassword(password);
        return list_errors;
    }

    private void ValidationUsername(String username) {
        if (username.equals("")) {
            this.list_errors.add("Username is empty.");
        }
    }

    private void ValidationPassword(String userpass) {
        if (userpass.equals("")) {
            this.list_errors.add("Password is empty.");
        }
    }
}
