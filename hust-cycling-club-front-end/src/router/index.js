import { createRouter, createWebHashHistory } from 'vue-router'
import { getLocal } from '@/common/js/utils'
import { showDialog, showFailToast } from 'vant';

const router = createRouter({
  history: createWebHashHistory(),
  routes: [
    {
      path: '/',
      redirect: '/index'
    },
    {
      path: '/index',
      name: 'index',
      component: () => import("@/views/Index.vue")
    },
    {
      path: '/login',
      name: 'login',
      component: () => import('@/views/Login.vue')
    },
    {
      path: '/register',
      name: 'register',
      component: () => import('@/views/Register.vue')
    },
    {
      path: '/user',
      name: 'user',
      component: () => import('@/views/User.vue')
    },
    {
      path: '/user/participants',
      name: 'participants',
      component: () => import('@/views/UserParticipants.vue')
    },
    {
      path: '/activity',
      name: 'activity',
      component: () => import('@/views/Activity.vue')
    },
     {
      path: '/activity-detail/:activityId',
      name: 'activity-detail',
      component: () => import('@/views/ActivityDetail.vue')
    },
    {
      path: '/user-detail',
      name: 'user-detail',
      component: () => import('@/views/UserDetail.vue')
    },
    {
      path: '/user-password',
      name: 'user-password',
      component: () => import('@/views/UserPassword.vue')
    },
  ]
})

router.beforeEach((to, from, next) => {
  const isAuthenticated = getLocal('token') || ''
  if (to.path === '/user' && isAuthenticated=='' && from.path !== '/login') {
    showFailToast("用户未登录")
    next({ path: '/login' });
  } else {
    next();
  }
});
export default router
