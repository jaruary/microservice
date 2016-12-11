package github.crazydais.rest.controller;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectController {

    private final Log log = LogFactory.getLog(ConnectController.class);

    // Create

    // Read
    @RequestMapping(value = "/api/connect/getById", method = RequestMethod.GET)
    public String getCustomerById(
        @RequestParam(value = "id", defaultValue = "0") Long id) {

        log.info("ConnectController called with id: " + id);
        return "(function () {console.log('This came from the web!')})();";
    }

    // Update

    // Delete
}
