-- ============================================================================
-- DreamStock - Comprehensive Seed Data for MySQL & ClickHouse
-- Populates all core tables so frontend views render charts after initialization
-- ============================================================================

-- ===================== MySQL: astock_emotion =====================
USE astock_emotion;
SET NAMES utf8mb4;
SET time_zone = '+08:00';

-- ============================================================================
-- 1. dim_stock_security - Core stock reference data
-- ============================================================================
INSERT IGNORE INTO dim_stock_security (stock_code, stock_name, stock_name_pinyin, is_shanghai, exchange_code, market_type, listing_status, list_date, industry_code, industry_name, is_st, is_registration_system)
VALUES
('000001', '平安银行', 'payh', 0, 'SZSE', 1, 1, '1991-04-03', 'J66', '银行', 0, 0),
('000002', '万科A', 'wka', 0, 'SZSE', 1, 1, '1991-01-29', 'K70', '房地产', 0, 0),
('000063', '中兴通讯', 'zxtx', 0, 'SZSE', 1, 1, '1997-11-18', 'C39', '通信设备', 0, 0),
('000100', 'TCL科技', 'tclkj', 0, 'SZSE', 1, 1, '2004-01-30', 'C39', '电子制造', 0, 0),
('000333', '美的集团', 'mdjt', 0, 'SZSE', 1, 1, '2013-09-18', 'C38', '家用电器', 0, 0),
('000625', '长安汽车', 'caqc', 0, 'SZSE', 1, 1, '1997-06-10', 'C36', '汽车制造', 0, 0),
('000725', '京东方A', 'jdfa', 0, 'SZSE', 1, 1, '2001-01-12', 'C39', '显示面板', 0, 0),
('000858', '五粮液', 'wly', 0, 'SZSE', 1, 1, '1998-04-27', 'C15', '白酒', 0, 0),
('002230', '科大飞讯', 'kdfx', 0, 'SZSE', 1, 1, '2008-05-12', 'I65', '人工智能', 0, 0),
('002594', '比亚迪', 'byd', 0, 'SZSE', 1, 1, '2011-06-30', 'C36', '新能源汽车', 0, 0),
('300059', '东方财富', 'dfcf', 0, 'SZSE', 2, 1, '2010-03-19', 'J67', '互联网金融', 0, 1),
('300124', '汇川技术', 'hcjs', 0, 'SZSE', 2, 1, '2010-09-28', 'C38', '工业自动化', 0, 1),
('300750', '宁德时代', 'ndsd', 0, 'SZSE', 2, 1, '2018-06-11', 'C38', '锂电池', 0, 1),
('600000', '浦发银行', 'pfyh', 1, 'SSE', 1, 1, '1999-11-10', 'J66', '银行', 0, 0),
('600036', '招商银行', 'zsyh', 1, 'SSE', 1, 1, '2002-04-09', 'J66', '银行', 0, 0),
('600276', '恒瑞医药', 'hryy', 1, 'SSE', 1, 1, '2000-10-18', 'C27', '医药', 0, 0),
('600519', '贵州茅台', 'gzmt', 1, 'SSE', 1, 1, '2001-08-27', 'C15', '白酒', 0, 0),
('600887', '伊利股份', 'ylgf', 1, 'SSE', 1, 1, '1996-03-12', 'C14', '乳制品', 0, 0),
('601318', '中国平安', 'zgpa', 1, 'SSE', 1, 1, '2007-03-01', 'J68', '保险', 0, 0),
('601398', '工商银行', 'gsyh', 1, 'SSE', 1, 1, '2006-10-27', 'J66', '银行', 0, 0),
('688981', '中芯国际', 'zxgj', 1, 'SSE', 3, 1, '2020-07-16', 'C39', '半导体', 0, 1),
('688256', '寒武纪', 'hwj', 1, 'SSE', 3, 1, '2020-07-20', 'C39', 'AI芯片', 0, 1),
('002049', '紫光国微', 'zggw', 0, 'SZSE', 1, 1, '2005-06-06', 'C39', '芯片设计', 0, 0),
('300124', '汇川技术', 'hcjs', 0, 'SZSE', 2, 1, '2010-09-28', 'C38', '工业自动化', 0, 1),
('002475', '立讯精密', 'lxjm', 0, 'SZSE', 1, 1, '2010-09-15', 'C39', '消费电子', 0, 0);

