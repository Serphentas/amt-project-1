package stackoverflow.application.answer;

import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.IAnswerRepo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerFacade {

    private IAnswerRepo answerRepo;

    public AnswerFacade(IAnswerRepo answerRepo){ this.answerRepo = answerRepo; }

    public void proposeAnswer(ProposeAnswerCmd command) {
        Answer submittedAnswer = Answer.builder()
                .author(command.getAuthor())
                .questionId(command.getQuestionId())
                .text(command.getText())
                .build();
        answerRepo.save(submittedAnswer);
    }

    public AnswersDTO getAnswers(AnswersQuery query) {
        Collection<Answer> allAnswers = answerRepo.find(query);

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream().map(answer -> AnswersDTO.AnswerDTO.builder()
                .text(answer.getText())
                .build()).collect(Collectors.toList());

        return AnswersDTO.builder()
                .answers(allAnswersDTO)
                .build();
    }
}
