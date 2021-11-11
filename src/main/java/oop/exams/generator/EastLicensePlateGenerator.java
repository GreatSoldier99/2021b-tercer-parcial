package oop.exams.generator;

import oop.exams.exception.BadRegionException;

import java.util.ArrayList;
import java.util.Random;

public class EastLicensePlateGenerator implements LicensePlateGenerator {
    private ArrayList<String> allowedStateCodes = new ArrayList();

    public EastLicensePlateGenerator() {
        allowedStateCodes.add("CAM");
        allowedStateCodes.add("ROO");
        allowedStateCodes.add("TAB");
        allowedStateCodes.add("VER");
        allowedStateCodes.add("YUC");
    }

    public void validateStateCode(String state) throws BadRegionException{
        if (allowedStateCodes.contains(state)) {
            return;
        }
        throw new BadRegionException("Allowed state codes: CAM, ROO, TAB, VER, YUC");
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

        for (int i = 0; i < 2; i++) {
            plate = plate + random.nextInt(9);
        }

        return '3' + plate + 'Z';
    }
}