-- ============================================================================
-- 2. dim_sector - Sector reference data
-- ============================================================================
INSERT IGNORE INTO dim_sector (sector_type, sector_code, sector_name, source)
VALUES
(2, 'BK0475', '银行', 0),
(2, 'BK0451', '房地产', 0),
(2, 'BK0447', '电子信息', 0),
(2, 'BK0438', '家用电器', 0),
(2, 'BK0481', '汽车制造', 0),
(2, 'BK0428', '白酒', 0),
(3, 'BK1000', '人工智能', 0),
(3, 'BK1001', '机器人', 0),
(3, 'BK1002', '低空经济', 0),
(3, 'BK1003', '半导体', 0),
(3, 'BK1004', '新能源汽车', 0),
(3, 'BK1005', '数据要素', 0),
(3, 'BK1006', '华为概念', 0),
(3, 'BK1007', '军工', 0),
(3, 'BK1008', '光伏', 0),
(4, 'SH000001', '上证指数', 2),
(4, 'SZ399001', '深证成指', 2),
(4, 'SZ399006', '创业板指', 2);

-- ============================================================================
-- 3. dim_market_theme - Active market themes
-- ============================================================================
INSERT IGNORE INTO dim_market_theme (theme_code, theme_name, theme_type, description, start_date, status, created_by)
VALUES
('AI_MAIN', '人工智能', 2, '人工智能全产业链，含算力、应用、数据', '2023-01-01', 1, 'system'),
('ROBOT_MAIN', '机器人', 2, '人形机器人、工业机器人产业链', '2024-06-01', 1, 'system'),
('LOW_ALT', '低空经济', 4, 'eVTOL、无人机、低空管理等', '2024-03-01', 1, 'system'),
('SEMICON', '半导体', 1, '芯片设计、制造、封测、设备、材料', '2019-01-01', 1, 'system'),
('NEV_MAIN', '新能源汽车', 1, '整车、三电、智能驾驶', '2020-01-01', 1, 'system'),
('DATA_ELEM', '数据要素', 4, '数据确权、数据交易、数据安全', '2024-01-01', 1, 'system'),
('HUAWEI', '华为概念', 3, '华为供应链、鸿蒙生态', '2019-05-01', 1, 'system'),
('MILITARY', '军工', 1, '航空装备、导弹、军用电子', '2020-01-01', 2, 'system');

-- ============================================================================
-- 4. biz_rule_set - Extended rule sets
-- ============================================================================
INSERT INTO biz_rule_set (rule_set_code, rule_set_name, rule_domain, version_no, status, effective_start_date, config_json, description, created_by, features)
SELECT 'SELL_DEFAULT', '默认卖点规则', 'SELL', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('stop_loss_pct', -5, 'take_profit_pct', 20),
       '系统默认卖点规则，含止损止盈条件', 'system', JSON_OBJECT('seed', true)
WHERE NOT EXISTS (SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'SELL_DEFAULT' AND version_no = 'V1.0.0');

INSERT INTO biz_rule_set (rule_set_code, rule_set_name, rule_domain, version_no, status, effective_start_date, config_json, description, created_by, features)
SELECT 'RISK_DEFAULT', '默认风控规则', 'RISK', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('max_drawdown_pct', -10, 'max_single_loss_pct', -3),
       '系统默认风控规则，控制最大回撤和单笔亏损', 'system', JSON_OBJECT('seed', true)
WHERE NOT EXISTS (SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'RISK_DEFAULT' AND version_no = 'V1.0.0');

INSERT INTO biz_rule_set (rule_set_code, rule_set_name, rule_domain, version_no, status, effective_start_date, config_json, description, created_by, features)
SELECT 'BACKTEST_DEFAULT', '默认回测规则', 'BACKTEST', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('initial_capital', 1000000, 'max_positions', 5),
       '系统默认回测规则参数配置', 'system', JSON_OBJECT('seed', true)
WHERE NOT EXISTS (SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'BACKTEST_DEFAULT' AND version_no = 'V1.0.0');

