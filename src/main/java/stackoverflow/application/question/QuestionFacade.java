package stackoverflow.application.question;

import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;

import java.util.Collection;
import java.util.stream.Collectors;

public class QuestionFacade {
    private IQuestionRepo questionRepository;

    public QuestionFacade(IQuestionRepo questionRepository){
        this.questionRepository = questionRepository;
    }

    public void proposeQuestion(ProposeQuestionCmd command){
        questionRepository.save(Question.builder()
            .title(command.getTitle())
            .text(command.getText())
            .userId(command.getUserId())
            .tags(command.getTags())
            .build()
        );
    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
        return questionListAsDTOList(questionRepository.find(query));
    }

    public QuestionsDTO.QuestionDTO getQuestionById(QuestionId id){
        Question question = questionRepository.findById(id).orElse(null);
        if( question == null)
            return null;
        return QuestionsDTO.QuestionDTO.builder()
                .text(question.getText())
                .id(question.getId())
                .title(question.getTitle())
                .build();
    }

    public QuestionsDTO getAllQuestions() {
        return questionListAsDTOList(questionRepository.findAll());
    }

    private QuestionsDTO questionListAsDTOList(Collection<Question> qList) {
        return QuestionsDTO.builder()
            .questions(qList.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .id(question.getId())
                .title(question.getTitle())
                .text(question.getText())
                .build()
            ).collect(Collectors.toList()))
        .build();
    }
}
