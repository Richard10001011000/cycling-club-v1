<template>
    <div class="activity-info">
        <div class="activity-card" @click="goToDetail(activity.activityId)">
            <img src="@/common/static/Image_1724379138312.jpg"
                alt="活动图片">
            <div class="context">
                <h2>{{ activity.title }}</h2>
                <div v-html="formatCategory(activity.category)"></div>
                <div>{{ formatDate(activity.startTime) }}</div>
                <div v-html="formatState(activity.stateCode)"></div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useRouter} from 'vue-router'
import { formatDate, formatState,formatCategory} from '@/utils/format'
const router = useRouter()

const props = defineProps({
    activity: {
        type: Object,
        required: true,
    },
})
const goToDetail = (activityId)=>{
    router.push({path: `/activity-detail/${activityId}`}); // 跳转到活动详情页
}

</script>

<style lang="less" scoped>
.activity-info {
    display: flex;
    flex-wrap: wrap;
    justify-content: center;
    gap: 20px;
    padding: 20px;

    .activity-card {
        cursor: pointer;
        width: 100%;
        /* 自动调整高度 */
        height: auto;
        background-color: #fff;
        border-radius: 10px;
        box-shadow: 0 2px 6px rgba(0, 0, 0, 0.1);
        overflow: hidden;
        transition: transform 0.3s;
        display: flex;
        align-items: center;
        padding: 10px;

        img {
            width: 40%;
            height: auto;
            object-fit: cover;
            border-radius: 10px;
            margin-left: 10px;
        }

        &:hover {
            transform: translateY(-5px);
        }

        .context {
            flex: 1;
            /* 让内容区域自适应宽度 */
            padding: 10px;
            text-align: center;
            h2 {
                font-size: 16px;
                margin-bottom: 10px;
            }

            p {
                font-size: 12px;
                color: #7f7f7f;
                margin-bottom: 4px;
                /* 减小间距以容纳更多文本 */
                line-height: 1.5;
                /* 调整行间距，使内容更易读 */
            }
        }
    }
}
</style>
