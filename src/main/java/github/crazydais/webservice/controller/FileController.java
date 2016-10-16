package github.crazydais.webservice.controller;

import github.crazydais.constants.ServerResponses;
import github.crazydais.data.entity.FileEntity;
import github.crazydais.data.repository.CustomerRepository;
import github.crazydais.utils.file.FileUtils;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.List;
import java.util.UUID;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.sql.rowset.serial.SerialBlob;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import github.crazydais.data.repository.FileRepository;

@RestController
public class FileController {

    private final Log LOGGER = LogFactory.getLog(FileController.class);

    @Autowired
    FileRepository fileRepo;

    @Autowired
    CustomerRepository custRepo;

    // Create
    @RequestMapping(value = "/api/file/add", method = RequestMethod.POST)
    public ResponseEntity<String> addFile(
            @RequestParam(value = "customerId", required = false) Long customerId,
            @RequestParam(value = "name", required = false) String name,
            @RequestParam(value = "ext", required = false) String fileTypeExtension,
            @RequestBody byte[] fileData,
            HttpServletRequest request) {
        LOGGER.info("Receiving file: customerId=" + customerId + ", name=" + name + ", ext=" + fileTypeExtension);
        FileEntity file = new FileEntity();
        try {
            if (fileData != null) {
                file.setCustomer(custRepo.findById(customerId));
                file.setFileData(new SerialBlob(fileData));
                file.setName(name);
                file.setExtension(fileTypeExtension);
                this.fileRepo.save(file);
                LOGGER.info(request.getMethod() + " : " + request.getServletPath() + " : " + file.getClass().getSimpleName() + " : SUCCESS \n");
                return ServerResponses.SUCCESS.response();
            } else {
                throw new NullPointerException("No file was posted to the FileController.");
            }
        } catch (SQLException | NullPointerException ex) {
            LOGGER.error(request.getMethod() + " : " + request.getServletPath() + " : " + file.getClass().getSimpleName() + " : FAILED \n", ex);
            return ServerResponses.NO_REQUEST_BODY.response();
        }
    }

    // Read
    @RequestMapping(value = "/api/file/getById", method = RequestMethod.GET)
    public ResponseEntity<String> getFileById(
            @RequestParam(value = "id", required = true) Long id) {

        try {
            FileEntity file = fileRepo.findById(id);
            Blob blob = file.getFileData();
            int blobLength = (int) blob.length();
            byte[] fileData = blob.getBytes(1, blobLength);
            blob.free();
            FileUtils.saveFileDataToFileSystem(fileData, file.getName(), file.getExtension());
        } catch (SQLException e) {
            LOGGER.error(e);
        }

        return ServerResponses.SUCCESS.response();
    }

    @RequestMapping(value = "/api/file/getAll", method = RequestMethod.GET)
    public List<FileEntity> getFiles(HttpSession session, @RequestParam(required = true, defaultValue = "true") Boolean all) {
        UUID uid = (UUID) session.getAttribute("uid");
        if (uid == null) {
            uid = UUID.randomUUID();
        }
        session.setAttribute("uid", uid);
        return fileRepo.findAll();
    }

    // Update
    @RequestMapping(value = "/api/file/updateById", method = RequestMethod.PUT)
    public ResponseEntity<String> updateFileById(
            @RequestParam(value = "id", required = true) Long id,
            @RequestParam(value = "firstName", required = true) String fname,
            @RequestParam(value = "lastName", required = true) String lname) {
//    try {
//      Zip updateMe = zipRepo.findById(id);
//      updateMe.setFirstName(fname);
//      zipRepo.save(updateMe);
//      return new ResponseEntity<>("{status: 'success'}", HttpStatus.OK);
//    } catch (Exception ex) {
//      // TODO : ex
//      return new ResponseEntity<>("{status: 'failed'}", HttpStatus.BAD_REQUEST);
//    }
        return null;
    }

    // Delete
    @RequestMapping(value = "/api/file/deleteById", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteFileById(
            @RequestParam(value = "id", required = true) Long id) {
        try {
            fileRepo.delete(id);
            return new ResponseEntity<>("{status: 'success'}", HttpStatus.OK);
        } catch (Exception ex) {
            // TODO : ex
            return new ResponseEntity<>("{status: 'failed'}", HttpStatus.BAD_REQUEST);
        }
    }
}
