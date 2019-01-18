package ua.od.game.service.impl;

import ua.od.game.dto.ResourceDto;
import ua.od.game.repository.dao.ResourceDao;
import ua.od.game.service.ResourceService;

import javax.inject.Inject;
import java.util.LinkedList;
import java.util.List;

public class ResourceServiceImpl implements ResourceService {

    @Inject
    public ResourceDao resourceDao;

    public List<ResourceDto> getAllResourceList() {
        final List<ResourceDto> resources = new LinkedList<>();
        resourceDao.getAllResourceList().forEach(resourceEntity -> resources.add(new ResourceDto() {{
            setId(resourceEntity.getId());
            setName(resourceEntity.getName());
            setDescription(resourceEntity.getDescription());
            setDefaultNumber(resourceEntity.getDefaultNumber());
        }}));
        return resources;
    }
}
