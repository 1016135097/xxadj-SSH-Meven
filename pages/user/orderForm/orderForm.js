//获得数据库引用
const db = wx.cloud.database();
const app = getApp();
// 订单页面
Page({

  /**
   * 页面的初始数据
   */
  data: {
    orderFromProceed: 'cur',//默认订单进行中

  },
  orderFromUpdate: function (e) {
    console.log("orderFromUpdate-------------底部触发。。。。。");

  },

  //导航点击切换
  daohangqiehuan: function (e) {
    let orderFromProceed = '';
    let orderFromFinish = '';
    let qiehuan = e.currentTarget.dataset.daohangqiehuan; //获得点击传递过的值
    if (qiehuan == 'orderFromFinish') {
      orderFromFinish = 'cur';
      this.daijiadingdan(true);
    } else {
      orderFromProceed = 'cur';
      this.daijiadingdan(false);
    }
    this.setData({
      orderFromFinish: orderFromFinish,
      orderFromProceed: orderFromProceed
    })
  },
  //获得数据
  daijiadingdan: function (ifFinish) {
    let that = this;
    
    wx.request({
      url: app.globalData.url + 'orderAction_conditionQuery', //条件查询全部
      data: {
        openid: app.globalDataOpenid.openid_,
        ifFinish: ifFinish, //表示是否完成
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
  orderFromDelete: function (e) {
    //其实是否确定删除
    wx.showModal({
      title: '确认删除',
      content: '订单删除后不可恢复，确认删除吗？',
      confirmText: '确定',
      cancelText: '取消',
      success(res) {
        //表示点击了取消
        if (res.confirm == false) {
          return;
        } else {
          //执行删除操作
          console.log("删除根据id删除", e.currentTarget.dataset.id);
          wx.request({
            url: app.globalData.url + 'orderAction_delete',
            data: {
              id: e.currentTarget.dataset.id,
            },
            method: 'POST',
            header: {
              'content-type': 'application/x-www-form-urlencoded'
            },
            success(res) {
              if (res.data.delete_data) {
                getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
              }

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
      url: 'orderFormDetail/orderFormDetail?detailId=' + e.currentTarget.dataset.id,
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