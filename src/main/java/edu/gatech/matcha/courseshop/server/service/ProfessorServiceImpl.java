package edu.gatech.matcha.courseshop.server.service;

import edu.gatech.matcha.courseshop.server.dto.ProfessorDto;
import edu.gatech.matcha.courseshop.server.model.Professor;
import edu.gatech.matcha.courseshop.server.repository.ProfessorRepository;
import org.springframework.stereotype.Service;

@Service
public class ProfessorServiceImpl implements ProfessorService {


    private final ProfessorRepository professorRepository;

    public ProfessorServiceImpl(ProfessorRepository professorRepository) {
        this.professorRepository = professorRepository;
    }

    @Override
    public ProfessorDto getProfessor(long id) {
        Professor professor = professorRepository.findById(id)
                                                 .orElse(null);
        if (professor == null) {
            return null;
        }
        return ProfessorDto.serialize(professor);
    }
}
