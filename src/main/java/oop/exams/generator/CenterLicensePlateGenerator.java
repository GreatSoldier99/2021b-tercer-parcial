package oop.exams.generator;

import oop.exams.exception.BadRegionException;

import java.util.ArrayList;
import java.util.Random;

public class CenterLicensePlateGenerator implements LicensePlateGenerator{
    private ArrayList<String> allowedStateCodes = new ArrayList<>();

    public CenterLicensePlateGenerator() {
        allowedStateCodes.add("SLP");
        allowedStateCodes.add("GUA");
        allowedStateCodes.add("QRO");
    }

    public void validateStateCode(String state) throws BadRegionException{
        if (allowedStateCodes.contains(state)) {
            return;
        }
        throw new BadRegionException("Allowed state codes: SLP, GUA, QRO");
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
