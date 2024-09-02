export function formatDate(dateString) {
    if (!dateString){
        return '';
    }
    // 将后端传来的日期字符串转换为Date对象
    const date = new Date(dateString);
    
    // 获取年月日
    const year = date.getFullYear();
    const month = date.getMonth() + 1; // 月份是从0开始的，所以要加1
    const day = date.getDate();

    // 获取小时
    let hour = date.getHours();
    const minutes = date.getMinutes();

    // 判断是上午还是下午
    const period = hour >= 12 ? '下午' : '上午';

    // 如果是下午，小时数减去12（24小时制转12小时制）
    hour = hour > 12 ? hour - 12 : hour;
    hour = hour === 0 ? 12 : hour; // 处理午夜12点的情况

    // 返回格式化后的字符串
    return `${year}年${month}月${day}日 ${period}${hour}点${minutes ? `:${minutes}分` : '30分'}`;
}

export function formatCategory(categoryCode) {
    const categories = {
        "1": `<span style="color: green;">日常休闲</span>`,
        "2": `<span style="color: blue;">周末长途</span>`,
        "3": `<span style="color: red;">假期长途</span>`,
    };

    return categories[categoryCode] || ``;
}

export function formatState(stateCode) {
    const states = {
        "1": `<span style="color: green;">正在报名中</span>`,
        "2": `<span style="color: black;">报名已截止</span>`,
        "3": `<span style="color: red;">活动进行中</span>`,
        "4": `<span style="color: gray;">活动已结束</span>`,
    };

    return states[stateCode] || ``;
}

