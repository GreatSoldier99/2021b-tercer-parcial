package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class WestLicensePlateGeneratorTest {

    @Test
    public void givenAWestState_whenGenerate_thenLicensePlateStartsWith2() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new WestLicensePlateGenerator();
        String state = "SIN";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("2");
        assertThat(licensePlate).endsWith(state);
        assertThat(licensePlate).hasSize(5);
        assertThat(licensePlate.substring(0, 2)).containsOnlyDigits();
    }

    @Test
    public void givenAWestState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new WestLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("NAY");
        String licensePlate2 = licensePlateProvider.generate("NAY");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonWestState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new WestLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: COL, JAL, NAY, SIN");
    }
}