-- ============================================================================
-- 5. biz_rule_item - Rule details for emotion scoring
-- ============================================================================
INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'LIMIT_UP_COUNT', '涨停家数', 'limit_up_count', 'BETWEEN', '[30,80]', 15.0000, 1, 1, 1, '涨停家数30~80正常，>80过热，<30冰点'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'FAIL_LIMIT_RATE', '炸板率', 'fail_limit_rate', 'LT', '30', 10.0000, -1, 0, 1, '炸板率>30%扣分'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'PROMOTION_RATE', '晋级率', 'promotion_rate', 'GT', '20', 12.0000, 1, 0, 1, '晋级率>20%加分'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'NORTH_MONEY_FLOW', '北向资金净流入', 'north_money', 'GT', '0', 8.0000, 1, 0, 1, '北向资金净流入>0加分'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'AMOUNT_PERCENTILE', '成交额百分位', 'amount_percentile', 'BETWEEN', '[40,80]', 10.0000, 1, 0, 1, '成交额百分位40-80正常范围加分'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'HIGH_BOARD_COUNT', '高板数量', 'high_board_count', 'GT', '2', 15.0000, 1, 0, 1, '3板以上数量>2说明市场高度打开'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'CONSECUTIVE_LIMIT', '连板数', 'consecutive_limit', 'GT', '3', 15.0000, 1, 0, 1, '最高连板>3板说明情绪持续'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_rule_item (rule_set_id, item_code, item_name, factor_code, operator, threshold_value, weight, score_direction, required_flag, enabled, formula_text)
SELECT rs.id, 'LIMIT_DOWN_COUNT', '跌停家数', 'limit_down_count', 'LT', '10', 15.0000, -1, 0, 1, '跌停>10家扣分'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

-- ============================================================================
-- 6. biz_review_daily_journal - Sample daily review journals
-- ============================================================================
INSERT IGNORE INTO biz_review_daily_journal (user_id, trade_date, emotion_stage, system_emotion_score, user_emotion_score, market_view, theme_view, leader_view, operation_summary, mistake_summary, next_day_plan, discipline_score, mentality_score, review_status)
VALUES
(1, '2026-06-22', 5, 72.5, 70.0, '大盘放量上涨，上证突破3300点。两市成交额突破1.2万亿。北向资金大幅净流入85亿。', '人工智能主线延续强势，机器人板块接力。低空经济有启动迹象。', '中芯国际5连板新高，寒武纪3连板跟随。', '早盘买入寒武纪，尾盘加仓中芯国际。', '追高了一只非主线股票，亏损2%止损。', '继续持有AI主线，关注机器人龙头晋级。', 85.0, 80.0, 1),
(1, '2026-06-23', 6, 68.3, 65.0, '大盘冲高回落，上证收跌0.3%。成交额萎缩至9500亿。市场出现分歧。', 'AI板块高位震荡，部分资金流出。机器人板块活跃度提升。', '中芯国际6连板天地板，市场分歧加大。寒武纪涨停。', '减仓中芯国际50%，锁定利润。', '减仓时机略晚，已有部分回撤。', '注意分歧风险，低仓观望为主。', 75.0, 70.0, 1),
(1, '2026-06-24', 4, 65.1, 62.0, '大盘缩量整理，上证微跌0.1%。成交额继续萎缩至8800亿。', '题材快速轮动，人工智能和机器人交替活跃。', '寒武纪接棒成为总龙，4连板。中芯国际高位回调。', '观望未操作，等待市场方向明确。', '无操作，保持纪律。', '若情绪修复可考虑低吸龙头回调。', 90.0, 85.0, 1),
(1, '2026-06-25', 3, 58.7, 55.0, '大盘低开高走，上证收涨0.5%。成交额回升至1万亿。情绪修复迹象。', 'AI回调企稳，半导体板块异动。低空经济连续第三天活跃。', '寒武纪5连板，确认龙头地位。低空新龙头出现。', '低吸买入低空经济龙头，半仓。', '操作符合计划。', '持有低空龙头，关注量能配合。', 92.0, 88.0, 1),
(1, '2026-06-26', 4, 71.2, 68.0, '大盘放量上涨，上证突破3350。两市成交1.15万亿。北向净流入62亿。', '多题材共振，AI+机器人+低空经济全线活跃。', '寒武纪断板后反包，低空龙头2连板。', '持股不动，低空龙头浮盈8%。', '无错误操作。', '关注周五效应，适当控仓。', 95.0, 90.0, 1);

