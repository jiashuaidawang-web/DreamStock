package com.dreamstock.app.service;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.dreamstock.domain.entity.mysql.BizUserWatchlist;
import com.dreamstock.infrastructure.mapper.mysql.BizUserWatchlistMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WatchlistService {

    private final BizUserWatchlistMapper watchlistMapper;

    public WatchlistService(BizUserWatchlistMapper watchlistMapper) {
        this.watchlistMapper = watchlistMapper;
    }

    public List<BizUserWatchlist> getUserWatchlist(Long userId, Integer watchType) {
        LambdaQueryWrapper<BizUserWatchlist> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(BizUserWatchlist::getUserId, userId)
               .eq(BizUserWatchlist::getStatus, 1);
        if (watchType != null) {
            wrapper.eq(BizUserWatchlist::getWatchType, watchType);
        }
        wrapper.orderByDesc(BizUserWatchlist::getUpdatedAt);
        return watchlistMapper.selectList(wrapper);
    }

    public BizUserWatchlist addToWatchlist(BizUserWatchlist item) {
        watchlistMapper.insert(item);
        return item;
    }

    public void removeFromWatchlist(Long id) {
        BizUserWatchlist item = watchlistMapper.selectById(id);
        if (item != null) {
            item.setStatus(2);
            watchlistMapper.updateById(item);
        }
    }
}
