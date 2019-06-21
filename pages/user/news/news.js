const db = wx.cloud.database();
const app = getApp();
var target_ = 0;
Page({

  /**
   * 页面的初始数据
   */
  data: {
  },
  //删除信息
  deletenews: function (e) {
    let that = this;
    //先判断是否已读，没有读，不能删除
    if (this.data.news.visit) {
      //给出提示，是否放弃此单
      wx.showModal({
        title: '删除消息失败',
        content: '请先查看消息！！',
        confirmText: '确认',
        cancelText: '取消',
      })
    } else {
      wx.request({
        url: app.globalData.url + 'messageAction_delete',
        data: {
          id: e.currentTarget.dataset.andid,
        },
        success(res) {
          getCurrentPages()[getCurrentPages().length - 1].onShow();
        }
      })
    }
  },
  /**
   * 
   * 已读操作
   * @param {} e 
   */
  yidunews: function (e) {
    wx.request({
      url: app.globalData.url + 'messageAction_update',
      data:{
        id: e.currentTarget.dataset.andid,
        visit:true, //表示已读
      },
      success(res){
        getCurrentPages()[getCurrentPages().length-1].onShow(); 
      }
    })
  },

  /**
   * 信息详细
   */
  newsDetail: function (e) {
    this.yidunews(e);
    //跳转编辑信息页面
    wx.navigateTo({
      url: '../orderForm/orderFormDetail/orderFormDetail?detailId=' + e.currentTarget.dataset.orderid,
    })
  },

  huodeshuju: function () {
    let that = this;
    wx.request({
      url: app.globalData.url + 'messageAction_findAll',
      success(res) {
        that.setData({
          news: res.data.data,
        })
      }
    })
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
  },


  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.huodeshuju();

  },


  /**
   * 页面相关事件处理函数--监听用户下拉动作
   */
  onPullDownRefresh: function () {
    this.huodeshuju();
  },
  /**
* 页面上拉触底事件的处理函数
*/
  onReachBottom: function () {
    this.huodeshuju();
  },


  // ListTouch触摸开始
  ListTouchStart(e) {
    console.log('ListTouch触摸开始', e.touches);
    this.setData({
      ListTouchStart: e.touches[0].pageX,
      ListTouchStartY: e.touches[0].pageY  //
    })
  },

  // ListTouch计算方向
  ListTouchMove(e) {
    //判断是是上下滑动还是左右滑动
    if (e.touches[0].pageY - this.data.ListTouchStartY > 100 || this.data.ListTouchStartY - e.touches[0].pageY > 100) {
      return;
    } else {
      this.setData({
        ListTouchDirection: e.touches[0].pageX - this.data.ListTouchStart > 0 ? 'right' : 'left'
      })
    }

  },

  // ListTouch计算滚动
  ListTouchEnd(e) {
    console.log('ListTouch计算滚动', e);
    if (this.data.ListTouchDirection == 'left') {
      this.setData({
        modalName: e.currentTarget.dataset.target
      })
    } else {
      this.setData({
        modalName: null
      })
    }
    this.setData({
      ListTouchDirection: null
    })
  },

})