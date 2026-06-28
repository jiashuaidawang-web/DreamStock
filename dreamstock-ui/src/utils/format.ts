export function formatAmount(value: number): string {
  if (Math.abs(value) >= 1e12) return (value / 1e12).toFixed(2) + '万亿'
  if (Math.abs(value) >= 1e8) return (value / 1e8).toFixed(2) + '亿'
  if (Math.abs(value) >= 1e4) return (value / 1e4).toFixed(2) + '万'
  return value.toFixed(2)
}

export function formatPct(value: number): string {
  const sign = value > 0 ? '+' : ''
  return sign + value.toFixed(2) + '%'
}

export function pctColorClass(value: number): string {
  if (value > 0) return 'text-up'
  if (value < 0) return 'text-down'
  return 'text-text-secondary'
}
