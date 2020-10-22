package stackoverflow.application.question;

import stackoverflow.domain.question.IQuestionRepo;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionType;
import stackoverflow.domain.question.QuestionId;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class QuestionFacade {
    private IQuestionRepo questionRepository;

    public QuestionFacade(IQuestionRepo questionRepository){
        this.questionRepository = questionRepository;
    }

    public void proposeQuestion(ProposeQuestionCmd command){
        questionRepository.save(Question.builder()
            .id(command.getId())
            .title(command.getTitle())
            .text(command.getText())
            .personId(command.getPersonId())
            .tags(command.getTags())
            .build()
        );
    }

    public QuestionsDTO getQuestions(QuestionsQuery query){
        Collection<Question> allQuestions = questionRepository.find(query);

        List<QuestionsDTO.QuestionDTO> allQuestionsDTO;

        if (query.getText().contains("sex")) {
            allQuestionsDTO = new ArrayList<>();
            for (Question q: allQuestions) {
                if (q.getQuestionType() != QuestionType.ADULT) {
                    allQuestionsDTO.add(QuestionsDTO.QuestionDTO.builder()
                        .title(q.getTitle())
                        .text(q.getText())
                        .build()
                    );
                }
            }
        } else {
            allQuestionsDTO = allQuestions.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .idQuestion(UUID.fromString(question.getId().asString()))
                .title(question.getTitle())
                .text(question.getText())
                .safeForChildren(!question.getText().contains("sex"))
                .build()
            ).collect(Collectors.toList());
        }

        return QuestionsDTO.builder()
                .questions(allQuestionsDTO)
                .build();

    }

    public QuestionsDTO.QuestionDTO getQuestionById(QuestionId id){
        Question question = questionRepository.findById(id).orElse(null);
        if( question == null)
            return null;
        return QuestionsDTO.QuestionDTO.builder()
                .text(question.getText())
                .idQuestion(UUID.fromString(question.getId().asString()))
                .title(question.getTitle())
                .build();
    }

    public QuestionsDTO getAllQuestions() {
        return questionListAsDTOList(questionRepository.findAll());
    }

    private QuestionsDTO questionListAsDTOList(Collection<Question> qList) {
        return QuestionsDTO.builder()
            .questions(qList.stream().map(question -> QuestionsDTO.QuestionDTO.builder()
                .title(question.getTitle())
                .text(question.getText())
                .safeForChildren(!question.getText().contains("sex"))
                .build()
            ).collect(Collectors.toList()))
        .build();
    }
}
