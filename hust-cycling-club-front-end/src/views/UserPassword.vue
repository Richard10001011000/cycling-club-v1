<template>
    <div class="user-password">
        <s-header :name="'修改密码'" />
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field v-model="userPassword.oldPassword" type="password" name="oldPassword" label="原密码"
                    placeholder="原密码" :rules="[{ required: true, message: '请填写原密码' }]" />
                <van-field v-model="userPassword.newPassword" type="password" name="newPassword" label="新密码"
                    placeholder="新密码" :rules="[{ required: true, message: '请填写新密码' }]" />
                <van-field v-model="userPassword.newPasswordCheckout" type="password" name="newPasswordCheckout"
                    label="确认密码" placeholder="确认密码" :rules="[{ required: true, message: '请填写确认密码' }]" />
            </van-cell-group>
            <div style="margin: 16px;">
                <van-button round block type="primary" native-type="submit">
                    提交
                </van-button>
            </div>
        </van-form>
    </div>
</template>

<script setup>
import sHeader from '@/components/SimpleHeader.vue'
import { ref, reactive } from 'vue'
import { updateUserPassword } from '@/service/user'
import md5 from 'js-md5'
import { useRouter  } from 'vue-router'
const userPassword = reactive({
});
const router = useRouter()
const onSubmit = async (values) => {
    if (values.newPassword != values.newPasswordCheckout) {
        showFailToast('两次密码不一致')
        return
    }
    await updateUserPassword({
        oldPasswordMd5: md5(values.oldPassword),
        newPasswordMd5: md5(values.newPassword),
    });
    await router.back()
    showSuccessToast('密码修改成功');

};

</script>

<style lang="less" scoped>
.user-password {
    padding-top: 50px;
}
</style>