package leo.bachelorsthesis.btempathy.service.emotionService;

import com.fasterxml.jackson.databind.JsonNode;
import com.google.gson.JsonObject;
import leo.bachelorsthesis.btempathy.configuration.AzureCognitiveServicesEmotionClient;
import leo.bachelorsthesis.btempathy.domain.dto.AzureCognitiveResponseDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyResponseDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@RefreshScope
public class EmotionServiceImpl implements EmotionService {

    @Value("${azure-token}")
    private String azureToken;

    @Value("${azure-enabled}")
    boolean azureEnabled;

    private final Random random;

    private final AzureCognitiveServicesEmotionClient azureCognitiveServicesEmotionClient;

    public EmotionServiceImpl(AzureCognitiveServicesEmotionClient azureCognitiveServicesEmotionClient, Random random) {
        this.azureCognitiveServicesEmotionClient = azureCognitiveServicesEmotionClient;
        this.random = random;
    }

    @Override
    public EmpathyResponseDTO analysePicture(EmpathyRequestDTO requestDTO) {
        Map<String, String> headers = getHeaders();
        List<AzureCognitiveResponseDTO> azureCognitiveResponseDTOList;
        if(azureEnabled){
            azureCognitiveResponseDTOList =
                    azureCognitiveServicesEmotionClient.sendImageToAzure(headers, requestDTO);
        }
        else{
            return genMockedEmpathyResponseDTO();
        }

        return azureCognitiveResponseDTOList.size() != 0 ?
                azureCognitiveResponseDTOList.get(0).getFaceAttributes().getEmotion() : new EmpathyResponseDTO();
    }

    private EmpathyResponseDTO genMockedEmpathyResponseDTO() {
        return EmpathyResponseDTO.builder()
                .anger(random.nextFloat())
                .contempt(random.nextFloat())
                .disgust(random.nextFloat())
                .fear(random.nextFloat())
                .happiness(random.nextFloat())
                .neutral(random.nextFloat())
                .sadness(random.nextFloat())
                .surprise(random.nextFloat())
                .build();
    }

    private Map<String, String> getHeaders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Content-Type", "application/json");
        headers.put("Ocp-Apim-Subscription-Key", azureToken);
        return headers;
    }
}
