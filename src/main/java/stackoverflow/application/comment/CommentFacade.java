package stackoverflow.application.comment;

import java.util.Collection;
import java.util.Optional;
import java.util.stream.Collectors;

import stackoverflow.domain.comment.Comment;
import stackoverflow.domain.comment.CommentId;
import stackoverflow.domain.comment.ICommentRepo;
import stackoverflow.domain.person.PersonId;

public class CommentFacade {
    private ICommentRepo commentRepo;

    public CommentFacade(ICommentRepo commentRepo) {
        this.commentRepo = commentRepo;
    }

    public void proposeComment(ProposeCommentCmd cmd) {
        commentRepo.save(Comment.builder()
            .id(new CommentId())
            .personId(cmd.getPersonId())
            .answerId(cmd.getAnswerId())
            .questionId(cmd.getQuestionId())
            .text(cmd.getText())
            .build()
        );
    }

    public CommentsDTO getQuestionComments(CommentQuery query) {
        return listToDTOList(commentRepo.findByQuestion(query));
    }

    public CommentsDTO getAnswerComments(CommentQuery query) {
        return listToDTOList(commentRepo.findByAnswer(query));
    }

    private CommentsDTO listToDTOList(Collection<Comment> list) {
        return CommentsDTO.builder()
            .comments(list.stream().map(c -> CommentsDTO.CommentDTO.builder()
                .id(c.getId())
                .answerId(c.getAnswerId())
                .questionId(c.getQuestionId())
                .personId(c.getPersonId())
                .text(c.getText())
                .author(c.getAuthor())
                .build()
            ).collect(Collectors.toList()))
        .build();
    }

    public Optional<Integer> getCountCommentOfUser(PersonId id) {
        return commentRepo.countAllOfUser(id);
    }

    public Optional<Integer> getCountComment() {
        return commentRepo.countAll();
    }
}
