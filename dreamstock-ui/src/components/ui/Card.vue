<script setup lang="ts">
import { cn } from '@/utils/cn'

const props = defineProps<{
  class?: string
}>()
</script>

<template>
  <div :class="cn('rounded-lg border border-border-primary bg-bg-card p-card', props.class)">
    <slot />
  </div>
</template>
