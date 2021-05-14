package leo.bachelorsthesis.btempathy.configuration;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import feign.HeaderMap;
import feign.Headers;
import feign.RequestLine;
import leo.bachelorsthesis.btempathy.domain.dto.AzureCognitiveResponseDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;

import java.util.List;
import java.util.Map;

@FeignClient(configuration = AppConfig.class)
public interface AzureCognitiveServicesEmotionClient {
    @RequestLine("POST ?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=emotion&" +
            "recognitionModel=recognition_04&returnRecognitionModel=false&detectionModel=detection_01&" +
            "faceIdTimeToLive=86400")
    @Headers("Content-Type: application/json")
    List<AzureCognitiveResponseDTO> sendImageToAzure(@HeaderMap Map<String, String> headers, EmpathyRequestDTO imageUrl);
}