-- ============================================================================
-- 7. biz_review_trade_record - Sample trade records
-- ============================================================================
INSERT IGNORE INTO biz_review_trade_record (journal_id, user_id, trade_date, stock_code, stock_name, operation_type, operation_price, operation_amount, position_ratio, buy_reason, matched_rule_code, expected_result, actual_result, pnl_pct)
VALUES
(1, 1, '2026-06-22', '688256', '寒武纪', 1, 285.50, 142750.00, 0.15, '人工智能主线龙头跟随，3连板确认趋势', 'LEADER_DEFAULT', '预期继续涨停', '当日涨停', 10.02),
(1, 1, '2026-06-22', '688981', '中芯国际', 3, 98.20, 49100.00, 0.05, '5连板总龙头，尾盘加仓', 'LEADER_DEFAULT', '预期继续强势', '次日天地板', -5.30),
(2, 1, '2026-06-23', '688981', '中芯国际', 4, 92.80, -46400.00, 0.03, NULL, NULL, NULL, '止盈减仓锁定利润', 8.50),
(4, 1, '2026-06-25', '000001', '低空经济龙1', 1, 15.80, 79000.00, 0.50, '低空经济启动，龙头首板低吸', 'TRADE_DEFAULT', '预期次日溢价', '次日涨停+8%', 8.00),
(5, 1, '2026-06-26', '000001', '低空经济龙1', 6, 17.06, 0.00, 0.50, NULL, NULL, '持股观察', '浮盈8%', 8.00);

-- ============================================================================
-- 8. biz_user_watchlist - Sample watchlist
-- ============================================================================
INSERT IGNORE INTO biz_user_watchlist (user_id, stock_code, stock_name, watch_type, watch_reason, start_date, status)
VALUES
(1, '688256', '寒武纪', 1, 'AI芯片总龙头，持续跟踪', '2026-06-20', 1),
(1, '688981', '中芯国际', 1, '半导体龙头，高位注意风险', '2026-06-18', 1),
(1, '300750', '宁德时代', 2, '新能源权重观察', '2026-06-15', 1),
(1, '002594', '比亚迪', 4, '新能源汽车备选', '2026-06-20', 1),
(1, '000725', '京东方A', 5, '连续亏损，暂列黑名单', '2026-06-10', 1),
(1, '300059', '东方财富', 2, '券商龙头，情绪修复关注', '2026-06-22', 1);

-- ============================================================================
-- 9. biz_backtest_task - Sample backtest tasks
-- ============================================================================
INSERT IGNORE INTO biz_backtest_task (task_no, task_name, rule_set_id, data_start_date, data_end_date, initial_capital, benchmark_code, max_position_count, single_position_ratio, status, started_at, finished_at, created_by)
SELECT 'BT-2026-001', '情绪周期V1回测-2025全年', rs.id, '2025-01-02', '2025-12-31', 1000000.0000, '000001.SH', 5, 0.2000, 2, '2026-06-20 10:00:00', '2026-06-20 10:05:32', 'admin'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'EMOTION_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

INSERT IGNORE INTO biz_backtest_task (task_no, task_name, rule_set_id, data_start_date, data_end_date, initial_capital, benchmark_code, max_position_count, single_position_ratio, status, started_at, finished_at, created_by)
SELECT 'BT-2026-002', '龙头策略V1回测-2025H2', rs.id, '2025-07-01', '2025-12-31', 500000.0000, '000001.SH', 3, 0.3000, 2, '2026-06-21 14:00:00', '2026-06-21 14:03:18', 'admin'
FROM biz_rule_set rs WHERE rs.rule_set_code = 'LEADER_DEFAULT' AND rs.version_no = 'V1.0.0' LIMIT 1;

