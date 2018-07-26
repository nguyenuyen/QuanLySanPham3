package model;

public class UserAccount {

    private int id;
    private String email;
    private String pass;
    private String role;
    private String phone;
    private String name;

    public UserAccount(String email, String pass, String role) {
        this.email = email;
        this.pass = pass;
        this.role = role;
    }

    public UserAccount(int id,String email, String pass, String phone, String name) {
        this.id=id;
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.name = name;
    }
    public UserAccount(String email, String pass, String phone, String name) {
        this.email = email;
        this.pass = pass;
        this.phone = phone;
        this.name = name;
    }

    public UserAccount(String phone, String name) {
        this.phone = phone;
        this.name = name;
    }
    public UserAccount() {
    }



    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPass() {
        return pass;
    }

    public void setPass(String pass) {
        this.pass = pass;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
