/**
 * Created by zhangkaihua on 2017/7/4.
 * 扩展NUI框架
 */

$N = nui;

function defaultAjaxConf() {
    this.dataType = "json";
    this.type = "POST";
    this.contentType = "application/x-www-form-urlencoded";
    this.isSuccMsg = true;
    this.isShowProcessBar = true;
    this.beforeSubmitFunc = null;
    this.successFunc = null;
    this.errorFunc = null;
    this.processMsg = null;
    this.async = true;
}

defaultAjaxConf.prototype.setContentType = function (contentType) {
    this.contentType = contentType;
};

defaultAjaxConf.prototype.setContentTypeToJson = function () {
    this.contentType = "application/json";
};

defaultAjaxConf.prototype.setIsShowSuccMsg = function (showSuccmsg) {
    this.isSuccMsg = showSuccmsg;
};

defaultAjaxConf.prototype.setIsShowProcessBar = function (isShowProcessBar) {
    this.isShowProcessBar = isShowProcessBar;
};

defaultAjaxConf.prototype.setBeforeSubmitFunc = function (beforeSubmitFunc) {
    this.beforeSubmitFunc = beforeSubmitFunc;
};

defaultAjaxConf.prototype.setSuccessFunc = function (successFunc) {
    this.successFunc = successFunc;
};

defaultAjaxConf.prototype.setErrorFunc = function (errorFunc) {
    this.errorFunc = errorFunc;
};

defaultAjaxConf.prototype.setIsAsync = function (isAsync) {
    this.async = isAsync;
};

defaultAjaxConf.prototype.submitForm = function (formId, reqUrl) {
    $J.submitForm(formId, reqUrl, this);
};

defaultAjaxConf.prototype.postByAjax = function (postData, reqUrl) {
    $J.postByAjax(postData, reqUrl, this);
};

//为URL添加前缀
nui.formatUrl = function (reqUrl) {
    if (appContext == null || appContext == "" || appContext == "/") {
        appContext = "";
    }
    var flagStr = appContext + "/";
    var rtnValue = "";
    if (reqUrl.startsWith(flagStr)) {
        rtnValue = reqUrl;
    } else {
        if (reqUrl.startsWith("/")) {
            rtnValue = appContext + reqUrl;
        } else {
            rtnValue = appContext + "/" + reqUrl;
        }
    }
    if (rtnValue.indexOf("?") > 0) {
        rtnValue = rtnValue + "&_reqseq=" + Math.random();
    } else {
        rtnValue = rtnValue + "?_reqseq=" + Math.random();
    }
    return rtnValue;
};

/**
 * Ajax提交表单
 * @param formId 需提交表单的id，不带井号#
 * @param reqUrl 数据接收地址
 * @param ajaxConf ajax配置，默认使用defaultAjaxConf
 * @returns {boolean}
 */
nui.submitForm = function (formId, reqUrl, ajaxConf) {
    var tempAjaxConf = null;
    if (ajaxConf) {
        tempAjaxConf = ajaxConf;
    } else {
        tempAjaxConf = new defaultAjaxConf();
    }
    var formTemp = new nui.Form("#" + formId);
    formTemp.validate();
    if (formTemp.isValid() === false) {
        return false;
    }
    //有提交前校验函数，则执行该函数
    if (tempAjaxConf.beforeSubmitFunc && !tempAjaxConf.beforeSubmitFunc()) {
        return false;
    }
    //表单校验通过继续提交
    if (tempAjaxConf.isShowProcessBar) {
        tempAjaxConf.processMsg = this.showProcessBar();
    }
    var ajaxSetting = this.getAjaxSetting(tempAjaxConf);
    if (ajaxSetting.contentType == "application/json") {
        ajaxSetting.data = nui.encode(formTemp.getData());
    } else {
        ajaxSetting.data = formTemp.getData(false, false);
    }
    ajaxSetting.url = this.formatUrl(reqUrl);
    $.ajax(ajaxSetting);
};

/**
 * Ajax提交数据
 * @param postData 需提交的数据
 * @param reqUrl 数据接收地址
 * @param ajaxConf ajax配置，默认使用defaultAjaxConf
 * @returns {boolean}
 */
nui.postByAjax = function (postData, reqUrl, ajaxConf) {
    var tempAjaxConf = null;
    if (ajaxConf) {
        tempAjaxConf = ajaxConf;
    } else {
        tempAjaxConf = new defaultAjaxConf();
    }
    if (tempAjaxConf.beforeSubmitFunc && !tempAjaxConf.beforeSubmitFunc()) {
        return false;
    }

    if (tempAjaxConf.isShowProcessBar) {
        tempAjaxConf.processMsg = this.showProcessBar();
    }

    var ajaxSetting = this.getAjaxSetting(tempAjaxConf);
    ajaxSetting.url = this.formatUrl(reqUrl);
    if (postData) {
        ajaxSetting.data = postData;
    }
    $.ajax(ajaxSetting);
};

/**
 * 打开模态窗口
 * @param title 窗口标题
 * @param url 窗口加载地址
 * @param width 窗口宽度
 * @param height 窗口高度
 * @param data 需传递至模态窗口的数据
 * @param callBackFunc 回调函数，关闭页面后执行
 * @param isShowCloseIcon 显示关闭图标
 * @param isAllowResize 是否允许更改窗口大小
 */
nui.showmodal = function (title, url, width, height, data, callBackFunc, isShowCloseIcon, isAllowResize) {
    var openConf = {};
    openConf.title = title;
    openConf.url = this.formatUrl(url);
    if (width) {
        openConf.width = width;
    } else {
        openConf.width = 650;
    }
    if (height) {
        openConf.height = height;
    } else {
        openConf.height = 450;
    }
    if (isShowCloseIcon != null) {
        openConf.showCloseButton = isShowCloseIcon;
    }
    if (isAllowResize != null) {
        openConf.allowResize = isAllowResize;
    }
    if (data) {
        openConf.onload = function () {
            var iframe = this.getIFrameEl();
            if (iframe != null && iframe.contentWindow != null && iframe.contentWindow.setData) {
                iframe.contentWindow.setData(data);
            }
        };
    }
    if (callBackFunc) {
        openConf.ondestroy = function (action) {
            if ("OK" == action.toUpperCase()) {
                if (this.getIFrameEl().contentWindow.getData) {
                    var data = this.getIFrameEl().contentWindow.getData();
                    data = nui.clone(data);
                    callBackFunc(data);
                } else {
                    callBackFunc();
                }
            }
        };
    }
    nui.open(openConf);
};