package se.ivankrizsan.restexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represents a circle shape with a given radius.
 *
 * @author Ivan Krizsan
 */
@Getter
@Setter
@Accessors(prefix = "m")
@NoArgsConstructor
@Entity(name = "Circle")
public class Circle extends Shape {
    /* Constant(s): */
    public static final int DEFAULT_RADIUS = 10;

    /* Instance variable(s): */
    @NonNull
    @Column(name = "radius", nullable = false)
    protected Integer mRadius = DEFAULT_RADIUS;

    /**
     * Creates a circlue with the suoplied radius.
     *
     * @param inRadius Radius of the new circle.
     */
    public Circle(@NonNull final Integer inRadius) {
        mRadius = inRadius;
    }
}
