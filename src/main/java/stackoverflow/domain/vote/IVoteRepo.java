package stackoverflow.domain.vote;

import stackoverflow.domain.IRepo;
import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.QuestionId;

public interface IVoteRepo extends IRepo<Vote, VoteId> {
    void toggle(Vote entity);
    
    boolean hasVotedQuestion(QuestionId questionId, PersonId currentUser);
    int nbrVoteQuestion(QuestionId questionId);

    //boolean hasVotedComment(CommentId commentId, PersonId currentUser);
    //int nbrVoteComment(CommentId commentId);
}
