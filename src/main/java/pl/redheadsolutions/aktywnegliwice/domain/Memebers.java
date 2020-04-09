package pl.redheadsolutions.aktywnegliwice.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;

import java.io.Serializable;
import java.util.Objects;

/**
 * A Memebers.
 */
@Entity
@Table(name = "memebers")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Memebers implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne
    @JsonIgnoreProperties("memebers")
    private ExtandadUser extandadUser;

    @ManyToOne
    @JsonIgnoreProperties("memebers")
    private SportsFacility sportsFacility;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public Memebers name(String name) {
        this.name = name;
        return this;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ExtandadUser getExtandadUser() {
        return extandadUser;
    }

    public Memebers extandadUser(ExtandadUser extandadUser) {
        this.extandadUser = extandadUser;
        return this;
    }

    public void setExtandadUser(ExtandadUser extandadUser) {
        this.extandadUser = extandadUser;
    }

    public SportsFacility getSportsFacility() {
        return sportsFacility;
    }

    public Memebers sportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
        return this;
    }

    public void setSportsFacility(SportsFacility sportsFacility) {
        this.sportsFacility = sportsFacility;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Memebers)) {
            return false;
        }
        return id != null && id.equals(((Memebers) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "Memebers{" +
            "id=" + getId() +
            ", name='" + getName() + "'" +
            "}";
    }
}
