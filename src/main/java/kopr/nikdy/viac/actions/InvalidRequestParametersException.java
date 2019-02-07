package kopr.nikdy.viac.actions;

import java.io.IOException;

/**
 * Request parameters are missing or couldn't be parsed
 */
public class InvalidRequestParametersException extends IOException {

    /**
     * @param requiredParameterFormat Example of how request parameters should look for request to work correctly
     */
    public InvalidRequestParametersException(String requiredParameterFormat) {
        super("Parameters need to be in format " + requiredParameterFormat);
    }

}
