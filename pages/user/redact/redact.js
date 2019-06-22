// pages/user/redact.js
//获得数据库引用
const db = wx.cloud.database();
const app = getApp();
let user_id;   //用户id
Page({
  /**
   * 页面的初始数据
   */
  data: {
    avatarUrl: '', //用户头像地址
    userInfo: '', //用户名
    name: 'BubbleTg', //姓名
    phone: '17863273072', //电话
    age: '0', //年龄
    jialing: '0', //驾龄
    suozaidi: '北京', //所在地
    spe_i: '未实名认证', //实名认证
    jiashi: '未驾驶认证', //驾驶认证
    region: ['山东省', '枣庄市', '市中区'],
    showData:false,
  },

  //打开弹出框
  showModal: function(e) {
    //获得模板显示
    var showName = e.currentTarget.dataset.modal;
    //获得模板标题
    var modalName = e.currentTarget.dataset.modalName;
    //获得修改的值
    var modalValue = e.currentTarget.dataset.modalValue;
    //获得修改的值
    var modalTitle = e.currentTarget.dataset.modalTitle;
    this.setData({
      ifShow: showName,
      modalName: modalName,
      modalValue: modalValue,
      modalTitle: modalTitle
    })
  },
  //关闭弹出框
  closeModal: function(e) {
    this.setData({
      ifShow: null
    })
  },
  //判断是否实名认证
  ifSpei: function(e) {
    if (this.data.user.realNameAuthentication != '未实名认证') {
      //提示
      wx.showToast({
        title: "您已经实名认证！！！不可修改",
        icon: "none",
        duration: 2000
      })
    } else {
      //打开弹窗口修改
      this.showModal(e);
    }
  },
  //判断是否驾驶认证
  ifJiashi: function(e) {
    if (this.data.user.drivingCertification != '未驾驶认证') {
      //提示
      wx.showToast({
        title: "您已经驾驶认证！！！不可修改",
        icon: "none",
        duration: 2000
      })
    } else {
      //打开弹窗口修改
      this.showModal(e);
    }
  },
  //修改姓名
  alterName: function(e) {
    //先判断是否实名，并打开修改框
    this.ifSpei(e)
  },
  //修改电话
  alterPhone: function(e) {
    //先打开弹窗口
    this.showModal(e);
  },
  //修改年龄
  alterAge: function(e) {
    //先判断是否实名
    this.ifSpei(e);
  },
  //修改驾龄
  alterJialing: function(e) {
    //先判断是否驾驶认证
    this.ifJiashi(e);
   
  },
  //修改所在地
  alterSuozaidi(e) {
    console.log("修改所在地被点击。。。。。。。。。。", e);
    //更新到数据库
    wx.request({
      url: app.globalData.url + 'userAction_update', //更新
      data: {
        id: this.data.user.id,
        openid: this.data.user.openid,
        name: this.data.user.name, //默认
        username: this.data.user.username, //默认
        portrait: this.data.user.portrait, //头像地址
        phone: this.data.user.phone, //电话
        age: this.data.user.age, //年龄
        drivingYears: this.data.user.drivingYears, //驾龄
        location: e.detail.value[0] + e.detail.value[1] + e.detail.value[2], //所在地
        realNameAuthentication: this.data.user.realNameAuthentication, //实名认证
        drivingCertification: this.data.user.drivingCertification, //驾驶认证
        region: e.detail.value[0] + e.detail.value[1] + e.detail.value[2],
        collectTheNumber: this.data.user.collectTheNumber,    //收藏数
        visitTheNumber: this.data.user.visitTheNumber,   //查看数
        commentsTheNumber: this.data.user.commentsTheNumber,  //评论数
        showCard: this.data.user.showCard,
        joinTime: this.data.user.joinTime,
      },           // method:'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
      }
    })
  },

  //修改实名认证
  alterSpei: function(e) {
    if (this.data.user.realNameAuthentication != '未实名认证') {
      //提示
      wx.showToast({
        title: "您已经实名认证！！",
        icon: "none",
        duration: 2000
      }
      )
      return;
    }else{
      //跳转编辑信息页面
      wx.navigateTo({
        url: 'alterSpei/alterSpei?openid=' + app.globalDataOpenid.openid_ + '&ifSpei=' + e.currentTarget.dataset.modalValue,
      })
    }
    
  },
  //修改驾驶认证
  alterJiashi: function(e) {
    //跳转编辑信息页面
    wx.navigateTo({
      url: 'alterJiashi/alterJiashi?openid=' + app.globalDataOpenid.openid_ + '&ifJiashi=' + e.currentTarget.dataset.modalValue,
    })
  },


  //更新
  updateAlter: function(e) {
    let modalTitle_ = e.detail.target.dataset.modalTitle;
    /**
     * 修改姓名
     */
    if (modalTitle_ == 'name') {
      //先判断用户是否填写完整
      if (e.detail.value.name == '') {
        //提示
        wx.showToast({
          title: "姓名不能为空",
          icon: "none",
          duration: 2000
        })
        return;
      }
      //当与原始ide数据相等时，不用更新数据库
      if (e.detail.value.name != this.data.user.name) {
        //更新到数据库
        wx.request({
          url: app.globalData.url + 'userAction_update', //更新
          data: {
            id: this.data.user.id,
            openid: this.data.user.openid,
            name: e.detail.value.name, //默认
            username: this.data.user.username, //默认
            portrait: this.data.user.portrait, //头像地址
            phone: this.data.user.phone, //电话
            age: this.data.user.age, //年龄
            drivingYears: this.data.user.drivingYears, //驾龄
            location: this.data.user.location, //所在地
            realNameAuthentication: this.data.user.realNameAuthentication, //实名认证
            drivingCertification: this.data.user.drivingCertification, //驾驶认证
            region: this.data.user.region,
            collectTheNumber: this.data.user.collectTheNumber,    //收藏数
            visitTheNumber: this.data.user.visitTheNumber,   //查看数
            commentsTheNumber: this.data.user.commentsTheNumber,  //评论数
            showCard: this.data.user.showCard,
            joinTime: this.data.user.joinTime,
          },           // method:'POST',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success(res) {
            getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
          }
        })
      }
    }
    /**
     * 修改电话
     */
    if (modalTitle_ == 'phone') {
      //先判断用户是否填写完整
      if (e.detail.value.phone == '') {
        //提示
        wx.showToast({
          title: "电话不能为空",
          icon: "none",
          duration: 2000
        })
        return;
      }
      if (!(/^1[3578]\d{9}$/.test(e.detail.value.phone))) {
        wx.showToast({
          title: "请添加正确的联系方式!",
          icon: "none",
          duration: 2000
        });
        return false;
      }
      //当与原始ide数据相等时，不用更新数据库
      if (e.detail.value.phone != this.data.user.phone) {
        //更新到数据库
        wx.request({
          url: app.globalData.url + 'userAction_update', //更新
          data: {
            id: this.data.user.id,
            openid: this.data.user.openid,
            name: this.data.user.name, //默认
            username: this.data.user.username, //默认
            portrait: this.data.user.portrait, //头像地址
            phone: e.detail.value.phone, //电话
            age: this.data.user.age, //年龄
            drivingYears: this.data.user.drivingYears, //驾龄
            location: this.data.user.location, //所在地
            realNameAuthentication: this.data.user.realNameAuthentication, //实名认证
            drivingCertification: this.data.user.drivingCertification, //驾驶认证
            region: this.data.user.region,
            collectTheNumber: this.data.user.collectTheNumber,    //收藏数
            visitTheNumber: this.data.user.visitTheNumber,   //查看数
            commentsTheNumber: this.data.user.commentsTheNumber,  //评论数
            showCard: this.data.user.showCard,
            joinTime: this.data.user.joinTime,
          },           // method:'POST',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success(res) {
            getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
          }
        })
      }
    }

    /**
     * 修改年龄
     */
    if (modalTitle_ == 'age') {
      //先判断用户是否填写完整
      if (e.detail.value.age == '') {
        //提示
        wx.showToast({
          title: "年龄不能为空",
          icon: "none",
          duration: 2000
        })
        return;
      }
      //当与原始ide数据相等时，不用更新数据库
      if (e.detail.value.age != this.data.user.age) {
        //更新到数据库
        wx.request({
          url: app.globalData.url + 'userAction_update', //更新
          data: {
            id: this.data.user.id,
            openid: this.data.user.openid,
            name: this.data.user.name, //默认
            username: this.data.user.username, //默认
            portrait: this.data.user.portrait, //头像地址
            phone: this.data.user.phone, //电话
            age: e.detail.value.age, //年龄
            drivingYears: this.data.user.drivingYears, //驾龄
            location: this.data.user.location, //所在地
            realNameAuthentication: this.data.user.realNameAuthentication, //实名认证
            drivingCertification: this.data.user.drivingCertification, //驾驶认证
            region: this.data.user.region,
            collectTheNumber: this.data.user.collectTheNumber,    //收藏数
            visitTheNumber: this.data.user.visitTheNumber,   //查看数
            commentsTheNumber: this.data.user.commentsTheNumber,  //评论数
            showCard: this.data.user.showCard,
            joinTime: this.data.user.joinTime,
          },           // method:'POST',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success(res) {
            getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
          }
        })
      }
    }

    /**
     * 修改驾龄
     */
    if (modalTitle_ == 'drivingYears') {
      //先判断用户是否填写完整
      if (e.detail.value.drivingYears == '') {
        //提示
        wx.showToast({
          title: "驾龄不能为空",
          icon: "none",
          duration: 2000
        })
        return;
      }
      //当与原始ide数据相等时，不用更新数据库
      if (e.detail.value.drivingYears != this.data.user.drivingYears) {
        //更新到数据库
        wx.request({
          url: app.globalData.url + 'userAction_update', //更新
          data: {
            id: this.data.user.id,
            openid: this.data.user.openid,
            name: this.data.user.name, //默认
            username: this.data.user.username, //默认
            portrait: this.data.user.portrait, //头像地址
            phone: this.data.user.phone, //电话
            age: this.data.user.age, //年龄
            drivingYears: e.detail.value.drivingYears, //驾龄
            location: this.data.user.location, //所在地
            realNameAuthentication: this.data.user.realNameAuthentication, //实名认证
            drivingCertification: this.data.user.drivingCertification, //驾驶认证
            region: this.data.user.region,
            collectTheNumber: this.data.user.collectTheNumber,    //收藏数
            visitTheNumber: this.data.user.visitTheNumber,   //查看数
            commentsTheNumber: this.data.user.commentsTheNumber,  //评论数
            showCard: this.data.user.showCard,
            joinTime: this.data.user.joinTime,
          },           // method:'POST',
          header: {
            'content-type': 'application/json' // 默认值
          },
          success(res) {
            getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
          }
        })
      }
    }
    //关闭修改框
    this.closeModal();
    //加载更新提示框
    this.setData({
      showLoading: true
    })
    //然后从新加载数据,关闭提示框
    this.setData({
      showLoading: false
    })
  },
  //更新SetShadow
  updateSetShadow:function(ee){
      //更新到数据库
      wx.request({
        url: app.globalData.url + 'userAction_update', //更新
        data: {
          id: this.data.user.id,
          openid: this.data.user.openid,
          name: this.data.user.name, //默认
          username: this.data.user.username, //默认
          portrait: this.data.user.portrait, //头像地址
          phone: this.data.user.phone, //电话
          age: this.data.user.age, //年龄
          drivingYears: this.data.user.drivingYears, //驾龄
          location: this.data.user.location, //所在地
          realNameAuthentication: this.data.user.realNameAuthentication, //实名认证
          drivingCertification: this.data.user.drivingCertification, //驾驶认证
          region: this.data.user.region,
          collectTheNumber: this.data.user.collectTheNumber,    //收藏数
          visitTheNumber: this.data.user.visitTheNumber,   //查看数
          commentsTheNumber: this.data.user.commentsTheNumber,  //评论数
          showCard: ee,
          joinTime: this.data.user.joinTime,
        },           // method:'POST',
        header: {
          'content-type': 'application/json' // 默认值
        },
        success(res) {
          getCurrentPages()[getCurrentPages().length - 1].onShow(); //重新页面显示
        }
      })
  },
  /**
   * 是否展示个人资料
   */
  SetShadow(e){
    let thiss  = this;
    //e.detail.value 为 true  表示打开
    if(e.detail.value){
      wx.showModal({
        title: '确认展示',
        content: '打开展示资料后，他人可以收藏，评论，分享你的资料卡片，确定打开吗？',
        confirmText: '确定',
        cancelText: '取消',
        success(ress) {
          //表示点击了取消
          if (ress.confirm == false) {
            thiss.setData({
              showData: e.detail.value==true?false:true, 
            })
            return;
          } else {
            thiss.updateSetShadow(e.detail.value);
          }
        }
      })
    }else{
      wx.showModal({
        title: '确认关闭',
        content: '关闭展示资料，他人将不可以收藏，评论，分享你的资料卡片了，确定关闭吗？',
        confirmText: '确定',
        cancelText: '取消',
        success(ress) {
          //表示点击了取消
          if (ress.confirm == false) {
            thiss.setData({
              showData: e.detail.value==false?true:false, 
            })
            return;
            
          } else {
            thiss.updateSetShadow(e.detail.value);
          }
        }
      })
     
    }
  },

  /**
   * 生命周期函数--监听页面加载
   */
  onLoad: function(options) {
    let that = this;
    //获得传递过来的 openid
    this.setData({
      openid: app.globalDataOpenid.openid_
    })  

  },
  /**
   * 生命周期函数--监听页面显示
   */
  onShow: function() {
    //显示加载
    wx.showLoading({
      title: '加载中',
      icon: 'loading',
    })
    var thiss = this;
    //查询数据
    wx.request({
      url: app.globalData.url + 'userAction_findOne', //查询一条数据
      data: {
        openid: app.globalDataOpenid.openid_, //通过全局查找当前用户
      },          // method:'POST',
      header: {
        'content-type': 'application/json' // 默认值
      },
      success(res) {
        thiss.setData({
          user: res.data
        })
        //关闭加载...
        wx.hideLoading()
        app.globalDataOpenid.user_id = res.data.id;
      }
    })
  },



})