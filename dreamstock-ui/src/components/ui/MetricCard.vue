<script setup lang="ts">
import { cn } from '@/utils/cn'

const props = defineProps<{
  label: string
  value: string | number
  valueClass?: string
  subValue?: string
  subValueClass?: string
}>()
</script>

<template>
  <div class="rounded-lg border border-border-primary bg-bg-card p-4">
    <p class="text-xs text-text-secondary mb-1">{{ props.label }}</p>
    <p :class="cn('text-2xl font-bold', props.valueClass)">{{ props.value }}</p>
    <p v-if="props.subValue" :class="cn('text-xs mt-1', props.subValueClass || 'text-text-tertiary')">
      {{ props.subValue }}
    </p>
  </div>
</template>
