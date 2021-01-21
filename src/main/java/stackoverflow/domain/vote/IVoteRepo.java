package stackoverflow.domain.vote;

import stackoverflow.domain.IRepo;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

import java.util.Optional;

public interface IVoteRepo extends IRepo<Vote, VoteId> {
    void toggle(Vote entity);

    boolean hasVotedQuestion(QuestionId questionId, PersonId currentUser);
    int nbrVoteQuestion(QuestionId questionId);

    boolean hasVotedComment(CommentId commentId, PersonId currentUser);
    int nbrVoteComment(CommentId commentId);

    void unvoteForQuestion(QuestionId commentId, PersonId currentUser);
    void unvoteForComment(CommentId commentId, PersonId currentUser);

    Optional<Integer> countAll();
    Optional<Integer> countAllOfUser(PersonId id);
}
