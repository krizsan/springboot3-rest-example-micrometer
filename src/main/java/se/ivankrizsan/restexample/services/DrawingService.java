package se.ivankrizsan.restexample.services;

import io.micrometer.observation.annotation.Observed;
import org.springframework.stereotype.Service;
import se.ivankrizsan.restexample.domain.Drawing;
import se.ivankrizsan.restexample.repositories.DrawingRepository;

import java.util.List;
import java.util.Optional;

/**
 * Service exposing operations on drawings.
 *
 * @author Ivan Krizsan
 */
@Service
@Observed(name = "DrawingService")
public class DrawingService extends AbstractServiceBasePlain<Drawing> {

    /**
     * Creates a service instance that will use the supplied repository
     * for entity persistence.
     *
     * @param inRepository Drawing repository.
     */
    public DrawingService(final DrawingRepository inRepository) {
        super(inRepository);
    }

    /*
     * In addition to the @Observed annotation on this (repository) interface also
     * need to include declarations of all the methods for which a span
     * is to be created by Micrometer.
     */

    @Override
    public Drawing save(Drawing inEntity) {
        return super.save(inEntity);
    }

    @Override
    public Drawing update(Drawing inEntity) {
        return super.update(inEntity);
    }

    @Override
    public Optional<Drawing> find(Long inEntityId) {
        return super.find(inEntityId);
    }

    @Override
    public List<Drawing> findAll() {
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
