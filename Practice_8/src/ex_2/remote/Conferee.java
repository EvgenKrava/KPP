package ex_2.remote;

public class Conferee {
    private String name;
    private String familyName;
    private String placeOfWork;
    private String reportTitle;
    private String email;

    public Conferee(String name, String familyName, String placeOfWork, String reportTitle, String email) {
        this.name = name;
        this.familyName = familyName;
        this.placeOfWork = placeOfWork;
        this.reportTitle = reportTitle;
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getFamilyName() {
        return familyName;
    }

    public String getPlaceOfWork() {
        return placeOfWork;
    }

    public String getReportTitle() {
        return reportTitle;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFamilyName(String familyName) {
        this.familyName = familyName;
    }

    public void setPlaceOfWork(String placeOfWork) {
        this.placeOfWork = placeOfWork;
    }

    public void setReportTitle(String reportTitle) {
        this.reportTitle = reportTitle;
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        s.append("name: ").append(name).append(", ");
        s.append("family name: ").append(familyName).append(", ");
        s.append("place of work: ").append(placeOfWork).append(", ");
        s.append("report title: ").append(reportTitle).append(", ");
        s.append("email: ").append(email).append(".");
        return s.toString();
    }
}
