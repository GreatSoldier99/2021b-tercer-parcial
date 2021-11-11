package oop.exams.generator;

import oop.exams.exception.BadRegionException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class CenterLicensePlateGeneratorTest {
    @Test
    public void givenACenterState_whenGenerate_thenLicensePlateStartsWith1() {

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
    public void givenACenterState_whenGenerateTwice_thenLicensesAreDifferent() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();

        // When:
        String licensePlate1 = licensePlateProvider.generate("QRO");
        String licensePlate2 = licensePlateProvider.generate("QRO");

        // Then:
        assertThat(licensePlate1).isNotEqualTo(licensePlate2);
    }

    @Test
    public void givenANonCenterState_whenGenerate_thenBadRegionExceptionIsThrown() {

        // Given:
        LicensePlateGenerator licensePlateProvider = new SouthLicensePlateGenerator();
        String randomStateAbbreviation = "X" + RandomString.make(2);

        // When:
        // Then:
        assertThatThrownBy(() -> licensePlateProvider.generate(randomStateAbbreviation))
                .isInstanceOf(BadRegionException.class)
                .hasMessage("Allowed state codes: SLP, GUA, QRO");
    }
}
