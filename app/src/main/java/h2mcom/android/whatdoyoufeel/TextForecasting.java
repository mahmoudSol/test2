package h2mcom.android.whatdoyoufeel;

class TextForecasting {
    String data;

    double Temperature;

    public TextForecasting(String data,  double temperature) {
        this.data = data;

        Temperature = temperature;
    }

    public String getData() {
        return data;
    }



    public double getTemperature() {
        return Temperature;
    }
}
