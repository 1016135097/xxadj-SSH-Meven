//获得数据库引用
const db = wx.cloud.database();
const app = getApp();
Page({

  /**
   * 页面的初始数据
   */
  data: {
    userid: '', //当前订单用户id
    xuanzhedeyonghu: '点击上面用户信息进行评论',  //提示
  },
  //获得数据
  daijiadingdan: function () {
    let that = this;
    //终点位置模糊查询
    wx.request({
      url: app.globalData.url + 'orderAction_findOne',//根据id查询信息
      data: {
        id: that.data.detailId,
      },
      success(res) {
        //完成
        that.setData({
          daijiadingdanDetail: res.data.OrderData,
        })
        //判断是否被接单,不等于空表示接单
        if (res.data.OrderData.daijiajiedan_id != '') {
          //查询接单者用户信息
          wx.request({
            url: app.globalData.url + 'userAction_findOne', //查询一条数据
            data: {
              id: res.data.OrderData.daijiajiedan_id, //通过全局查找当前用户
            },
            success(res) {
              that.setData({
                jie_user: res.data,
                jiedanren_length: 1,
              })

            }
          })
        }
        // this.tianjiasiji([],0);
        //关闭加载...
        wx.hideLoading()
      }
    })
  },



  /**
   * 点击查看详细接单人
   */
  jiedanrenxiangxi: function () {
    this.setData({
      sijitanchuang: true,
      jiedanrenxiangxi_an: true,
    })
  },

  /**
   * 自己选的司机
   * tianjiasiji_  暂时保存数据数组，
   * i 递归调用自己下标，作用，获得xuandingren里数据，判断何时停止调用自己
   */
  tianjiasiji(tianjiasiji_, i) {
    db.collection('user').doc(this.data.daijiadingdanDetail.zhidingsij[i]).get().then(res => {
      tianjiasiji_.push(res.data)
      //i = (this.data.xuandingren).length 表示要获取的用户卡片信息查询完成。
      if (i < (this.data.daijiadingdanDetail.zhidingsij).length) {
        console.log("----------调用自己---------i=" + i)
        i++;
        this.tianjiasiji(tianjiasiji_, i); //调用自己
      }
    })
    this.setData({
      tianjiasiji: tianjiasiji_,
      tianjiasiji_length: tianjiasiji_.length,
    })
  },

  /**
   * 评论司机,拉起操作
   */
  pinglunTap: function () {
    //先判断是否完成
    if (this.data.daijiadingdanDetail.ifFinish != false) {
      this.setData({
        sijitanchuang: true,
        pinglunTapFaView: true,
      })
    } else {
      wx.showToast({
        title: '请先完成订单',
        icon: 'none',
        duration: 2000
      })
    }
  },
  //点击查看详细代驾司机
  chankaidaij: function () {
    this.setData({
      sijitanchuang: true,
    })
  },
  //关闭点击查看详细代驾司机
  chankaidaijguanbi: function () {
    this.setData({
      pinglunTapFaView: false,
      sijitanchuang: false,
      jiedanrenxiangxi_an: false,
      userid: '', //当前订单用户id
      xuanzhedeyonghu: '点击上面用户信息进行评论',  //提示
    })
  }
  ,
  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function (options) {
    //提示加载数据
    wx.showLoading({
      title: '加载中',
    })
    console.log("-----------------详细页面", options.detailId)
    //获得传递过来的 detail
    this.setData({
      detailId: options.detailId
    })

  },

  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    this.daijiadingdan();
    let that = this;
    //获得当前用户信息
    wx.getUserInfo({
      success(res) {
        that.setData({
          userInfo: res.userInfo,
        })
      }
    })
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
    let that = this;
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
          wx.request({
            url: app.globalData.url + 'orderAction_update', //更新
            data: {
              id: that.data.detailId, //要修改的表id
              ifFinish: true,
              what: 'ifFinish',
            },
            success(res) {
              getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
            }
          })
        }
      }
    })


  },
  /**
   * 用户点击删除时
   * 
   */
  //删除根据id删除
  orderFromDelete: function (e) {
    let that = this;
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
          wx.request({
            url: app.globalData.url + 'orderAction_delete',
            data: {
              id: that.data.detailId,
            },
            success(res) {
              if (res.data.delete_data) {
                //返回上一层页面
                wx.navigateBack();
              }

            }
          })
        }
      }
    })

  },
  /**
   * 订单修改
   */
  orderFormUpdate: function (e) {
    //跳转编辑信息页面
    wx.navigateTo({
      url: '../orderFormUpdate/orderFormUpdate?detailId=' + e.currentTarget.dataset.id,
    })
  },

})