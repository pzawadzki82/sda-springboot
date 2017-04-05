package pl.sda.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import pl.sda.ExceptionWrapper;

/**
 * Created by pzawa on 05.04.2017.
 */
@ControllerAdvice
public class RestExceptionHandler {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExceptionHandler.class);

    @ExceptionHandler(value = Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ExceptionWrapper handleInternalError(Exception ex) {
        return handleException(ex);
    }

    private ExceptionWrapper handleException(Exception ex) {
        LOGGER.error("Request execution error", ex);
        return new ExceptionWrapper(ex);
    }

}
