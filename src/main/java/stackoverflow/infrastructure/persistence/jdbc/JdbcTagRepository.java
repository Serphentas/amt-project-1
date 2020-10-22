package stackoverflow.infrastructure.persistence.jdbc;

import stackoverflow.domain.person.PersonId;
import stackoverflow.domain.question.Question;
import stackoverflow.domain.question.QuestionId;
import stackoverflow.domain.tag.ITag;
import stackoverflow.domain.tag.Tag;
import stackoverflow.domain.tag.TagId;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Optional;

import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;

@ApplicationScoped
@Named("JdbcTagRepository")
public class JdbcTagRepository implements ITag {
    @Resource(lookup = "jdbc/StackOverFlowDS")
    DataSource dataSource;

    public JdbcTagRepository(){

    }

    public JdbcTagRepository(DataSource dataSource){
        this.dataSource = dataSource;
    }

    @Override
    public void save(Tag entity) {

    }

    @Override
    public void remove(TagId id) {

    }

    @Override
    public Optional<Tag> findById(TagId id) {
        return Optional.empty();
    }

    @Override
    public Collection<Tag> findAll() {
        ArrayList<Tag> tags = new ArrayList<>();

        try {
            PreparedStatement statement = dataSource.getConnection().prepareStatement(
                    "SELECT * " +
                            "FROM codemad.Tag");

            ResultSet rs = statement.executeQuery();

            while (rs.next()) {
                Tag tag = Tag.builder()
                        .tagId(new TagId(rs.getString("idTag")))
                        .tag(rs.getString("tag"))
                        .build();
                tags.add(tag);
            }

        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        return tags;
    }
}
