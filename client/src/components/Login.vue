<template>
     <div>
          <v-app id="inspire">
               <v-content>
                    <v-container fluid fill-height>
                         <v-layout  justify-center>
                              <v-flex xs12 sm8 md4>
                                   <v-row align="center" justify="center">
                                        <v-col cols="12" sm="6" md=9>
                                             <v-alert type="success" dismissible v-model="alertSuccess"
                                                  >บันทึกข้อมูลสำเร็จ</v-alert
                                             >
                                             <v-alert type="error" dismissible v-model="alertFailed"
                                                  >{{ alertmsg }}!</v-alert
                                             >
                                        </v-col>
                                   </v-row>
                                   <br><br><br><br><br><br> 
                                   <v-card class="elevation-12">
                                        <v-toolbar color="amber darken-3" dark flat>
                                             <v-toolbar-title
                                                  >LOGIN FORM</v-toolbar-title
                                             >
                                             <v-spacer></v-spacer> 
                                             <v-tooltip bottom>
                                                  <template v-slot:activator="{ on }">
                                                       <v-btn
                                                       :href="source"
                                                       icon
                                                       large
                                                       v-on="on"
                                                       to="/Register"
                                                       >
                                                       <v-icon>mdi-account-plus</v-icon>
                                                       </v-btn>
                                                  </template>
                                                  <span>Create Account</span>
                                             </v-tooltip>
                                        </v-toolbar>
                                        <v-card-text>
                                             <v-form>
                                                  <v-text-field
                                                       v-model="username"
                                                       label="Username"
                                                       prepend-icon="mdi-account"
                                                       type="text"
                                                  ></v-text-field>

                                                  <v-text-field
                                                       v-model="password"
                                                       label="Password"
                                                       prepend-icon="mdi-lock"
                                                       :append-icon="value_icon"
                                                       @click:append="switchVisibility()"
                                                       :type="value_type"
                                                  ></v-text-field>
                                             </v-form>
                                        </v-card-text>
                                        <v-card-actions>
                                             <v-spacer></v-spacer>
                                             <v-btn 
                                             color="amber darken-2"
                                             @click="checkLogIn()"
                                             @keyup.enter="checkLogIn()"
                                                  >Login</v-btn
                                             ><v-spacer></v-spacer>
                                        </v-card-actions>
                                   </v-card>
                              </v-flex>
                         </v-layout>
                    </v-container>
               </v-content>
          </v-app>
     </div>
</template>

<script>
import api from "../api.js";
export default {
     data: () => ({
          username: undefined,
          password: undefined,
          value_icon: "mdi-eye",
          value_type: "password",
          alertFailed: false,
          alertSuccess: false,
          alertmsg: undefined
     }),
     methods: {
          switchVisibility() {
               if(this.value_icon == "mdi-eye") {
                    this.value_icon = "mdi-eye-off";
               } else {
                    this.value_icon = "mdi-eye";
               }
               if(this.value_type == "password") {
                    this.value_type = "text";
               } else {
                    this.value_type = "password";
               }
          },
          clearAlert() {
               this.alertSuccess = false;
               this.alertFailed = false;
          },
          checkLogIn() {
               if (!this.username || !this.password) {
                    this.clearAlert();
                    this.alertmsg = "กรุณากรอกข้อมูลให้ครบ";
                    this.alertFailed = true;
               } else {
                    this.LogIn();
               }
          },
          LogIn() {
               api.get("/webblog/user/login/" + this.username + "/" + this.password)
               .then((res) => {
                    this.clearAlert();
                    this.alertSuccess = true;
                    
                    let user = res.data;
                    localStorage.setItem("user", JSON.stringify(user));
                    window.location.href = "/"
               }).catch((e) => {
                    console.log("error in LogIn(): " + e);
                    this.loginError();
               });
          },
          loginError() {
               this.clearAlert();
               this.alertmsg = "username หรือ password ไม่ถูกต้อง";
               this.alertFailed = true;
          }
     }
}
</script>