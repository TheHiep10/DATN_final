package com.example.afinal.room.member;

public class  add_member {
    private String nameMember, genderMember, dateMember, addressMember, timeMember, phoneMember, idMember;

    add_member() {

    }

    public add_member(String nameMember, String genderMember, String dateMember, String addressMember, String timeMember, String phoneMember, String idMember) {
        this.nameMember = nameMember;
        this.genderMember = genderMember;
        this.dateMember = dateMember;
        this.addressMember = addressMember;
        this.timeMember = timeMember;
        this.phoneMember = phoneMember;
        this.idMember = idMember;
    }

    public String getNameMember() {
        return nameMember;
    }

    public void setNameMember(String nameMember) {
        this.nameMember = nameMember;
    }

    public String getGenderMember() {
        return genderMember;
    }

    public void setGenderMember(String genderMember) {
        this.genderMember = genderMember;
    }

    public String getDateMember() {
        return dateMember;
    }

    public void setDateMember(String dateMember) {
        this.dateMember = dateMember;
    }

    public String getAddressMember() {
        return addressMember;
    }

    public void setAddressMember(String addressMember) {
        this.addressMember = addressMember;
    }

    public String getTimeMember() {
        return timeMember;
    }

    public void setTimeMember(String timeMember) {
        this.timeMember = timeMember;
    }

    public String getPhoneMember() {
        return phoneMember;
    }

    public void setPhoneMember(String phoneMember) {
        this.phoneMember = phoneMember;
    }

    public String getIdMember() {
        return idMember;
    }

    public void setIdMember(String idMember) {
        this.idMember = idMember;
    }
}
