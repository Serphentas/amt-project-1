package stackoverflow.domain.comment;

import java.util.Collection;
import java.util.Optional;

import stackoverflow.application.comment.CommentQuery;
import stackoverflow.domain.IRepo;
import stackoverflow.domain.person.PersonId;

public interface ICommentRepo extends IRepo<Comment, CommentId> {
    Collection<Comment> findByQuestion(CommentQuery query);

    Collection<Comment> findByAnswer(CommentQuery query);

    Optional<Integer> countAll();
    Optional<Integer> countAllOfUser(PersonId id);
}
