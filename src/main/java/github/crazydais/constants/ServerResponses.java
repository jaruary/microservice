package github.crazydais.constants;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public enum ServerResponses {

    SUCCESS(1L,
        new ResponseEntity<>(ResponseMessages.SUCCESS.json(), HttpStatus.OK)),
    FAILED(2L, new ResponseEntity<>(ResponseMessages.FAILED.json(),
        HttpStatus.BAD_REQUEST)),
    INTERNAL_ERROR(3L,
        new ResponseEntity<>(ResponseMessages.INTERNAL_ERROR.json(),
            HttpStatus.INTERNAL_SERVER_ERROR)),
    NO_REQUEST_PARAM(4L,
        new ResponseEntity<>(ResponseMessages.NO_REQUEST_PARAM.json(),
            HttpStatus.NO_CONTENT)),
    NO_REQUEST_BODY(5L,
        new ResponseEntity<>(ResponseMessages.NO_REQUEST_BODY.json(),
            HttpStatus.NO_CONTENT)),;

    Long id;
    private ResponseEntity<String> response;

    ServerResponses(Long id, ResponseEntity<String> response) {

        this.id = id;
        this.response = response;
    }

    public Long id() {

        return id;
    }

    public ResponseEntity<String> response() {

        return response;
    }


}
