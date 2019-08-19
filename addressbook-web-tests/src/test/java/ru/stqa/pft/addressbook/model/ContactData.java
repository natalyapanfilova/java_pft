package ru.stqa.pft.addressbook.model;

public class ContactData {
    private final String lastName;
    private final String firstName;
    private final String address;
    private final String email;
    private final String mobilePhone;

    public ContactData(String lastName, String firstName, String address, String email, String mobilePhone) {
        this.lastName = lastName;
        this.firstName = firstName;
        this.address = address;
        this.email = email;
        this.mobilePhone = mobilePhone;
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
}
