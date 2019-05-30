package interfaces;

import java.io.Serializable;
import java.util.Objects;

public class Conferee implements Serializable {
    private String name;
    private String surname;
    private String organization;
    private String report;
    private String email;

    public Conferee(String name, String surname, String organization, String report, String email) {
        this.name = name;
        this.surname = surname;
        this.organization = organization;
        this.report = report;
        this.email = email;
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
    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }
    public String getReport() {
        return report;
    }

    public void setReport(String report) {
        this.report = report;
    }
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return new StringBuilder("name: ")
                .append(name)
                .append(", surname: ").append(surname)
                .append(", organization: ").append(organization)
                .append(", report: ").append(report)
                .append(", e-mail: ").append(email).toString();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Conferee conferee = (Conferee) o;
        return Objects.equals(name, conferee.name) &&
                Objects.equals(surname, conferee.surname) &&
                Objects.equals(organization, conferee.organization) &&
                Objects.equals(report, conferee.report) &&
                Objects.equals(email, conferee.email);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, surname, organization, report, email);
    }
}
