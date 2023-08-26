package se.ivankrizsan.restexample.domain;

import com.fasterxml.jackson.annotation.JsonTypeInfo;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.awt.*;

/**
 * Abstract base class for shapes in a drawing.
 *
 * @author Ivan Krizsan
 */
@Getter
@Setter
@Accessors(prefix = "m")
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
/* Need to include type information since there are collections that contain shapes. */
@JsonTypeInfo(use = JsonTypeInfo.Id.MINIMAL_CLASS,
    include = JsonTypeInfo.As.PROPERTY, property = "shapeType")
public abstract class Shape extends LongIdEntity {
    /* Constant(s): */

    /* Instance variable(s): */
    @Column(name = "colour", nullable = false)
    protected String mColour;
    @Column(name = "position", nullable = false)
    protected Point mPosition;
}
