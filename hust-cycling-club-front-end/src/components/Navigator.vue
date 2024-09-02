<template>
    <div class="nav-bar">
        <ul class="nav-list">
            <router-link tag="li" class="nav-list-item active" to="/index">
                <i class="iconfont icon-shouye-zhihui"></i>
                <span>首页</span>
            </router-link>
            <router-link tag="li" class="nav-list-item" to="/activity">
                <i class="iconfont icon-zihangche"></i>
                <span>活动</span>
            </router-link>
            <router-link tag="li" class="nav-list-item" to="/user">
                <i class="iconfont icon-gerenzhongxin-zhihui"></i>
                <span>我的</span>
            </router-link>
        </ul>
    </div>
</template>

<style lang="less" scoped>
@import '@/common/style/mixin';
.nav-bar {
    position: fixed;
    left: 0;
    bottom: 0;
    width: 100%;
    padding: 5px 0;
    z-index: 1000;
    background: #fff;
    transform: translateZ(0);
    -webkit-transform: translateZ(0);
    .nav-list {
        width: 100%;
        .fj();
        flex-direction: row;
        padding: 0;

        .nav-list-item {
            display: flex;
            flex: 1;
            flex-direction: column;
            text-align: center;
            color: #666;
            &.router-link-active {
                color: @primary;
            }
            i{
                text-align: center;
                font-size: 22px;
            }
            span {
                font-size: 12px;
            }

        }
    }
}
</style>
