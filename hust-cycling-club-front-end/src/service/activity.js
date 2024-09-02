import axios from '@/utils/axios'; // 二次封装的 axios

export function listActivity(params) {
  return axios.post('/activity/all', params);
}

export function getActivityDetail(activityId) {
  return axios.get(`/activity/detail/${activityId}`);
}