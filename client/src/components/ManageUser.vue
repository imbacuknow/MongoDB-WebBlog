<template>
     <div>
          <v-app id="inspire">
               <v-content>
                    <v-container fluid fill-height>
                         <v-layout  justify-center>
                              <v-flex xs12 sm8 md4>
                                   <v-row align="center" justify="center">
                                        <v-col cols="12" sm="8" md=10>
                                             <v-alert type="success" dismissible v-model="alertSuccess"
                                                  >อัพเดทข้อมูลสำเร็จ</v-alert
                                             >
                                             <v-alert type="error" dismissible v-model="alertFailed"
                                                  >{{ alertmsg }}!</v-alert
                                             >
                                        </v-col>
                                   </v-row>
                                   <br><br><br>
                                   <v-card class="elevation-12" color="#E8C4D8">
                                        <v-card-text>
                                             <v-form>
                                                  <br>
                                                  <v-text-field
                                                       v-model="username"
                                                       label="Username"
                                                       type="text"
                                                       solo
                                                  ></v-text-field>

                                                  <v-select
                                                       v-model="role"
                                                       :items="roles"
                                                       label="Roles"
                                                       solo
                                                       item-text="roleOfUser"
                                                       item-value="id"
                                                  ></v-select>
                                             </v-form>
                                        </v-card-text>
                                        <v-card-actions>
                                             <v-spacer></v-spacer>
                                             <v-btn
                                             class="white--text mb-4" 
                                             color="blue-grey darken-3"
                                             @click="changeRoleOfUser()"
                                                  >Update Role</v-btn
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
import api from "../api";
export default {
     name: "ManageUser",
     data: () => ({
          username: undefined,
          roles: [],
          role: undefined,
          alertFailed: false,
          alertSuccess: false,
          alertmsg: undefined,
     }),
     mounted() {
          this.getRole();
     },
     methods: {
          getRole() {
               api.get("/webblog/role/details/all")
               .then((res) => {
                    this.roles = res.data;
               }).catch((e) => {
                    console.log("error in getRole(): " + e);
               });
          },
          changeRoleOfUser() {
               api.put("/webblog/user/update/" + this.username + "/" + this.role)
               .then(() => {
                    this.alertSuccess = true;
                    window.location.href = "/ManageUser";
               })
          },
          clearAlert() {
               this.alertSuccess = false;
               this.alertFailed = false;
          },
          getUser() {
               api.get("webblog/userid=" + 5).then((response) => {
                    let user = response.data;
                    localStorage.setItem("user", JSON.stringify(user));
               });
          },
     },
};
</script>