package pl.redheadsolutions.aktywnegliwice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.redheadsolutions.aktywnegliwice.web.rest.TestUtil;

public class MemebersTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(Memebers.class);
        Memebers memebers1 = new Memebers();
        memebers1.setId(1L);
        Memebers memebers2 = new Memebers();
        memebers2.setId(memebers1.getId());
        assertThat(memebers1).isEqualTo(memebers2);
        memebers2.setId(2L);
        assertThat(memebers1).isNotEqualTo(memebers2);
        memebers1.setId(null);
        assertThat(memebers1).isNotEqualTo(memebers2);
    }
}
