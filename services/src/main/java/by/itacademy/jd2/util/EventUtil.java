package by.itacademy.jd2.util;

import by.itacademy.jd2.event.Factor;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public enum EventUtil implements IEventUtil{
    EVENT_UTIL;


    @Override
    public List<Factor> createFactors(double winFactor, double loseFactor, double drawFactor) {
        Factor factorWin = new Factor("win",winFactor);
        Factor factorLose = new Factor("lose",loseFactor);
        Factor factorDraw = new Factor("draw",drawFactor);
        return new ArrayList<>(Arrays.asList(factorWin,factorLose,factorDraw));
    }
}
