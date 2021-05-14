package leo.bachelorsthesis.btempathy.service.emotionService;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import leo.bachelorsthesis.btempathy.configuration.AzureCognitiveServicesEmotionClient;
import leo.bachelorsthesis.btempathy.domain.dto.AzureCognitiveResponseDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyResponseDTO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
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

        List<AzureCognitiveResponseDTO> azureCognitiveResponseDTOList =
                 azureCognitiveServicesEmotionClient.sendImageToAzure(headers, requestDTO);

        return azureCognitiveResponseDTOList.size() != 0 ?
                azureCognitiveResponseDTOList.get(0).getFaceAttributes().getEmotion() : new EmpathyResponseDTO();
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Ocp-Apim-Subscription-Key", azureToken);
        return headers;
    }
}
