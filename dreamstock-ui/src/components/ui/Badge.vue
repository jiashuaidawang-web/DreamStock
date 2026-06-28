<script setup lang="ts">
import { cn } from '@/utils/cn'

const props = defineProps<{
  variant?: 'default' | 'up' | 'down' | 'warning' | 'info'
  class?: string
}>()

const variantClasses: Record<string, string> = {
  default: 'bg-bg-tertiary text-text-secondary',
  up: 'bg-up-bg text-up',
  down: 'bg-down-bg text-down',
  warning: 'bg-amber-500/10 text-accent-yellow',
  info: 'bg-blue-500/10 text-accent-blue'
}
</script>

<template>
  <span :class="cn('inline-flex items-center rounded-md px-2 py-0.5 text-xs font-medium',
    variantClasses[props.variant || 'default'], props.class)">
    <slot />
  </span>
</template>
