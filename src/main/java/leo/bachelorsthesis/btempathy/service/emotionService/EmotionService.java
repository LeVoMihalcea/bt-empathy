package leo.bachelorsthesis.btempathy.service.emotionService;

import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyResponseDTO;

public interface EmotionService {

    EmpathyResponseDTO analysePicture(EmpathyRequestDTO imageUrl);
}
