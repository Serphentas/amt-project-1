package stackoverflow.application.comment;

import stackoverflow.domain.comment.Comment;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.comment.ICommentRepo;

public class CommentFacade {
    private ICommentRepo commentRepo;

    public CommentFacade(ICommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public void comment(WriteCommentCmd cmd) {
        commentRepo.save(Comment.builder()
            .id(new CommentId())
            .idUser(cmd.getIdUser())
            .idAnswer(cmd.getIdAnswer())
            .idQuestion(cmd.getIdQuestion())
            .text(cmd.getText())
            .build()
        );
    }
}
