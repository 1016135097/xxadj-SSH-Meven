// pages/user/user.js
//获得数据库引用
const db = wx.cloud.database();
const app = getApp();
var updatePortrait_ = false;//防止多次执行更新头像
Page({

  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '../../images/user-unlogin.png',
    userInfo: '',
  },
  //登录授权
  onGetUserInfo: function (e) {
    console.log("---点击登录授权---", e)
    var thiss = this;
    if (!this.logged && e.detail.userInfo) {
      this.setData({
        logged: true,
        avatarUrl: e.detail.userInfo.avatarUrl,
        userInfo: e.detail.userInfo,
        openid: e.target.dataset.openid,

      })
      app.globalDataOpenid.openid_ = e.target.dataset.openid;
    }
    //插入用户信息
    this.adduser(e);
  },

  /**
   * 插入用户信息
   * 
   */

  adduser(e) {
    let currentDate = new Date();
    wx.request({
      url: app.globalData.url + 'userAction_add', //上传数据
      data:{
        id: 1,
        openid: '' + e.target.dataset.openid,
        name: '' + this.data.userInfo.nickName, //默认
        username: '' + this.data.userInfo.nickName, //默认
        portrait: '' + this.data.userInfo.avatarUrl, //头像地址
        phone: '17863273072', //电话
        age: '0', //年龄
        drivingYears: '0', //驾龄
        location: '北京', //所在地
        realNameAuthentication: '未实名认证', //实名认证
        drivingCertification: '未驾驶认证', //驾驶认证
        region: '山东省'+ '枣庄市'+'市中区',
        collectTheNumber: 0,    //收藏数
        visitTheNumber: 0,   //查看数
        commentsTheNumber: 0,  //评论数
        showCard: false,
        joinTime: currentDate.getFullYear() + '/' + (currentDate.getMonth() + 1) + '/' + currentDate.getDate(),//加入时间
      },          
       method:'POST',
      header: {
        'content-type': 'application/x-www-form-urlencoded' 
      },
      success(res) {
        console.log(res.data) 
        app.globalDataOpenid.user_id = res.data.id; //获得返回回来的ID，保存到全局
      },
      fail(res) {
        console.log(res.data) //返回数据为：id=1  格式，通过=分开
      }  
    })
  },


  //判断是否登录
  ifLongin: function (e) {
    if (this.data.userInfo != '') {
      return true;
    } else {
      return false;
    }

  },
  //cutInterface 切换界面
  cutInterface: function (e) {
    var interfaceZ = '' + e.currentTarget.dataset.interface; //要切换的页面
    var openidZ = '' + e.currentTarget.dataset.openid; //获得openid
    console.log(interfaceZ)
    //先判断是否登录，当是设置，或者关于我们时，则不用判断
    if (interfaceZ == 'setting') {
      wx.openSetting()
    } else if (interfaceZ == 'FAQ') {
       //跳转编辑信息页面
       wx.navigateTo({
        url: interfaceZ + '/' + interfaceZ + '?openid=' + openidZ,
      })
    } else if (this.ifLongin(e)) {
      //跳转编辑信息页面
      wx.navigateTo({
        url: interfaceZ + '/' + interfaceZ + '?openid=' + openidZ,
      })
    } else {
      wx.showToast({
        title: "请先登录！",
        icon: "none",
        duration: 2000
      });
      return;
    }
  },
  /**
 * 生命周期函数--监听页面加载
 */
  onLoad: function (options) {
    wx.showLoading({
      title: '登录中',
      icon: 'loading',
    })
    //执行云涵数，获得openid作为id
    wx.cloud.callFunction({
      name: 'login',
      complete: (res) => {
        app.globalDataOpenid.openid_ = res.result.openid;
      }
    })
  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function () {
    wx.showLoading({
      title: '刷新中',
      icon: 'loading',
    })
    let that = this;
      //判断是否登录
      wx.getSetting({
        success(res) {
          console.log(res.authSetting)
          //没有授权
          if (!res.authSetting['scope.userInfo']) {
            //关闭加载...
        wx.hideLoading()
            that.setData({
              avatarUrl: '../../images/user-unlogin.png',
              userInfo: '',
            })
          } else {
            wx.getUserInfo({
              success(res) {
                //关闭加载...
                wx.hideLoading()
                that.setData({
                  avatarUrl: res.userInfo.avatarUrl,
                  userInfo: res.userInfo,
                });
              }
            })
          }
        }
      })
      console.log('-------app.globalDataOpenid.user_id-----------',app.globalDataOpenid.user_id)
       //消息查询，消息是否全部已读
       wx.request({
        url: app.globalData.url + 'messageAction_unread',
        data:{
          visit:false,
          embracerUserId:app.globalDataOpenid.user_id,
        },
        success(res) {
           //关闭加载...
        wx.hideLoading()
          if(res.data.data){
            that.setData({
              huodexiaoxiifdakai: true,
            })
          }else{
            that.setData({
              huodexiaoxiifdakai: false,
            })
          }
        }
      })
     
  },

  /**
   * 生命周期函数--监听页面隐藏
   */
  onHide: function () {

  },
})