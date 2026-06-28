package com.dreamstock.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamstock.domain.entity.mysql.BizReviewDailyJournal;
import com.dreamstock.domain.entity.mysql.BizReviewTradeRecord;
import com.dreamstock.infrastructure.mapper.mysql.BizReviewDailyJournalMapper;
import com.dreamstock.infrastructure.mapper.mysql.BizReviewTradeRecordMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

@Service
public class ReviewJournalService {

    private final BizReviewDailyJournalMapper journalMapper;
    private final BizReviewTradeRecordMapper tradeRecordMapper;

    public ReviewJournalService(BizReviewDailyJournalMapper journalMapper,
                                BizReviewTradeRecordMapper tradeRecordMapper) {
        this.journalMapper = journalMapper;
        this.tradeRecordMapper = tradeRecordMapper;
    }

    public BizReviewDailyJournal getJournalByDate(Long userId, LocalDate tradeDate) {
        LambdaQueryWrapper<BizReviewDailyJournal> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizReviewDailyJournal::getUserId, userId)
               .eq(BizReviewDailyJournal::getTradeDate, tradeDate);
        return journalMapper.selectOne(wrapper);
    }

    public List<BizReviewDailyJournal> listRecentJournals(Long userId, int limit) {
        LambdaQueryWrapper<BizReviewDailyJournal> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizReviewDailyJournal::getUserId, userId)
               .orderByDesc(BizReviewDailyJournal::getTradeDate)
               .last("LIMIT " + limit);
        return journalMapper.selectList(wrapper);
    }

    @Transactional
    public BizReviewDailyJournal saveJournal(BizReviewDailyJournal journal) {
        BizReviewDailyJournal existing = getJournalByDate(journal.getUserId(), journal.getTradeDate());
        if (existing != null) {
            journal.setId(existing.getId());
            journalMapper.updateById(journal);
        } else {
            journalMapper.insert(journal);
        }
        return journal;
    }

    public List<BizReviewTradeRecord> getTradeRecords(Long journalId) {
        LambdaQueryWrapper<BizReviewTradeRecord> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizReviewTradeRecord::getJournalId, journalId)
               .orderByAsc(BizReviewTradeRecord::getOperationTime);
        return tradeRecordMapper.selectList(wrapper);
    }

    @Transactional
    public BizReviewTradeRecord saveTradeRecord(BizReviewTradeRecord record) {
        if (record.getId() != null) {
            tradeRecordMapper.updateById(record);
        } else {
            tradeRecordMapper.insert(record);
        }
        return record;
    }

    public void deleteTradeRecord(Long id) {
        tradeRecordMapper.deleteById(id);
    }
}
