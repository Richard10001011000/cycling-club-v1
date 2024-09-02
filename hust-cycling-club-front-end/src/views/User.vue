<template>
    <div class="user-box">
        <s-header :name="'我的'"></s-header>
        <div class="user-info">
            <div class="info">
                <img src="//s.weituibao.com/1583583975067/user-graduate%20(1).png" />
                <div class="user-desc">
                    <span>昵称：{{ state.user.nickName }}</span>
                    <span class="name">个性签名：{{ state.user.signature }}</span>
                </div>
            </div>
        </div>
        <ul class="user-list">
            <li @click="goTo('/user-password')"> 
                <span>修改密码</span>
                <van-icon name="arrow"/>
            </li>
            <li @click="goTo('/user-detail')">
                <span>完善信息</span>
                <van-icon name="arrow" />
            </li>
            <li @click="goTo('/user/participants')">
                <span>我的参加</span>
                <van-icon name="arrow" />
            </li>
            <li @click="logout">
                <span>退出登录</span>
                <van-icon name="arrow" />
            </li>
        </ul>
        <Navigator />
    </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import { reactive, onMounted } from 'vue';
import { getUserInfo } from '@/service/user'
import Navigator from '@/components/Navigator.vue';
import { setLocal } from '@/common/js/utils'
import {useRouter} from 'vue-router'
const state = reactive({
    user: {}
})
const router = useRouter()
onMounted(async () => {
    const { data } = await getUserInfo()
    state.user = data
})

const logout = () => {
    showConfirmDialog({
        title: '确认退出',
        message: '是否确认退出登录?',
    }).then(() => {
        setLocal('token', '')
        window.location.href = '/'
        showSuccessToast("已退出登录")
    })
    
}

const goTo = (url) => {
    router.push({ path: url})
}

</script>

<style lang="less" scoped>
@import '../common/style/mixin';

.user-box {
    .user-info {
        width: 94%;
        margin: 10px;
        height: 115px;
        background: linear-gradient(90deg, @primary, #51c7c7);
        box-shadow: 0 2px 5px #269090;
        border-radius: 6px;
        margin-top: 50px;

        .info {
            position: relative;
            display: flex;
            width: 100%;
            height: 100%;
            padding: 25px 20px;
            .boxSizing();

            img {
                .wh(60px, 60px);
                border-radius: 50%;
                margin-top: 4px;
            }

            .user-desc {
                display: flex;
                flex-direction: column;
                margin-left: 10px;
                line-height: 20px;
                font-size: 14px;
                color: #fff;

                span {
                    color: #fff;
                    font-size: 14px;
                    padding: 2px 0;
                }
            }

            .account-setting {
                position: absolute;
                top: 10px;
                right: 20px;
                font-size: 13px;
                color: #fff;

                .van-icon-setting-o {
                    font-size: 16px;
                    vertical-align: -3px;
                    margin-right: 4px;
                }
            }
        }
    }

    .user-list {
        padding: 0 20px;
        margin-top: 20px;

        li {
            height: 40px;
            line-height: 40px;
            display: flex;
            justify-content: space-between;
            font-size: 14px;

            .van-icon-arrow {
                margin-top: 13px;
            }
        }
    }
}
</style>
