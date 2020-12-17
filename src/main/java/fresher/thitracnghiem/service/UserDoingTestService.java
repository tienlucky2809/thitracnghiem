package fresher.thitracnghiem.service;



import fresher.thitracnghiem.model.TestDTO;
import fresher.thitracnghiem.model.UserDoingQuesionDTO;
import fresher.thitracnghiem.model.UserDoingTestDTO;

import java.util.List;

public interface UserDoingTestService {
    void add(UserDoingTestDTO userDoingTestDTO);
    UserDoingTestDTO getOne(Long id);
    List<UserDoingTestDTO> getAll();

    UserDoingTestDTO submit(List<UserDoingQuesionDTO> userDoingQuesionDTOS);

    UserDoingTestDTO getBy(Long idTest);
}
