package pl.redheadsolutions.aktywnegliwice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.redheadsolutions.aktywnegliwice.web.rest.TestUtil;

public class SportsFacilityTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(SportsFacility.class);
        SportsFacility sportsFacility1 = new SportsFacility();
        sportsFacility1.setId(1L);
        SportsFacility sportsFacility2 = new SportsFacility();
        sportsFacility2.setId(sportsFacility1.getId());
        assertThat(sportsFacility1).isEqualTo(sportsFacility2);
        sportsFacility2.setId(2L);
        assertThat(sportsFacility1).isNotEqualTo(sportsFacility2);
        sportsFacility1.setId(null);
        assertThat(sportsFacility1).isNotEqualTo(sportsFacility2);
    }
}
