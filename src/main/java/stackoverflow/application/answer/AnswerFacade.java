package stackoverflow.application.answer;

import stackoverflow.application.question.QuestionsDTO;
import stackoverflow.domain.answer.Answer;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.answer.IAnswerRepo;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.vote.IVoteRepo;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class AnswerFacade {

    private IAnswerRepo answerRepo;
    private IVoteRepo voteRepo;

    public AnswerFacade(IAnswerRepo answerRepo, IVoteRepo voteRepo){
        this.answerRepo = answerRepo;
        this.voteRepo = voteRepo;
    }

    public void proposeAnswer(ProposeAnswerCmd command) {
        Answer submittedAnswer = Answer.builder()
                .personId(command.getPersonId())
                .questionId(command.getQuestionId())
                .text(command.getText())
                .build();
        answerRepo.save(submittedAnswer);
    }

    public AnswersDTO.AnswerDTO getAnswerById(AnswerId id){
        Answer answer = answerRepo.findById(id).orElse(null);
        if( answer == null)
            return null;
        return AnswersDTO.AnswerDTO.builder()
                .text(answer.getText())
                .author(answer.getAuthor())
                .id(answer.getId())
                .build();
    }

    public AnswersDTO getAnswers(AnswersQuery query) {
        Collection<Answer> allAnswers = answerRepo.find(query);

        List<AnswersDTO.AnswerDTO> allAnswersDTO = allAnswers.stream().map( answer -> AnswersDTO.AnswerDTO.builder()
            .id(answer.getId())
            .text(answer.getText())
            .author(answer.getAuthor())
            .votes(voteRepo.nbrVoteAnswer(answer.getId()))
        .build()
        ).collect(Collectors.toList());

        return AnswersDTO.builder()
            .answers(allAnswersDTO)
            .build();
    }
}
