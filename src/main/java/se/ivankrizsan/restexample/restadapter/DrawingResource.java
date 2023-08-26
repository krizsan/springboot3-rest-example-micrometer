package se.ivankrizsan.restexample.restadapter;

import io.micrometer.observation.annotation.Observed;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import se.ivankrizsan.restexample.domain.Drawing;
import se.ivankrizsan.restexample.services.DrawingService;

import java.util.List;

/**
 * REST resource exposing operations on drawings.
 *
 * @author Ivan Krizsan
 */
@RestController
@RequestMapping(value = DrawingResource.PATH, produces = { MediaType.APPLICATION_JSON_VALUE })
@Observed(name = "DrawingResource")
public class DrawingResource extends RestResourceBasePlain<Drawing> {
    /* Constant(s): */
    public static final String PATH = "/drawings";

    /**
     * Creates a REST resource using the supplied service to manipulate entities.
     *
     * @param inService Service used to manipulate entities.
     */
    public DrawingResource(final DrawingService inService) {
        setService(inService);
    }

    @Override
    protected Drawing[] entityListToArray(final List<Drawing> inEntityList) {
        return inEntityList.toArray(new Drawing[0]);
    }
}
