package ua.nure.bolhov.SummaryTask4.web.bean;

public class SettingBean {


    private long id;
    private String firstName;
    private String lastName;
    private int age;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(String ageStr) {
        this.age = (ageStr.isEmpty()) ? 0 : Integer.parseInt(ageStr);
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
