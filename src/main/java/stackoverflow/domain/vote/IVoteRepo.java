package stackoverflow.domain.vote;

import stackoverflow.domain.IRepo;
import stackoverflow.domain.person.PersonId;

public interface IVoteRepo extends IRepo<Vote, VoteId> {
    void toggle(Vote entity);
    //boolean hasVotedComment(CommentId commentId, PersonId currentUser);
}
