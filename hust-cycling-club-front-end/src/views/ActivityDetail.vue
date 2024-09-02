<template>
    <div class="activity-detail">
        <s-header name="活动详情" />
        <div v-html="formatCategory(activity.category)"></div>
        <p><strong>开始时间:</strong> {{ formatDate(activity.startTime) }}</p>
        <p><strong>结束时间:</strong> {{ formatDate(activity.endTime)}}</p>
        <p><strong>集合地点:</strong> {{ activity.gatheringPlace }}</p>
        <p><strong>报名截止时间:</strong> {{ formatDate(activity.registrationTime) }}</p>
        <p><strong>状态:</strong> {{ activity.stateCode || '未设置' }}</p>
        <p><strong>简介:</strong> {{ activity.introduce }}</p>
        <div v-if="!activity.isParticipant" class="botton">
            <van-button round block :disabled="activity.stateCode != '1'" color="#1baeae" native-type="submit"
                @click="joinActivity">参加活动</van-button>
        </div>
        <div v-else class="botton">
            <van-button round block :disabled="activity.stateCode == '4'" color="#FF0000" native-type="submit"
                @click="quitActivity">退出活动</van-button>
        </div>
    </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import { onMounted,reactive } from 'vue'
import { useRoute} from 'vue-router'
import { getActivityDetail } from '@/service/activity'
import { saveParticipant, deleteParticipant } from '@/service/user'
import { formatDate, formatState ,formatCategory } from '@/utils/format'
const route = useRoute()
const activity = reactive({
    
})
onMounted(async ()=>{
    showLoadingToast({
        message: '加载中...',
        forbidClick: true
    });
    const { activityId }  = route.params
    const {data} = await getActivityDetail(activityId)
    Object.assign(activity, data)
    closeToast()
})
const joinActivity = () =>{
    showConfirmDialog({
        title: '参加活动',
        message:
            '确认参加活动吗？',
    }).then(async () => {
        await saveParticipant(activity.activityId)
        showSuccessToast('参加成功')
        activity.isParticipant = !activity.isParticipant
    }).catch(() => {});
        
}

const quitActivity = () => {
    showConfirmDialog({
        title: '退出活动',
        message:
            '确认退出活动吗？',
    }).then(async () => {
        await deleteParticipant(activity.activityId)
        showSuccessToast('退出成功')
        activity.isParticipant = !activity.isParticipant
    })
        
}

</script>

<style lang="less" scoped>
.activity-detail{
    padding: 50px 0;
    .botton{
        width: 100%;
        position: fixed;
        bottom: 0;
        padding: 5px 0;
    }
    
}
</style>