-- ============================================================================
-- 10. biz_backtest_summary - Sample backtest results
-- ============================================================================
INSERT IGNORE INTO biz_backtest_summary (task_id, task_no, total_return_pct, annual_return_pct, max_drawdown_pct, sharpe_ratio, win_rate_pct, profit_loss_ratio, trade_count, win_trade_count, loss_trade_count, avg_holding_days, best_trade_return_pct, worst_trade_return_pct, final_capital, benchmark_return_pct, excess_return_pct)
SELECT t.id, 'BT-2026-001', 45.32, 45.32, -12.80, 1.85, 62.50, 2.35, 120, 75, 45, 3.20, 20.05, -8.30, 1453200.0000, 12.50, 32.82
FROM biz_backtest_task t WHERE t.task_no = 'BT-2026-001' LIMIT 1;

INSERT IGNORE INTO biz_backtest_summary (task_id, task_no, total_return_pct, annual_return_pct, max_drawdown_pct, sharpe_ratio, win_rate_pct, profit_loss_ratio, trade_count, win_trade_count, loss_trade_count, avg_holding_days, best_trade_return_pct, worst_trade_return_pct, final_capital, benchmark_return_pct, excess_return_pct)
SELECT t.id, 'BT-2026-002', 28.75, 57.50, -15.20, 1.52, 58.00, 2.10, 68, 39, 29, 2.80, 30.15, -10.50, 643750.0000, 8.30, 20.45
FROM biz_backtest_task t WHERE t.task_no = 'BT-2026-002' LIMIT 1;

-- ============================================================================
-- 11. ops_data_job_run_log - Sample data job logs
-- ============================================================================
INSERT IGNORE INTO ops_data_job_run_log (job_code, job_name, source, biz_date, status, total_count, success_count, fail_count, started_at, finished_at)
VALUES
('STOCK_DAILY_SYNC', '个股日线行情同步', 0, '2026-06-27', 2, 5200, 5200, 0, '2026-06-27 16:00:00', '2026-06-27 16:02:35'),
('SECTOR_DAILY_SYNC', '板块日线数据同步', 0, '2026-06-27', 2, 480, 480, 0, '2026-06-27 16:03:00', '2026-06-27 16:04:10'),
('LIMIT_BOARD_SYNC', '涨跌停数据同步', 0, '2026-06-27', 2, 150, 150, 0, '2026-06-27 16:05:00', '2026-06-27 16:05:45'),
('NORTH_MONEY_SYNC', '北向资金数据同步', 0, '2026-06-27', 2, 1, 1, 0, '2026-06-27 16:06:00', '2026-06-27 16:06:15'),
('EMOTION_CALC', '情绪指标计算', 3, '2026-06-27', 2, 1, 1, 0, '2026-06-27 16:10:00', '2026-06-27 16:10:30'),
('THEME_HEAT_CALC', '题材热度计算', 3, '2026-06-27', 2, 20, 20, 0, '2026-06-27 16:11:00', '2026-06-27 16:11:45'),
('LEADER_IDENTIFY', '龙头识别计算', 3, '2026-06-27', 2, 30, 30, 0, '2026-06-27 16:12:00', '2026-06-27 16:12:30'),
('FUND_FLOW_SYNC', '资金流向同步', 0, '2026-06-27', 3, 5200, 5100, 100, '2026-06-27 16:15:00', '2026-06-27 16:18:00');

-- ============================================================================
-- 12. ops_data_quality_check_result - Sample quality checks
-- ============================================================================
INSERT IGNORE INTO ops_data_quality_check_result (check_date, table_name, check_item_code, check_item_name, check_status, expected_value, actual_value, abnormal_count, message)
VALUES
('2026-06-27', 'ck_stock_daily_kline', 'MISSING_RATE', '缺失率检查', 1, '<0.1%', '0.02%', 0, '数据完整性良好'),
('2026-06-27', 'ck_stock_daily_kline', 'DUPLICATE_KEY', '主键重复检查', 1, '0', '0', 0, '无重复记录'),
('2026-06-27', 'ck_stock_daily_kline', 'PRICE_ABNORMAL', '价格异常检查', 2, '0', '3', 3, '发现3条涨跌幅超过20%的异常记录(注册制新股)'),
('2026-06-27', 'ck_market_emotion_daily', 'MISSING_RATE', '缺失率检查', 1, '<0.1%', '0.00%', 0, '当日情绪数据完整'),
('2026-06-27', 'ck_market_emotion_daily', 'VALUE_RANGE', '数值范围检查', 1, '[0,100]', '[35,82]', 0, '情绪分数在合理范围内'),
('2026-06-27', 'ck_sector_daily_kline', 'MISSING_RATE', '缺失率检查', 1, '<0.5%', '0.10%', 2, '2个板块数据缺失'),
('2026-06-27', 'ck_limit_board_daily', 'DUPLICATE_KEY', '主键重复检查', 1, '0', '0', 0, '无重复记录'),
('2026-06-27', 'ck_capital_flow_daily', 'MISSING_RATE', '缺失率检查', 3, '<0.1%', '1.92%', 100, '资金流向同步失败导致数据缺失');

