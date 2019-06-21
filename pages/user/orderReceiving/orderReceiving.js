//获得数据库引用
const db = wx.cloud.database();
const app = getApp();
// 订单页面
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderReceivingProceed: 'cur',//默认订单进行中

  },
  orderReceivingUpdate: function (e) {
    console.log("orderReceivingUpdate-------------底部触发。。。。。");

  },

  //导航点击切换
  daohangqiehuan: function (e) {
    let orderReceivingProceed = '';
    let orderReceivingFinish = '';
    let qiehuan = e.currentTarget.dataset.daohangqiehuan; //获得点击传递过的值
    if (qiehuan == 'orderReceivingFinish') {
      orderReceivingFinish = 'cur';
      this.daijiadingdan(true);
    } else {
      orderReceivingProceed = 'cur';
      this.daijiadingdan(false);
    }
    this.setData({
      orderReceivingFinish: orderReceivingFinish,
      orderReceivingProceed: orderReceivingProceed
    })
  },
  /**
   * 
   * 获得数据
   * 因为是接单管理，所以查询条件接单人员是自己的数据
   *  */
  daijiadingdan: function (ifFinish) {
    let that = this;
    wx.request({
      url: app.globalData.url + 'ordersAction_conditionQuery', 
      data: {
        openid: that.data.openid,
        ifFinish: ifFinish, //表示是否完成
      },
      method: 'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded'
      },
      success(res) {
        if (ifFinish) {
          //完成
          that.setData({
            daijiadingdanFinish: res.data.data,
          })
        } else {
          //未完成
          that.setData({
            daijiadingdanNoFinish: res.data.data,
          })
        }
        //得到数据，关闭加载
        wx.hideLoading();
      }
    })

  },

  //删除根据id删除
  orderReceivingquxiao: function (e) {
    let that  = this;
    let daijiadingdanNoFinish = that.data.daijiadingdanNoFinish[e.currentTarget.dataset.id];
    //其实是否确定删除
    wx.showModal({
      title: '确认取消',
      content: '订单取消后此单后可在首页或搜索继续添加！',
      confirmText: '确定',
      cancelText: '取消',
      success(res) {
        //表示点击了取消
        if (res.confirm == false) {
          return;
        } else {
          wx.showLoading({
            title:"取消中",
          })
//先删除（删除接单表），再取消(订单表）。
wx.request({
  url: app.globalData.url + 'ordersAction_delete',
  data: {
    id: daijiadingdanNoFinish.id,
  },
  success(res){
    //修改订单表，表示取消接单
    wx.request({
      url: app.globalData.url + 'orderAction_update', 
      data: {
        id: daijiadingdanNoFinish.orderId, //要修改的表id
        ifAccept:false,  
        what:'ifAccept',
      },
      success(res) {      
         //关闭加载...
         wx.hideLoading(); 
         getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示         
      }
    })  

  }
})
        }
      }
    })

  },
  orderReceivingDelete: function (e) {
    let that  = this;
    //其实是否确定删除
    wx.showModal({
      title: '确认删除',
      content: '订单删除后不可恢复，确认删除！',
      confirmText: '确定',
      cancelText: '取消',
      success(res) {
        //表示点击了取消
        if (res.confirm == false) {
          return;
        } else {
          wx.showLoading({
            title:"删除中",
          })
          //先删除（删除接单表），再取消(订单表）。
          wx.request({
            url: app.globalData.url + 'ordersAction_delete',
            data: {
              id: e.currentTarget.dataset.id,
            },
            success(res){
    //关闭加载...
    wx.hideLoading(); 
    getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示  

            }
          })
        }
      }
    })
  },

  //进入订单详细
  coderFormDetail: function (e) {
    //跳转编辑信息页面
    wx.navigateTo({
      url: 'orderReceivingDetail/orderReceivingDetail?detailId=' + e.currentTarget.dataset.id,
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //获得传递过来的 openid
    this.setData({
      openid: options.openid
    })
    //提示加载数据
    wx.showLoading({
      title: '加载中',
    })


  },

  /**
   * 生命周期函数--监听页面初次渲染完成
   */
  onReady: function () {

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    console.log("onShow");
    //获得数据,默认先获得没有完成的
    this.daijiadingdan(false);
    this.daijiadingdan(true);
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },

  /**
   * 生命周期函数--监听页面卸载
   */
  onUnload: function () {

  },

  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {

  },

  /**
   * 页面上拉触底事件的处理函数
   */
  onReachBottom: function () {

  },

  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  }
})