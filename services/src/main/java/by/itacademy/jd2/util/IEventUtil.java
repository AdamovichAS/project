package by.itacademy.jd2.util;

import by.itacademy.jd2.event.Factor;

import java.util.List;

public interface IEventUtil {

    List<Factor> createFactors(double winFactor, double loseFactor, double drawFactor);
}
