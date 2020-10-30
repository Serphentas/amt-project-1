package stackoverflow.domain.vote;

import stackoverflow.domain.IRepo;
import stackoverflow.domain.answer.AnswerId;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

public interface IVoteRepo extends IRepo<Vote, VoteId> {
    void toggle(Vote entity);

    boolean hasVotedAnswer(AnswerId answerId, PersonId currentUser);
    int nbrVoteAnswer(AnswerId answerId);

    boolean hasVotedQuestion(QuestionId questionId, PersonId currentUser);
    int nbrVoteQuestion(QuestionId questionId);

    boolean hasVotedComment(CommentId commentId, PersonId currentUser);
    int nbrVoteComment(CommentId commentId);

    void unvoteForAnswer(AnswerId answerId, PersonId currentUser);
    void unvoteForQuestion(QuestionId commentId, PersonId currentUser);
    void unvoteForComment(CommentId commentId, PersonId currentUser);
}
