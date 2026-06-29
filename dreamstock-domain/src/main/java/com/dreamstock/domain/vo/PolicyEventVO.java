package com.dreamstock.domain.vo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class PolicyEventVO {

    private List<EventItem> events;

    public static class EventItem {
        private Long id;
        private LocalDate eventDate;
        private String eventType;
        private String title;
        private String content;
        private String impactScope;
        private String impactDirection;
        private List<String> relatedThemes;
        private List<String> relatedStocks;
        private BigDecimal return1d;
        private BigDecimal return3d;
        private BigDecimal return5d;
        private String verificationStatus;

        public Long getId() { return id; }
        public void setId(Long id) { this.id = id; }
        public LocalDate getEventDate() { return eventDate; }
        public void setEventDate(LocalDate eventDate) { this.eventDate = eventDate; }
        public String getEventType() { return eventType; }
        public void setEventType(String eventType) { this.eventType = eventType; }
        public String getTitle() { return title; }
        public void setTitle(String title) { this.title = title; }
        public String getContent() { return content; }
        public void setContent(String content) { this.content = content; }
        public String getImpactScope() { return impactScope; }
        public void setImpactScope(String impactScope) { this.impactScope = impactScope; }
        public String getImpactDirection() { return impactDirection; }
        public void setImpactDirection(String impactDirection) { this.impactDirection = impactDirection; }
        public List<String> getRelatedThemes() { return relatedThemes; }
        public void setRelatedThemes(List<String> relatedThemes) { this.relatedThemes = relatedThemes; }
        public List<String> getRelatedStocks() { return relatedStocks; }
        public void setRelatedStocks(List<String> relatedStocks) { this.relatedStocks = relatedStocks; }
        public BigDecimal getReturn1d() { return return1d; }
        public void setReturn1d(BigDecimal return1d) { this.return1d = return1d; }
        public BigDecimal getReturn3d() { return return3d; }
        public void setReturn3d(BigDecimal return3d) { this.return3d = return3d; }
        public BigDecimal getReturn5d() { return return5d; }
        public void setReturn5d(BigDecimal return5d) { this.return5d = return5d; }
        public String getVerificationStatus() { return verificationStatus; }
        public void setVerificationStatus(String verificationStatus) { this.verificationStatus = verificationStatus; }
    }

    public List<EventItem> getEvents() { return events; }
    public void setEvents(List<EventItem> events) { this.events = events; }
}
