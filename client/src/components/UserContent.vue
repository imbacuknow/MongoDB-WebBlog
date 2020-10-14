<template>
     <div align="center">
          <v-card
               class="rounded-lg amber lighten-4"
               width="800"
               height="auto"
               max-height="2000"
          >
               <v-col cols="12" sm="8" md="10">
                    <h5 align="right" class="font-weight-light" >Date: {{set_content.postDate }}</h5>
                    <v-card-title>{{ set_content.title }}</v-card-title>
               </v-col>

               <v-col align="left" class="" cols="12" sm="8" md="10">
                    <v-card-text>{{ set_content.story }}</v-card-text>
               </v-col>

               <h4 class="ml-6 font-weight-regular" align="center"> Tag: </h4>
               <template v-for="(item) in set_content.tags">
                    <v-chip class="ma-1" :color="item.color" :key="item.id">{{ item.text }} </v-chip>
               </template>

               <v-col align="right">
                    <v-btn
                         class="ma-4"
                         raised
                         @click="goToHome"
                         x-large
                         dark
                         color="brown darken-3"
                         >Back</v-btn
                    >
               </v-col>
          </v-card>
          <br />

          <v-card width="800" class="grey lighten-5">
               <!--  -->
               <v-card-text class="blue-grey darken-1 white--text">
                    <h2>Comment Zone</h2>
               </v-card-text>
               <!-- list comment -->
               <v-list
                    three-line
                    class="grey lighten-5 black--text"
                    align="left"
               >
                    <v-list-item-group
                         v-model="selected"
                         multiple
                         class="grey lighten-5 mx-2"
                    >
                         <template v-for="(item, index) in set_content.comments">
                              <v-list-item :key="item.id">
                                   <v-list-item-content>
                                        <v-list-item-title>
                                             <h4 align="left">
                                                  {{ item.username }}
                                             </h4>
                                             <h5 class="font-weight-thin" align="right">
                                                  {{ item.commentDate }}
                                             </h5>
                                        </v-list-item-title>
                                        <v-list-item-subtitle
                                             class="black--text"
                                             v-text="item.msg"
                                        />
                                   </v-list-item-content>
                              </v-list-item>
                              <v-divider :key="index" :inset="false" />
                         </template>
                    </v-list-item-group>
               </v-list>
               <!--  -->
               <v-form class="mx-2">
                    <v-container>
                         <v-col class="grey lighten-3">
                              <v-row>
                                   <v-text-field
                                        class="mx-2"
                                        v-model="cmt"
                                        label="Comment"
                                   />
                              </v-row>
                              <v-row justify="center">
                                   <v-btn
                                        class="mx-2"
                                        @click="onClickCM"
                                        raised
                                        dark
                                        width="720"
                                        color="green lighten-2"
                                        >comment</v-btn
                                   >
                              </v-row>
                         </v-col>
                    </v-container>
               </v-form>
          </v-card>
     </div>
</template>

<script>
import api from "../api.js";
export default {
     name: "UserContent",
     props: ["content_id"],
     data: () => ({
          set_content: [],
          comments: [],
          set_com:[],
          cmt: "",
          user: [],
     }),
     mounted() {
          this.getContent();
          this.user = JSON.parse(localStorage.getItem("user"));
     },
     methods: {
          goToHome() {
               this.$router.push({ name: "Home" });
          },
           getContent() {
               api.get("/webblog/post/content/" + this.content_id)
               .then((response) => {
                    this.set_content = response.data;
                    console.log(JSON.parse(JSON.stringify(response.data)));
               }).catch((e) => {
                    console.log("Error in getForDisContent():" + e);
               });
          },
          onClickCM() {
               if (!this.cmt) {
                    alert("กรุณากรอกข้อความ comment");
               } else {
                    this.submitComment();
               }
          },
          submitComment() {
               let databox = {
                    msg: this.cmt,
                    user_id: this.user.id,
                    post_id: this.content_id
               };
               api.post("/webblog/comment/add_new", databox)
                    .then(() => {
                         this.cmt = null;
                         this.getContent();
                    })
                    .catch((e) => {
                         console.log("Error in submitComment():" + e);
                    });
          },
     },
}
</script>