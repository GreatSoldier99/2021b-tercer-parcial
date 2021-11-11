package oop.exams.generator;

import oop.exams.exception.BadRegionException;

import java.util.ArrayList;
import java.util.Random;

public class NorthLicensePlateGenerator implements LicensePlateGenerator {
    private ArrayList<String> allowedStateCodes = new ArrayList<>();

    public NorthLicensePlateGenerator() {
        allowedStateCodes.add("BCN");
        allowedStateCodes.add("BCS");
        allowedStateCodes.add("CHH");
        allowedStateCodes.add("COA");
        allowedStateCodes.add("NLE");
        allowedStateCodes.add("SON");
        allowedStateCodes.add("TAM");
    }

    public void validateStateCode(String state) throws BadRegionException{
        if (allowedStateCodes.contains(state)) {
            return;
        }
        throw new BadRegionException("Allowed state codes: BCN, BCS, CHH, COA, NLE, SON, TAM");
    }

    @Override
    public String generate(String state) throws BadRegionException {
        String plate = "";
        Random random = new Random();

        for (int i = 0; i < 2; i++) {
            plate = plate + random.nextInt(9);
        }

        try {
            validateStateCode(state);
        } catch(BadRegionException ex) {
            throw ex;
        }
        return '1' + state + plate;
    }
}
