package com.lendingkart.utils;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;
import com.fasterxml.jackson.dataformat.xml.XmlMapper;
import com.lendingkart.exception.CustomXmlJsonConvertionException;

@Component
public class XmlToJsonUtils {
	
	private Logger logger = LoggerFactory.getLogger(XmlToJsonUtils.class);
	
	public String convertXmlToJson(String xml) {
		try {
			logger.debug("Xml to JSon convertion process started");
			XmlMapper xmlMapper = new XmlMapper();
            JsonNode rootNode = xmlMapper.readTree(xml);           
            
            int totalMatchScore = calculateTotalMatchScore(rootNode);
            logger.debug("Total Match Score calculated : ", totalMatchScore);
            
            ObjectMapper objectMapper = new ObjectMapper();
            ObjectNode matchSummary = objectMapper.createObjectNode();
            matchSummary.put("TotalMatchScore", totalMatchScore);
            
            JsonNode matchDetailsNode = rootNode.path("ResultBlock");          
            if (matchDetailsNode.isObject()) {
                ObjectNode matchDetailsObject = (ObjectNode) matchDetailsNode;
                matchDetailsObject.set("MatchSummary", matchSummary);               
            }
            
            String json = objectMapper.writerWithDefaultPrettyPrinter().writeValueAsString(rootNode); 
            logger.debug("Xml to Json convertion process complected");
            logger.info("Xml successfully converted into Json");
            
	        return json;
        } catch (JsonProcessingException e) {
            logger.error("Error parsing XML or generating JSON:", e.getMessage());
            throw new CustomXmlJsonConvertionException(e.getMessage());
        } catch (IOException e) {
            logger.error("Error reading XML file:", e.getMessage());
            throw new CustomXmlJsonConvertionException(e.getMessage());
        }
	}
	
	private static int calculateTotalMatchScore(JsonNode rootNode) {
	    int totalScore = 0;	    	    
	    JsonNode matchDetailsNode = rootNode.path("ResultBlock").path("MatchDetails").path("Match");
	    
	    
	    for (JsonNode match : matchDetailsNode) {
	    	
            int score = match.path("Scores").asInt();
            totalScore += score;
        }	        
	    return totalScore;
	}

	        
}
