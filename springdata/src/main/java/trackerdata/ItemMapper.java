package trackerdata;

import org.springframework.jdbc.core.RowMapper;
import tracker.Item;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Конвертация записей из БД в объект Item.
 */
public class ItemMapper implements RowMapper<Item> {

    @Override
    public Item mapRow(ResultSet rs, int i) throws SQLException {
        Item item = new Item();
        item.setId(rs.getString("item_id"));
        item.setName(rs.getString("item_name"));
        item.setDescription(rs.getString("item_description"));
        item.setCreate(rs.getTimestamp("create_date").getTime());
        return item;
    }
}
