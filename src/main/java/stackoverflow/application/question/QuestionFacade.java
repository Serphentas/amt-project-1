package stackoverflow.application.question;

import stackoverflow.domain.question.IQuestionRepository;
import stackoverflow.domain.question.Question;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class QuestionFacade {

    private IQuestionRepository questionRepository;
    public QuestionFacade(IQuestionRepository questionRepository){this.questionRepository = questionRepository;}

    public void proposeQuestion(ProposeQuestionCommand command){
        Question submittedQuestion = Question.builder()
                .author(command.getAuthor())
                .text(command.getText())
                .build();
        questionRepository.save(submittedQuestion);
    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .text(question.getText())
                .build()).collect(Collectors.toList());

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();
    }
}
