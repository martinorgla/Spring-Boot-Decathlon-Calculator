package com.industry62.decathlonrestservice;

public enum DecathlonEvent {
    M100("100 m", 25.4347, 18.0, 1.81, SportType.TRACK),
    LONG_JUMP("Long jump", 0.14354, 220.0, 1.4, SportType.FIELD),
    SHOT_PUT("Shot put", 51.39, 1.5, 1.05, SportType.FIELD),
    HIGH_JUMP("High jump", 0.8465, 75.0, 1.42, SportType.FIELD),
    M400("400 m", 1.53775, 82.0, 1.81, SportType.TRACK),
    M110_HURDLES("110 m hurdles", 5.74352, 28.5, 1.92, SportType.TRACK),
    DISCUS_THROW("Discus throw", 12.91, 4.0, 1.1, SportType.FIELD),
    POLE_VAULT("Pole vault", 0.2797, 100.0, 1.35, SportType.FIELD),
    JAVELIN_THROW("Javelin throw", 10.14, 7.0, 1.08, SportType.FIELD),
    M1500("1500 m",0.03768, 480.0, 1.85, SportType.TRACK);

    DecathlonEvent(String name, Double a, Double b, Double c, SportType sportType) {
        this.name = name;
        this.a = a;
        this.b = b;
        this.c = c;
        this.sportType = sportType;
    }

    private String name;
    private Double a;
    private Double b;
    private Double c;
    private SportType sportType;

    public String getName() {
        return name;
    }

    public Double getA() {
        return a;
    }

    public Double getB() {
        return b;
    }

    public Double getC() {
        return c;
    }

    public SportType getSportType() {
        return sportType;
    }
}
