package com.bbs.service.impl;

import com.bbs.dao.ReportDao;
import com.bbs.domain.Report;
import com.bbs.service.IReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by geekhoon on 2019/6/21.
 */
@Service
@Transactional
public class ReportServiceImpl implements IReportService {
    @Autowired
    private ReportDao reportDao;

    @Override
    public void addReport(Report report) {
        reportDao.insert(report);
    }
}
