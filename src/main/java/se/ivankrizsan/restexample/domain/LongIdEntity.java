package se.ivankrizsan.restexample.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Objects;

/**
 * Abstract base class for JPA entities that have a long integer id.
 *
 * @author Ivan Krizsan
 */
@Getter
@Setter
@Accessors(prefix = "m")
@MappedSuperclass
public abstract class LongIdEntity {
    /* Constant(s): */

    /* Instance variable(s): */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    protected Long mId;

    @Override
    public boolean equals(final Object inOtherObject) {
        if (this == inOtherObject) {
            return true;
        }
        if (inOtherObject == null || getClass() != inOtherObject.getClass()) {
            return false;
        }

        final LongIdEntity theOtherEntity = (LongIdEntity) inOtherObject;

        return Objects.equals(mId, theOtherEntity.mId);

    }

    @Override
    public int hashCode() {
        return mId != null ? mId.hashCode() : 0;
    }
}
