package h2mcom.android.whatdoyoufeel;

class Text {
    String nameOfCity;
    double temperature;

    public Text(String nameOfCity, double temperature) {
        this.nameOfCity = nameOfCity;
        this.temperature = temperature;
    }

    public String getNameOfCity() {
        return nameOfCity;
    }

    public double getTemperature() {
        return temperature;
    }
}
