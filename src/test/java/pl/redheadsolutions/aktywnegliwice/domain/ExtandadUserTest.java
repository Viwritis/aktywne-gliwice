package pl.redheadsolutions.aktywnegliwice.domain;

import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.assertThat;
import pl.redheadsolutions.aktywnegliwice.web.rest.TestUtil;

public class ExtandadUserTest {

    @Test
    public void equalsVerifier() throws Exception {
        TestUtil.equalsVerifier(ExtandadUser.class);
        ExtandadUser extandadUser1 = new ExtandadUser();
        extandadUser1.setId(1L);
        ExtandadUser extandadUser2 = new ExtandadUser();
        extandadUser2.setId(extandadUser1.getId());
        assertThat(extandadUser1).isEqualTo(extandadUser2);
        extandadUser2.setId(2L);
        assertThat(extandadUser1).isNotEqualTo(extandadUser2);
        extandadUser1.setId(null);
        assertThat(extandadUser1).isNotEqualTo(extandadUser2);
    }
}
