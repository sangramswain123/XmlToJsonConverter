package com.lendingkart.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lendingkart.exception.CustomXmlJsonConvertionException;
import com.lendingkart.service.XmlToJsonService;
import com.lendingkart.utils.XmlToJsonUtils;

@RestController
@RequestMapping("/convert")
public class XmlToJsonRestController {
	
	private Logger logger = LoggerFactory.getLogger(XmlToJsonUtils.class);
	
	@Autowired
    private XmlToJsonService xmlToJsonService;

    @PostMapping("/xml-to-json")
    public ResponseEntity<String> convertXmlToJson(@RequestBody String xmlInput) {
        try {
        	logger.debug("Xml to Json convertion process started from controller");
            String jsonData = xmlToJsonService.convertXmlToJson(xmlInput);
            logger.info("Xml to Json converted successfully");
            return new ResponseEntity<>(jsonData, HttpStatus.OK);
        } catch (Exception e) {
            throw new CustomXmlJsonConvertionException(e.getMessage());
        }
    }
}