-- ============================================================================
-- =================== ClickHouse: astock_emotion_ck ===================
-- Note: Below are INSERT statements for ClickHouse tables.
-- Execute separately against ClickHouse: clickhouse-client < seed_data_ck.sql
-- Or if using the HTTP interface: curl -d @seed_data_ck_part.sql http://localhost:8123/
-- ============================================================================

-- These statements use ClickHouse SQL syntax
-- Uncomment and run against ClickHouse instance separately

/*
-- ClickHouse seed data for ck_stock_daily_kline
INSERT INTO astock_emotion_ck.ck_stock_daily_kline (trade_date, stock_code, stock_name, open_price, high_price, low_price, close_price, prev_close, pct_chg, amount, volume, turnover_rate, amplitude, pe_ttm, pb, total_mv, circ_mv)
VALUES
('2026-06-27', '688256', '寒武纪', 290.00, 305.50, 288.00, 303.20, 289.50, 4.73, 850000000, 28000000, 8.50, 6.05, -1, 25.80, 120000000000, 45000000000),
('2026-06-27', '688981', '中芯国际', 95.00, 97.50, 93.80, 96.30, 94.50, 1.90, 3200000000, 33000000, 3.20, 3.92, 45.60, 3.80, 750000000000, 320000000000),
('2026-06-27', '300750', '宁德时代', 218.00, 225.80, 216.50, 224.50, 217.30, 3.31, 8500000000, 38000000, 5.10, 4.28, 32.50, 6.20, 980000000000, 520000000000),
('2026-06-27', '002594', '比亚迪', 285.00, 292.00, 283.50, 290.80, 284.20, 2.32, 6800000000, 23500000, 4.80, 2.99, 28.40, 4.50, 840000000000, 380000000000),
('2026-06-27', '600519', '贵州茅台', 1680.00, 1695.00, 1672.00, 1688.50, 1678.00, 0.63, 4500000000, 2680000, 0.45, 1.37, 28.30, 10.20, 2120000000000, 2120000000000);

-- ClickHouse seed data for ck_market_emotion_daily
INSERT INTO astock_emotion_ck.ck_market_emotion_daily (trade_date, emotion_score, emotion_stage, limit_up_count, limit_down_count, fail_limit_count, promotion_rate, first_board_count, second_board_count, third_board_count, high_board_count, max_board_height, total_amount, north_money_net, margin_balance, seal_success_rate)
VALUES
('2026-06-22', 72.5, 5, 68, 5, 12, 28.5, 45, 15, 5, 3, 6, 12000, 85.0, 16800, 82.4),
('2026-06-23', 68.3, 6, 52, 8, 18, 22.0, 35, 12, 3, 2, 6, 9500, -35.0, 16750, 71.2),
('2026-06-24', 65.1, 4, 48, 6, 15, 25.0, 32, 10, 4, 2, 5, 8800, 12.0, 16700, 73.5),
('2026-06-25', 58.7, 3, 42, 10, 20, 18.0, 30, 8, 3, 1, 5, 10000, 42.0, 16650, 65.8),
('2026-06-26', 71.2, 4, 62, 4, 10, 30.0, 40, 14, 5, 3, 5, 11500, 62.0, 16900, 84.0),
('2026-06-27', 75.8, 5, 72, 3, 8, 32.0, 48, 16, 5, 3, 6, 12500, 78.0, 17000, 86.5);

-- ClickHouse seed data for ck_sector_daily_kline
INSERT INTO astock_emotion_ck.ck_sector_daily_kline (trade_date, sector_code, sector_name, sector_type, pct_chg, amount, net_inflow, main_net_inflow, stock_count, up_count, down_count, limit_up_count, limit_down_count, leader_stock_code, leader_stock_name)
VALUES
('2026-06-27', 'BK1000', '人工智能', 3, 3.25, 28500000000, 2800000000, 1500000000, 120, 98, 15, 12, 0, '688256', '寒武纪'),
('2026-06-27', 'BK1001', '机器人', 3, 2.80, 15800000000, 1200000000, 800000000, 65, 52, 8, 8, 0, '300124', '汇川技术'),
('2026-06-27', 'BK1002', '低空经济', 3, 4.10, 8500000000, 950000000, 600000000, 35, 32, 2, 6, 0, '000063', '中兴通讯'),
('2026-06-27', 'BK1003', '半导体', 3, 2.15, 22000000000, 1800000000, 1000000000, 95, 75, 12, 5, 0, '688981', '中芯国际'),
('2026-06-27', 'BK1004', '新能源汽车', 3, 1.85, 18500000000, 500000000, 200000000, 80, 55, 18, 3, 0, '002594', '比亚迪'),
('2026-06-27', 'BK0475', '银行', 2, 0.35, 8200000000, -200000000, -100000000, 42, 28, 12, 0, 0, '601398', '工商银行'),
('2026-06-27', 'BK0428', '白酒', 2, -0.80, 6500000000, -500000000, -300000000, 20, 5, 14, 0, 0, '600519', '贵州茅台');

-- ClickHouse seed data for ck_capital_flow_daily
INSERT INTO astock_emotion_ck.ck_capital_flow_daily (trade_date, total_amount, amount_percentile, north_money_net, margin_balance, main_net_inflow, retail_net_inflow, amount_5d_avg, amount_20d_avg, supply_demand_label)
VALUES
('2026-06-22', 12000, 78.5, 85.0, 16800, 2500, -2500, 10500, 9800, 'DEMAND'),
('2026-06-23', 9500, 55.2, -35.0, 16750, -1200, 1200, 10200, 9900, 'BALANCED'),
('2026-06-24', 8800, 42.0, 12.0, 16700, -800, 800, 9800, 9800, 'SUPPLY'),
('2026-06-25', 10000, 62.3, 42.0, 16650, 800, -800, 9500, 9700, 'BALANCED'),
('2026-06-26', 11500, 72.8, 62.0, 16900, 1800, -1800, 10200, 9900, 'DEMAND'),
('2026-06-27', 12500, 80.0, 78.0, 17000, 2800, -2800, 10500, 10000, 'DEMAND');

-- ClickHouse seed data for ck_limit_board_daily (涨停数据)
INSERT INTO astock_emotion_ck.ck_limit_board_daily (trade_date, stock_code, stock_name, limit_type, pct_chg, amount, board_count, seal_time, seal_amount, open_count, turnover_rate, reason, theme_name)
VALUES
('2026-06-27', '688256', '寒武纪', 1, 20.00, 850000000, 6, '09:35:12', 520000000, 0, 8.50, '人工智能AI芯片龙头', '人工智能'),
('2026-06-27', '300124', '汇川技术', 1, 10.02, 320000000, 3, '10:15:30', 180000000, 1, 5.20, '机器人核心零部件', '机器人'),
('2026-06-27', '000063', '中兴通讯', 1, 10.01, 580000000, 2, '09:42:55', 350000000, 0, 4.80, '低空经济通信设备', '低空经济'),
('2026-06-27', '002049', '紫光国微', 1, 10.03, 280000000, 2, '13:05:20', 150000000, 2, 6.30, '芯片设计国产替代', '半导体'),
('2026-06-27', '002475', '立讯精密', 1, 10.00, 450000000, 1, '14:22:10', 200000000, 1, 3.90, '消费电子苹果链', '消费电子');
*/

-- ============================================================================
-- END OF SEED DATA
-- ============================================================================
