package com.bbs.service.impl;

import com.bbs.dao.ZoneDao;
import com.bbs.domain.Zone;
import com.bbs.service.IZoneService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ZoneService implements IZoneService{

    @Autowired
    private ZoneDao zoneDao;

    @Override
    public List<Zone> findZoneList() {
        return zoneDao.selectByExample(null);
    }
}
