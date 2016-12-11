package github.crazydais.constants;

public enum ResponseMessages {

    SUCCESS(1L, "{'status': 'success'}"),
    FAILED(2L, "{'status': 'failed'}"),
    INTERNAL_ERROR(3L, "{'status': 'internal error'}"),
    NO_REQUEST_PARAM(4L,
        "{'status': 'one or more request params were not found'}"),
    NO_REQUEST_BODY(5L, "{'status': 'the request body was not found'}"),;

    private final Long id;
    private final String json;

    ResponseMessages(Long id, String json) {

        this.id = id;
        this.json = json;
    }

    public Long id() {

        return id;
    }

    public String json() {

        return json;
    }


}
