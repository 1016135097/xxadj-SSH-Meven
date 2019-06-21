//获得数据库引用
const db = wx.cloud.database();
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {

  },
  //获得数据
  daijiajiedan: function () {
    let that = this;
    wx.request({
      url: app.globalData.url + 'ordersAction_findOne',//根据id查询信息
      data: {
        id: that.data.detailId,
      },
      success(res) {
        //完成
        that.setData({
          daijiajiedanDetail: res.data.OrdersData,
        })
        wx.hideLoading()
      }
    })
  },
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //提示加载数据
    wx.showLoading({
      title: '加载中',
    })
    //获得传递过来的 detail
    this.setData({
      detailId: options.detailId
    })
  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.daijiajiedan();
  },


  /**
   * 用户点击右上角分享
   */
  onShareAppMessage: function () {

  },
  /**
   * 用户点击完成操作
   */
  wanchengcaozuo: function (e) {
    let that =this;
    wx.showModal({
      title: '确认完成',
      content: '订单完成后不可修改，确认完成了吗？',
      confirmText: '确定',
      cancelText: '取消',
      success(res) {
        //表示点击了取消
        if (res.confirm == false) {
          return;
        } else {
          wx.showLoading({
            title: "加载中...",
          })
          wx.request({
            url: app.globalData.url + 'ordersAction_update',
            data: {
              id:that.data.daijiajiedanDetail.id, //要修改的表id
              ifFinish: true,
              what: 'ifFinish',
            },
            success(res) {
              wx.hideLoading();
              getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示     
            }})
        }
      }
    })


  },
  /**
   * 用户点击删除时，删除根据id删除
   */
  orderReceivingDelete: function (e) {
    let that =this;
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
            title: "删除中",
          })
          //先删除（删除接单表），再取消(订单表）。
          wx.request({
            url: app.globalData.url + 'ordersAction_delete',
            data: {
              id:that.data.daijiajiedanDetail.id,
            },
            success(res) {
              //返回上一层页面
              wx.navigateBack();
            }
          })
        }
      }
    })
  },
  /**
 * 订单取消
 */
  orderReceivingUpdate: function (e) {
    let that = this;
    let daijiajiedanDetail = that.data.daijiajiedanDetail;
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
            title: "取消中",
          })
          //先删除（删除接单表），再取消(订单表）。
          wx.request({
            url: app.globalData.url + 'ordersAction_delete',
            data: {
              id: daijiajiedanDetail.id,
            },
            success(res) {
              //修改订单表，表示取消接单
              wx.request({
                url: app.globalData.url + 'orderAction_update',
                data: {
                  id: daijiajiedanDetail.orderId, //要修改的表id
                  ifAccept: false,
                  what: 'ifAccept',
                },
                success(res) {
                  //返回上一层页面
                  wx.navigateBack();
                }
              })

            }
          })
        }
      }
    })
  },

})