package ru.stqa.pft.vk.model;

import java.util.Arrays;

public class MainData {

    private String firstName;
    private String lastName;
    private String maiden_name;
    private String gender;
    private String familyStatus;
    private String birsdayDate;
    private String homeTown;
    private String[] grandmothersAndGrandfathers;
    private String[] parents;
    private String[] brothersAndSisters;
    private String[] children;
    private String[] grandchildren;

    public String getFirstName() {
        return firstName;
    }

    public MainData withFirstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public String getLastName() {
        return lastName;
    }

    public MainData withLastName(String lestName) {
        this.lastName = lestName;
        return this;
    }

    public String getMaiden_name() {
        return maiden_name;
    }

    public MainData withMaiden_name(String maiden_name) {
        this.maiden_name = maiden_name;
        return this;
    }

    public String getGender() {
        return gender;
    }

    public MainData withGender(String gender) {
        this.gender = gender;
        return this;
    }

    public String getFamilyStatus() {
        return familyStatus;
    }

    public MainData withFamilyStatus(String familyStatus) {
        this.familyStatus = familyStatus;
        return this;
    }

    public String getBirsdayDate() {
        return birsdayDate;
    }

    public MainData withBirsdayDate(String birsdayDate) {
        this.birsdayDate = birsdayDate;
        return this;
    }

    public String getHomeTown() {
        return homeTown;
    }

    public MainData withHomeTown(String homeTown) {
        this.homeTown = homeTown;
        return this;
    }

    public String[] getGrandmothersAndGrandfathers() {
        return grandmothersAndGrandfathers;
    }

    public MainData withGrandmothersAndGrandfathers(String[] grandmothersAndGrandfathers) {
        this.grandmothersAndGrandfathers = grandmothersAndGrandfathers;
        return this;
    }

    public String[] getParents() {
        return parents;
    }

    public MainData withParents(String[] parents) {
        this.parents = parents;
        return this;
    }

    public String[] getBrothersAndSisters() {
        return brothersAndSisters;
    }

    public MainData withBrothersAndSisters(String[] brothersAndSisters) {
        this.brothersAndSisters = brothersAndSisters;
        return this;
    }

    public String[] getChildren() {
        return children;
    }

    public MainData withChildren(String[] children) {
        this.children = children;
        return this;
    }

    public String[] getGrandchildren() {
        return grandchildren;
    }

    public MainData withGrandchildren(String[] grandchildren) {
        this.grandchildren = grandchildren;
        return this;
    }

    @Override
    public String toString() {
        return "MainData{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", maiden_name='" + maiden_name + '\'' +
                ", gender='" + gender + '\'' +
                ", familyStatus='" + familyStatus + '\'' +
                ", birsdayDate='" + birsdayDate + '\'' +
                ", homeTown='" + homeTown + '\'' +
                ", grandmothersAndGrandfathers=" + Arrays.toString(grandmothersAndGrandfathers) +
                ", parents=" + Arrays.toString(parents) +
                ", brothersAndSisters=" + Arrays.toString(brothersAndSisters) +
                ", children=" + Arrays.toString(children) +
                ", grandchildren=" + Arrays.toString(grandchildren) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        MainData mainData = (MainData) o;

        if (firstName != null ? !firstName.equals(mainData.firstName) : mainData.firstName != null) return false;
        if (lastName != null ? !lastName.equals(mainData.lastName) : mainData.lastName != null) return false;
        if (maiden_name != null ? !maiden_name.equals(mainData.maiden_name) : mainData.maiden_name != null)
            return false;
        if (gender != null ? !gender.equals(mainData.gender) : mainData.gender != null) return false;
        if (familyStatus != null ? !familyStatus.equals(mainData.familyStatus) : mainData.familyStatus != null)
            return false;
        if (birsdayDate != null ? !birsdayDate.equals(mainData.birsdayDate) : mainData.birsdayDate != null)
            return false;
        if (homeTown != null ? !homeTown.equals(mainData.homeTown) : mainData.homeTown != null) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(grandmothersAndGrandfathers, mainData.grandmothersAndGrandfathers)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(parents, mainData.parents)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(brothersAndSisters, mainData.brothersAndSisters)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        if (!Arrays.equals(children, mainData.children)) return false;
        // Probably incorrect - comparing Object[] arrays with Arrays.equals
        return Arrays.equals(grandchildren, mainData.grandchildren);
    }

    @Override
    public int hashCode() {
        int result = firstName != null ? firstName.hashCode() : 0;
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (maiden_name != null ? maiden_name.hashCode() : 0);
        result = 31 * result + (gender != null ? gender.hashCode() : 0);
        result = 31 * result + (familyStatus != null ? familyStatus.hashCode() : 0);
        result = 31 * result + (birsdayDate != null ? birsdayDate.hashCode() : 0);
        result = 31 * result + (homeTown != null ? homeTown.hashCode() : 0);
        result = 31 * result + Arrays.hashCode(grandmothersAndGrandfathers);
        result = 31 * result + Arrays.hashCode(parents);
        result = 31 * result + Arrays.hashCode(brothersAndSisters);
        result = 31 * result + Arrays.hashCode(children);
        result = 31 * result + Arrays.hashCode(grandchildren);
        return result;
    }
}
