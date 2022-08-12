package model;

public class FakeUser {
    private String user_name;
    private String email;
    private String password;
    private String about;

    private String sentAttachment;

    public FakeUser() {
    }

    public FakeUser(String user_name, String email, String password, String about) {
        this.user_name = user_name;
        this.email = email;
        this.password = password;
        this.about = about;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

    public String getSentAttachment() {
        return sentAttachment;
    }

    public void setSentAttachment(String sentAttachment) {
        this.sentAttachment = sentAttachment;
    }

    @Override
    public String toString() {
        return "FakeUser{" +
                "user_name='" + user_name + '\'' +
                ", email='" + email + '\'' +
                ", password='" + password + '\'' +
                ", about='" + about + '\'' +
                ", sentAttachment='" + sentAttachment + '\'' +
                '}';
    }
}
