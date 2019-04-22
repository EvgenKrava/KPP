package card;

import java.io.Serializable;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class User implements Serializable {
    private static final DateFormat dateFormatter = new SimpleDateFormat("dd.MM.yyyy");
    private static final DateFormat dateParser = dateFormatter;

    private String name;
    private String surName;
    private String sex;
    private Date birthday;


    public User() {
        name = "None";
        surName = "None";
        sex = "none";
        try {
            this.birthday = dateParser.parse("1.01.1979");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public User(String name, String surName, String sex, String birthday) {
        this.name = name;
        this.surName = surName;
        this.sex = sex;
        try {
            this.birthday = dateParser.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
            System.out.println("Invalid date format");
        }
    }

    @Override
    public String toString() {
        return name + " " + surName + " " + sex +
                " " + dateFormatter.format(birthday);
    }

    public String getName() {
        return name;
    }

    public String getSurName() {
        return surName;
    }

    public String getSex() {
        return sex;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }

    public void setBirthday(String birthday) {
        try {
            this.birthday = dateParser.parse(birthday);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}