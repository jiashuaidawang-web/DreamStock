package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public class CycleTrendVO {

    private List<TrendPoint> points;
    private List<EventMarker> events;

    public static class TrendPoint {
        private LocalDate tradeDate;
        private BigDecimal emotionScore;
        private Integer emotionStage;
        private String emotionStageName;
        private BigDecimal indexClose;
        private BigDecimal indexPctChg;
        private BigDecimal totalAmount;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public BigDecimal getEmotionScore() { return emotionScore; }
        public void setEmotionScore(BigDecimal emotionScore) { this.emotionScore = emotionScore; }
        public Integer getEmotionStage() { return emotionStage; }
        public void setEmotionStage(Integer emotionStage) { this.emotionStage = emotionStage; }
        public String getEmotionStageName() { return emotionStageName; }
        public void setEmotionStageName(String emotionStageName) { this.emotionStageName = emotionStageName; }
        public BigDecimal getIndexClose() { return indexClose; }
        public void setIndexClose(BigDecimal indexClose) { this.indexClose = indexClose; }
        public BigDecimal getIndexPctChg() { return indexPctChg; }
        public void setIndexPctChg(BigDecimal indexPctChg) { this.indexPctChg = indexPctChg; }
        public BigDecimal getTotalAmount() { return totalAmount; }
        public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
    }

    public static class EventMarker {
        private LocalDate tradeDate;
        private String eventType;
        private String eventTitle;
        private String impactDirection;

        public LocalDate getTradeDate() { return tradeDate; }
        public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
        public String getEventType() { return eventType; }
        public void setEventType(String eventType) { this.eventType = eventType; }
        public String getEventTitle() { return eventTitle; }
        public void setEventTitle(String eventTitle) { this.eventTitle = eventTitle; }
        public String getImpactDirection() { return impactDirection; }
        public void setImpactDirection(String impactDirection) { this.impactDirection = impactDirection; }
    }

    public List<TrendPoint> getPoints() { return points; }
    public void setPoints(List<TrendPoint> points) { this.points = points; }
    public List<EventMarker> getEvents() { return events; }
    public void setEvents(List<EventMarker> events) { this.events = events; }
}
