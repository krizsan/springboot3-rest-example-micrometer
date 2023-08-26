package se.ivankrizsan.restexample.restadapter;

import jakarta.persistence.EntityNotFoundException;
import jakarta.validation.constraints.NotNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.ivankrizsan.restexample.domain.LongIdEntity;
import se.ivankrizsan.restexample.services.AbstractServiceBasePlain;

import java.util.List;
import java.util.Optional;
import java.util.function.Supplier;

/**
 * Abstract base class for REST resources exposing operations on an entity type.
 * All operations will return HTTP status 500 with a plain text body containing an
 * error message if an error occurred during request processing.
 *
 * @param <E> Entity type.
 * @author Ivan Krizsan
 */
@Slf4j
@RequestMapping(
        produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_PLAIN_VALUE},
        consumes = {MediaType.APPLICATION_JSON_VALUE})
public abstract class RestResourceBasePlain<E extends LongIdEntity> {
    /* Constant(s): */

    /* Instance variable(s): */
    protected AbstractServiceBasePlain<E> mService;


    /**
     * Retrieves all entities.
     *
     * @return HTTP response object with HTTP status 200 if operation succeeded or
     * HTTP error status code and a plain-text error message if an error occurred.
     */
    @GetMapping
    public ResponseEntity<?> getAll() {
        log.info("Received request to get all entities");

        return performServiceOperation(
                () -> {
                    final List<E> theEntitiesList = mService.findAll();
                    return ResponseEntity
                            .ok()
                            .contentType(MediaType.APPLICATION_JSON)
                            .body(theEntitiesList);
                },
                500, "An error occurred retrieving all entities: "
        );
    }

    /**
     * Deletes the entity with supplied id.
     *
     * @param inEntityId Id of entity to delete.
     * @return HTTP response object with HTTP status 200 if operation succeeded or
     * HTTP error status code and a plain-text error message if an error occurred.
     */
    @DeleteMapping(path = "{id}")
    public ResponseEntity<?> deleteEntityById(@PathVariable("id") @NotNull final Long inEntityId) {
        log.info("Received request to delete entity with id {}", inEntityId);

        return performServiceOperation(
                () -> {
                    mService.delete(inEntityId);
                    return ResponseEntity
                            .ok()
                            .build();
                },
                500, "An error occurred deleting entity with id " + inEntityId + ": "
        );
    }

    /**
     * Deletes all entities.
     * Will return HTTP status 500 if error occurred during request processing.
     *
     * @return HTTP response object with HTTP status 200 if operation succeeded or
     * HTTP error status code and a plain-text error message if an error occurred.
     */
    @DeleteMapping
    public ResponseEntity<?> deleteAllEntities() {
        log.info("Received request to delete all entities");

        return performServiceOperation(
                () -> {
                    mService.deleteAll();
                    return ResponseEntity
                            .ok()
                            .build();
                },
                500, "An error occurred deleting all entities: "
        );
    }

    /**
     * Retrieves entity with supplied id.
     *
     * @param inEntityId Id of entity to retrieve.
     * @return HTTP response object with HTTP status 200 if operation succeeded or
     * HTTP error status code and a plain-text error message if an error occurred.
     */
    @GetMapping(path = "{id}")
    public ResponseEntity<?> getEntityById(@PathVariable("id") Long inEntityId) {
        log.info("Received request to get entity with id {}", inEntityId);

        return performServiceOperation(
                () -> {
                    final Optional<E> theEntityOptional = mService.find(inEntityId);
                    if (theEntityOptional.isEmpty()) {
                        throw new EntityNotFoundException();
                    }
                    return ResponseEntity
                            .ok(theEntityOptional.get());
                },
                500, "An error occurred finding entity with id " + inEntityId + ": "
        );
    }

    /**
     * Updates the entity with supplied id by overwriting it with the supplied entity.
     *
     * @param inEntity   Entity data to write.
     * @param inEntityId Id of entity to update.
     * @return HTTP response object with HTTP status 200 if operation succeeded or
     * HTTP error status code and a plain-text error message if an error occurred.
     */
    @PutMapping(path = "{id}")
    public ResponseEntity<?> updateEntity(@RequestBody final E inEntity,
                                          @PathVariable("id") @NotNull final Long inEntityId) {
        log.info("Received request to update entity with id {}", inEntityId);

        final ResponseEntity<?> theResponse = performServiceOperation(
                () -> {
                    inEntity.setId(inEntityId);
                    final E theEntity = mService.update(inEntity);
                    return ResponseEntity.ok(theEntity);
                },
                500, "An error occurred updating entity with id "
                        + inEntityId + ": "
        );
        return theResponse;
    }

    /**
     * Creates a new entity using the supplied entity data.
     *
     * @param inEntity Entity data to use when creating new entity.
     * @return HTTP response object with HTTP status 200 containing entity representation
     * if operation succeeded or HTTP error status code and a plain-text error message
     * if an error occurred.
     */
    @PostMapping
    public ResponseEntity<?> createEntity(@RequestBody final E inEntity) {
        log.info("Received request to create a new entity");

        return performServiceOperation(
                () -> {
                    ResponseEntity<?> theResponse;
                    if (inEntity.getId() != null) {
                        theResponse = ResponseEntity
                                .status(400)
                                .contentType(MediaType.TEXT_PLAIN)
                                .body("Id must not be set on new entity");
                    } else {
                        final E theEntity = mService.save(inEntity);
                        theResponse = ResponseEntity
                                .status(200)
                                .contentType(MediaType.APPLICATION_JSON)
                                .body(theEntity);
                    }
                    return theResponse;
                },
                500, "An error occurred creating a new entity: "
        );
    }

    /**
     * Performs the operation as defined by the supplied response supplier.
     * If the operation completes without errors, the response being the result of the operation
     * is returned as result of this method.
     * If an error occurs during the operation, an error response is created with the
     * supplied HTTP error status and the supplied plain-text error message with the message
     * from the exception that occurred appended.
     *
     * @param inResponseSupplier Operation to complete.
     * @param inErrorHttpStatus  HTTP status to set in response in case of error.
     * @param inErrorMessage     Plain-text error message to set in response in case of error.
     * @return Response object.
     */
    protected static ResponseEntity<?> performServiceOperation(final Supplier<ResponseEntity<?>> inResponseSupplier,
                                                               final int inErrorHttpStatus, final String inErrorMessage) {
        ResponseEntity<?> theResponse;
        try {
            theResponse = inResponseSupplier.get();
        } catch (final Throwable theException) {
            theResponse = ResponseEntity
                    .status(inErrorHttpStatus)
                    .contentType(MediaType.TEXT_PLAIN)
                    .body(inErrorMessage + theException.getMessage());
        }
        return theResponse;
    }

    /**
     * Creates an array containing the entities in the supplied list.
     *
     * @param inEntityList List of entities.
     * @return Array containing the entities from the list.
     */
    protected abstract E[] entityListToArray(List<E> inEntityList);

    public AbstractServiceBasePlain<E> getService() {
        return mService;
    }

    public void setService(final AbstractServiceBasePlain<E> inService) {
        mService = inService;
    }
}
