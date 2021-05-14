package leo.bachelorsthesis.btempathy.controller;

import leo.bachelorsthesis.btempathy.domain.dto.EmpathyRequestDTO;
import leo.bachelorsthesis.btempathy.domain.dto.EmpathyResponseDTO;
import leo.bachelorsthesis.btempathy.service.emotionService.EmotionService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/emotion")
public class EmotionController {

    private static final Logger logger = LoggerFactory.getLogger(EmotionController.class);

    private final EmotionService emotionService;

    public EmotionController(EmotionService emotionService) {
        this.emotionService = emotionService;
    }

    @PostMapping()
    public EmpathyResponseDTO analysePicture(@RequestBody EmpathyRequestDTO request){
        logger.info("analysePicture request received: {}", request);

        EmpathyResponseDTO response = emotionService.analysePicture(request);

        logger.info("analysePicture request done: {}", response);
        return response;
    }
}
