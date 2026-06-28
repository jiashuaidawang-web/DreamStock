-- ============================================================================
-- A股情绪周期复盘与决策系统 - MySQL 初始化脚本
-- 适用版本：MySQL 8.0+
-- 说明：MySQL 负责业务配置层、规则层、用户复盘层、任务与回测汇总层。
-- 执行方式：mysql -uroot -p < mysql_init_astock_emotion.sql
-- ============================================================================

CREATE DATABASE IF NOT EXISTS astock_emotion
  DEFAULT CHARACTER SET utf8mb4
  DEFAULT COLLATE utf8mb4_0900_ai_ci
  COMMENT 'A股情绪周期复盘与决策系统业务库';

USE astock_emotion;

SET NAMES utf8mb4;
SET time_zone = '+08:00';

-- ============================================================================
-- 1. 股票证券基础维表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dim_stock_security (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    stock_code VARCHAR(16) NOT NULL COMMENT '股票代码，例如000001、600519',
    stock_name VARCHAR(64) NOT NULL COMMENT '股票名称，保留当前最新名称',
    stock_name_pinyin VARCHAR(128) NULL COMMENT '股票名称拼音，用于搜索',
    is_shanghai TINYINT NOT NULL COMMENT '是否沪市：0深市，1沪市，2北交所预留',
    exchange_code VARCHAR(16) NOT NULL COMMENT '交易所代码：SZSE深交所，SSE上交所，BSE北交所',
    market_type TINYINT NOT NULL DEFAULT 1 COMMENT '市场类型：1主板，2创业板，3科创板，4北交所，9其他',
    listing_status TINYINT NOT NULL DEFAULT 1 COMMENT '上市状态：1上市，2停牌，3退市，4暂停上市',
    list_date DATE NULL COMMENT '上市日期',
    delist_date DATE NULL COMMENT '退市日期',
    industry_code VARCHAR(64) NULL COMMENT '默认行业代码，通常取交易所或主数据源行业',
    industry_name VARCHAR(128) NULL COMMENT '默认行业名称',
    is_st TINYINT NOT NULL DEFAULT 0 COMMENT '是否ST：0否，1是',
    is_registration_system TINYINT NOT NULL DEFAULT 0 COMMENT '是否注册制股票：0否，1是',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存储供应商扩展属性、标签、备注',
    PRIMARY KEY (id),
    UNIQUE KEY uk_stock_code (stock_code),
    KEY idx_stock_name (stock_name),
    KEY idx_exchange_market (exchange_code, market_type)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='股票证券基础维表，存放股票静态属性，不存每日行情数据';

-- ============================================================================
-- 2. 板块基础信息表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dim_sector (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    sector_type TINYINT NOT NULL COMMENT '板块类型：1地域，2行业，3概念，4指数，5自定义题材',
    sector_code VARCHAR(64) NOT NULL COMMENT '板块代码，来源方提供的板块代号',
    sector_name VARCHAR(128) NOT NULL COMMENT '板块名称',
    source TINYINT NOT NULL COMMENT '来源：0东财，1同花顺，2交易所，3自建，9其他',
    parent_sector_code VARCHAR(64) NULL COMMENT '父级板块代码，用于行业层级或概念归类',
    enabled TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用：0停用，1启用',
    first_seen_at DATETIME(3) NULL COMMENT '首次发现该板块的时间',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存储原始来源字段、别名、映射关系',
    PRIMARY KEY (id),
    UNIQUE KEY uk_source_sector_code (source, sector_code),
    KEY idx_sector_type (sector_type),
    KEY idx_sector_name (sector_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='板块基础信息表，存放地域、行业、概念等板块静态信息，板块新增时写入';

-- ============================================================================
-- 3. 题材主线基础表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dim_market_theme (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    theme_code VARCHAR(64) NOT NULL COMMENT '题材代码，系统生成或人工维护',
    theme_name VARCHAR(128) NOT NULL COMMENT '题材名称，例如人工智能、机器人、低空经济',
    theme_type TINYINT NOT NULL COMMENT '题材类型：1行业型，2概念型，3事件型，4政策型，5混合型',
    description TEXT NULL COMMENT '题材说明',
    start_date DATE NULL COMMENT '题材首次被系统识别或人工确认的日期',
    end_date DATE NULL COMMENT '题材失效日期，未失效为空',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1活跃，2降温，3退潮，4归档',
    created_by VARCHAR(64) NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放关联板块、关键词、催化事件等',
    PRIMARY KEY (id),
    UNIQUE KEY uk_theme_code (theme_code),
    KEY idx_theme_name (theme_name),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='题材主线基础表，用于沉淀市场主线题材，不等同于单一板块';

-- ============================================================================
-- 4. 规则集表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_rule_set (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    rule_set_code VARCHAR(64) NOT NULL COMMENT '规则集编码，例如EMOTION_V1、LEADER_V1、BUY_BOARD_V1',
    rule_set_name VARCHAR(128) NOT NULL COMMENT '规则集名称',
    rule_domain VARCHAR(32) NOT NULL COMMENT '规则领域：EMOTION情绪，THEME题材，LEADER龙头，BUY买点，SELL卖点，RISK风控，BACKTEST回测',
    version_no VARCHAR(32) NOT NULL COMMENT '规则版本号，例如V1.0.0',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '状态：0草稿，1启用，2停用，3归档',
    effective_start_date DATE NULL COMMENT '规则生效开始日期',
    effective_end_date DATE NULL COMMENT '规则生效结束日期',
    config_json JSON NULL COMMENT '规则集全局配置JSON，例如权重、阈值、回测参数',
    description TEXT NULL COMMENT '规则集说明',
    created_by VARCHAR(64) NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放实验参数、灰度信息',
    PRIMARY KEY (id),
    UNIQUE KEY uk_rule_set_code_version (rule_set_code, version_no),
    KEY idx_rule_domain_status (rule_domain, status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='规则集表，管理情绪周期、题材、龙头、买卖点、风控、回测等规则版本';

-- ============================================================================
-- 5. 规则明细表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_rule_item (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    rule_set_id BIGINT UNSIGNED NOT NULL COMMENT '规则集ID，逻辑关联biz_rule_set.id',
    item_code VARCHAR(64) NOT NULL COMMENT '规则项编码，例如LIMIT_UP_COUNT_SCORE、TURNOVER_FILTER',
    item_name VARCHAR(128) NOT NULL COMMENT '规则项名称',
    factor_code VARCHAR(64) NOT NULL COMMENT '因子编码，例如limit_up_count、amount_ratio、emotion_score',
    operator VARCHAR(16) NOT NULL COMMENT '比较操作符：GT大于，GE大于等于，LT小于，LE小于等于，EQ等于，BETWEEN区间，IN包含',
    threshold_value VARCHAR(256) NULL COMMENT '阈值配置，复杂阈值用JSON字符串',
    weight DECIMAL(10,4) NOT NULL DEFAULT 1.0000 COMMENT '规则权重',
    score_direction TINYINT NOT NULL DEFAULT 1 COMMENT '计分方向：1正向加分，-1反向扣分，0仅过滤',
    required_flag TINYINT NOT NULL DEFAULT 0 COMMENT '是否必选条件：0否，1是',
    enabled TINYINT NOT NULL DEFAULT 1 COMMENT '是否启用：0否，1是',
    formula_text TEXT NULL COMMENT '公式说明，用于产品、研发、投研共同确认',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放规则解释、样例、实验参数',
    PRIMARY KEY (id),
    UNIQUE KEY uk_rule_set_item_code (rule_set_id, item_code),
    KEY idx_rule_set_id (rule_set_id),
    KEY idx_factor_code (factor_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='规则明细表，存放规则集下每个因子、阈值、权重和过滤条件';

-- ============================================================================
-- 6. 回测任务表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_backtest_task (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    task_no VARCHAR(64) NOT NULL COMMENT '回测任务编号，全局唯一',
    task_name VARCHAR(128) NOT NULL COMMENT '回测任务名称',
    rule_set_id BIGINT UNSIGNED NOT NULL COMMENT '使用的规则集ID，逻辑关联biz_rule_set.id',
    data_start_date DATE NOT NULL COMMENT '回测数据开始日期',
    data_end_date DATE NOT NULL COMMENT '回测数据结束日期',
    initial_capital DECIMAL(24,4) NOT NULL COMMENT '初始资金，单位元',
    benchmark_code VARCHAR(32) NULL COMMENT '基准指数代码，例如000001.SH、399001.SZ',
    stock_pool_config JSON NULL COMMENT '股票池配置JSON，例如只测涨停池、全A、某板块、某题材',
    fee_rate DECIMAL(10,6) NOT NULL DEFAULT 0.000300 COMMENT '手续费率',
    stamp_tax_rate DECIMAL(10,6) NOT NULL DEFAULT 0.000500 COMMENT '印花税率',
    slippage_rate DECIMAL(10,6) NOT NULL DEFAULT 0.000500 COMMENT '滑点率',
    max_position_count INT NOT NULL DEFAULT 5 COMMENT '最大持仓数量',
    single_position_ratio DECIMAL(10,4) NOT NULL DEFAULT 0.2000 COMMENT '单票最大仓位比例，例如0.2表示20%',
    status TINYINT NOT NULL DEFAULT 0 COMMENT '任务状态：0待执行，1执行中，2成功，3失败，4取消',
    error_message TEXT NULL COMMENT '失败原因',
    started_at DATETIME(3) NULL COMMENT '任务开始时间',
    finished_at DATETIME(3) NULL COMMENT '任务结束时间',
    created_by VARCHAR(64) NULL COMMENT '创建人',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放回测运行环境、数据版本、实验标签',
    PRIMARY KEY (id),
    UNIQUE KEY uk_task_no (task_no),
    KEY idx_rule_set_id (rule_set_id),
    KEY idx_status (status),
    KEY idx_date_range (data_start_date, data_end_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='回测任务表，存储每一次策略回测的任务配置、状态和执行参数';

-- ============================================================================
-- 7. 回测汇总结果表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_backtest_summary (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    task_id BIGINT UNSIGNED NOT NULL COMMENT '回测任务ID，逻辑关联biz_backtest_task.id',
    task_no VARCHAR(64) NOT NULL COMMENT '回测任务编号',
    total_return_pct DECIMAL(12,4) NULL COMMENT '总收益率，百分比点',
    annual_return_pct DECIMAL(12,4) NULL COMMENT '年化收益率，百分比点',
    max_drawdown_pct DECIMAL(12,4) NULL COMMENT '最大回撤，百分比点',
    sharpe_ratio DECIMAL(12,4) NULL COMMENT '夏普比率',
    win_rate_pct DECIMAL(12,4) NULL COMMENT '胜率，百分比点',
    profit_loss_ratio DECIMAL(12,4) NULL COMMENT '盈亏比',
    trade_count INT NULL COMMENT '交易次数',
    win_trade_count INT NULL COMMENT '盈利交易次数',
    loss_trade_count INT NULL COMMENT '亏损交易次数',
    avg_holding_days DECIMAL(12,4) NULL COMMENT '平均持仓天数',
    best_trade_return_pct DECIMAL(12,4) NULL COMMENT '单笔最大盈利，百分比点',
    worst_trade_return_pct DECIMAL(12,4) NULL COMMENT '单笔最大亏损，百分比点',
    final_capital DECIMAL(24,4) NULL COMMENT '期末资金，单位元',
    benchmark_return_pct DECIMAL(12,4) NULL COMMENT '基准收益率，百分比点',
    excess_return_pct DECIMAL(12,4) NULL COMMENT '超额收益率，百分比点',
    summary_json JSON NULL COMMENT '完整指标JSON，存放更多评估指标',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放图表指标、分组统计、异常标记',
    PRIMARY KEY (id),
    UNIQUE KEY uk_task_id (task_id),
    KEY idx_task_no (task_no)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='回测汇总结果表，存储单次回测的收益、回撤、胜率、盈亏比等汇总指标';

-- ============================================================================
-- 8. 用户每日复盘日志表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_review_daily_journal (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    trade_date DATE NOT NULL COMMENT '复盘交易日',
    emotion_stage TINYINT NULL COMMENT '用户主观判断的情绪阶段：1冰点，2修复，3启动，4发酵，5高潮，6分歧，7退潮，8混沌',
    system_emotion_score DECIMAL(10,4) NULL COMMENT '系统当日情绪总分',
    user_emotion_score DECIMAL(10,4) NULL COMMENT '用户主观情绪评分',
    market_view TEXT NULL COMMENT '大盘与市场环境复盘',
    theme_view TEXT NULL COMMENT '主线题材复盘',
    leader_view TEXT NULL COMMENT '龙头与梯队复盘',
    operation_summary TEXT NULL COMMENT '当日操作总结',
    mistake_summary TEXT NULL COMMENT '错误总结',
    next_day_plan TEXT NULL COMMENT '次日交易计划',
    discipline_score DECIMAL(10,4) NULL COMMENT '纪律评分，0到100',
    mentality_score DECIMAL(10,4) NULL COMMENT '心态评分，0到100',
    review_status TINYINT NOT NULL DEFAULT 0 COMMENT '复盘状态：0草稿，1已提交，2已锁定',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放截图、标签、AI总结、语音转文字等',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_trade_date (user_id, trade_date),
    KEY idx_trade_date (trade_date)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户每日复盘日志表，记录用户对市场、题材、龙头、操作和心态的每日复盘';

-- ============================================================================
-- 9. 用户交易复盘明细表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_review_trade_record (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    journal_id BIGINT UNSIGNED NOT NULL COMMENT '每日复盘日志ID，逻辑关联biz_review_daily_journal.id',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    trade_date DATE NOT NULL COMMENT '交易日期',
    stock_code VARCHAR(16) NOT NULL COMMENT '股票代码',
    stock_name VARCHAR(64) NOT NULL COMMENT '股票名称',
    operation_type TINYINT NOT NULL COMMENT '操作类型：1买入，2卖出，3加仓，4减仓，5做T，6观察未操作',
    operation_time DATETIME(3) NULL COMMENT '操作时间',
    operation_price DECIMAL(18,4) NULL COMMENT '操作价格，单位元',
    operation_amount DECIMAL(24,4) NULL COMMENT '操作金额，单位元',
    position_ratio DECIMAL(10,4) NULL COMMENT '操作后仓位比例',
    buy_reason TEXT NULL COMMENT '买入理由',
    sell_reason TEXT NULL COMMENT '卖出理由',
    matched_rule_code VARCHAR(64) NULL COMMENT '命中的系统规则编码',
    expected_result TEXT NULL COMMENT '操作前预期',
    actual_result TEXT NULL COMMENT '操作后结果',
    mistake_type TINYINT NULL COMMENT '错误类型：1追高，2卖飞，3格局过头，4无计划交易，5情绪化交易，6违反风控，9其他',
    pnl_amount DECIMAL(24,4) NULL COMMENT '盈亏金额，单位元',
    pnl_pct DECIMAL(12,4) NULL COMMENT '盈亏比例，百分比点',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放截图、盘口记录、分时位置、备注',
    PRIMARY KEY (id),
    KEY idx_journal_id (journal_id),
    KEY idx_user_trade_date (user_id, trade_date),
    KEY idx_stock_code (stock_code)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户交易复盘明细表，记录每一笔操作的理由、规则命中、结果和错误归因';

-- ============================================================================
-- 10. 用户关注股票池表
-- ============================================================================
CREATE TABLE IF NOT EXISTS biz_user_watchlist (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    user_id BIGINT UNSIGNED NOT NULL COMMENT '用户ID',
    stock_code VARCHAR(16) NOT NULL COMMENT '股票代码',
    stock_name VARCHAR(64) NOT NULL COMMENT '股票名称',
    watch_type TINYINT NOT NULL COMMENT '关注类型：1龙头，2重点关注，3持仓，4备选，5黑名单，6题材观察',
    watch_reason TEXT NULL COMMENT '关注理由',
    start_date DATE NOT NULL COMMENT '开始关注日期',
    end_date DATE NULL COMMENT '结束关注日期',
    status TINYINT NOT NULL DEFAULT 1 COMMENT '状态：1关注中，2已取消',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放自定义标签、提醒条件、关联题材',
    PRIMARY KEY (id),
    UNIQUE KEY uk_user_stock_type (user_id, stock_code, watch_type),
    KEY idx_stock_code (stock_code),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='用户关注股票池表，记录龙头、重点关注、持仓、备选、黑名单等个性化股票池';

-- ============================================================================
-- 11. 数据采集任务日志表
-- ============================================================================
CREATE TABLE IF NOT EXISTS ops_data_job_run_log (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    job_code VARCHAR(64) NOT NULL COMMENT '任务编码，例如STOCK_DAILY_SYNC、SECTOR_DAILY_SYNC',
    job_name VARCHAR(128) NOT NULL COMMENT '任务名称',
    source TINYINT NOT NULL COMMENT '数据来源：0东财，1同花顺，2交易所，3自建，9其他',
    biz_date DATE NULL COMMENT '业务日期，通常为交易日',
    status TINYINT NOT NULL COMMENT '任务状态：0待执行，1执行中，2成功，3失败，4部分成功',
    total_count BIGINT NULL COMMENT '应处理总数',
    success_count BIGINT NULL COMMENT '成功数量',
    fail_count BIGINT NULL COMMENT '失败数量',
    started_at DATETIME(3) NULL COMMENT '任务开始时间',
    finished_at DATETIME(3) NULL COMMENT '任务结束时间',
    error_message TEXT NULL COMMENT '错误信息',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放请求参数、批次号、重试次数、原始响应摘要',
    PRIMARY KEY (id),
    KEY idx_job_biz_date (job_code, biz_date),
    KEY idx_status (status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据采集任务日志表，记录行情、板块、题材、池子等数据采集任务执行情况';

-- ============================================================================
-- 12. 数据质量检查结果表
-- ============================================================================
CREATE TABLE IF NOT EXISTS ops_data_quality_check_result (
    id BIGINT UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '主键ID',
    check_date DATE NOT NULL COMMENT '检查日期',
    table_name VARCHAR(128) NOT NULL COMMENT '被检查表名',
    check_item_code VARCHAR(64) NOT NULL COMMENT '检查项编码，例如MISSING_RATE、DUPLICATE_KEY、PRICE_ABNORMAL',
    check_item_name VARCHAR(128) NOT NULL COMMENT '检查项名称',
    check_status TINYINT NOT NULL COMMENT '检查状态：1通过，2警告，3失败',
    expected_value VARCHAR(256) NULL COMMENT '期望值',
    actual_value VARCHAR(256) NULL COMMENT '实际值',
    abnormal_count BIGINT NULL COMMENT '异常记录数',
    message TEXT NULL COMMENT '检查结果说明',
    created_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) COMMENT '创建时间',
    updated_at DATETIME(3) NOT NULL DEFAULT CURRENT_TIMESTAMP(3) ON UPDATE CURRENT_TIMESTAMP(3) COMMENT '修改时间',
    features JSON NULL COMMENT '扩展字段JSON，存放异常样本、检查SQL、数据批次号',
    PRIMARY KEY (id),
    KEY idx_check_date_table (check_date, table_name),
    KEY idx_check_status (check_status)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci COMMENT='数据质量检查结果表，记录行情、板块、因子、信号等数据的完整性、唯一性、异常值检查结果';

-- ============================================================================
-- 13. 初始化默认规则集种子数据
-- ============================================================================
INSERT INTO biz_rule_set (
    rule_set_code,
    rule_set_name,
    rule_domain,
    version_no,
    status,
    effective_start_date,
    config_json,
    description,
    created_by,
    features
)
SELECT 'EMOTION_DEFAULT', '默认市场情绪周期评分规则', 'EMOTION', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('score_range', JSON_ARRAY(0, 100), 'stage_count', 8),
       '系统默认情绪周期规则，后续可按回测结果迭代权重和阈值',
       'system',
       JSON_OBJECT('seed', true)
WHERE NOT EXISTS (
    SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'EMOTION_DEFAULT' AND version_no = 'V1.0.0'
);

INSERT INTO biz_rule_set (
    rule_set_code,
    rule_set_name,
    rule_domain,
    version_no,
    status,
    effective_start_date,
    config_json,
    description,
    created_by,
    features
)
SELECT 'THEME_DEFAULT', '默认主线题材识别规则', 'THEME', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('rank_limit', 20),
       '系统默认主线题材识别规则，后续可按题材延续性和收益验证迭代',
       'system',
       JSON_OBJECT('seed', true)
WHERE NOT EXISTS (
    SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'THEME_DEFAULT' AND version_no = 'V1.0.0'
);

INSERT INTO biz_rule_set (
    rule_set_code,
    rule_set_name,
    rule_domain,
    version_no,
    status,
    effective_start_date,
    config_json,
    description,
    created_by,
    features
)
SELECT 'LEADER_DEFAULT', '默认龙头识别规则', 'LEADER', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('leader_roles', JSON_ARRAY('总龙头', '板块龙头', '补涨龙', '趋势龙', '反包龙')),
       '系统默认龙头识别规则，后续可按龙头带动性、穿越性、收益验证迭代',
       'system',
       JSON_OBJECT('seed', true)
WHERE NOT EXISTS (
    SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'LEADER_DEFAULT' AND version_no = 'V1.0.0'
);

INSERT INTO biz_rule_set (
    rule_set_code,
    rule_set_name,
    rule_domain,
    version_no,
    status,
    effective_start_date,
    config_json,
    description,
    created_by,
    features
)
SELECT 'TRADE_DEFAULT', '默认买卖点与风控规则', 'BUY', 'V1.0.0', 1, '2000-01-01',
       JSON_OBJECT('risk_first', true, 'no_future_function', true),
       '系统默认买卖点与风控规则，后续可拆分为BUY、SELL、RISK多个规则域',
       'system',
       JSON_OBJECT('seed', true)
WHERE NOT EXISTS (
    SELECT 1 FROM biz_rule_set WHERE rule_set_code = 'TRADE_DEFAULT' AND version_no = 'V1.0.0'
);
