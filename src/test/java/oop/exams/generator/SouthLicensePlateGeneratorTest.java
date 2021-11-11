package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SouthLicensePlateGeneratorTest {

    @Test
    public void givenASouthState_whenGenerate_thenLicensePlateStartsWith4() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();
        String state = "OAX";

        // When:
        String licensePlate = licensePlateProvider.generate(state);

        // Then:
        assertThat(licensePlate).startsWith("4");
        assertThat(licensePlate).hasSize(7);
        assertThat(licensePlate).contains(state);
    }

    @Test
    public void givenASouthState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("GRO");
        String licensePlate2 = licensePlateProvider.generate("GRO");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonSouthState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: CHP, GRO, MIC, OAX");
    }
}
