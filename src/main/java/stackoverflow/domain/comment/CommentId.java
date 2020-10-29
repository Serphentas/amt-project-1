package stackoverflow.domain.comment;

import java.util.UUID;

import stackoverflow.domain.Id;

public class CommentId extends Id {
    public CommentId() {
        super();
    }

    public CommentId(String id) {
        super(id);
    }

    public CommentId(UUID id) {
        super(id);
    }
}
