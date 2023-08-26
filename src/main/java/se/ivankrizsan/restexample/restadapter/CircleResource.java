package se.ivankrizsan.restexample.restadapter;

import io.micrometer.observation.annotation.Observed;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.services.CircleService;

import java.util.List;

/**
 * REST resource exposing operations on circles.
 *
 * @author Ivan Krizsan
 */
@RestController
@RequestMapping(value = CircleResource.PATH)
@Observed(name = "CircleResource")
public class CircleResource extends RestResourceBasePlain<Circle> {
    /* Constant(s): */
    public static final String PATH = "/circles";

    /**
     * Creates a REST resource using the supplied service to manipulate entities.
     *
     * @param inService Service used to manipulate entities.
     */
    public CircleResource(final CircleService inService) {
        setService(inService);
    }

    @Override
    protected Circle[] entityListToArray(final List<Circle> inEntityList) {
        return inEntityList.toArray(new Circle[0]);
    }
}
