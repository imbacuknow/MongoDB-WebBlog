<template>
     <v-app id="inspire">
          <!-- not login yet -->
          <template v-if="!ThereIsSIgnIn">
               <div>
                    <v-toolbar app class="light-blue" height="180px">
                         <v-col>
                              <v-row class="justify-center align-center">
                                   <v-spacer></v-spacer>
                                   <v-col cols="auto">
                                        <img
                                             @click="gotoHome"
                                             justify="center"
                                             src="./assets/IM_logo.png"
                                             alt="IM_logo"
                                             width="180"
                                             height="80"
                                        />
                                   </v-col>
                                   <v-spacer></v-spacer>
                              </v-row>

                              <v-row class="pt-ma-2" color="blue">
                                   <v-tabs
                                        class="blue"
                                        centered
                                        color="blue-grey darken-4"
                                   >
                                        <v-tab to="/">Home</v-tab>
                                        <v-tab to="/Login">Login</v-tab>
                                        <v-tabs-slider color="pink"></v-tabs-slider>
                                   </v-tabs>
                              </v-row>
                         </v-col>
                    </v-toolbar>
               </div>
          </template>

          <!-- There is login -->
          <template v-else>
               <!-- Admin -->
               <div v-if="user.roleName === 'Admin' ">
                    <v-toolbar app class="light-blue" height="180px">
                         <v-col>
                              <v-row class="justify-center align-center">
                                   <v-spacer></v-spacer>
                                   <v-col cols="auto">
                                        <img
                                             @click="gotoHome"
                                             justify="center"
                                             src="./assets/IM_logo.png"
                                             alt="IM_logo"
                                             width="180"
                                             height="80"
                                        />
                                   </v-col>
                                   <v-spacer></v-spacer>
                              </v-row>

                              <v-row class="pt-ma-2" color="blue">
                                   <v-tabs
                                        class="blue"
                                        centered
                                        color="blue-grey darken-4"
                                   >
                                        <v-tab to="/">Home</v-tab>
                                        <v-tab to="/Upcoming">Upcoming</v-tab>
                                        <v-tab to="/Post">Post</v-tab>
                                        <v-tab to="/ManageUser">Manage User</v-tab>
                                        <v-tab @click="Logout()">Logout</v-tab>
                                        <v-tabs-slider color="pink"></v-tabs-slider>
                                   </v-tabs>
                              </v-row>
                         </v-col>
                    </v-toolbar>
               </div>
               <!-- Experienced -->
               <div v-else-if="user.roleName === 'Experienced' ">
                    <v-toolbar app class="light-blue" height="180px">
                         <v-col>
                              <v-row class="justify-center align-center">
                                   <v-spacer></v-spacer>
                                   <v-col cols="auto">
                                        <img
                                             @click="gotoHome"
                                             justify="center"
                                             src="./assets/IM_logo.png"
                                             alt="IM_logo"
                                             width="180"
                                             height="80"
                                        />
                                   </v-col>
                                   <v-spacer></v-spacer>
                              </v-row>

                              <v-row class="pt-ma-2" color="blue">
                                   <v-tabs
                                        class="blue"
                                        centered
                                        color="blue-grey darken-4"
                                   >
                                        <v-tab to="/">Home</v-tab>
                                        <v-tab to="/Upcoming">Upcoming</v-tab>
                                        <v-tab to="/Post">Post</v-tab>
                                        <v-tab @click="Logout()">Logout</v-tab>
                                        <v-tabs-slider color="pink"></v-tabs-slider>
                                   </v-tabs>
                              </v-row>
                         </v-col>
                    </v-toolbar>
               </div>
               <!-- Newbie -->
               <div v-else>
                    <v-toolbar app class="light-blue" height="180px">
                         <v-col>
                              <v-row class="justify-center align-center">
                                   <v-spacer></v-spacer>
                                   <v-col cols="auto">
                                        <img
                                             @click="gotoHome"
                                             justify="center"
                                             src="./assets/IM_logo.png"
                                             alt="IM_logo"
                                             width="180"
                                             height="80"
                                        />
                                   </v-col>
                                   <v-spacer></v-spacer>
                              </v-row>

                              <v-row class="pt-ma-2" color="blue">
                                   <v-tabs
                                        class="blue"
                                        centered
                                        color="blue-grey darken-4"
                                   >
                                        <v-tab to="/">Home</v-tab>
                                        <v-tab to="/Post">Post</v-tab>
                                        <v-tab @click="Logout()">Logout</v-tab>
                                        <v-tabs-slider color="pink"></v-tabs-slider>
                                   </v-tabs>
                              </v-row>
                         </v-col>
                    </v-toolbar>
               </div>
          </template>

          <template>
               <div id="app">
                    <v-container align="center">
                         <router-view></router-view>
                    </v-container>
               </div>
          </template>
     </v-app>
</template>

<script>
export default {
     data: () => ({
          user: {},
          ThereIsSIgnIn: false,
     }),
     mounted() {
          this.user = JSON.parse(localStorage.getItem("user"));
          if (this.user) {
               this.ThereIsSIgnIn = true;
          } else {
               this.ThereIsSIgnIn = false;
          }
     },
     methods: {
          gotoHome() {
               window.location.href = "/";
          },
          Logout() {
               this.ThereIsSIgnIn = false;
               localStorage.removeItem("user");
               window.location.href = "/";
          }
     },
};
</script>