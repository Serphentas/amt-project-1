package stackoverflow.domain.tag;

import stackoverflow.domain.Id;

import java.util.UUID;

public class TagId extends Id {

    public TagId() {
        super();
    }

    public TagId(String id){
        super(id);
    }

    public TagId(UUID id){
        super(id);
    }
}
