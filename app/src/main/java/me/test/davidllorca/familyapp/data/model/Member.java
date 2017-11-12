package me.test.davidllorca.familyapp.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

/**
 * Created by David Llorca <davidllorcabaron@gmail.com> on 11/11/17.
 */

@Entity(tableName = "members")
public class Member {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private int role;
    private String name;
    private String surname;
    private int age;
    private char gender;

    public Member(int role, String name, String surname, int age, char gender) {
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.age = age;
        this.gender = gender;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getRole() {
        return role;
    }

    public void setRole(int role) {
        this.role = role;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public char getGender() {
        return gender;
    }

    public void setGender(char gender) {
        this.gender = gender;
    }

    public static class Role {
        public static int FATHER = 0;
        public static int MOTHER = 1;
        public static int SON = 2;
        public static int DAUGHTER = 3;
        public static int PET = 4;
    }

    public static int[] getChildTypes() {
        return new int[]{Role.SON, Role.DAUGHTER};
    }
}
