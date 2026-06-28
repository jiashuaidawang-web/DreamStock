-- ============================================================================
-- A股情绪周期复盘与决策系统 - ClickHouse 初始化脚本
-- 适用版本：ClickHouse 22.x+
-- 说明：ClickHouse 负责行情事实层、情绪分析层、策略信号层、回测明细层。
-- 执行方式：clickhouse-client --multiquery < clickhouse_init_astock_emotion.sql
-- ============================================================================

CREATE DATABASE IF NOT EXISTS astock_emotion_ck COMMENT 'A股情绪周期复盘与决策系统分析库';

USE astock_emotion_ck;

-- ============================================================================
-- 1. 股票日K行情表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dwd_stock_daily_quote (
    trade_date Date COMMENT '交易日期',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称，当日快照名称',
    is_shanghai UInt8 COMMENT '是否沪市：0深市，1沪市，2北交所预留',
    source UInt8 COMMENT '数据来源：0东财，1同花顺，2交易所，9其他',

    price Nullable(Decimal(18,4)) COMMENT '最新价格或收盘价格，单位元',
    pct_chg Nullable(Decimal(12,4)) COMMENT '涨跌幅，百分比点，例如5.23表示5.23%',
    chg_amount Nullable(Decimal(18,4)) COMMENT '涨跌额，单位元',
    volume_hand Nullable(UInt64) COMMENT '成交量，单位手',
    amount Nullable(Decimal(24,4)) COMMENT '成交额，单位元',
    amplitude_pct Nullable(Decimal(12,4)) COMMENT '振幅，百分比点',
    high_price Nullable(Decimal(18,4)) COMMENT '最高价格，单位元',
    low_price Nullable(Decimal(18,4)) COMMENT '最低价格，单位元',
    open_price Nullable(Decimal(18,4)) COMMENT '今开价格，单位元',
    pre_close_price Nullable(Decimal(18,4)) COMMENT '昨收价格，单位元',
    volume_ratio Nullable(Decimal(12,4)) COMMENT '量比',
    turnover_rate_pct Nullable(Decimal(12,4)) COMMENT '换手率，百分比点',
    pe_dynamic Nullable(Decimal(18,4)) COMMENT '市盈率，动态',
    pb Nullable(Decimal(18,4)) COMMENT '市净率',
    roe_pct Nullable(Decimal(12,4)) COMMENT '资产收益率ROE，百分比点',
    total_market_value Nullable(Decimal(24,4)) COMMENT '总市值，单位元',
    float_market_value Nullable(Decimal(24,4)) COMMENT '流通市值，单位元',

    limit_up_flag UInt8 COMMENT '是否涨停：0否，1是',
    limit_down_flag UInt8 COMMENT '是否跌停：0否，1是',
    st_flag UInt8 COMMENT '是否ST：0否，1是',
    suspended_flag UInt8 COMMENT '是否停牌：0否，1是',
    adj_factor Nullable(Decimal(18,8)) COMMENT '复权因子，用于后复权或前复权计算',
    data_status UInt8 COMMENT '数据状态：1正常，2缺失补录，3异常修正，4停牌无行情',

    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放供应商原始字段、复权字段、标签'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (stock_code, trade_date)
COMMENT '股票日K行情表，存储每只股票每日行情基础字段，是所有复盘、因子、回测的底层事实表';

-- ============================================================================
-- 2. 股票分时行情表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dwd_stock_minute_quote (
    trade_date Date COMMENT '交易日期',
    quote_time DateTime64(3) COMMENT '分时时间，格式yyyy-MM-dd HH:mm:ss',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称，当日快照名称',
    is_shanghai UInt8 COMMENT '是否沪市：0深市，1沪市，2北交所预留',
    source UInt8 COMMENT '数据来源：0东财，1同花顺，2交易所，9其他',
    granularity_seconds UInt16 COMMENT '分时粒度秒数：60表示1分钟，300表示5分钟',

    price Nullable(Decimal(18,4)) COMMENT '当前价格，单位元',
    pct_chg Nullable(Decimal(12,4)) COMMENT '涨跌幅，百分比点，例如5.23表示5.23%',
    chg_amount Nullable(Decimal(18,4)) COMMENT '涨跌额，单位元',
    volume_hand Nullable(UInt64) COMMENT '成交量，单位手',
    amount Nullable(Decimal(24,4)) COMMENT '成交额，单位元',
    amplitude_pct Nullable(Decimal(12,4)) COMMENT '振幅，百分比点',
    high_price Nullable(Decimal(18,4)) COMMENT '截至当前最高价格，单位元',
    low_price Nullable(Decimal(18,4)) COMMENT '截至当前最低价格，单位元',
    open_price Nullable(Decimal(18,4)) COMMENT '今开价格，单位元',
    pre_close_price Nullable(Decimal(18,4)) COMMENT '昨收价格，单位元',
    volume_ratio Nullable(Decimal(12,4)) COMMENT '量比',
    turnover_rate_pct Nullable(Decimal(12,4)) COMMENT '换手率，百分比点',
    pe_dynamic Nullable(Decimal(18,4)) COMMENT '市盈率，动态',
    pb Nullable(Decimal(18,4)) COMMENT '市净率',
    roe_pct Nullable(Decimal(12,4)) COMMENT '资产收益率ROE，百分比点',
    total_market_value Nullable(Decimal(24,4)) COMMENT '总市值，单位元',
    float_market_value Nullable(Decimal(24,4)) COMMENT '流通市值，单位元',

    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放盘口、委买委卖、供应商原始字段'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMMDD(trade_date)
ORDER BY (stock_code, quote_time)
COMMENT '股票分时行情表，存储重点股票或全市场股票的分钟级行情，用于龙头分时、打板、炸板、回封、风控分析';

-- ============================================================================
-- 3. 板块日行情明细表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dwd_sector_daily_quote (
    trade_date Date COMMENT '交易日期',
    sector_code String COMMENT '板块代码',
    sector_name String COMMENT '板块名称',
    sector_type UInt8 COMMENT '板块类型：1地域，2行业，3概念，4指数，5自定义题材',
    source UInt8 COMMENT '来源：0东财，1同花顺，2交易所，3自建，9其他',

    up_count UInt32 COMMENT '上涨家数',
    down_count UInt32 COMMENT '下跌家数',
    flat_count UInt32 COMMENT '平盘家数',
    pct_chg_rank UInt32 COMMENT '涨幅排名，1表示当日涨幅第一',
    member_pct_chg_json String COMMENT '板块成分股涨跌幅JSON，存储股票代码、名称、涨跌幅、排名',

    super_large_inflow Nullable(Decimal(24,4)) COMMENT '超大单流入金额，单位元',
    super_large_outflow Nullable(Decimal(24,4)) COMMENT '超大单流出金额，单位元',
    large_inflow Nullable(Decimal(24,4)) COMMENT '大单流入金额，单位元',
    large_outflow Nullable(Decimal(24,4)) COMMENT '大单流出金额，单位元',
    medium_inflow Nullable(Decimal(24,4)) COMMENT '中单流入金额，单位元',
    medium_outflow Nullable(Decimal(24,4)) COMMENT '中单流出金额，单位元',
    small_inflow Nullable(Decimal(24,4)) COMMENT '小单流入金额，单位元',
    small_outflow Nullable(Decimal(24,4)) COMMENT '小单流出金额，单位元',
    net_inflow Nullable(Decimal(24,4)) COMMENT '净流入金额，单位元',

    price Nullable(Decimal(18,4)) COMMENT '板块指数最新价格或收盘价格',
    pct_chg Nullable(Decimal(12,4)) COMMENT '涨跌幅，百分比点，例如5.23表示5.23%',
    chg_amount Nullable(Decimal(18,4)) COMMENT '涨跌额',
    volume_hand Nullable(UInt64) COMMENT '成交量，单位手',
    amount Nullable(Decimal(24,4)) COMMENT '成交额，单位元',
    amplitude_pct Nullable(Decimal(12,4)) COMMENT '振幅，百分比点',
    high_price Nullable(Decimal(18,4)) COMMENT '最高价格',
    low_price Nullable(Decimal(18,4)) COMMENT '最低价格',
    open_price Nullable(Decimal(18,4)) COMMENT '今开',
    pre_close_price Nullable(Decimal(18,4)) COMMENT '昨收',
    volume_ratio Nullable(Decimal(12,4)) COMMENT '量比',
    turnover_rate_pct Nullable(Decimal(12,4)) COMMENT '换手率，百分比点',
    pe_dynamic Nullable(Decimal(18,4)) COMMENT '市盈率，动态',
    pb Nullable(Decimal(18,4)) COMMENT '市净率',
    roe_pct Nullable(Decimal(12,4)) COMMENT '资产收益率ROE，百分比点',
    total_market_value Nullable(Decimal(24,4)) COMMENT '总市值，单位元',
    float_market_value Nullable(Decimal(24,4)) COMMENT '流通市值，单位元',

    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放供应商原始字段、榜单数据'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (trade_date, source, sector_code)
COMMENT '板块日行情明细表，存储板块每日涨跌、资金流、成分股表现和基础行情字段，与板块基础表为多对一关系';

-- ============================================================================
-- 4. 股票与板块关系日快照表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dwd_stock_sector_relation_daily (
    trade_date Date COMMENT '交易日期',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称，当日快照名称',
    sector_source UInt8 COMMENT '所属板块来源：0东财，1同花顺，2交易所，3自建，9其他',
    sector_id UInt64 COMMENT '所属板块ID，对应MySQL dim_sector.id',
    sector_code String COMMENT '所属板块代码',
    sector_name String COMMENT '板块名称',
    sector_type UInt8 COMMENT '板块类型：1地域，2行业，3概念，4指数，5自定义题材',
    relation_status UInt8 COMMENT '关系状态：1有效，2失效',
    weight Nullable(Decimal(12,4)) COMMENT '股票在板块中的权重，无法获取时为空',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放成分股来源、加入原因、供应商原始字段'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (stock_code, trade_date, sector_source, sector_code)
COMMENT '股票与板块关系日快照表，用于按交易日反查股票所属板块，避免回测时使用未来板块关系';

-- ============================================================================
-- 5. 股票池快照表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dwd_stock_pool_snapshot (
    trade_date Date COMMENT '交易日期',
    request_time DateTime64(3) COMMENT '请求时间，记录本次采集池子的时间点',
    pool_type UInt8 COMMENT '池类型：1涨停池，2跌停池，3强势股池，4次新股池，5炸板池，6连板池，7回封池，8高标池',
    source UInt8 COMMENT '数据来源：0东财，1同花顺，2交易所，3自建，9其他',

    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称',
    price Nullable(Decimal(18,4)) COMMENT '价格，单位元',
    pct_chg Nullable(Decimal(12,4)) COMMENT '涨跌幅，百分比点，例如5.23表示5.23%',
    amount Nullable(Decimal(24,4)) COMMENT '成交额，单位元',
    turnover_rate_pct Nullable(Decimal(12,4)) COMMENT '换手率，百分比点',
    total_market_value Nullable(Decimal(24,4)) COMMENT '总市值，单位元',
    float_market_value Nullable(Decimal(24,4)) COMMENT '流通市值，单位元',

    seal_amount Nullable(Decimal(24,4)) COMMENT '封板资金，单位元',
    first_seal_time Nullable(DateTime64(3)) COMMENT '首次封板时间',
    last_seal_time Nullable(DateTime64(3)) COMMENT '最后封板时间',
    open_board_count Nullable(UInt32) COMMENT '开板次数',
    consecutive_limit_count Nullable(UInt32) COMMENT '连板次数',
    industry_sector String COMMENT '所属行业板块',
    total_days Nullable(UInt32) COMMENT '统计周期总天数，用于次新股池或强势股池',
    limit_up_days Nullable(UInt32) COMMENT '统计周期内涨停天数',
    pool_rank Nullable(UInt32) COMMENT '池内排名',
    reason String COMMENT '入池原因，例如涨停、炸板、连板、强势、次新',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放题材、标签、盘口、榜单原始字段'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMMDD(trade_date)
ORDER BY (trade_date, pool_type, request_time, stock_code)
COMMENT '股票池快照表，统一存储涨停池、跌停池、强势股池、次新股池、炸板池、连板池等池子数据';

-- ============================================================================
-- 6. 市场情绪周期日评分表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dws_market_emotion_daily (
    trade_date Date COMMENT '交易日期',
    rule_set_id UInt64 COMMENT '情绪评分规则集ID，对应MySQL biz_rule_set.id',
    calc_version String COMMENT '计算版本号，用于复现历史结果',
    data_snapshot_time DateTime64(3) COMMENT '数据快照时间，防止未来函数',

    emotion_score Decimal(10,4) COMMENT '市场情绪总分，0到100',
    emotion_stage UInt8 COMMENT '情绪阶段：1冰点，2修复，3启动，4发酵，5高潮，6分歧，7退潮，8混沌',
    emotion_stage_name String COMMENT '情绪阶段名称',
    trend_score Nullable(Decimal(10,4)) COMMENT '趋势结构分',
    index_score Nullable(Decimal(10,4)) COMMENT '指数环境分',
    money_score Nullable(Decimal(10,4)) COMMENT '资金供需分',
    profit_effect_score Nullable(Decimal(10,4)) COMMENT '赚钱效应分',
    loss_effect_score Nullable(Decimal(10,4)) COMMENT '亏钱效应分',
    limit_effect_score Nullable(Decimal(10,4)) COMMENT '涨停生态分',
    theme_score Nullable(Decimal(10,4)) COMMENT '题材主线分',
    leader_score Nullable(Decimal(10,4)) COMMENT '龙头强度分',
    risk_score Nullable(Decimal(10,4)) COMMENT '风险压力分',

    up_count UInt32 COMMENT '上涨股票家数',
    down_count UInt32 COMMENT '下跌股票家数',
    limit_up_count UInt32 COMMENT '涨停家数',
    limit_down_count UInt32 COMMENT '跌停家数',
    open_board_count UInt32 COMMENT '炸板家数',
    consecutive_limit_high UInt32 COMMENT '市场最高连板高度',
    above_5pct_count UInt32 COMMENT '涨幅大于5%的股票家数',
    below_minus_5pct_count UInt32 COMMENT '跌幅小于-5%的股票家数',
    total_amount Nullable(Decimal(24,4)) COMMENT '全市场成交额，单位元',
    north_money_net_inflow Nullable(Decimal(24,4)) COMMENT '北向资金净流入，单位元，无法获取时为空',

    conclusion String COMMENT '系统情绪结论，例如情绪修复但缩量，适合轻仓试错',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放评分明细、异常解释、样本股票'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (trade_date, rule_set_id, calc_version)
COMMENT '市场情绪周期日评分表，存储每日情绪总分、周期阶段、分项评分和市场统计指标';

-- ============================================================================
-- 7. 主线题材日判断表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dws_theme_daily (
    trade_date Date COMMENT '交易日期',
    theme_code String COMMENT '题材代码，对应MySQL dim_market_theme.theme_code',
    theme_name String COMMENT '题材名称',
    theme_type UInt8 COMMENT '题材类型：1行业型，2概念型，3事件型，4政策型，5混合型',
    rule_set_id UInt64 COMMENT '题材判断规则集ID，对应MySQL biz_rule_set.id',
    calc_version String COMMENT '计算版本号',

    theme_strength_score Decimal(10,4) COMMENT '题材强度分，0到100',
    theme_rank UInt32 COMMENT '当日题材排名，1表示最强主线',
    continuity_days UInt32 COMMENT '题材连续活跃天数',
    heat_stage UInt8 COMMENT '热度阶段：1启动，2发酵，3高潮，4分歧，5退潮',
    leader_stock_code String COMMENT '题材核心龙头股票代码',
    leader_stock_name String COMMENT '题材核心龙头股票名称',
    limit_up_count UInt32 COMMENT '题材内涨停家数',
    above_5pct_count UInt32 COMMENT '题材内涨幅大于5%的股票家数',
    avg_pct_chg Nullable(Decimal(12,4)) COMMENT '题材成分股平均涨跌幅，百分比点',
    amount Nullable(Decimal(24,4)) COMMENT '题材成分股总成交额，单位元',
    net_inflow Nullable(Decimal(24,4)) COMMENT '题材资金净流入，单位元',
    sector_codes String COMMENT '关联板块代码列表JSON',
    stock_codes String COMMENT '核心成分股代码列表JSON',
    conclusion String COMMENT '题材判断结论',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放催化事件、新闻关键词、人工修正信息'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (trade_date, theme_rank, theme_code)
COMMENT '主线题材日判断表，存储每日题材强度、排名、阶段、龙头、资金和成分股表现';

-- ============================================================================
-- 8. 龙头判断日表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dws_stock_leader_daily (
    trade_date Date COMMENT '交易日期',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称',
    rule_set_id UInt64 COMMENT '龙头判断规则集ID，对应MySQL biz_rule_set.id',
    calc_version String COMMENT '计算版本号',

    leader_score Decimal(10,4) COMMENT '龙头强度分，0到100',
    leader_role UInt8 COMMENT '龙头角色：1总龙头，2板块龙头，3补涨龙，4趋势龙，5反包龙，6后排，7跟风，8掉队',
    leader_role_name String COMMENT '龙头角色名称',
    market_height UInt32 COMMENT '市场空间高度，例如连板高度或趋势高度',
    consecutive_limit_count UInt32 COMMENT '连板次数',
    limit_up_days_10d UInt32 COMMENT '近10日涨停天数',
    first_start_date Nullable(Date) COMMENT '首次启动日期',
    related_theme_code String COMMENT '关联题材代码',
    related_theme_name String COMMENT '关联题材名称',
    related_sector_codes String COMMENT '关联板块代码列表JSON',
    status UInt8 COMMENT '状态：1活跃，2分歧，3弱化，4退潮，5死亡',
    risk_level UInt8 COMMENT '风险等级：1低，2中，3高，4极高',
    reason String COMMENT '判断理由',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放盘口、分时、筹码、龙虎榜、人工标签'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (trade_date, leader_score, stock_code)
COMMENT '龙头判断日表，存储每日股票龙头身份、强度、角色、题材归属和风险状态';

-- ============================================================================
-- 9. 个股与大盘情绪相关性表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dws_stock_emotion_corr_daily (
    trade_date Date COMMENT '交易日期',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称',
    index_code String COMMENT '对比指数代码，例如000001.SH、399001.SZ',
    theme_code String COMMENT '关联题材代码，没有则为空字符串',
    rule_set_id UInt64 COMMENT '相关性判断规则集ID，对应MySQL biz_rule_set.id',

    corr_emotion_5d Nullable(Decimal(12,6)) COMMENT '个股涨跌幅与市场情绪分近5日相关系数',
    corr_emotion_10d Nullable(Decimal(12,6)) COMMENT '个股涨跌幅与市场情绪分近10日相关系数',
    corr_emotion_20d Nullable(Decimal(12,6)) COMMENT '个股涨跌幅与市场情绪分近20日相关系数',
    beta_index_20d Nullable(Decimal(12,6)) COMMENT '个股相对指数近20日Beta',
    excess_return_1d Nullable(Decimal(12,4)) COMMENT '个股相对指数1日超额收益，百分比点',
    excess_return_3d Nullable(Decimal(12,4)) COMMENT '个股相对指数3日超额收益，百分比点',
    excess_return_5d Nullable(Decimal(12,4)) COMMENT '个股相对指数5日超额收益，百分比点',
    resonance_type UInt8 COMMENT '共振类型：1市场共振，2题材共振，3独立走强，4补涨，5掉队，6背离风险',
    resonance_name String COMMENT '共振类型名称',
    divergence_risk_score Nullable(Decimal(10,4)) COMMENT '背离风险分，0到100',
    conclusion String COMMENT '相关性判断结论',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放计算样本、窗口参数、异常说明'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (stock_code, trade_date, index_code, theme_code)
COMMENT '个股与大盘情绪相关性表，判断个股相对市场情绪、指数和题材的共振、独立、补涨、掉队或背离关系';

-- ============================================================================
-- 10. 策略信号日表
-- ============================================================================
CREATE TABLE IF NOT EXISTS dws_strategy_signal_daily (
    trade_date Date COMMENT '交易日期',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称',
    rule_set_id UInt64 COMMENT '策略规则集ID，对应MySQL biz_rule_set.id',
    rule_item_id UInt64 COMMENT '触发的规则项ID，对应MySQL biz_rule_item.id',
    signal_code String COMMENT '信号编码，例如BUY_FIRST_DIVERGENCE、SELL_HIGH_WEAK、RISK_LIMIT_DOWN',
    signal_name String COMMENT '信号名称',

    signal_type UInt8 COMMENT '信号类型：1买点，2卖点，3风控，4观察，5禁买',
    action_type UInt8 COMMENT '建议动作：1买入，2低吸，3打板，4持有，5减仓，6清仓，7禁买，8观察',
    trigger_strength Decimal(10,4) COMMENT '信号强度，0到100',
    confidence_score Nullable(Decimal(10,4)) COMMENT '置信度，0到100',
    emotion_stage UInt8 COMMENT '触发时市场情绪阶段',
    leader_role UInt8 COMMENT '触发时个股龙头角色',
    buy_zone_low Nullable(Decimal(18,4)) COMMENT '建议买入区间下限，单位元',
    buy_zone_high Nullable(Decimal(18,4)) COMMENT '建议买入区间上限，单位元',
    stop_loss_price Nullable(Decimal(18,4)) COMMENT '风控止损价，单位元',
    take_profit_price Nullable(Decimal(18,4)) COMMENT '止盈参考价，单位元',
    invalid_condition String COMMENT '信号失效条件',
    evidence_json String COMMENT '触发证据JSON，包含情绪、题材、龙头、量价、资金、风险等证据',
    calc_time DateTime64(3) COMMENT '信号计算时间',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放人工复核、解释文本、模型输出'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (trade_date, stock_code, signal_type, rule_set_id)
COMMENT '策略信号日表，存储买点、卖点、风控、观察、禁买等规则触发结果，是回测和实盘复盘的核心输入表';

-- ============================================================================
-- 11. 回测交易明细表
-- ============================================================================
CREATE TABLE IF NOT EXISTS ads_backtest_trade_detail (
    task_id UInt64 COMMENT '回测任务ID，对应MySQL biz_backtest_task.id',
    task_no String COMMENT '回测任务编号',
    trade_id String COMMENT '回测交易编号',
    rule_set_id UInt64 COMMENT '策略规则集ID',
    stock_code String COMMENT '股票代码',
    stock_name String COMMENT '股票名称',

    entry_date Date COMMENT '买入日期',
    entry_time Nullable(DateTime64(3)) COMMENT '买入时间，日线回测可为空',
    entry_price Decimal(18,4) COMMENT '买入价格，单位元',
    entry_signal_code String COMMENT '买入信号编码',
    entry_emotion_stage UInt8 COMMENT '买入时市场情绪阶段',
    entry_leader_role UInt8 COMMENT '买入时龙头角色',
    entry_amount Decimal(24,4) COMMENT '买入金额，单位元',
    entry_position_ratio Decimal(10,4) COMMENT '买入仓位比例',

    exit_date Nullable(Date) COMMENT '卖出日期，未卖出为空',
    exit_time Nullable(DateTime64(3)) COMMENT '卖出时间，日线回测可为空',
    exit_price Nullable(Decimal(18,4)) COMMENT '卖出价格，单位元',
    exit_signal_code String COMMENT '卖出信号编码',
    exit_reason String COMMENT '卖出原因',
    holding_days Nullable(UInt32) COMMENT '持仓天数',
    pnl_amount Nullable(Decimal(24,4)) COMMENT '盈亏金额，单位元',
    pnl_pct Nullable(Decimal(12,4)) COMMENT '盈亏比例，百分比点',
    max_profit_pct Nullable(Decimal(12,4)) COMMENT '持仓期间最大浮盈，百分比点',
    max_drawdown_pct Nullable(Decimal(12,4)) COMMENT '持仓期间最大回撤，百分比点',
    fee_amount Nullable(Decimal(24,4)) COMMENT '手续费金额，单位元',
    stamp_tax_amount Nullable(Decimal(24,4)) COMMENT '印花税金额，单位元',
    slippage_amount Nullable(Decimal(24,4)) COMMENT '滑点损耗金额，单位元',

    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放回测成交假设、撮合详情、异常标记'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(entry_date)
ORDER BY (task_id, stock_code, entry_date, trade_id)
COMMENT '回测交易明细表，记录每一笔模拟交易的买入、卖出、信号、情绪阶段、龙头角色、盈亏和风控结果';

-- ============================================================================
-- 12. 回测净值曲线表
-- ============================================================================
CREATE TABLE IF NOT EXISTS ads_backtest_equity_curve (
    task_id UInt64 COMMENT '回测任务ID，对应MySQL biz_backtest_task.id',
    task_no String COMMENT '回测任务编号',
    trade_date Date COMMENT '交易日期',
    total_asset Decimal(24,4) COMMENT '总资产，单位元',
    cash_asset Decimal(24,4) COMMENT '现金资产，单位元',
    position_asset Decimal(24,4) COMMENT '持仓市值，单位元',
    daily_return_pct Nullable(Decimal(12,4)) COMMENT '当日收益率，百分比点',
    cumulative_return_pct Nullable(Decimal(12,4)) COMMENT '累计收益率，百分比点',
    drawdown_pct Nullable(Decimal(12,4)) COMMENT '当前回撤，百分比点',
    position_count UInt32 COMMENT '持仓股票数量',
    turnover_rate Nullable(Decimal(12,4)) COMMENT '换手率，百分比点',
    benchmark_asset Nullable(Decimal(24,4)) COMMENT '基准资产净值，单位元',
    benchmark_return_pct Nullable(Decimal(12,4)) COMMENT '基准累计收益率，百分比点',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放当日持仓、调仓原因、风控触发信息'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(trade_date)
ORDER BY (task_id, trade_date)
COMMENT '回测净值曲线表，记录每个交易日的总资产、现金、持仓、收益、回撤和基准表现';

-- ============================================================================
-- 13. 信号效果评估表
-- ============================================================================
CREATE TABLE IF NOT EXISTS ads_signal_evaluation_daily (
    eval_date Date COMMENT '评估日期',
    signal_trade_date Date COMMENT '信号产生日期',
    target_type UInt8 COMMENT '评估对象类型：1市场情绪，2题材，3股票龙头，4买点信号，5卖点信号，6风控信号',
    target_code String COMMENT '评估对象代码，市场可填MARKET，题材填theme_code，股票填stock_code',
    target_name String COMMENT '评估对象名称',
    rule_set_id UInt64 COMMENT '规则集ID',
    signal_code String COMMENT '信号编码或判断编码',

    predicted_label String COMMENT '预测标签，例如情绪启动、主线题材、总龙头、买点成立',
    actual_label String COMMENT '事后验证标签',
    hit_flag UInt8 COMMENT '是否命中：0否，1是',
    forward_return_1d Nullable(Decimal(12,4)) COMMENT '信号后1日收益率，百分比点',
    forward_return_3d Nullable(Decimal(12,4)) COMMENT '信号后3日收益率，百分比点',
    forward_return_5d Nullable(Decimal(12,4)) COMMENT '信号后5日收益率，百分比点',
    forward_return_10d Nullable(Decimal(12,4)) COMMENT '信号后10日收益率，百分比点',
    max_profit_after_signal Nullable(Decimal(12,4)) COMMENT '信号后最大涨幅，百分比点',
    max_drawdown_after_signal Nullable(Decimal(12,4)) COMMENT '信号后最大回撤，百分比点',
    evaluation_score Nullable(Decimal(10,4)) COMMENT '本次评估得分，0到100',
    failure_reason String COMMENT '未命中原因，例如情绪误判、题材弱化、龙头掉队、风控失效',
    created_at DateTime64(3) COMMENT '创建时间',
    updated_at DateTime64(3) COMMENT '修改时间',
    features String COMMENT '扩展字段JSON字符串，存放评估窗口、样本明细、归因结果'
)
ENGINE = ReplacingMergeTree(updated_at)
PARTITION BY toYYYYMM(eval_date)
ORDER BY (target_type, target_code, signal_trade_date, rule_set_id)
COMMENT '信号效果评估表，用于事后验证情绪周期、主线题材、龙头、买卖点、风控规则的准确率和收益表现';
