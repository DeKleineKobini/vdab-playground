package world.inetum.realdolmen.playground.api.jms;


public class PlayCommand {

    private String email;
    private String country;

    public PlayCommand() {
    }

    public PlayCommand(String email, String country) {
        this.email = email;
        this.country = country;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "PlayCommand{" +
                "email='" + email + '\'' +
                ", country='" + country + '\'' +
                '}';
    }

}
