public class Map {
    private String city;
    private String office;
    private String university;
    private double distance;

    public Map(String city, String office, String university, double distance) {
        this.city = city;
        this.office = office;
        this.university = university;
        this.distance = distance;
    }

    public String getCity() {
        return this.city;
    }

    public String getOffice() {
        return this.office;
    }

    public String getUniversity() {
        return this.university;
    }

    public double getDistance() {
        return this.distance;
    }
}
