package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String lastName;
    private final String firstName;
    private final String address;
    private final String email;
    private final String mobilePhone;
    private final String group;

    public ContactData(String lastName, String firstName, String address, String email, String mobilePhone, String group) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.mobilePhone = mobilePhone;
        this.group = group;
    }


    public String getLastName() {
        return lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getAddress() {
        return address;
    }

    public String getEmail() {
        return email;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public String getGroup() {
        return group;
    }
}
