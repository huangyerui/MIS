<template>
     <div class="login">
        <section class="form_container">
            <div class="manage_tip">
                <span class="title">傻子的奶茶店</span>
            </div>
            <el-form :model="loginUser" :rules="rules" class="loginForm" ref="loginForm" label-width="80px">
                <el-form-item label="工号" prop="email">
                    <el-input v-model="loginUser.user_number" placeholder="请输入邮箱"></el-input>
                </el-form-item>
                <el-form-item label="密码" prop="password">
                    <el-input v-model="loginUser.user_password" placeholder="请输入密码" type="password"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary"  class="submit_btn" @click="submitForm('loginForm')">登录</el-button>
                </el-form-item>
            </el-form>
           
        </section>
    </div>
</template>
<script>
export default {
    name:'login',
    data() {
        return {
            loginUser:{
                user_number:'',
                user_password:''
            },
            rules:{
                 user_number:[
                    {required:true,message:'工号不能为空',trigger:'blur'},
                ],
                user_password:[
                    {required:true,message:'密码不能为空',trigger:'blur'},
                    {min:6,max:30,message:'长度在6到30个字符之间',trigger:'blur'}
                ]
            }
        }
    },
    methods: {
        submitForm:function(formName){
            console.log(formName)
            this.$refs[formName].validate(valid=>{
                if(valid){
                   this.$axios.get("/api/user/login",{params: this.loginUser})
                   .then(res=>{
                       console.log(res)
                       this.$store.commit('setUser',res.data);
                       this.$router.push('/commodity');
                   })
                   .catch(err=>{
                       this.$message({
                           message:'请输入正确的账号密码！',
                           type:"success"
                       })
                       console.log(err)
                   })
                  
                }else{
                    this.$route
                    return false;
                }
            })
        },
        isEmpty(value){
            return (
            value === undefined ||
            value === null ||
            (typeof value === 'object' && Object.keys(value).length===0)||
            (typeof value === 'string' && value.trim().length===0)
            );
        }
    },
    components:{}
};
</script>
<style scoped>
.login {
  position: fixed;
  top:0;
  left: 0;
  width: 100vw;
  height: 100vh;
  background: url(../assets/loginBack.jpg) no-repeat center center;
  background-size: 100% 100%;
}
.form_container {
  width: 370px;
  height: 210px;
  position: absolute;
  top: 10%;
  left: 34%;
  padding: 25px;
  border-radius: 5px;
  text-align: center;
}
.form_container .manage_tip .title {
  font-family: "Microsoft YaHei";
  font-weight: bold;
  font-size: 26px;
  color: #fff;
}
.loginForm {
  margin-top: 20px;
  background-color: #fff;
  padding: 20px 40px 20px 20px;
  border-radius: 5px;
  box-shadow: 0px 5px 10px #cccc;
}

.submit_btn {
  width: 100%;
}

.tiparea{
    text-align: right;
    font-size: 12px;
    color: #333;
}
.tiparea p a{
    color: #409eff;
}
</style>