import java.util.Date;

public class Employee {
    private String firstName;
    private String lastName;
    private Date dateOfBirth;
    private double experience;

    public String getFirstName() {
        return firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public Date getDateOfBirth() {
        return dateOfBirth;
    }
    public double getExperience() {
        return experience;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }
    public void setExperience(double experience) {
        this.experience = experience;
    }
}
