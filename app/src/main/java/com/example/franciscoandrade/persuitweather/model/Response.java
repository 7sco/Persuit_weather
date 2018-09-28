package com.example.franciscoandrade.persuitweather.model;

import java.io.Serializable;
import java.util.List;

public class Response implements Serializable {

    private List<Periods> periods;
    public List<Periods> getPeriods() {
        return periods;
    }
}
