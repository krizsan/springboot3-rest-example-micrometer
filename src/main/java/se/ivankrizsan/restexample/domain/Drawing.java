package se.ivankrizsan.restexample.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * A drawing that consists of an arbitrary number of shapes.
 *
 * @author Ivan Krizsan
 */
@Getter
@Setter
@Accessors(prefix = "m")
@Entity(name = "Drawing")
@Table(name = "Drawings")
public class Drawing extends LongIdEntity {
    /* Constant(s): */

    /* Instance variable(s): */
    @Column(name = "name", nullable = false)
    protected String mName;
    @Column(name = "creationDate", nullable = false)
    protected Date mCreationDate;
    @OneToMany(fetch = FetchType.EAGER, orphanRemoval = true, cascade = CascadeType.ALL)
    @JoinTable(
        name = "DrawingShapes",
        joinColumns = {@JoinColumn(name = "drawing_id")},
        inverseJoinColumns = {@JoinColumn(name = "shape_id")})
    protected Set<Shape> mShapes = new HashSet<>();

    /**
     * Adds the supplied shape to the current shapes of the drawing.
     *
     * @param inShape Shape to add to drawing.
     */
    public void addShape(final Shape inShape) {
        mShapes.add(inShape);
    }
}
