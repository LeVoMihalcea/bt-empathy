package leo.bachelorsthesis.btempathy.configuration;

import feign.HeaderMap;
import feign.Headers;
import feign.RequestLine;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import org.springframework.http.ResponseEntity;

import java.util.Map;

public interface AzureCognitiveServicesEmotionClient {
    @RequestLine("POST ?returnFaceId=true&returnFaceLandmarks=false&returnFaceAttributes=emotion&" +
            "recognitionModel=recognition_04&returnRecognitionModel=false&detectionModel=detection_01&" +
            "faceIdTimeToLive=86400")
    @Headers("Content-Type: application/json")
    ResponseEntity<String> sendImageToAzure(@HeaderMap Map<String, String> headers, EmpathyRequestDTO imageUrl);
}
