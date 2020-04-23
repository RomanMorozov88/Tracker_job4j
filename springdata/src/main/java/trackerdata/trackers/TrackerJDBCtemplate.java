package trackerdata.trackers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import tracker.Item;
import tracker.trackers.ITracker;
import trackerdata.ItemMapper;

import java.sql.*;
import java.util.List;
import java.util.Random;

/**
 * Пример работы с БД через JdbcTemplate.
 */
@org.springframework.stereotype.Repository
public class TrackerJDBCtemplate implements ITracker {

    private static final Random RN = new Random();
    private final ItemMapper mapper = new ItemMapper();;

    private final JdbcTemplate template;

    @Autowired
    public TrackerJDBCtemplate(final JdbcTemplate template) {
        this.template = template;
    }


    @Override
    public Item add(Item item) {
        item.setId(this.generateId(this.RN));
        item.setCreate(this.setTimeCreate());
        String insertSql =
                "INSERT INTO items (item_id, item_name, item_description, create_date) VALUES (?, ?, ?, ?);";
        this.template.update(
                new PreparedStatementCreator() {
                    @Override
                    public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                        PreparedStatement ps = connection.prepareStatement(insertSql);
                        ps.setString(1, item.getId());
                        ps.setString(2, item.getName());
                        ps.setString(3, item.getDescription());
                        ps.setTimestamp(4, new Timestamp(item.getCreate()));
                        return ps;
                    }
                }
        );
        return item;
    }

    @Override
    public boolean replace(String id, String newname, String newdescription) {
        return this.template.update("UPDATE items SET item_name = ?, item_description = ? WHERE item_id = ?;",
                new String[]{newname, newdescription, id}) != 0;
    }

    @Override
    public boolean delete(String id) {
        return this.template.update("DELETE FROM items WHERE item_id = ?", id) != 0;
    }

    @Override
    public List<Item> findAll() {
        return this.template.query("SELECT * FROM items;", mapper);
    }

    @Override
    public List<Item> findByName(String key) {
        String insertSql = "SELECT * FROM items WHERE item_name = ?;";
        return this.template.query(insertSql, mapper, new String[]{key});
    }

    @Override
    public Item findById(String id) {
        Item result = this.template.queryForObject("SELECT * FROM items WHERE item_id = ?;",
                new Object[]{id}, mapper);
        return result;
    }
}
