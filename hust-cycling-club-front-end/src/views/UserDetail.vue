<template>
    <div class="user-detail">
        <s-header :name="'完善信息'" />
        <van-form @submit="onSubmit">
            <van-cell-group inset>
                <van-field v-model="userDetail.realName" name="realName" label="真实姓名" placeholder="真实姓名"
                    :rules="[{ required: true, message: '请填写真实姓名' }]" required />
                <van-field v-model="userDetail.nickName" name="nickName" label="昵称" placeholder="昵称"
                    :rules="[{ required: true, message: '请填写昵称' }]" required />

                <van-field name="gender" label="性别">
                    <template #input>
                        <van-radio-group v-model="userDetail.gender" direction="horizontal">
                            <van-radio name="男">男</van-radio>
                            <van-radio name="女">女</van-radio>
                        </van-radio-group>
                    </template>
                </van-field>
                <van-field v-model="userDetail.qq" name="qq" label="QQ" placeholder="QQ" />

                <van-field v-model="userDetail.phoneNumber" name="phoneNumber" label="电话号码" placeholder="电话号码" />

                <van-field v-model="userDetail.signature" name="signature" label="个性签名" placeholder="个性签名" />

                <van-field v-model="userDetail.college" is-link readonly name="college" label="学院" placeholder="点击选择学院"
                    @click="showPicker = true" />
                <van-popup :show="showPicker" position="bottom">
                    <van-picker :columns="collegeColumns" @confirm="onConfirm" @cancel="showPicker = false" />
                </van-popup>

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
import { ref,reactive } from 'vue'
import { getUserDetail, updateUserDetail } from '@/service/user'
import { closeToast, showLoadingToast, showSuccessToast } from 'vant'
import { onMounted } from 'vue'
const userDetail = reactive({
})
const showPicker = ref(false)
const collegeColumns = [
    { text: '计算机科学与技术学院', value: '计算机科学与技术学院' },
    { text: '新闻与传播学院', value: '新闻与传播学院' },
    { text: '温州', value: 'Wenzhou' },
    { text: '绍兴', value: 'Shaoxing' },
    { text: '湖州', value: 'Huzhou' },
];

const onConfirm = ({ selectedOptions }) => {
    userDetail.college = selectedOptions[0]?.text;
    showPicker.value = false;
};

const onSubmit = async (values) => {
    await updateUserDetail(values);
    init()
    showSuccessToast('信息修改成功');
};
const init = async () =>{
    const { data } = await getUserDetail();
    Object.assign(userDetail, data);
}
onMounted(async () => {
    showLoadingToast("加载用户信息中...")
    init()
    closeToast()
})
</script>



<style lang="less" scoped>
.user-detail{
    padding-top: 50px;
}
</style>