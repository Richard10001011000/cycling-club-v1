import axios from '@/utils/axios'; // 二次封装的 axios

export function userLogin(params) {
  return axios.post('/user/login', params);
}

export function userRegister(params) {
  return axios.post('/user/register', params);
}

export function getUserInfo() {
  return axios.get('/user/info');
}

// 用户修改密码
export function updateUserPassword(params) {
  return axios.put('/user/password', params);
}

// 获取用户全部信息
export function getUserDetail() {
  return axios.get('/user/detail');
}

// 用户补充或修改信息
export function updateUserDetail(params) {
  return axios.put('/user/detail', params);
}

// 管理员更新活动
export function updateUserActivity(params) {
  return axios.put('/user/activity', params);
}

// 管理员创建活动 (用户需为管理员)
export function createActivity(params) {
  return axios.post('/user/activity', params);
}

// 管理员删除活动
export function deleteActivity(activityId) {
  return axios.delete(`/user/activity/${activityId}`);
}

// 用户参加活动
export function saveParticipant(activityId) {
  return axios.post(`/user/participant/${activityId}`);
}

export function deleteParticipant(activityId) {
  return axios.delete(`/user/participant/${activityId}`);
}

// 获取用户参加的活动列表
export function getUserParticipants() {
  return axios.get('/user/participant');
}
