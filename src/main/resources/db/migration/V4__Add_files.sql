SET SCHEMA `microservice`;

INSERT INTO `file` (`created`, `extension`, `filedata`, `name`, `updated`, `customerid`) VALUES
(CURRENT_TIMESTAMP(), 'txt', '54686973206973206120746578742066696c652e2e2e', 'myfile', CURRENT_TIMESTAMP(), 1);