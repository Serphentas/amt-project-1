package stackoverflow.domain.comment;

import java.util.Collection;

import stackoverflow.application.comment.CommentQuery;
import stackoverflow.domain.IRepo;

public interface ICommentRepo extends IRepo<Comment, CommentId> {
    Collection<Comment> findByQuestion(CommentQuery query);

    Collection<Comment> findByAnswer(CommentQuery query);
}
