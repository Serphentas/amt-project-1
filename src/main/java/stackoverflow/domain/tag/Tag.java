package stackoverflow.domain.tag;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import stackoverflow.domain.IEntity;

@Getter
@Setter
@Builder(toBuilder = true)
public class Tag implements IEntity<Tag, TagId> {

    @Setter(AccessLevel.NONE)
    private TagId tagId;
    private String tag;



    @Override
    public TagId getId() {
        return tagId;
    }

    @Override
    public Tag deepClone() {
        return this.toBuilder()
                .tagId(new TagId(tagId.asString()))
                .build();
    }


}
