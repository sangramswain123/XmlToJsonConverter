package com.lendingkart.service.impl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.lendingkart.service.XmlToJsonService;
import com.lendingkart.utils.XmlToJsonUtils;

@Service
public class XmlToJsonServiceImpl implements XmlToJsonService{
	
	private Logger logger = LoggerFactory.getLogger(XmlToJsonUtils.class);
	
	@Autowired
	private XmlToJsonUtils utils;
	
	@Override
	public String convertXmlToJson(String xml) {
		logger.debug("Xml to json convertion process stated in service");
		return utils.convertXmlToJson(xml);
	}

	
}
