package oop.exams.generator;

import oop.exams.exception.BadRegionException;

import java.util.ArrayList;
import java.util.Random;

public class DefaultLicensePlateGenerator implements LicensePlateGenerator {
    private ArrayList<String> allowedStateCodes = new ArrayList<>();

    public DefaultLicensePlateGenerator() {
        allowedStateCodes.add("AGU");
        allowedStateCodes.add("CMX");
        allowedStateCodes.add("DUR");
        allowedStateCodes.add("GUA");
        allowedStateCodes.add("HID");
        allowedStateCodes.add("MEX");
        allowedStateCodes.add("PUE");
        allowedStateCodes.add("QUQ");
        allowedStateCodes.add("SLP");
        allowedStateCodes.add("TLA");
        allowedStateCodes.add("ZAC");
    }

    public void validateStateCode(String state) throws BadRegionException {
        if (allowedStateCodes.contains(state)) {
            return;
        }
        throw new BadRegionException("Allowed state codes: AGU, CMX, DUR, GUA, HID, MEX, PUE, QUE, SLP, TLA, ZAC");
    }

    @Override
    public String generate(String state) throws BadRegionException {
        String plate = "";
        Random random = new Random();

        try {
            validateStateCode(state);
        } catch(BadRegionException ex) {
            throw ex;
        }

        for (int i = 0; i < 7; i++) {
            plate = plate + random.nextInt(9);
        }
        return '5' + plate;
    }
}