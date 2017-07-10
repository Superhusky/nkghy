/**
 * Created by zhangkaihua on 2017/7/4.
 * 项目用文本
 * 创建方式参照ui项
 * 使用时通过message.ui.noRight获取文本
 */

message = new UIMessage();

function UIMessage() {
};

UIMessage.prototype.ui = {
    errorPrompt: "错误提示",
    noRight: "您没有访问权限！",
    success: "操作成功！",
    wait: "处理中，请稍后...",
    prompt: "提示",
    ok: "确定",
    cancel: "取消",
    yes: "是",
    no: "否",
    alertTitle: "提醒",
    confirmTitle: "确认",
    prompTitle: "输入",
    prompMessage: "请输入内容："
};
