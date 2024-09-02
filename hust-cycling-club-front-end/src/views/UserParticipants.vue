<template>
    <div class="use-participant">
        <s-header name="我的参加" />
        <ActivityInfo v-for="activity in state.activityList" :activity="activity" :key="activity.activityId" />
    </div>
</template>

<script setup>
import {  onMounted, reactive } from 'vue'
import { getUserParticipants } from '@/service/user'
import ActivityInfo from "@/components/ActivityInfo.vue"
import sHeader from '@/components/SimpleHeader.vue'
const state = reactive({
    activityList: [],
})
const handleChange = async () => {
    const { data } = await getUserParticipants()
    state.activityList = data
}

onMounted(handleChange)

</script>

<style scoped>
.use-participant {
    padding-top: 50px;
}
</style>