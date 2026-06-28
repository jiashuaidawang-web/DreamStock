package com.dreamstock.domain.entity.mysql;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

@TableName("biz_review_daily_journal")
public class BizReviewDailyJournal {

    @TableId(type = IdType.AUTO)
    private Long id;
    private Long userId;
    private LocalDate tradeDate;
    private Integer emotionStage;
    private BigDecimal systemEmotionScore;
    private BigDecimal userEmotionScore;
    private String marketView;
    private String themeView;
    private String leaderView;
    private String operationSummary;
    private String mistakeSummary;
    private String nextDayPlan;
    private BigDecimal disciplineScore;
    private BigDecimal mentalityScore;
    private Integer reviewStatus;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private String features;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }
    public LocalDate getTradeDate() { return tradeDate; }
    public void setTradeDate(LocalDate tradeDate) { this.tradeDate = tradeDate; }
    public Integer getEmotionStage() { return emotionStage; }
    public void setEmotionStage(Integer emotionStage) { this.emotionStage = emotionStage; }
    public BigDecimal getSystemEmotionScore() { return systemEmotionScore; }
    public void setSystemEmotionScore(BigDecimal systemEmotionScore) { this.systemEmotionScore = systemEmotionScore; }
    public BigDecimal getUserEmotionScore() { return userEmotionScore; }
    public void setUserEmotionScore(BigDecimal userEmotionScore) { this.userEmotionScore = userEmotionScore; }
    public String getMarketView() { return marketView; }
    public void setMarketView(String marketView) { this.marketView = marketView; }
    public String getThemeView() { return themeView; }
    public void setThemeView(String themeView) { this.themeView = themeView; }
    public String getLeaderView() { return leaderView; }
    public void setLeaderView(String leaderView) { this.leaderView = leaderView; }
    public String getOperationSummary() { return operationSummary; }
    public void setOperationSummary(String operationSummary) { this.operationSummary = operationSummary; }
    public String getMistakeSummary() { return mistakeSummary; }
    public void setMistakeSummary(String mistakeSummary) { this.mistakeSummary = mistakeSummary; }
    public String getNextDayPlan() { return nextDayPlan; }
    public void setNextDayPlan(String nextDayPlan) { this.nextDayPlan = nextDayPlan; }
    public BigDecimal getDisciplineScore() { return disciplineScore; }
    public void setDisciplineScore(BigDecimal disciplineScore) { this.disciplineScore = disciplineScore; }
    public BigDecimal getMentalityScore() { return mentalityScore; }
    public void setMentalityScore(BigDecimal mentalityScore) { this.mentalityScore = mentalityScore; }
    public Integer getReviewStatus() { return reviewStatus; }
    public void setReviewStatus(Integer reviewStatus) { this.reviewStatus = reviewStatus; }
    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
    public LocalDateTime getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(LocalDateTime updatedAt) { this.updatedAt = updatedAt; }
    public String getFeatures() { return features; }
    public void setFeatures(String features) { this.features = features; }
}
