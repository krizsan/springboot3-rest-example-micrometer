package se.ivankrizsan.restexample.services;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import se.ivankrizsan.restexample.domain.Circle;
import se.ivankrizsan.restexample.repositories.CircleRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service exposing operations on circles.
 * Need to override the service methods implemented in the service base class in order to have the
 * name in the @Observed annotation on this class appear in Zipkin. Otherwise the name of the base-class
 * will be shown instead in Zipkin.
 *
 * @author Ivan Krizsan
 */
@Service
@Observed(name = "CircleService")
public class CircleService extends AbstractServiceBasePlain<Circle> {

    /**
     * Creates a service instance that will use the supplied repository
     * for entity persistence.
     *
     * @param inRepository Circle repository.
     */
    public CircleService(final CircleRepository inRepository) {
        super(inRepository);
    }

    /*
     * In addition to the @Observed annotation on this (repository) interface also
     * need to include declarations of all the methods for which a span
     * is to be created by Micrometer.
     */

    @Override
    public Circle save(Circle inEntity) {
        return super.save(inEntity);
    }

    @Override
    public Circle update(Circle inEntity) {
        return super.update(inEntity);
    }

    @Override
    public Optional<Circle> find(Long inEntityId) {
        return super.find(inEntityId);
    }

    @Override
    public List<Circle> findAll() {
        return super.findAll();
    }

    @Override
    public void delete(Long inId) {
        super.delete(inId);
    }

    @Override
    public void deleteAll() {
        super.deleteAll();
    }
}
