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
                                                  >สมัครสมาชิกเรียบร้อย</v-alert
                                             >
                                             <v-alert type="error" dismissible v-model="alertFailed"
                                                  >{{ alertmsg }}!</v-alert
                                             >
                                        </v-col>
                                   </v-row>
                                   <br><br><br>
                                   <v-card class="elevation-12">
                                        <v-toolbar color="blue-grey darken-3" dark flat>
                                             <v-toolbar-title
                                                  >REGISTER FORM</v-toolbar-title
                                             >
                                             <v-spacer></v-spacer>
                                             <v-tooltip bottom>
                                                  <template v-slot:activator="{ on }">
                                                       <v-btn
                                                       :href="source"
                                                       icon
                                                       large
                                                       v-on="on"
                                                       to="/Login"
                                                       >
                                                       <v-icon>mdi-login-variant</v-icon>
                                                       </v-btn>
                                                  </template>
                                                  <span>Login</span>
                                                  </v-tooltip>
                                        </v-toolbar>
                                        <v-card-text>
                                             <v-form>
                                                       <v-text-field
                                                       v-model="name"
                                                       label="Name"
                                                       type="text"
                                                  ></v-text-field>

                                                  <v-text-field
                                                       v-model="email"
                                                       label="Email"
                                                       type="text"
                                                  ></v-text-field>

                                                  <v-text-field
                                                       v-model="username"
                                                       label="Username"
                                                       type="text"
                                                  ></v-text-field>

                                                  <v-text-field
                                                       v-model="password"
                                                       label="Password"
                                                       :append-icon="value_icon"
                                                       @click:append="switchVisibility()"
                                                       :type="value_type"
                                                  ></v-text-field>
                                             </v-form>
                                        </v-card-text>
                                        <v-card-actions>
                                             <v-spacer></v-spacer>
                                             <v-btn 
                                             class="white--text" 
                                             color="blue-grey darken-3"
                                             @click="checkRegister()"
                                                  >Enroll</v-btn
                                             >
                                             <v-spacer></v-spacer>
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
          name: undefined,
          email: undefined,
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
          checkRegister() {
               if (!this.name || !this.email
               || !this.username || !this.password) {
                    this.clearAlert();
                    this.alertmsg = "กรุณากรอกข้อมูลให้ครบ";
                    this.alertFailed = true;
               } else {
                    this.createUser();
               }
          },
          createUser() {
               let data = {
                    name: this.name,
                    email: this.email,
                    username: this.username,
                    password: this.password,
                    role: {
                         id: 1,
                         roleOfUser:  "Newbie"
                    }
               };
               api.post("/webblog/user/create", data)
               .then(() => {
                    this.clearAlert();
                    this.alertSuccess = true;
                    this.name = null;
                    this.email = null;
                    this.username = null;
                    this.password = null;
                    window.location.href = "/Login";
               }).catch((e) => {
                    console.log("error in createUser():" + e);
                    this.someError();
               });
          },
          someError() {
               this.clearAlert();
               this.alertmsg = "ตรวจพบ username หรือ email มีการใช้ซ้ำ";
               this.alertFailed = true;
          }
     },
}
</script>