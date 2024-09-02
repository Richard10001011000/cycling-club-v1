<template>
  <div class="activity">
    <Navigator/>
    <van-dropdown-menu>
      <van-dropdown-item v-model="state.activityQuery.category" :options="categoryOptions" @closed="handleChange" />
      <van-dropdown-item v-model="state.activityQuery.stateCode" :options="stateOptions" @closed="handleChange" />
    </van-dropdown-menu>
    <ActivityInfo v-for="activity in state.activityList" :activity="activity" :key="activity.activityId" />
  </div>
</template>

<script setup>
import { ref, onMounted, reactive } from 'vue'
import { listActivity } from '@/service/activity'
import ActivityInfo from "@/components/ActivityInfo.vue"
import Navigator from '@/components/Navigator.vue';
const state = reactive({
  activityList:[],
  activityQuery:{
    category:0,
    stateCode:0,
    keyWord:''
  },
  loading: true
})
const handleChange = async () =>{
  state.loading = true
  const { data } = await listActivity(state.activityQuery)
  state.activityList = data
  state.loading = false
}

onMounted(handleChange)
 
// 活动类型列表
const categoryOptions = [
  { text: '全部类型', value: 0 },
  { text: '日常休闲', value: 1 },
  { text: '周末长途', value: 2 },
  { text: '假期长途', value: 3 },
];

//活动状态选项
const stateOptions = [
    { text: '全部状态', value: 0 },
    { text: '正在报名中', value: 1 },
    { text: '报名已截止', value: 2 },
    { text: '活动进行中', value: 3 },
    { text: '活动已结束', value: 4 },
]


</script>

<style>
.activity{
  padding-bottom: 50px;
}
</style>