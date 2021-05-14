package leo.bachelorsthesis.btempathy.domain.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
public class AzureCognitiveResponseDTO {
    private String faceId;
    private FaceRectangle faceRectangle;
    private FaceAttributes faceAttributes;
}
