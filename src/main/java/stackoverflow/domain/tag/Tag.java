package stackoverflow.domain.tag;

import lombok.*;
import stackoverflow.domain.IEntity;

@Getter
@Setter
@Builder(toBuilder = true)
public class Tag implements IEntity<Tag, TagId> {

    @Setter(AccessLevel.NONE)
    @Builder.Default
    private TagId tagId = new TagId();
    @NonNull
    private String tag;



    @Override
    public TagId getId() {
        return tagId;
    }

    @Override
    public Tag deepClone() {
        return this.toBuilder()
                .tagId(new TagId(tagId.asString()))
                .tag(tag)
                .build();
    }
}
