<template class="rounded-lg justify-center align-center">
     <div id="app">
          <v-app id="inspire">
               <v-card 
                    width="780" 
                    height="auto" 
                    class="mx-auto"
                    v-if="list_posts.length != 0"
               >
                    <v-container>
                         <v-row dense>
                              <v-col
                                   v-for="(item, i) in list_posts"
                                   :key="i"
                                   cols="12"
                              >
                                   <v-card class="m" color="#A03771" dark>
                                        <div
                                             class="d-flex flex-no-wrap justify-space-between"
                                        >
                                             <div>
                                                  <v-card-title
                                                       class="headline"
                                                       v-text="item.title"
                                                       @click="ReadMore(item.id)"
                                                  />

                                                  <v-card-subtitle>{{item.story.slice(0,440)}}...</v-card-subtitle>

                                                  <v-card-action>
                                                       <v-btn
                                                       class="ml-5 mb-4"
                                                       outlined
                                                       rounded
                                                       small
                                                       @click="ReadMore(item.id)"
                                                       >
                                                       Read more
                                                       </v-btn>
                                                  </v-card-action>
                                             </div>
                                        </div>
                                   </v-card>
                              </v-col>
                         </v-row>
                    </v-container>
               </v-card>
          </v-app>
     </div>
</template>

<script>
import api from "../api.js";
export default {
     name: "Upcoming",
     data: () => ({
          list_posts: [],
          set_content: [],
          set_content_id: undefined,
     }),
     mounted() {
          this.getPost();
     },
     methods: {
          getPost() {
               api.get("/webblog/post/upcoming")
                    .then((response) => {
                         this.list_posts = response.data;
                         console.log(JSON.parse(JSON.stringify(response.data)));
                    })
                    .catch((e) => {
                         console.log("Error in getPost() : " + e);
                    });
          },
          ReadMore(value) {
               this.$router.push({
                    name: "Content",
                    params: { content_id: value },
               });
          },
     },
};
</script>