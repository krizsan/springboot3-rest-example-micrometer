package se.ivankrizsan.restexample.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Represents a rectangle shape with a given height and width.
 *
 * @author Ivan Krizsan
 */
@Getter
@Setter
@Accessors(prefix = "m")
@NoArgsConstructor
@Entity(name = "Rectangle")
public class Rectangle extends Shape {
    /* Constant(s): */
    public static final int DEFAULT_WIDTH = 10;
    public static final int DEFAULT_HEIGHT = 10;

    /* Instance variable(s): */
    @Column(name = "height", nullable = false)
    protected Integer mHeight = DEFAULT_HEIGHT;
    @Column(name = "width", nullable = false)
    protected Integer mWidth = DEFAULT_WIDTH;

    /**
     * Creates a new rectangle with the supplied height and width.
     *
     * @param inHeight Height of new rectangle.
     * @param inWidth Width of new rectangle.
     */
    public Rectangle(final Integer inHeight, final Integer inWidth) {
        mHeight = inHeight;
        mWidth = inWidth;
    }
}
