$(function () {

    $('#page').page({
        total:100,
        firstBtnText:'首页',
        lastBtnText:'尾页',
        prevBtnText:'上一页',
        nextBtnText:'下一页',
        showInfo:true,
        showJump:true,
        jumpBtnText:'跳转',
        showPageSizes:true,
        infoFormat:'{start} ~ {end}条，共{total}条'
    });
});