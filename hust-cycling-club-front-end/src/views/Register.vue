<template>
    <div class="register">
        <s-header :name="'注册'" :back="'/home'"></s-header>
        <img class="logo" src="@/common/static/Image_1724379138312.jpg" alt="">
        <div class=" register-body register">
        <van-form @submit="onSubmit">
            <van-field v-model="state.studentNum" name="studentNum" label="学号" placeholder="学号"
                :rules="[{ required: true, message: '请填写学号' }]" />
            <van-field v-model="state.password" type="password" name="password" label="密码" placeholder="密码"
                :rules="[{ required: true, message: '请填写密码' }]" />
            <van-field v-model="state.passwordCheckout" type="passwordCheckout" name="passwordCheckout" label="确认密码"
                placeholder="确认密码" :rules="[{ required: true, message: '请填写确认密码' }]" />
            <van-field v-model="state.realName" name="realName" label="真实姓名" placeholder="真实姓名"
                :rules="[{ required: true, message: '请填写真实姓名' }]" />
            <van-field v-model="state.nickname" type="nickname" name="nickname" label="个性昵称" placeholder="个性昵称"
                :rules="[{ required: true, message: '请填写个性昵称' }]" />
            <van-field center clearable label="验证码" placeholder="输入验证码" v-model="state.verify">
                <template #button>
                    <vue-img-verify ref="verifyRef" />
                </template>
            </van-field>
            <div style="margin: 16px;">
                <router-link tag="li" to="login">
                    <div class="link-login">登录</div>
                </router-link>
                <van-button round block color="#1baeae" native-type="submit">注册</van-button>
            </div>
        </van-form>
    </div>
    </div>
</template>

<script setup>
import { reactive, ref } from 'vue'
import sHeader from '@/components/SimpleHeader.vue'
import vueImgVerify from '@/components/VueImageVerify.vue'
import { userRegister } from '@/service/user'
import { setLocal } from '@/common/js/utils'
import md5 from 'js-md5'
import { showSuccessToast, showFailToast } from 'vant'
const verifyRef = ref(null)
const state = reactive({
    studentNum: '',
    password: '',
    passwordCheckout: '',
    realName: '',
    nickname: '',
    imgCode: '',
    verify: ''
})

// 提交登录或注册表单
const onSubmit = async (values) => {
    if(values.password != values.passwordCheckout){
        showFailToast('两次密码不一致')
        return
    }

    state.imgCode = verifyRef.value.state.imgCode || ''
    if (state.verify.toLowerCase() != state.imgCode.toLowerCase()) {
        showFailToast('验证码有误')
        return
    }
    const { data } = await userRegister({
        "studentNum": values.studentNum,
        "passwordMd5": md5(values.password),
        "realName": values.realName,
        "nickName": values.nickname
    })
    showSuccessToast("注册成功")
    window.location.href = '/login'
}
</script>

<style lang="less">
.register {
    .logo {
        width: 120px;
        height: 120px;
        display: block;
        margin: 80px auto 20px;
    }

    .register-body {
        padding: 0 20px;
    }

    .register {
        .link-login {
            font-size: 14px;
            margin-bottom: 20px;
            color: #1989fa;
            display: inline-block;
        }
    }

    .verify-bar-area {
        margin-top: 24px;

        .verify-left-bar {
            border-color: #1baeae;
        }

        .verify-move-block {
            background-color: #1baeae;
            color: #fff;
        }
    }

    .verify {
        >div {
            width: 100%;
        }

        display: flex;
        justify-content: center;

        .cerify-code-panel {
            margin-top: 16px;
        }

        .verify-code {
            width: 40% !important;
            float: left !important;
        }

        .verify-code-area {
            float: left !important;
            width: 54% !important;
            margin-left: 14px !important;

            .varify-input-code {
                width: 90px;
                height: 38px !important;
                border: 1px solid #e9e9e9;
                padding-left: 10px;
                font-size: 16px;
            }

            .verify-change-area {
                line-height: 44px;
            }
        }
    }
}
</style>
