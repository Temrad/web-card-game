package ua.od.game.repository.dao.impl;

import ua.od.game.model.ResourceEntity;
import ua.od.game.repository.dao.ResourceDao;
import ua.od.game.repository.helper.SqlHelper;

import java.sql.ResultSet;
import java.util.LinkedList;
import java.util.List;

public class ResourceDaoImpl implements ResourceDao {

    private final String GET_RESOURCE_LIST_QUERY = "SELECT * FROM Resource";

    @Override
    public List<ResourceEntity> getAllResourceList() {
        return SqlHelper.prepareStatement(GET_RESOURCE_LIST_QUERY, statementForResourceList -> {
            ResultSet resourceResultSet = statementForResourceList.executeQuery();
            List<ResourceEntity> resources = new LinkedList<>();
            while (resourceResultSet.next()) {
                resources.add(new ResourceEntity() {{
                    setId(resourceResultSet.getInt("id"));
                    setName(resourceResultSet.getString("name"));
                    setDescription(resourceResultSet.getString("description"));
                    setDefaultNumber(resourceResultSet.getInt("default_number"));
                }});
            }
            return resources;
        });

    }
}
