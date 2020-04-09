package pl.redheadsolutions.aktywnegliwice.domain;

import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import javax.validation.constraints.*;

import java.io.Serializable;
import java.util.Objects;

import pl.redheadsolutions.aktywnegliwice.domain.enumeration.SportsFacilityType;

/**
 * A SportsFacility.
 */
@Entity
@Table(name = "sports_facility")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class SportsFacility implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "sequenceGenerator")
    @SequenceGenerator(name = "sequenceGenerator")
    private Long id;

    @NotNull
    @Column(name = "housing_association_name", nullable = false)
    private String housingAssociationName;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(name = "type", nullable = false)
    private SportsFacilityType type;

    @OneToOne
    @JoinColumn(unique = true)
    private Location location;

    // jhipster-needle-entity-add-field - JHipster will add fields here, do not remove
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHousingAssociationName() {
        return housingAssociationName;
    }

    public SportsFacility housingAssociationName(String housingAssociationName) {
        this.housingAssociationName = housingAssociationName;
        return this;
    }

    public void setHousingAssociationName(String housingAssociationName) {
        this.housingAssociationName = housingAssociationName;
    }

    public SportsFacilityType getType() {
        return type;
    }

    public SportsFacility type(SportsFacilityType type) {
        this.type = type;
        return this;
    }

    public void setType(SportsFacilityType type) {
        this.type = type;
    }

    public Location getLocation() {
        return location;
    }

    public SportsFacility location(Location location) {
        this.location = location;
        return this;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
    // jhipster-needle-entity-add-getters-setters - JHipster will add getters and setters here, do not remove

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof SportsFacility)) {
            return false;
        }
        return id != null && id.equals(((SportsFacility) o).id);
    }

    @Override
    public int hashCode() {
        return 31;
    }

    @Override
    public String toString() {
        return "SportsFacility{" +
            "id=" + getId() +
            ", housingAssociationName='" + getHousingAssociationName() + "'" +
            ", type='" + getType() + "'" +
            "}";
    }
}
