package leo.bachelorsthesis.btempathy.service.emotionService;

import leo.bachelorsthesis.btempathy.configuration.AzureCognitiveServicesEmotionClient;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class EmotionServiceImpl implements EmotionService {

    @Value("${azureToken}")
    private String azureToken;

    private final AzureCognitiveServicesEmotionClient azureCognitiveServicesEmotionClient;

    public EmotionServiceImpl(AzureCognitiveServicesEmotionClient azureCognitiveServicesEmotionClient) {
        this.azureCognitiveServicesEmotionClient = azureCognitiveServicesEmotionClient;
    }

    @Override
    public EmpathyResponseDTO analysePicture(EmpathyRequestDTO requestDTO) {
        Map<String, String> headers = getHeaders();

        ResponseEntity<String> responseEntity =
                azureCognitiveServicesEmotionClient.sendImageToAzure(headers, requestDTO);

        return new EmpathyResponseDTO();
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Ocp-Apim-Subscription-Key", azureToken);
        return headers;
    }
}
