<template>
  <header class="head-nav">
    <el-row class="container">
      <el-col :span="6" class="logo-container">
        <img src="../assets/logo.png" class="logo" alt />
        <span class="title">傻子的奶茶店</span>
      </el-col>
      <el-col :span="6" class="user">
        <div class="userinfo">
          <img
            src='http://gravatar.com/avatar/6b244ff8cc0acbcc18ae0ae292c06176?s=200&r=pg&d=mm'
            class="avatar"
            alt
          />
          <div class="welcome">
            <p class="name comename">欢迎</p>
            <p class="name avatarname">{{user.userName}}</p>
          </div>
          <span class="username">
            <el-dropdown trigger="click" @command="setDialogInfor">
              <span class="el-dropdown-link">
                <i class="el-icon-caret-bottom el-icon--right"></i>
              </span>
              <el-dropdown-menu slot="dropdown">
                <el-dropdown-item>{{user.userPost}}</el-dropdown-item>
                <el-dropdown-item command='logout'>退出</el-dropdown-item>
              </el-dropdown-menu>
            </el-dropdown>
          </span>
        </div>
      </el-col>
    </el-row>
  </header>
</template>
<script>
export default {
  name: "head-nav",
  data(){
    return {
     
    }
  },
  created(){
    console.log(this.$store.state.user)
  },
  computed:{
      user(){
          return this.$store.state.user;
      }
  },
  methods: {
      setDialogInfor(cmdItem){
          switch (cmdItem){
              case 'infor':
                  this.showInfoList();
                  break;
              case 'logout':
                  this.logout();
                  break;
          }
      },
      showInfoList(){
          this.$router.push("/infoShow")
      },
      logout(){
          //清除token
          localStorage.removeItem('eleToken');
          //设置vuex store
           this.$store.dispatch("clearCurrentState")
           this.$router.push("/login")
          console.log('退出')
      }
  },
};
</script>
<style scoped>
.head-nav {
  width: 100%;
  height: 60px;
  padding: 5px;
  background: #324057;
  color: #fff;
  position: fixed;
  top: 0;
  left: 0;
  z-index: 99;
  border-bottom: 1px solid #1f2d3d;
}
.container {
  display: flex;
  width: 100%;
  height: 100%;
}
.logo-container {
  display: flex;
  flex: 1;
  flex-direction: row;
  align-items: center;
  line-height: 60px;
}
.logo {
  height: 50px;
  width: 50px;
  margin-right: 5px;
}
.user {
  display: flex;
  flex: 1;
  flex-direction: column;
  align-items: flex-end;
  justify-content: center;
  line-height: 60px;
  padding-right: 10px;
}
.userinfo {
  display: flex;
  flex-direction: row;
  align-items: center;
}
.welcome {
  padding: 0 5px;
}
.avatar {
  height: 40px;
  width: 40px;
  border-radius: 50% 50%;
}
.name {
  line-height: 20px;
  text-align: center;
  font-size: 14px;
}
.comename {
  font-size: 12px;
}
.avatarname {
  color: #409eff;
  font-weight: bolder;
}
.username {
  cursor: pointer;
  margin-right: 5px;
}
.el-dropdown {
  color: #fff;
}
</style>